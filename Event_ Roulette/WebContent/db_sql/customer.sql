drop table tb_customer purge;

create table tb_customer(
id varchar2(10) primary key,
password varchar2(10) not null,
name varchar2(16));

create table tb_customer(
id varchar(10) primary key,
password varchar(10) not null,
name varchar(16));

select * from tab;
select * from tb_customer;

select * from event_db

select * from TempDB_T1.dbo.test_db;

select * from EventDB.dbo.event_db;



////////////////////////////////////////////////////////////////////////

create table test1( client_mem_code int,
					id varchar(20),
					name varchar(20),
					enter_yn varchar(10))
					
					
drop table test1

insert into test1 (client_mem_code, id, name, enter_yn)values(52510, 'test1', '테스트1', 'Y')


delete from  test1 where client_mem_code=52510

select * from test1 where client_mem_code=52510