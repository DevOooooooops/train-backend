create table "quest" (
    id varchar constraint quest_pk primary key default uuid_generate_v4(),
    name varchar constraint name_quest_constraint unique not null ,
    objective_description varchar not null,
    amount_objective double precision not null default 0,
    points integer not null default 0,
    required_level integer not null default 0
);