drop table if exists author CASCADE;
drop table if exists author_book CASCADE;
drop table if exists book CASCADE;
drop table if exists comment CASCADE;
drop table if exists genre CASCADE;

drop sequence if exists hibernate_sequence;
create sequence hibernate_sequence start with 1 increment by 1;

create table author
(
    id   bigint not null,
    name varchar(255),
    primary key (id)
);

create table author_book
(
    author_id bigint not null,
    book_id   bigint not null
);

create table book
(
    id       bigint not null,
    name     varchar(255),
    fk_genre bigint,
    primary key (id)
);

create table comment
(
    id      bigint not null,
    comment varchar(2000),
    fk_book bigint,
    primary key (id)
);

create table genre
(
    id   bigint not null,
    name varchar(255),
    primary key (id)
);

alter table author_book
    add constraint FKioufdohwqqxemkciuruoq9269
        foreign key (book_id)
            references author;

alter table author_book
    add constraint FK123vmbirb0ho9824t7cw6syol
        foreign key (author_id)
            references book;

alter table book
    add constraint FKbh9hyov6yd4265iyetwtcn538
        foreign key (fk_genre)
            references genre;

alter table comment
    add constraint FK9qq34igt6fo885b995ufxewu9
        foreign key (fk_book)
            references book;
    