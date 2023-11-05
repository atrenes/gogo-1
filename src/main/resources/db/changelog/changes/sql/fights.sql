create table fights(
    id serial primary key,
    first_stand_id integer not null references stands(id),
    second_stand_id integer not null references stands(id),
    winner_id integer not null references stands(id),
    dropped_item_id integer references items(id)
);