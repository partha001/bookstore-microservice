use mydatabase;

drop table users;
create table users(
	id integer not null primary key AUTO_INCREMENT,
	username varchar(50) not null ,
	password varchar(60) not null,
	enabled boolean not null,
	accountNonExpired     boolean not null,
    accountNonLocked      boolean not null,
    credentialsNonExpired boolean not null,
    constraint uk_users_username UNIQUE (username)
);

create table authorities (
	username varchar(50) not null,
	authority varchar(50) not null,
	constraint fk_authorities_users foreign key(username) references users (username));

create unique index ix_auth_username on authorities (username,authority);


insert into users (username , password ,enabled,accountNonExpired,accountNonLocked,credentialsNonExpired) values ('partha','partha',1,1,1,1);

