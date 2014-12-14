create table T_Member(
	User_ID int,
	User_Name varchar(100),
	User_CellPhone varchar(20),
	User_Email varchar(20),
	User_addr varchar(200),
	User_door varchar(50),
	User_remark varchar(200)
);
create table T_Order(
	Order_ID int,
	Order_Code varchar(100),
	Order_OrderTime date,
	Order_Price double,
	Order_DealTime date,
	Order_Status varchar(10),
	Order_remark varchar(100)
);
create table T_Beautician(
	Beautician_ID int,
	Beautician_Name varchar(100),
	Beautician_img varchar(100),
	Beautician_CellPhone varchar(20),
	Beautician_feature varchar(200)
);
create table T_comment(
	comment_ID int,
	Beautician_ID int,
	Order_Code varchar(100),
	comment_Time date,
	comment_detail varchar(1000)
);
create table T_User(
	name varchar(100),
	addr varchar(100)
);
create table T_BeauticianWorks(
	Works_id int,
	Beautician_ID int,
	works_img varchar(100),
	works_remark varchar(500),
	works_duration varchar(100),
	works_spendtime varchar(100)
);