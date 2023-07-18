create table address (id bigint not null, city varchar(255), country varchar(255), street varchar(255), zip varchar(255), primary key (id));
alter table if exists airport add column address_id bigint;
alter table if exists airport add constraint FK_airport_address foreign key (address_id) references address;