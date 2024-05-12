create table if not exists notifications
(
    id         uuid primary key not null,
    client_id  uuid             not null,
    products   text,
    created_at timestamp
);


create table if not exists orders
(
    id         uuid primary key not null,
    client_id  uuid             not null,
    products   text,
    status     varchar(255),
    created_at timestamp
);