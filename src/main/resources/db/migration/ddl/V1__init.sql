create table public.users
(
    seq         bigserial
        primary key,
    create_time timestamp,
    id          varchar(100) not null,
    name        varchar(50)  not null,
    password    varchar(500) not null
);