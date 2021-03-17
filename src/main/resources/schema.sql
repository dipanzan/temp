drop table if exists users;

create table users
(
    id       integer             not null auto_increment,
    uuid     varchar(255) unique null,
    phone    varchar(14) unique  not null,
    password varchar(255)        not null,
    primary key (id)
);

insert into users (phone, password)
    value ('01833182799', 'abcABC123#');