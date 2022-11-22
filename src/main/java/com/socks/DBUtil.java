package com.socks;

import java.sql.*;

public class DBUtil {

	private static Connection conn = null;

	public static Connection open() {

		String url = "jdbc:sqlserver://INNO_SERVER:1433;" + "database=BBKRDFDEV;";
		String id = "bbkrdev";
		String pw = "1bluebell!";

		// 디비 연결 필수 > 드라이브 사용해서 커넥을 하는데 문제가 생겼는 지 안생겼는 지 보기 위해
		try {

			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

			conn = DriverManager.getConnection(url, id, pw);

			System.out.println("디비 연결 성공");

			return conn;
		}

		catch (Exception e) {
			System.out.println("디비 연결 오류");
			e.printStackTrace();
		}

		return null;
	}

	/*
	 * public static void main(String[] args) throws ClassNotFoundException,
	 * SQLException {
	 * 
	 * conn = DBUtil.open();
	 * 
	 * 
	 * 
	 * Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
	 * 
	 * String connectionUrl = "jdbc:sqlserver://INNO_SERVER:1433;" +
	 * "database=BBKRDFDEV;" + "user=bbkrdev;" + "password=1bluebell!;";
	 * 
	 * 
	 * String connectionUrl = "jdbc:sqlserver://192.168.11.205:1433;" +
	 * "database=BBKRDFDEV;" + "user=bbkrdev;" + "password=1bluebell!;";
	 * 
	 * 
	 * try (Connection connection = DriverManager.getConnection(connectionUrl);) {
	 * // Code here. Statement stmt = connection.createStatement(); ResultSet rs =
	 * stmt .executeQuery("SELECT Description from bbis_brand");
	 * 
	 * while (rs.next()) { String Description = rs.getString("Description");
	 * 
	 * System.out.println(Description); } rs.close(); stmt.close();
	 * connection.close(); } // Handle any errors that may have occurred. catch
	 * (SQLException e) { e.printStackTrace(); }
	 * 
	 * 
	 * }
	 */

}
