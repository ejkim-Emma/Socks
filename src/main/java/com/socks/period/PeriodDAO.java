package com.socks.period;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.socks.DBUtil;
import com.socks.dto.dateDTO;
import com.socks.dto.orderdueDTO;
import com.socks.dto.periodDTO;

public class PeriodDAO {

	private Connection conn;
	private Statement stat;
	private PreparedStatement pstat;
	private ResultSet rs;

	// 디비 사용할 수 있도록 클래스 불러오기
	public PeriodDAO() {
		conn = DBUtil.open();
	}

	public ArrayList<orderdueDTO> list() {
		try {

			String sql = "select * from bbsupply_order_due order by Due_ID desc";

			stat = conn.createStatement();

			rs = stat.executeQuery(sql);

			ArrayList<orderdueDTO> olist = new ArrayList<orderdueDTO>();

			while (rs.next()) {

				orderdueDTO odto = new orderdueDTO();

				odto.setDue_ID(rs.getString("due_ID"));
				odto.setPeriod(rs.getString("period"));

				odto.setYear(rs.getString("year"));
				odto.setMonth(rs.getString("month"));

				odto.setSta_Date(rs.getString("sta_Date"));
				odto.setClosing_Date(rs.getString("closing_Date"));

				olist.add(odto);

			}
			// 커서 닫기
			rs.close();
			// sql문 사용 명령어 닫기
			stat.close();
			// 디비 연동 닫기
			conn.close();

			return olist;

		} catch (Exception e) {
			System.out.println("기간 목록을 불러올 수 없다.");
			e.printStackTrace();
		}
		return null;
	}

	public ArrayList<dateDTO> date() {
		try {

			conn = DBUtil.open();

			String sql = "select distinct Year\r\n" + "  from bbis_calendar\r\n" + " order by Year desc";

			stat = conn.createStatement();

			rs = stat.executeQuery(sql);

			ArrayList<dateDTO> list = new ArrayList<dateDTO>();

			while (rs.next()) {

				dateDTO year = new dateDTO();

				year.setYear(rs.getString("year"));

				list.add(year);
			}

			rs.close();
			stat.close();
			conn.close();

			return list;

		} catch (Exception e) {
			System.out.println("년 / 월을 불러오지 못했습니다.");
			e.getStackTrace();
		}
		return null;
	}

	// AddOk.java > 데이터 받은 것을 저장하는 부분
	public int add(periodDTO dto) {

		try {

			conn = DBUtil.open();

			String sql = "insert into bbsupply_order_due (Due_ID, Period, Year, Month, Sta_Date, Closing_Date) \r\n"
					+ "values ((select concat(left(num, 6), convert(nvarchar(50), format(convert(int, RIGHT(num,4) + 1), '0000'))) \r\n"
					+ "	       from (select max(Due_ID) as num  from bbsupply_order_due) as bk), \r\n"
					+ "		 CONCAT(?, '년',?, '월(',?+1,'~',?+2,'월분)'), ?, ?, ?, ?)";

			// concat은 문자열과 숫자를 더할 때 쓴다. '+'도 마찬가지 이나 문자 문자 / 숫자 숫자 만 가능할 뿐 문자 숫자는 안된다.

			pstat = conn.prepareStatement(sql);

			/* pstat.setString(1, dto.getPeriod()); */
			pstat.setInt(1, dto.getYear());
			pstat.setInt(2, dto.getMonth());
			pstat.setInt(3, dto.getMonth());
			pstat.setInt(4, dto.getMonth());

			pstat.setInt(5, dto.getYear());
			pstat.setInt(6, dto.getMonth());
			pstat.setString(7, dto.getSta_Date());
			pstat.setString(8, dto.getClosing_Date());

			return pstat.executeUpdate();

		} catch (Exception e) {
			System.out.println("기간이 더해지지 않았습니다.");
			e.getStackTrace();
		}

		return 0;
	}

	public int edit(periodDTO dto) {
		try {

			String sql = "update bbsupply_order_due set Period = CONCAT(?, '년',?, '월(',?+1,'~',?+2,'월분)'), Year = ?, Month\r\n"
					+ " = ?, Sta_Date = ?, Closing_Date = ? where Due_ID = ?";

			pstat = conn.prepareStatement(sql);

			
			pstat.setInt(1, dto.getYear());
			pstat.setInt(2, dto.getMonth());
			pstat.setInt(3, dto.getMonth());
			pstat.setInt(4, dto.getMonth());
			
			pstat.setInt(5, dto.getYear());
			pstat.setInt(6, dto.getMonth());
			pstat.setString(7, dto.getSta_Date());
			pstat.setString(8, dto.getClosing_Date());

			pstat.setString(9, dto.getDue_ID());

			return pstat.executeUpdate();

		} catch (Exception e) {

			System.out.println("신청기간 수정에 실패하였습니다.");
			e.printStackTrace();
		}
		return 0;
	}

	public int del(String due_ID) {

		try {

			conn = DBUtil.open();

			String sql = "delete\r\n" + 
					"  from bbsupply_order_due\r\n" + 
					" where Due_ID = ?";

			pstat = conn.prepareStatement(sql);

			System.out.println(sql);

			pstat.setString(1, due_ID);
			System.out.println("코드:" + due_ID);

			return pstat.executeUpdate();

		} catch (Exception e) {
			System.out.println("목록 삭제 실패!");
			e.printStackTrace();
		}
		return 0;
	}

}
