create sequence revinfo_seq start with 1 increment by 50;
create table revinfo (rev integer not null, revtstmp bigint, primary key (rev));

create table course_aud (rev integer not null, revtype smallint, id bigint not null, student_id bigint, name varchar(255), primary key (rev, id));
create table student_aud (freely_used_semesters integer, rev integer not null, revtype smallint, semester integer, student_central_id integer, birthdate timestamp(6), id bigint not null, name varchar(255), primary key (rev, id));
create table student_curse_aud (rev integer not null, revtype smallint, course_id bigint not null, student_id bigint not null, primary key (rev, course_id, student_id));
create table teacher_aud (rev integer not null, revtype smallint, birthdate timestamp(6), id bigint not null, name varchar(255), primary key (rev, id));
create table teacher_course_aud (rev integer not null, revtype smallint, course_id bigint not null, teacher_id bigint not null, primary key (rev, course_id, teacher_id));
alter table if exists course_aud add constraint FK_course_aud_rev foreign key (rev) references revinfo;
alter table if exists student_aud add constraint FK_student_aud_rev foreign key (rev) references revinfo;
alter table if exists student_curse_aud add constraint FK_student_curse_aud_rev foreign key (rev) references revinfo;
alter table if exists teacher_aud add constraint FK_teacher_aud_rev foreign key (rev) references revinfo;
alter table if exists teacher_course_aud add constraint FK_teacher_course_aud_rev foreign key (rev) references revinfo;
