package com.clinic.services;

import com.clinic.domain.User;
import com.clinic.dto.UserDto;
import com.clinic.repositories.RoleRepository;
import com.clinic.repositories.TicketRepository;
import com.clinic.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.Collections;

@Service
public class ClinicUserDetailService implements UserDetailsService {
    Logger logger = LoggerFactory.getLogger(ClinicUserDetailService.class);

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private TicketRepository ticketRepository;

    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(userName);
        if (user == null) {
            throw new UsernameNotFoundException(userName);
        }
        logger.info("User " + userName + " found");
        return new MyUserDetails(user);
    }

    public void create(UserDto userDto) {
        User user = new User();
        user.setUsername(userDto.getName());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setRole(roleRepository.findByName("ROLE_USER"));
        userRepository.save(user);
        logger.info("User " + userDto.getName() + " saved");
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Iterable<User> findAll() {
        return userRepository.findByRoleName("ROLE_USER");
    }

    public void addTicket(User user, Long ticketId) {
        ticketRepository.findById(ticketId).ifPresent(ticket -> {
            ticket.setUser(user);
            ticketRepository.save(ticket);
        });
    }

    @Transactional
    public User findUserByNameWithTickets(String userName){
        User user = userRepository.findByUsername(userName);
        user.getTickets();
        return user;
    }

    public static class MyUserDetails implements UserDetails {
        private final User user;

        public MyUserDetails(User user) {
            this.user = user;
        }

        public Collection<? extends GrantedAuthority> getAuthorities() {
            return Collections.singletonList(new SimpleGrantedAuthority(user.getRole().getName()));
        }

        public String getPassword() {
            return user.getPassword();
        }

        public String getUsername() {
            return user.getUsername();
        }

        public boolean isAccountNonExpired() {
            return true;
        }

        public boolean isAccountNonLocked() {
            return true;
        }

        public boolean isCredentialsNonExpired() {
            return true;
        }

        public boolean isEnabled() {
            return true;
        }

        public User getUser(){
            return user;
        }
    }
}
