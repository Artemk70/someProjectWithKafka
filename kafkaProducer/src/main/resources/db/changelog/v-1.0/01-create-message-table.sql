create table messages
(
    id           bigint       not null auto_increment,
    date_of_send datetime(6) not null,
    text         varchar(255) not null,
    primary key (id)
) engine=InnoDB
GO
