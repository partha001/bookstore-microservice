drop table users;
create table users(
	id integer not null primary key,
	username varchar(50) not null ,
	password varchar(60) not null,
	enabled boolean not null,
	accountNonExpired     boolean not null,
    accountNonLocked      boolean not null,
    credentialsNonExpired boolean not null,
    constraint contacts_unique UNIQUE (username)
);

create table authorities (
	username varchar(50) not null,
	authority varchar(50) not null,
	constraint fk_authorities_users foreign key(username) references users (username));

create unique index ix_auth_username on authorities (username,authority);

