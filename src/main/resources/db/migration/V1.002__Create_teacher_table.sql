create sequence teacher_seq start with 1 increment by 1;

create table teacher (id bigint not null, name varchar(255), birthdate timestamp(6), primary key (id));