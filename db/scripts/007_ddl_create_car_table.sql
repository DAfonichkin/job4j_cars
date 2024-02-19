create table car(
    id serial primary key,
    name VARCHAR NOT NULL,
    engine_id int not null unique references engine(id)
);