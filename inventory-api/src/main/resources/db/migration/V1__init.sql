
create table if not exists users (id serial primary key, username varchar(50) unique not null, password varchar(120) not null, role varchar(50) not null);
create table if not exists product (id serial primary key, sku varchar(60) unique not null, name varchar(200) not null, price numeric(19,2), stock int);
insert into users(username,password,role) values ('admin','$2a$10$h0R0h2v1t3g6R0n0m7o4ue8KJ5o9s3O0n5s6n0C2o5l7a2Cj7wU4y','ROLE_ADMIN') on conflict (username) do nothing;
