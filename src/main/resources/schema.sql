drop table customers if exists;

create table customers
(
    id              integer identity primary key,
    name            varchar(255) not null,
    address_line_1  varchar(200) not null,
    address_line_2  varchar(200) null,
    town            varchar(200) not null,
    county          varchar(200) not null,
    country         varchar(200) not null,
    postcode        varchar(200) not null,
);