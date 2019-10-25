create table user_transaction(
id int primary key  auto_increment,
user_id int  not null,
request_id int not null,
amount_donated double not null,
transaction_date timestamp not null default current_timestamp,
modified_date timestamp not null default current_timestamp on update current_timestamp,
check(amount_donated>0)
);