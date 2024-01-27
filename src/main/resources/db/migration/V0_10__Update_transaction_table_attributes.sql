ALTER TABLE transaction
    ADD COLUMN if not exists creation_datetime timestamp with time zone default now();