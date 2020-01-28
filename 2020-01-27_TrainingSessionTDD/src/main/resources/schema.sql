drop table if exists currency;

create table currency
(
    id bigserial not null constraint currency_primary_key primary key,
    type varchar(80) not null,
    currency_value int not null,
    english_number_name varchar(500) not null
);