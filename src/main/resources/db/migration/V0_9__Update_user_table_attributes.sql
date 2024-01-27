DO
$$
begin
        if not exists (select from pg_type where typname = 'sex') then
create type sex as ENUM ('M', 'F', 'O');
end if;
end
$$;
ALTER TABLE "user_table"
    ADD COLUMN if not exists  first_name varchar;
ALTER TABLE "user_table"
    ADD COLUMN if not exists  last_name varchar;
ALTER TABLE "user_table"
    ADD COLUMN if not exists  sex sex;
