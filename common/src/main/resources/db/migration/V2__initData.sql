INSERT INTO role VALUES (1, 'ROLE_ADMIN'), (2, 'ROLE_USER');
INSERT INTO user VALUES (default, 'admin', '$2a$11$8sKgcjB87QeMNaprQZWarubI80/hyqGWWEolfAFwE9yYm7MP21HTu', "Administrator", NULL, 1);

INSERT INTO department VALUES
                    (default, 'Хирургическое отделение'),
                    (default, 'Терапевтическое отделение'),
                    (default, 'Стоматолоническое отделение'),
                    (default, 'Кардиологическое отделение'),
                    (default, 'Травмотологическое отделение'),
                    (default, 'Педиатрическое отделение');