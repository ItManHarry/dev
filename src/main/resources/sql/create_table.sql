create table tb_employee(
	id int primary key auto_increment,
	name varchar(20),
	code varchar(20),	
	gender int,		
	email varchar(100),	
	mobile varchar(20),	
	address varchar(200),	
	job varchar(50),
	createuserid varchar(50),
	createtime date,
	modifyuserid varchar(50),
	modifytime date,
	rowver int,
	delflag char
);