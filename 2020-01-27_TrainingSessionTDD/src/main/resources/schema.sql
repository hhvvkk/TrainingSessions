drop table if exists currency;

create table currency
(
    id bigserial not null constraint currency_primary_key primary key,
    type varchar(80) not null,
    currency_value int not null,
    english_number_name varchar(500) not null
);

drop table if exists todo;

create table todo
(
    id bigserial not null constraint todo_primary_key primary key,
    valid boolean not null,
    text varchar(500) not null
);