DO
$$
begin
        if not exists (select from pg_type where typname = 'sex') then
create type sex as ENUM ('M', 'F', 'O');
end if;
end
$$;
ALTER TABLE "user_table"
    ADD first_name varchar;
ALTER TABLE "user_table"
    ADD last_name varchar;
ALTER TABLE "user_table"
    ADD sex sex;

ALTER TABLE "user_table"
    ADD COLUMN if not exists balance int;

ALTER TABLE "user_table"
    ADD COLUMN if not exists level int;

ALTER TABLE "user_table"
    ADD COLUMN if not exists score int;