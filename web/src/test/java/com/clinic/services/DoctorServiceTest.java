//package com.clinic.services;
//
//import org.junit.Test;
//
//import java.time.LocalDate;
//import java.time.LocalDateTime;
//
//import static org.junit.Assert.*;
//
//public class DoctorServiceTest {
//
//    @Test
//    public void addNextWorkingHour() {
//        LocalDate current = LocalDate.now();
//        LocalDateTime given = LocalDateTime.of(current.getYear(), current.getMonth(), 19, 13, 0);
//        System.out.println(given);
//        for (int i = 0; i < 40; i++){
//            LocalDateTime next = DoctorService.addNextWorkingHour(given);
//            System.out.println(next);
//            given = next;
//        }
//    }
//}