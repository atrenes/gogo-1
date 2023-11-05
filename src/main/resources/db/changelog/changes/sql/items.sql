create table items (
    id serial primary key,
    name text not null,
    drop_possibility double precision not null check ( drop_possibility <= 1 and drop_possibility >= 0 )
);