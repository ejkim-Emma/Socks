package com.socks;

import java.sql.*;

public class DBUtil {

	public static void main(String[] args) throws ClassNotFoundException {
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

		String connectionUrl = "jdbc:sqlserver://127.0.0.1:1433;" + "database=BBKRDFDEV;" + "user=bbkrdev;"
				+ "password=1bluebell!;";

		try (Connection connection = DriverManager.getConnection(connectionUrl);) {
			// Code here.
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt
					.executeQuery("SELECT Description from bbis_brand");

			while (rs.next()) {
				String Description = rs.getString("Description");

				System.out.println(Description);
			}
			rs.close();
			stmt.close();
			connection.close();
		}
		// Handle any errors that may have occurred.
		catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
