create table student (
  id   bigint(20)   not null primary key auto_increment,
  name varchar(255) not null,
  age  int          not null
)
  engine = innodb
  default charset = utf8;

insert into student
values (default, 'zhangsan', 20);
insert into student
values (default, 'lisi', 22);
insert into student
values (default, 'wanger', 25);

select *
from student;
select * from other_employee;
select * from other_test_employee;
insert into other_employee values (default ,21,'test1');
insert into other_employee values (default ,22,'test2');
insert into other_employee values (default ,20,'test3');
insert into other_employee values (default ,21,'test4');
insert into other_employee values (default ,22,'test5');
insert into other_employee values (default ,20,'test15');