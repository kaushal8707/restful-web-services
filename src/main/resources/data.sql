--create table user
--(
--   id integer not null,
--   name varchar(255) not null,
--   location varchar(255),
--   birth_date timestamp,
--   primary key(id)
--);

insert into user values(10001,sysdate(),'ab');
insert into user values(10002,sysdate(),'jill');
insert into user values(10003,sysdate(),'jam');
insert into post values(11001,'My First Post',10001);
insert into post values(11002,'My Second Post',10001);
