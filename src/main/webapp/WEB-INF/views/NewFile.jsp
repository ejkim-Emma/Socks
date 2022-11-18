<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%
request.setCharacterEncoding("UTF-8");
response.setContentType("text/html; charset=UTF-8");
%>
<%@ page import = "java.sql.*" %>
<% 

Connection conn = null;
String sql = null;
PreparedStatement pstmt = null;

try
{
    String jdbcDriver = "jdbc:sqlserver://localhost:1433;database=BBKDFDEV;";
    String dbUser = "bbkrdev";
    String dbPass = "1bluebell!";
    conn = DriverManager.getConnection(jdbcDriver, dbUser, dbPass);

    //conn = DriverManager.getConnection(jdbcUrl);
    out.println("제대로 연결되었습니다");
}
catch(Exception e)
{
    out.println("제대로 연결이 안 되었습니다");
    e.printStackTrace();
}

%>