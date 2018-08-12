use mydatabase;

drop table users;
create table users(
	id integer not null primary key AUTO_INCREMENT,
	username varchar(50) not null ,
	password varchar(60) not null,
	firstname varchar(60) not null ,
	lastname varchar(60) not null,
	enabled boolean not null,
	accountNonExpired     boolean not null,
    accountNonLocked      boolean not null,
    credentialsNonExpired boolean not null,
    constraint uk_users_username UNIQUE (username)
);



create table authorities (
	id integer not null primary key ,
	userId integer not null,
	authority varchar(50) not null,
	constraint fk_authorities_users foreign key(userId) references users (id));

create unique index ix_auth_username on authorities (userId,authority);


insert into users (username , password , firstname ,lastname, enabled,accountNonExpired,accountNonLocked,credentialsNonExpired) values ('partha@gmail.com','partha','partha','biswas',1,1,1,1);
insert into authorities (id ,userId ,authority) values (1,1,'ROLE_USER');
insert into authorities (id ,userId ,authority) values (2,1,'ROLE_ADMIN');

