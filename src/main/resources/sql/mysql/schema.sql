drop table if exists hyjj_task;
drop table if exists hyjj_user;


create table hyjj_task (
	id bigint auto_increment,
	title varchar(128) not null,
	description varchar(255),
	user_id bigint not null,
    primary key (id)
) engine=InnoDB;

create table hyjj_user (
	id bigint auto_increment,
	login_name varchar(64) not null unique,
	name varchar(64) not null,
	password varchar(255) not null,
	salt varchar(64) not null,
	roles varchar(255) not null,
	register_date timestamp not null default current_timestamp,
	primary key (id)
) engine=InnoDB;

drop table if exists hyjj_datasource;
create table hyjj_datasource (
	id bigint auto_increment,
	name varchar(64) not null,
	description varchar(512) not null,
	db_type varchar(32) not null,
	connection_url varchar(256) not null,
	username varchar(32) not null,
	password varchar(32) not null,
	user_id bigint not null,
	create_date timestamp not null default current_timestamp,
    valid tinyint(1) default 0,
	primary key (id)
) engine=InnoDB;