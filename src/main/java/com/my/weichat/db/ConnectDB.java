package com.my.weichat.db;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ConnectDB {

	public static void main(String args[]) throws IOException {
		Connection connection = null;
		Statement stmt = null;
		ResultSet rs = null;
		String sql = null;
		try {
			/***** 1. 填写数据库相关信息(请查找数据库详情页) *****/
			String databaseName = " ybclcjrfndWjDroRBKtz ";
			String host = "sqld.duapp.com";
			String port = "4050";
			String username = "jGFpAc5j3tO5QLQm7BhqcxAI";// 用户名(api key);
			String password = "eCbNViwYAwmbqLScp0gdvHm2d9aYkSmA";// 密码(secret key)
			String driverName = "com.mysql.jdbc.Driver";
			String dbUrl = "jdbc:mysql://";
			String serverName = host + ":" + port + "/";
			String connName = dbUrl + serverName + databaseName;

			/****** 2. 接着连接并选择数据库名为databaseName的服务器 ******/
			Class.forName(driverName);
			connection = DriverManager.getConnection(connName, username,password);
			stmt = connection.createStatement();
			/* 至此连接已完全建立，就可对当前数据库进行相应的操作了 */
			/* 3. 接下来就可以使用其它标准mysql函数操作进行数据库操作 */
			// 创建一个数据库表
			sql = "create table if not exists test_mysql("
					+ "id int primary key auto_increment," + "no int, "
					+ "name varchar(1024)," + "key idx_no(no))";
			stmt.execute(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
