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

				odto.setYear(rs.getInt("year"));
				odto.setMonth(rs.getInt("month"));

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
					+ "values ((select concat(left(num, 6), convert(nvarchar(50), format(convert(int, RIGHT(num,4) + 1), '0000')))\r\n"
					+ "	       from (select max(Due_ID) as num \r\n"
					+ "				   from bbsupply_order_due) as bk), CONCAT(?, '년',?, '월(',\r\n"
					+ "						case when ?=12 then 0 \r\n" + "						else ? end +1,'~',\r\n"
					+ "						case when ?=12 then 0 else ? end +2,'월분)'), ?, ?, ?, ?)";

			// concat은 문자열과 숫자를 더할 때 쓴다. '+'도 마찬가지 이나 문자 문자 / 숫자 숫자 만 가능할 뿐 문자 숫자는 안된다.

			pstat = conn.prepareStatement(sql);

			/* pstat.setString(1, dto.getPeriod()); */
			pstat.setInt(1, dto.getYear());
			pstat.setInt(2, dto.getMonth());
			pstat.setInt(3, dto.getMonth());
			pstat.setInt(4, dto.getMonth());
			pstat.setInt(5, dto.getMonth());
			pstat.setInt(6, dto.getMonth());

			pstat.setInt(7, dto.getYear());
			pstat.setInt(8, dto.getMonth());
			pstat.setString(9, dto.getSta_Date());
			pstat.setString(10, dto.getClosing_Date());

			return pstat.executeUpdate();

		} catch (Exception e) {
			System.out.println("기간이 더해지지 않았습니다.");
			e.getStackTrace();
		}

		return 0;
	}

	public int edit(periodDTO dto) {
		try {

			String sql = "update bbsupply_order_due set Period = CONCAT(?, '년',?, '월(',case when ?=12 then 0 else ? end +1,'~',case when ?=12 then 0 else ? end +2,'월분)'), Year = ?, Month\r\n"
					+ "					 = ?, Sta_Date = ?, Closing_Date = ? where Due_ID = ?";

			pstat = conn.prepareStatement(sql);

			pstat.setInt(1, dto.getYear());
			pstat.setInt(2, dto.getMonth());
			pstat.setInt(3, dto.getMonth());
			pstat.setInt(4, dto.getMonth());

			pstat.setInt(5, dto.getMonth());
			pstat.setInt(6, dto.getMonth());

			pstat.setInt(7, dto.getYear());
			pstat.setInt(8, dto.getMonth());

			pstat.setString(9, dto.getSta_Date());
			pstat.setString(10, dto.getClosing_Date());
			pstat.setString(11, dto.getDue_ID());

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

			String sql = "delete\r\n" + "  from bbsupply_order_due\r\n" + " where Due_ID = ?";

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

	// 저장버튼으로 한번에 수정 삭제 하기
	public int periodSave(periodDTO dto) {
		System.out.println("넘어왓나?");

		// 셀렉트 박스가 체크되어 있다면
		if (dto.getSelectDueId() != null) {
			System.out.println("if 문 안에 들어오나?");

			// 디비에 접근할 때는 무조건 try-catch문을 이용해야한다.
			try {
				
				int count[] = new int[dto.getSelectDueId().length];

				String sql = "delete from bbsupply_order_due where Due_ID = ?";

				pstat = conn.prepareStatement(sql);

				for (int i = 0; i < dto.getSelectDueId().length; i++) {
					pstat.setString(1, dto.getSelectDueId()[i]);

					pstat.addBatch();
					pstat.clearParameters();
				}

				count = pstat.executeBatch();
				System.out.println("카운트 담았니?" + count);
				// System.out.println("길이: " + dto.getSelectCode().length + "/" + count.length);

				int cnt[] = new int[dto.getOrderPeriod().length];
				System.out.println("길이: " + cnt.length);

				// sql문 적기
				String sql1 = "update bbsupply_order_due set Period = ?, Year = ?, Month = ?, Sta_Date = ?, Closing_Date = ? where Due_ID = ?";

				pstat = conn.prepareStatement(sql1);

				for (int i = 0; i < cnt.length; i++) {

					pstat.setString(1, dto.getOrderPeriod()[i]);
					// System.out.println("기간" + dto.getOrderPeriod()[i]);
					pstat.setInt(2, dto.getOrderYear()[i]);
					// System.out.println("년" + dto.getOrderYear()[i]);
					pstat.setInt(3, dto.getOrderMonth()[i]);
					// System.out.println("월" + dto.getOrderMonth()[i]);
					pstat.setString(4, dto.getOrderStart()[i]);
					// System.out.println("시작기간" + dto.getOrderStart()[i]);
					pstat.setString(5, dto.getOrderClose()[i]);
					// System.out.println("종료기간" +dto.getOrderClose()[i]);

					pstat.setNString(6, dto.getOrderDueId()[i]);
					// System.out.println("아이디" + dto.getOrderDueId()[i]);

				}

				cnt = pstat.executeBatch();
				System.out.println("길이:" + cnt.length + "내가 만든 길이" + dto.getOrderDueId().length);

			} catch (Exception e) {
				System.out.println("기간 수정에 실패했습니다.");
				e.printStackTrace();
				return 0;
			}

			// 셀렉트 박스가 체크가 안되어 있다면
		} else {
			// 디비에 접근할 때는 무조건 try-catch문을 이용해야한다.
			try {

				int cnt[] = new int[dto.getOrderPeriod().length];
				System.out.println("길이: " + cnt.length);

				// sql문 적기
				String sql = "update bbsupply_order_due set Period = ?, Year = ?, Month = ?, Sta_Date = ?, Closing_Date = ? where Due_ID = ?";

				pstat = conn.prepareStatement(sql);

				for (int i = 0; i < cnt.length; i++) {

					pstat.setString(1, dto.getOrderPeriod()[i]);
					// System.out.println("기간" + dto.getOrderPeriod()[i]);
					pstat.setInt(2, dto.getOrderYear()[i]);
					// System.out.println("년" + dto.getOrderYear()[i]);
					pstat.setInt(3, dto.getOrderMonth()[i]);
					// System.out.println("월" + dto.getOrderMonth()[i]);
					pstat.setString(4, dto.getOrderStart()[i]);
					// System.out.println("시작기간" + dto.getOrderStart()[i]);
					pstat.setString(5, dto.getOrderClose()[i]);
					// System.out.println("종료기간" +dto.getOrderClose()[i]);

					pstat.setString(6, dto.getOrderDueId()[i]);
					// System.out.println("아이디" + dto.getOrderDueId()[i]);
					
					pstat.addBatch();

					pstat.clearParameters();

				}

				cnt = pstat.executeBatch();
				System.out.println("길이:" + cnt.length + "내가 만든 길이" + dto.getOrderDueId().length);

			} catch (Exception e) {
				System.out.println("기간 수정에 실패했습니다.");
				e.printStackTrace();
				return 0;
			}
			return 0;
		}
		return 1;
	}

}
