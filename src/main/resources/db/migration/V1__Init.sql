create table user (
 id int not null AUTO_INCREMENT,
 active bit not null,
 password varchar(255) not null,
 username varchar(255) not null,
 primary key (id)) engine=MyISAM;

create table user_role (
user_id integer not null,
roles varchar(255)) engine=MyISAM;

alter table user_role add constraint roles_user_userid_fk foreign key (user_id) references user(id) ON DELETE CASCADE;