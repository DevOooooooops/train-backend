DO
$$
BEGIN
        IF NOT EXISTS(SELECT FROM pg_type WHERE typname = 'transaction_type') THEN
            CREATE TYPE transaction_type AS ENUM ('INCOME', 'OUTCOME');
END IF;
END
$$;
create table if not exists "transaction" (
    id varchar constraint transaction_pk primary key default uuid_generate_v4(),
    user_id varchar not null references "user_table"(id),
    amount varchar not null,
    type transaction_type not null,
    reason varchar
);