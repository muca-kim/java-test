drop table if exists member;
create table member (
    name varchar(255) not null,
    age integer not null,
    grade integer,
    sex varchar(255),
    primary key (name)
);