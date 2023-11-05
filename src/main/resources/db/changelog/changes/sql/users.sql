create table users (
    id serial primary key,
    name varchar(255) not null unique,
    password varchar(255) not null,
    role_id integer references status(id),
    stand_id integer default null
);