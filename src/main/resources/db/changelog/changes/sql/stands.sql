create table stands (
    id serial primary key,
    owner_id integer not null references users(id),
    rating double precision not null,
    power double precision not null,
    speed double precision not null,
    range double precision not null,
    durability double precision not null,
    precision double precision not null
);

alter table users
add foreign key (stand_id) references stands(id);