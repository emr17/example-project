insert into course(id, name, created_date, last_updated_date)
values(10001,'Math', CURRENT_DATE(), CURRENT_DATE());
insert into course(id, name, created_date, last_updated_date)
values(10002,'Physics', CURRENT_DATE(), CURRENT_DATE());
insert into course(id, name, created_date, last_updated_date)
values(10003,'Chemistry', CURRENT_DATE(), CURRENT_DATE());

insert into student(id,name)
values(20001,'Mike');
insert into student(id,name)
values(20002,'Adam');
insert into student(id,name)
values(20003,'Jane');


insert into student_course(student_id,course_id)
values(20001,10001);
insert into student_course(student_id,course_id)
values(20002,10001);
insert into student_course(student_id,course_id)
values(20003,10001);
insert into student_course(student_id,course_id)
values(20001,10003);