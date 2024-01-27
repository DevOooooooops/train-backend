DO
$$
begin
        if not exists (select from pg_type where typname = 'budget_saving_mode') then
create type budget_saving_mode as ENUM ('DAILY', 'WEEKLY', 'MONTHLY');
end if;
end
$$;

ALTER TABLE "user_table"
    ADD COLUMN if not exists budget_saving_mode budget_saving_mode;

ALTER TABLE "user_table"
    ADD CONSTRAINT username_unique unique (username);

ALTER TABLE "user_table"
ADD COLUMN if not exists income jsonb default '{}';
