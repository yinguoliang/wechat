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
			/***** 1. ��д���ݿ������Ϣ(��������ݿ�����ҳ) *****/
			String databaseName = " ybclcjrfndWjDroRBKtz ";
			String host = "sqld.duapp.com";
			String port = "4050";
			String username = "jGFpAc5j3tO5QLQm7BhqcxAI";// �û���(api key);
			String password = "eCbNViwYAwmbqLScp0gdvHm2d9aYkSmA";// ����(secret key)
			String driverName = "com.mysql.jdbc.Driver";
			String dbUrl = "jdbc:mysql://";
			String serverName = host + ":" + port + "/";
			String connName = dbUrl + serverName + databaseName;

			/****** 2. �������Ӳ�ѡ�����ݿ���ΪdatabaseName�ķ����� ******/
			Class.forName(driverName);
			connection = DriverManager.getConnection(connName, username,password);
			stmt = connection.createStatement();
			/* ������������ȫ�������ͿɶԵ�ǰ���ݿ������Ӧ�Ĳ����� */
			/* 3. �������Ϳ���ʹ��������׼mysql���������������ݿ���� */
			// ����һ�����ݿ��
			sql = "create table if not exists test_mysql("
					+ "id int primary key auto_increment," + "no int, "
					+ "name varchar(1024)," + "key idx_no(no))";
			stmt.execute(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
