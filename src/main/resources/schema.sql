# drop table if exists users;
#
# create table users
# (
#     id       integer             not null auto_increment,
#     username    varchar(14) unique  not null,
#     password varchar(255)        not null,
#     active bool not null default true,
#     primary key (id)
# );