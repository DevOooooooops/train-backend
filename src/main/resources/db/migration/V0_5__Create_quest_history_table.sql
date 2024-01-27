DO
$$
    begin
        if not exists (select from pg_type where typname = 'quest_status') then
            create type quest_status as ENUM ('PENDING', 'IN_PROGRESS', 'SUCCESS', 'FAILED');
        end if;
    end
$$;

CREATE TABLE "quest_history" (
    id varchar constraint quest_history_pk primary key default uuid_generate_v4(),
    quest_id varchar,
    user_id varchar,
    status quest_status not null default 'PENDING',
    constraint quest_with_history_fk foreign key (quest_id) references "quest"(id),
    constraint user_quest_history_fk foreign key (user_id) references "user_table"(id)
);