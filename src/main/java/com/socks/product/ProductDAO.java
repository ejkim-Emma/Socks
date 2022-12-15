package com.socks.product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import com.socks.DBUtil;
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
	public ArrayList<productDTO> list(HashMap<String, String> map) {

		try {

			// 검색 관련

			// 일단 검색을 하려면 sql문에 where절이 붙어야 하기 때문에 where를 변수명으로 지정
			// String where = "";
			// System.out.println("where절 만들거예요.");

//			if (map.get("isSearch").equals("y")) {
//				where = String.format("where product_name like '%%%s%%'"
//										,map.get("product_name"));
//			}

			String sql = "select *\r\n" + "  from bbsupply_product\r\n" + " where (? is null or product_name = ?)\r\n"
					+ "   and (? is null or product_size = ?)\r\n"
					+ "   and (? is null or product_color = ?) order by product_name asc";

//			System.out.println("mapping" + map.get("product_name"));

			// sql문 만들기
//			String sql = "select * from bbsupply_product";
//			System.out.println("sql문 작성");

			// sql 문 쓸거예요
//			stat = conn.createStatement();
			pstat = conn.prepareStatement(sql);
//			System.out.println("sql 명령어 사용");

			pstat.setString(1, map.get("product_name"));
			pstat.setString(2, map.get("product_name"));
			pstat.setString(3, map.get("product_size"));
			pstat.setString(4, map.get("product_size"));
			pstat.setString(5, map.get("product_color"));
			pstat.setString(6, map.get("product_color"));
			// System.out.println("sql 쿼리" + pstat.toString());

			// 커서는 sql에서 움직일 거예요.
			// excuteQuery: select 할 때 사용
			rs = pstat.executeQuery();
//			System.out.println("sql문에 커서 사용");

			// 상품리스트를 담을 상자를 plist로 선언 > 상자들 자체를 배열로 만듬
			ArrayList<productDTO> plist = new ArrayList<productDTO>();
//			System.out.println("상자들을 배열로 만들기");

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
				pdto.setOrder_unit(rs.getInt("order_unit"));

				// pdto 를 plist에 추가 할게요
				plist.add(pdto);

			}
			// 커서 닫기
			rs.close();
			// sql문 사용 명령어 닫기
			pstat.close();
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

	// 상품명 등록
	public int add(productDTO dto) {
		try {

			String sql = "insert into bbsupply_product (product_name, product_size, product_code, product_color, unit_price, order_unit) values (?, ?, ?, ?, ?, ?)";

			pstat = conn.prepareStatement(sql);

			pstat.setString(1, dto.getProduct_name());
			pstat.setString(2, dto.getProduct_size());
			pstat.setString(3, dto.getProduct_code());
			pstat.setString(4, dto.getProduct_color());
			pstat.setString(5, dto.getUnit_price());
			pstat.setInt(6, dto.getOrder_unit());

			return pstat.executeUpdate();

		} catch (Exception e) {

			System.out.println("상품목록 등록에 실패하였습니다.");
			e.printStackTrace();
		}
		return 0;
	}

	// DelOk.java
	public int del(String product_code) {

		try {

			conn = DBUtil.open();

			String sql = "delete from bbsupply_product where product_code = ?";

			pstat = conn.prepareStatement(sql);

			System.out.println(sql);

			pstat.setString(1, product_code);
			System.out.println("코드:" + product_code);

			return pstat.executeUpdate();

		} catch (Exception e) {
			System.out.println("상품 삭제 실패!");
			e.printStackTrace();
		}

		return 0;
	}

	public int edit(productDTO dto) {
		try {

			String sql = "update bbsupply_product set product_name = ?, product_size = ?, product_color = ?, unit_price = ? where product_code = ?";

			pstat = conn.prepareStatement(sql);

			pstat.setString(1, dto.getProduct_name());
			pstat.setString(2, dto.getProduct_size());
			pstat.setString(3, dto.getProduct_color());
			pstat.setString(4, dto.getUnit_price());

			pstat.setString(5, dto.getProduct_code());

			return pstat.executeUpdate();

		} catch (Exception e) {

			System.out.println("상품목록 수정에 실패하였습니다.");
			e.printStackTrace();
		}
		return 0;
	}

	public ArrayList<productDTO> plist() {

		try {

			conn = DBUtil.open();

			String sql = "select product_code, product_name, product_size, product_color\r\n"
					+ "  from bbsupply_product";

			stat = conn.createStatement();

			rs = stat.executeQuery(sql);

			ArrayList<productDTO> clist = new ArrayList<productDTO>();

			while (rs.next()) {

				productDTO cdto = new productDTO();

				cdto.setProduct_code(rs.getString("product_code"));
				cdto.setProduct_name(rs.getString("product_name"));
				cdto.setProduct_size(rs.getString("product_size"));
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

	public int save(productDTO dto) {

		// for (int i = 0; i < dto.getProductCode().length; i++) {
			// System.out.println("데이터 넘어왔니?" + dto.getProductName()[i]);
		// }

		// String pCode = dto.getSelectCode()[0];
		// System.out.println("코드: " + pCode);

		if (dto.getSelectCode() != null) {

			try {

				int count[] = new int[dto.getSelectCode().length];

				String sql = "delete from bbsupply_product where product_code = ?";

				pstat = conn.prepareStatement(sql);

				for (int i = 0; i < dto.getSelectCode().length; i++) {
					pstat.setString(1, dto.getSelectCode()[i]);
					System.out.println("상품코드 get: " + dto.getSelectCode()[i]);

					pstat.addBatch();
					pstat.clearParameters();
				}

				count = pstat.executeBatch();
				System.out.println("카운트 담았니?" + count);
				// System.out.println("길이: " + dto.getSelectCode().length + "/" + count.length);

				int updateCount[] = new int[dto.getProductCode().length];

				String sql2 = "update bbsupply_product set product_name = ?, product_size = ?, product_color = ?, unit_price = ?, order_unit = ? where product_code = ?";

				pstat = conn.prepareStatement(sql2);

				// System.out.println("updateCount의 길이는?" + updateCount.length);

				for (int j = 0; j < updateCount.length; j++) {
					System.out.println("반복문 도는 거니?" + j);

					// 상품명
					pstat.setString(1, dto.getProductName()[j]);
					// 사이즈
					pstat.setString(2, dto.getProductSize()[j]);
					// 컬러
					pstat.setString(3, dto.getProductColor()[j]);
					// 단가
					pstat.setInt(4, dto.getProductPrice()[j]);
					// 항목 당 수량
					pstat.setInt(5, dto.getProductQty()[j]);
					// 상품코드
					pstat.setString(6, dto.getProductCode()[j]);

					pstat.addBatch();
					pstat.clearParameters();

				}

				updateCount = pstat.executeBatch();
				System.out.println("업데이트 몇 개?" + updateCount);
				// System.out.println("업데이트 길이?" + updateCount.length);

			} catch (Exception e) {

				System.out.println("상품 코드 삭제에 실패하였습니다.");
				e.printStackTrace();
				return 0;
			}

		} else if (dto.getSelectCode() == null) {
			try {

				int updateCount[] = new int[dto.getProductCode().length];

				String sql2 = "update bbsupply_product set product_name = ?, product_size = ?, product_color = ?, unit_price = ?, order_unit = ? where product_code = ?";

				pstat = conn.prepareStatement(sql2);

				// System.out.println("updateCount의 길이는?" + updateCount.length);

				for (int j = 0; j < updateCount.length; j++) {
					// System.out.println("반복문 도는 거니?" + j);

					// 상품명
					pstat.setString(1, dto.getProductName()[j]);
					// System.out.println("이름" + dto.getProductName()[j]);
					// 사이즈
					pstat.setString(2, dto.getProductSize()[j]);
					// System.out.println("이름" + dto.getProductSize()[j]);
					// 컬러
					pstat.setString(3, dto.getProductColor()[j]);
					// System.out.println("이름" + dto.getProductColor()[j]);
					// 단가
					pstat.setInt(4, dto.getProductPrice()[j]);
					System.out.println("이름" + dto.getProductPrice()[j]);
					// 항목 당 수량
					pstat.setInt(5, dto.getProductQty()[j]);
					// System.out.println("이름" + dto.getProductQty()[j]);
					// 상품코드
					pstat.setString(6, dto.getProductCode()[j]);
					// System.out.println("이름" + dto.getProductCode()[j]);

					pstat.addBatch();
					pstat.clearParameters();

				}

				updateCount = pstat.executeBatch();
				System.out.println("업데이트 몇 개?" + updateCount);
				System.out.println("업데이트 길이?" + updateCount.length);
			} catch (Exception e) {
				System.out.println("상품 코드 삭제에 실패하였습니다.");
				e.printStackTrace();
				return 0;
			}
		}

		return 1;
	}

	// 확인하는 방법
	// public static void main(String[] args) {

	// ProductDAO dao = new ProductDAO();

	// ArrayList<productDTO> clist = dao.clist();

	// System.out.println(clist);

	// }

}
