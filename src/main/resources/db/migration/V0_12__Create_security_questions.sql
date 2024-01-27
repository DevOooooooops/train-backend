CREATE TABLE IF NOT EXISTS "security_question"
(
    id varchar primary key not null default uuid_generate_v4(),
    question varchar not null
);