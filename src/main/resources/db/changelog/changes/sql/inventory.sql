create table inventory(
  user_id integer not null references users(id),
  item_id integer not null references items(id),
  primary key (user_id, item_id)
);