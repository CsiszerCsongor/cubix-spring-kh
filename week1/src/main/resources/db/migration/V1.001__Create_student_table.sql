create sequence student_seq start with 1 increment by 1;

create table student (
    id bigint not null,
    name varchar(255),
    birthdate timestamp(6),
    semester int, primary key (id)
);