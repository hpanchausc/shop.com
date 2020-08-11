insert into Department (budget, name) values (10000, 'SE');
insert into Department (budget, name) values (84759, 'CS');
insert into Department (budget, name) values (48395, 'IT');
insert into Department (budget, name) values (43895, 'Computer Engineering');

insert into STUDENT (enrollment_date, first_name, last_name) values ( '2017-07-15', 'Mr. ', 'Hector');
insert into STUDENT (enrollment_date, first_name, last_name) values ('2017-08-15', 'M. ', 'Parsons');
insert into STUDENT (enrollment_date, first_name, last_name) values ('2017-09-15', 'Simon', 'Minter');
insert into STUDENT (enrollment_date, first_name, last_name) values ('2017-01-15', 'Danish', 'Patel');

insert into Course(credit_hours, title, department_id) values (2, 'PF', 1);
insert into Course(credit_hours, title, department_id) values (3, 'ICT', 2);
insert into Course(credit_hours, title, department_id) values (4, 'FM', 3);
insert into Course(credit_hours, title, department_id) values (3, 'RM', 4);
insert into Course(credit_hours, title, department_id) values (4, 'SAD', 1);
insert into Course(credit_hours, title, department_id) values (3, 'PP', 2);
insert into Course(credit_hours, title, department_id) values (4, 'ECO', 3);
insert into Course(credit_hours, title, department_id) values (3, 'OOSE', 4);
insert into Course(credit_hours, title, department_id) values (4, 'SEE', 1);
insert into Course(credit_hours, title, department_id) values (2, 'SDA', 2);
insert into Course(credit_hours, title, department_id) values (1, 'DDL', 3);
insert into Course(credit_hours, title, department_id) values (2, 'IK', 4);

insert into Instructor(first_name, hire_date, last_name) values ('Daniel', '2017-10-17', 'Rebibo');
insert into Instructor(first_name, hire_date, last_name) values ('Alex', '2017-08-17', 'Conan');
insert into Instructor(first_name, hire_date, last_name) values ('Amy', '2017-08-17', 'Parsons');
insert into Instructor(first_name, hire_date, last_name) values ('Logan', '2017-01-17', 'Lucky');

insert into COURSE_ASSIGNMENT (course_id, instructor_id) values( 1,1);
insert into COURSE_ASSIGNMENT (course_id, instructor_id) values( 2,3);
insert into COURSE_ASSIGNMENT (course_id, instructor_id) values( 3,1);
insert into COURSE_ASSIGNMENT (course_id, instructor_id) values( 4,4);
insert into COURSE_ASSIGNMENT (course_id, instructor_id) values(11,1);

insert into ENROLLMENT (grade, course_id, student_id) values(0, 2,1);
insert into ENROLLMENT (grade, course_id, student_id) values(0, 3,2);
insert into ENROLLMENT (grade, course_id, student_id) values(0, 4,3);
insert into ENROLLMENT (grade, course_id, student_id) values(0, 1,4);
insert into ENROLLMENT (grade, course_id, student_id) values(0, 2,2);

insert into OFFICE_ASSIGNMENT (location, instructor_id) values('GreenBay',1);
insert into OFFICE_ASSIGNMENT (location, instructor_id) values('LAX',2);
insert into OFFICE_ASSIGNMENT (location, instructor_id) values('Louisiana',3);
insert into OFFICE_ASSIGNMENT (location, instructor_id) values('Idaho',1);
insert into OFFICE_ASSIGNMENT (location, instructor_id) values('SFO',3);

insert into Department (budget, name, admin_id) values (43895, 'Electronic Engineering', 2);
