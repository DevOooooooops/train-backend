ALTER TABLE transaction
    ADD COLUMN creation_datetime timestamp with time zone default now();