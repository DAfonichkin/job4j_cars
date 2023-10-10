create table auto_user
(
    id serial primary key,
    description text not null,
    created timestamp not null,
    auto_user_id references auto_post (id) not null
);