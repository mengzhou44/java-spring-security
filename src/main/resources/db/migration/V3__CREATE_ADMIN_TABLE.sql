create table admins
(
    id       bigint auto_increment primary key,
    name     varchar(255) not null,
    email    varchar(255) not null,
    role     varchar(255) not null,
    password varchar(255) null
);
