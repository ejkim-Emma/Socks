package com.socks.product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.socks.DBUtil;
import com.socks.dto.brandDTO;
import com.socks.dto.productDTO;

public class ProductDAO {

	private Connection conn;
	private Statement stat;
	private PreparedStatement pstat;
	private ResultSet rs;

	// 디비 사용할 수 있도록 클래스 불러오기
	public ProductDAO() {
		conn = DBUtil.open();
	}

	// List.java > 목록
	public ArrayList<productDTO> list() {

		try {

			// 디비 열기
			conn = DBUtil.open();
			System.out.println("디비 열기");

			// sql문 만들기
			String sql = "select * from bbsupply_product";
			System.out.println("sql문 작성");

			// sql 문 쓸거예요
			stat = conn.createStatement();
			System.out.println("sql 명령어 사용");

			// 커서는 sql에서 움직일 거예요.
			// excuteQuery: select 할 때 사용
			rs = stat.executeQuery(sql);
			System.out.println("sql문에 커서 사용");

			// 상품리스트를 담을 상자를 plist로 선언 > 상자들 자체를 배열로 만듬
			ArrayList<productDTO> plist = new ArrayList<productDTO>();
			System.out.println("상자들을 배열로 만들기");

			// 커서 while 문을 통해 돌기
			while (rs.next()) {

				// System.out.println("커서 돌리기");
				// 데이터 담을 상자를 만들게요
				productDTO pdto = new productDTO();

				// sql에서 받은 아이들을 스트링으로 형변환하면서 커서를 움직일 게요. > 그러면서 상자에 담을게요
				pdto.setProduct_name(rs.getString("product_name"));
				pdto.setProduct_code(rs.getString("product_code"));
				pdto.setProduct_size(rs.getString("product_size"));
				pdto.setProduct_color(rs.getString("product_color"));
				pdto.setUnit_price(rs.getString("unit_price"));

				// pdto 를 plist에 추가 할게요
				plist.add(pdto);

			}

			// 커서 닫기
			rs.close();
			// sql문 사용 명령어 닫기
			stat.close();
			// 디비 연동 닫기
			conn.close();

			return plist;

		} catch (Exception e) {
			System.out.println("상품명 리스트 담기가 오류입니다. > ArrayList<productDTO> list() 찾아가기");
			e.printStackTrace();
		}
		return null;
	}

	// 검색창의 상품명만 가져오는 것
	public ArrayList<productDTO> nlist() {

		try {

			conn = DBUtil.open();

			String sql = "select distinct product_name from bbsupply_product";

			stat = conn.createStatement();

			rs = stat.executeQuery(sql);

			ArrayList<productDTO> nlist = new ArrayList<productDTO>();

			while (rs.next()) {

				// System.out.println("while 출력");
				productDTO pdto = new productDTO();

				pdto.setProduct_name(rs.getString("product_name"));

				nlist.add(pdto);

			}

			rs.close();
			stat.close();
			conn.close();

			// 1번
			return nlist;

		} catch (Exception e) {
			System.out.println("상품명이 담기지 않았습니다.");
			e.printStackTrace();

		}

		return null;

	}

	// 컬러가 담기지 않았습니다.
	public ArrayList<productDTO> slist() {

		try {

			conn = DBUtil.open();

			String sql = "select distinct product_size from bbsupply_product";

			stat = conn.createStatement();

			rs = stat.executeQuery(sql);

			ArrayList<productDTO> slist = new ArrayList<productDTO>();

			while (rs.next()) {

				productDTO sdto = new productDTO();

				sdto.setProduct_size(rs.getString("product_size"));

				slist.add(sdto);
			}

			rs.close();
			stat.close();
			conn.close();

			return slist;

		} catch (Exception e) {
			System.out.println("사이즈가 담기지 않았습니다.");
			e.printStackTrace();
		}

		return null;
	}

	public ArrayList<productDTO> clist() {
		
		try {

			conn = DBUtil.open();

			String sql = "select distinct product_color from bbsupply_product";

			stat = conn.createStatement();

			rs = stat.executeQuery(sql);

			ArrayList<productDTO> clist = new ArrayList<productDTO>();

			while (rs.next()) {

				productDTO cdto = new productDTO();

				cdto.setProduct_color(rs.getString("product_color"));
				
				clist.add(cdto);
			}

			rs.close();
			stat.close();
			conn.close();

			return clist;

		} catch (Exception e) {
			System.out.println("컬러가 담기지 않았습니다.");
			e.printStackTrace();
		}
		
		return null;
	}
	
	// 확인하는 방법
	//public static void main(String[] args) {
		
		//ProductDAO dao = new ProductDAO();
		
		//ArrayList<productDTO> clist = dao.clist();
		
		//System.out.println(clist);
		
	//}

}
