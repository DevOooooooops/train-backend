CREATE TABLE IF NOT EXISTS "security_question_user_answer"
(
    id          varchar primary key not null default uuid_generate_v4(),
    question_id varchar             not null references security_question ("id"),
    answer      varchar             not null,
    answered_at timestamp not null default now()
);