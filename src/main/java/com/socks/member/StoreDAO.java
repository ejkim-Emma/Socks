package com.socks.member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.socks.DBUtil;
import com.socks.dto.brandDTO;
import com.socks.dto.storeDTO;

public class StoreDAO {

	private Connection conn;
	private Statement stat;
	private PreparedStatement pstat;
	private ResultSet rs;

	// 클래스 명과 같은 메소드를 하나 만들어서 dbutil을 가져온다.
	public StoreDAO() {
		// 디비 열기
		conn = DBUtil.open();
	}

	// 브랜드 리스트
	public ArrayList<brandDTO> brandlist() {
		
		try {

			conn = DBUtil.open();
			
			String sql = "select Brand_ID, Description, Brand_code from bbis_brand";

			stat = conn.createStatement();

			rs = stat.executeQuery(sql);
			// System.out.println("rs");
			
			ArrayList<brandDTO> blist = new ArrayList<brandDTO>();
			// 리스트에 가져오는 열의 개수를 확인
			// System.out.println("확인" + rs.getRow());
			
			while (rs.next()) {

				// System.out.println("while 출력");
				brandDTO bdto = new brandDTO();

				bdto.setBrand_ID(rs.getString("Brand_ID"));
				bdto.setDescription(rs.getString("Description"));

				blist.add(bdto);
			}

			rs.close();
			stat.close();
			conn.close();

			// 1번
			return blist;

		} catch (Exception e) {
			System.out.println("brand 오류입니다.");
			e.printStackTrace();
		}
		
		// 1번까지 오지 않았을 때 null 값을 넘긴다.
		return null;
	}

	public ArrayList<storeDTO> store(String Brand_ID) {

		try {
			
			// 디비 열기
			conn = DBUtil.open();
			
			// sql문을 가지고 'sql'이라는 변수 선언
			/*
			 * String sql = "select st.Store_ID, st.Description\r\n" +
			 * "  from bbis_store st\r\n" + " inner join bbis_brand br\r\n" +
			 * "    on st.Brand_ID = ?";
			 */
			
			String sql = "select Store_ID, Description\r\n" + 
					"  from bbis_store\r\n" + 
					" where Brand_ID = ?";
			
			//System.out.println("확인");
			
			pstat = conn.prepareStatement(sql);
			
			// ? 에 들어가야 할 내용이 무엇인지 적기 > 파라미터의 변수명과 같아야 함
			pstat.setString(1, Brand_ID);
			// System.out.println("?: " + pstat.toString());
			rs = pstat.executeQuery();
			
			ArrayList<storeDTO> slist = new ArrayList<storeDTO>();
			
			while (rs.next()) {
				
				// System.out.println("while 문 돌리기");
				storeDTO sdto = new storeDTO();
				// System.out.println("상자만들기");

				// System.out.println(rs.getString("Store_ID"));
				sdto.setStore_ID(rs.getString("Store_ID"));
				
				// System.out.println(rs.getString("Description"));
				sdto.setDescription(rs.getString("Description"));
	
				// System.out.println("만든 dto에 넣어지나?");
				slist.add(sdto);
				
			}
			
			rs.close();
            pstat.close();
            conn.close();
			
            return slist;
            
		} catch (Exception e) {
			System.out.println("조인한 스토어를 가져오지 못했습니다.");
			e.getMessage();
		}
		
		return null;
	}

	public storeDTO login(storeDTO dto) {
		try {
			
		}
		
		catch(Exception e) {
			
		}
		return null;
	}

	// 매장 리스트

	/*
	 * public ArrayList<dutyfreeDTO> dutylist() { try {
	 * 
	 * conn = DBUtil.open();
	 * 
	 * // sql문을 'sql'이라는 변수명 안에 담기 String sql =
	 * "select DF_Store_ID, Description, location_code from bbis_dutyfree;";
	 * 
	 * 
	 * stat = conn.createStatement();
	 * 
	 * // 'sql' 변수명을 사용할 꺼야 rs = stat.executeQuery(sql);
	 * 
	 * ArrayList<dutyfreeDTO> dlist = new ArrayList<dutyfreeDTO>();
	 * 
	 * while (rs.next()) {
	 * 
	 * dutyfreeDTO ddto = new dutyfreeDTO();
	 * 
	 * ddto.setDF_Store_ID(rs.getString("DF_Store_ID"));
	 * ddto.setDescription(rs.getString("Description"));
	 * 
	 * dlist.add(ddto); }
	 * 
	 * rs.close(); stat.close(); conn.close();
	 * 
	 * return dlist;
	 * 
	 * } catch (Exception e) { System.out.println("duty_free 오류입니다.");
	 * e.printStackTrace(); } return null; }
	 */

}
