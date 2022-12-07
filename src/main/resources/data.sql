# --------------------------------------------------------
#  DDL for schema JWT
# --------------------------------------------------------
drop schema if exists jwt;
create schema if not exists jwt;

# --------------------------------------------------------
#   DDL for Table JWT.USERS
# --------------------------------------------------------
drop table if exists jwt.users;
create table if not exists jwt.users
(
    id       bigint       not null auto_increment,
    name     varchar(100) not null,
    password varchar(100) not null,
    role     varchar(100) not null,
    primary key (id),
    unique index idx_users_user_id_unique (id asc),
    unique index idx_users_login_unique (name asc)
);

# --------------------------------------------------------
#   DDL for Table JWT.MESSAGES
# --------------------------------------------------------
drop table if exists jwt.messages;
create table if not exists jwt.messages
(
    id      bigint       not null auto_increment,
    text    varchar(400) not null,
    user_id bigint       not null,
    primary key (id),
    unique index idx_products_id_unique (id asc),
    constraint fk_messages_user_id_users_id
        foreign key (user_id) references jwt.users (id)
            on delete cascade
            on update cascade
);

# --------------------------------------------------------
--  DML for Table JWT.USERS
# --------------------------------------------------------
insert into jwt.users(id, name, password, role)
values (1, 'Иван', '$2y$10$eZYr4UnGHHG4TwpNAKJqy./jnD64vaPqUNtWV/BgomjHXJP0TyNy6', 'ADMIN');
insert into jwt.users(id, name, password, role)
values (2, 'Петр', '$2y$10$eZYr4UnGHHG4TwpNAKJqy./jnD64vaPqUNtWV/BgomjHXJP0TyNy6', 'USER');
insert into jwt.users(id, name, password, role)
values (3, 'Дмитрий', '$2y$10$eZYr4UnGHHG4TwpNAKJqy./jnD64vaPqUNtWV/BgomjHXJP0TyNy6', 'USER');

# --------------------------------------------------------
--  DML for Table JWT.MESSAGES
# --------------------------------------------------------
insert into jwt.messages(text, user_id)
values ('Первое сообщение Ивана', 1);
insert into jwt.messages(text, user_id)
values ('Второе сообщение Ивана', 1);
insert into jwt.messages(text, user_id)
values ('Третье сообщение Ивана', 1);
insert into jwt.messages(text, user_id)
values ('Четвертое сообщение Ивана', 1);
insert into jwt.messages(text, user_id)
values ('Пятое сообщение Ивана', 1);
insert into jwt.messages(text, user_id)
values ('Шестое сообщение Ивана', 1);
insert into jwt.messages(text, user_id)
values ('Седьмое сообщение Ивана', 1);
insert into jwt.messages(text, user_id)
values ('Восьмое сообщение Ивана', 1);
insert into jwt.messages(text, user_id)
values ('Девятое сообщение Ивана', 1);
insert into jwt.messages(text, user_id)
values ('Десятое сообщение Ивана', 1);

insert into jwt.messages(text, user_id)
values ('Первое сообщение Петра', 2);
insert into jwt.messages(text, user_id)
values ('Второе сообщение Петра', 2);
insert into jwt.messages(text, user_id)
values ('Третье сообщение Петра', 2);
insert into jwt.messages(text, user_id)
values ('Четвертое сообщение Петра', 2);
insert into jwt.messages(text, user_id)
values ('Пятое сообщение Петра', 2);
insert into jwt.messages(text, user_id)
values ('Шестое сообщение Петра', 2);
insert into jwt.messages(text, user_id)
values ('Седьмое сообщение Петра', 2);
insert into jwt.messages(text, user_id)
values ('Восьмое сообщение Петра', 2);
insert into jwt.messages(text, user_id)
values ('Девятое сообщение Петра', 2);
insert into jwt.messages(text, user_id)
values ('Десятое сообщение Петра', 2);

insert into jwt.messages(text, user_id)
values ('Первое сообщение Алесандра', 3);
insert into jwt.messages(text, user_id)
values ('Второе сообщение Алесандра', 3);
insert into jwt.messages(text, user_id)
values ('Третье сообщение Алесандра', 3);
insert into jwt.messages(text, user_id)
values ('Четвертое сообщение Алесандра', 3);
insert into jwt.messages(text, user_id)
values ('Пятое сообщение Алесандра', 3);