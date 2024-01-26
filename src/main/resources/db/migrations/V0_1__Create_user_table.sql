create extension if not exists "uuid-ossp";

create table if not exists "user_table" (
    id varchar constraint user_pk primary key default uuid_generate_v4(),
    username varchar not null,
    password varchar not null,
    birthdate date not null,
);