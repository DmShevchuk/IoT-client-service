create table if not exists users
(
    id           uuid primary key not null,
    login        varchar(255)     not null unique,
    first_name   varchar(50)      not null,
    last_name    varchar(50)      not null,
    middle_name  varchar(50),
    email        varchar(255)     not null,
    phone_number varchar(18)      not null,
    password     varchar(255)     not null
);