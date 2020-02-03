CREATE TABLE `users` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `first_name` varchar(50) NOT NULL,
  `last_name` varchar(50) NOT NULL,
  `created_date` datetime NOT NULL,
  `date_of_birth` date NOT NULL,
  `email` varchar(100) UNIQUE NOT NULL,
  `enabled` int(11) NOT NULL,
  `password` varchar(255) NOT NULL,
  `phone_number` varchar(50) NOT NULL,
  `role` varchar(50) NOT NULL
)
password = 123456
insert into users values(null,'Sundeep','Chaurasiya','2020-01-26 04:51:19','1990-09-03','sundeep.chaurasiya@gmail.com',1,'$2a$10$XK2xaD95orhWEThxFFBC8uBWEArZOOlvWRJaP0v66KUEeWd859eFi','8767452390','ADMIN');
insert into users values(null,'Hariom','Kumar','2020-01-26 04:51:19','1990-09-03','hariom.kumar@gmail.com',1,'$2a$10$XK2xaD95orhWEThxFFBC8uBWEArZOOlvWRJaP0v66KUEeWd859eFi','8767456390','LIBRARIAN');
insert into users values(null,'Anand','Gupta','2020-01-26 04:51:19','1990-09-03','anand.gupta@gmail.com',1,'$2a$10$XK2xaD95orhWEThxFFBC8uBWEArZOOlvWRJaP0v66KUEeWd859eFi','8831745239','TEACHER');
insert into users values(null,'Ravi','Sah','2020-01-26 04:51:19','1990-09-03','ravi.sah@gmail.com',1,'$2a$10$XK2xaD95orhWEThxFFBC8uBWEArZOOlvWRJaP0v66KUEeWd859eFi','8756342390','STUDENT');
insert into users values(null,'Suraj','Ram','2020-01-26 04:51:19','1990-09-03','suraj.ram@gmail.com',1,'$2a$10$XK2xaD95orhWEThxFFBC8uBWEArZOOlvWRJaP0v66KUEeWd859eFi','8767452390','STUDENT');