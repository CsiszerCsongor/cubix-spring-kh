create sequence course_seq start with 1 increment by 1;

create table course (id bigint not null, name varchar(255), primary key (id));

create table student_curse (course_id bigint not null, student_id bigint not null, primary key (course_id, student_id));
create table teacher_course (course_id bigint not null, teacher_id bigint not null, primary key (course_id, teacher_id));

alter table if exists student_curse add constraint FK_student_course__course foreign key (course_id) references course(id);
alter table if exists student_curse add constraint FK_student_course__student foreign key (student_id) references student(id);
alter table if exists teacher_course add constraint FK_teacher_course__course foreign key (course_id) references course(id);
alter table if exists teacher_course add constraint FK_teacher_course__teacher foreign key (teacher_id) references teacher(id);