create sequence hibernate_sequence start 1 increment 1;
create table laptop
(
    laptop_id       int8   not null,
    hd              float8,
    model           int8   not null,
    price           float8,
    ram             int2,
    screen_square   int4   not null,
    processor_speed float8 not null,
    primary key (laptop_id)
);
create table message
(
    message_id int8 not null,
    filename   varchar(255),
    tag        varchar(255),
    text       varchar(2048),
    user_id    int8,
    primary key (message_id)
);
create table pc
(
    pc_id           int4   not null,
    cd              varchar(10),
    hd              float8,
    model           int8   not null,
    price           float8,
    ram             int2,
    processor_speed float8 not null,
    primary key (pc_id)
);
create table printer
(
    printer_id int4        not null,
    color      char(1),
    model      int8        not null,
    price      int4,
    type       varchar(45) not null,
    primary key (printer_id)
);
create table product
(
    model int8        not null,
    maker varchar(10) not null,
    type  varchar(45),
    primary key (model)
);
create table user_role
(
    user_id int8 not null,
    roles   varchar(255)
);
create table usr
(
    user_id         int8    not null,
    activation_code varchar(255),
    active          boolean not null,
    email           varchar(255),
    password        varchar(255) not null,
    username        varchar(255) not null,
    primary key (user_id)
);
alter table if exists message
    add constraint message_user_fk foreign key (user_id) references usr;
alter table if exists pc
    add constraint FKmfll5wgxb1ia349golm5qvuw3 foreign key (model) references product;
alter table if exists printer
    add constraint FKc0luy9by9xmhh01i9srk3bm4r foreign key (model) references product;
alter table if exists user_role
    add constraint user_role_user_fk foreign key (user_id) references usr;