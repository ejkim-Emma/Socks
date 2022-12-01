package com.socks.order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import com.socks.DBUtil;
import com.socks.dto.brandDTO;
import com.socks.dto.dateDTO;
import com.socks.dto.duty_freeDTO;
import com.socks.dto.dutyfreeDTO;
import com.socks.dto.orderDTO;
import com.socks.dto.orderdueDTO;
import com.socks.dto.productDTO;

public class OrderDAO {

	private Connection conn;
	private Statement stat;
	private PreparedStatement pstat;
	private ResultSet rs;

	// 디비 사용할 수 있도록 클래스 불러오기
	public OrderDAO() {
		conn = DBUtil.open();
	}

	public ArrayList<orderDTO> list(HashMap<String, String> map) {
		try {

			String sql = "select od.Order_ID as order_id, od.product_code as Product_code, od.Due_ID as due_id, od.Store_ID as store_id, odu.Period as period, br.Description as BrDescription, du.Description as DuDescription, pr.product_name as Product_name, pr.product_size as Product_size, pr.product_color as Product_color, od.Product_Qty"
					+ "  from bbis_store st\r\n" + " inner join bbis_brand br\r\n"
					+ "    on st.Brand_ID = br.Brand_ID\r\n" + " inner join bbis_dutyfree du\r\n"
					+ "    on st.DF_Store_ID = du.DF_Store_ID\r\n" + " inner join bbsupply_order od\r\n"
					+ "    on st.Store_ID = od.Store_ID\r\n" + " inner join bbsupply_product pr\r\n"
					+ "    on od.Product_Code = pr.product_code\r\n" + " inner join bbsupply_order_due odu\r\n"
					+ "    on od.Due_ID =  odu.Due_ID\r\n" + " where (? is null or odu.Year = ?)\r\n"
					+ "   and (? is null or odu.MONTH = ?)\r\n" + "   and (? is null or br.Description = ?)\r\n"
					+ "   and (? is null or du.Description = ?)\r\n" + "   and (? is null or product_name = ?)\r\n"
					+ "   and (? is null or product_size = ?)\r\n" + "   and (? is null or product_color = ?)";

			pstat = conn.prepareStatement(sql);

			pstat.setString(1, map.get("year"));
			pstat.setString(2, map.get("year"));
			pstat.setString(3, map.get("month"));
			pstat.setString(4, map.get("month"));

			pstat.setString(5, map.get("BrDescription"));
			pstat.setString(6, map.get("BrDescription"));
			pstat.setString(7, map.get("DuDescription"));
			pstat.setString(8, map.get("DuDescription"));

			pstat.setString(9, map.get("Product_name"));
			pstat.setString(10, map.get("Product_name"));
			pstat.setString(11, map.get("Product_size"));
			pstat.setString(12, map.get("Product_size"));
			pstat.setString(13, map.get("Product_color"));
			pstat.setString(14, map.get("Product_color"));

			rs = pstat.executeQuery();

			ArrayList<orderDTO> orlist = new ArrayList<orderDTO>();

			while (rs.next()) {

				orderDTO ordto = new orderDTO();

				ordto.setBrDescription(rs.getString("BrDescription"));
				ordto.setDuDescription(rs.getString("DuDescription"));
				
				ordto.setProduct_name(rs.getString("Product_name"));
				ordto.setProduct_size(rs.getString("Product_size"));
				ordto.setProduct_color(rs.getString("Product_color"));
				ordto.setProduct_Qty(rs.getString("Product_Qty"));
				
				ordto.setOrder_id(rs.getString("order_id"));
				ordto.setDue_id(rs.getString("due_id"));
				ordto.setStore_id(rs.getString("store_id"));
				ordto.setProduct_code(rs.getString("Product_code"));
				ordto.setPeriod(rs.getString("period"));

				orlist.add(ordto);

			}

			rs.close();
			pstat.close();
			conn.close();

			return orlist;

		} catch (Exception e) {
			System.out.println("신청매장 목록을 불러올 수 없다.");
			e.printStackTrace();
		}
		return null;
	}

	// 검색된 목록 찾기

	public ArrayList<orderdueDTO> ylist() {
		try {
			conn = DBUtil.open();

			String sql = "select distinct Year\r\n" + "  from bbsupply_order_due";

			stat = conn.createStatement();
			rs = stat.executeQuery(sql);
			ArrayList<orderdueDTO> ylist = new ArrayList<orderdueDTO>();
			while (rs.next()) {
				orderdueDTO cdto = new orderdueDTO();

				cdto.setYear(rs.getString("Year"));
				ylist.add(cdto);
			}

			rs.close();
			stat.close();
			conn.close();

			// 1번
			return ylist;

		} catch (Exception e) {
			System.out.println("년도가 담기지 않았습니다.");
			e.printStackTrace();
		}

		return null;
	}

	public ArrayList<orderdueDTO> mlist() {
		try {
			conn = DBUtil.open();

			String sql = "select distinct MONTH\r\n" + "  from bbsupply_order_due";

			stat = conn.createStatement();
			rs = stat.executeQuery(sql);
			ArrayList<orderdueDTO> mlist = new ArrayList<orderdueDTO>();
			while (rs.next()) {
				orderdueDTO cdto = new orderdueDTO();

				cdto.setMonth(rs.getString("Month"));
				mlist.add(cdto);
			}

			rs.close();
			stat.close();
			conn.close();

			// 1번
			return mlist;

		} catch (Exception e) {
			System.out.println("월이 담기지 않았습니다.");
			e.printStackTrace();
		}

		return null;
	}

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

	public ArrayList<brandDTO> blist() {
		try {

			conn = DBUtil.open();

			String sql = "select Description, Brand_code from bbis_brand";

			stat = conn.createStatement();

			rs = stat.executeQuery(sql);
			// System.out.println("rs");

			ArrayList<brandDTO> blist = new ArrayList<brandDTO>();
			// 리스트에 가져오는 열의 개수를 확인
			// System.out.println("확인" + rs.getRow());

			while (rs.next()) {

				// System.out.println("while 출력");
				brandDTO bdto = new brandDTO();

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

	public ArrayList<duty_freeDTO> dflist() {
		try {

			conn = DBUtil.open();

			String sql = "select Description\r\n" + "  from bbis_dutyfree";

			stat = conn.createStatement();

			rs = stat.executeQuery(sql);
			// System.out.println("rs");

			ArrayList<duty_freeDTO> dflist = new ArrayList<duty_freeDTO>();
			// 리스트에 가져오는 열의 개수를 확인
			// System.out.println("확인" + rs.getRow());

			while (rs.next()) {

				// System.out.println("while 출력");
				duty_freeDTO dfdto = new duty_freeDTO();

				dfdto.setDescription(rs.getString("Description"));

				dflist.add(dfdto);
			}

			rs.close();
			stat.close();
			conn.close();

			// 1번
			return dflist;

		} catch (Exception e) {
			System.out.println("brand 오류입니다.");
			e.printStackTrace();
		}

		// 1번까지 오지 않았을 때 null 값을 넘긴다.
		return null;
	}

	// DelOk.java
	public int del(String order_id) {

		try {

			conn = DBUtil.open();

			String sql = "delete from bbsupply_order where Order_ID = ?";

			pstat = conn.prepareStatement(sql);

			System.out.println(sql);

			pstat.setString(1, order_id);
			System.out.println("코드:" + order_id);

			return pstat.executeUpdate();

		} catch (Exception e) {
			System.out.println("목록 삭제 실패!");
			e.printStackTrace();
		}

		return 0;
	}

	public int add(dutyfreeDTO dto) {

		try {
			
			String sql = "insert into bbsupply_order (Store_ID, Due_ID, Product_Code, Product_Qty) values (?, ?, ?, ?)";

			pstat = conn.prepareStatement(sql);
			
			pstat.setString(1, dto.getStore());
			pstat.setString(2, dto.getDue_id());
			pstat.setString(3, dto.getProduct());
			pstat.setString(4, dto.getProduct_Qty());

			return pstat.executeUpdate();

		} catch (Exception e) {

			System.out.println("수량 등록에 실패하였습니다.");
			e.printStackTrace();
		}
		return 0;
	}

	public int edit(orderDTO dto) {
		
		try {

			System.out.println("orderDAO안에 들어오는 가?");
			String sql = "update bbsupply_order set Store_ID = ?, Due_ID = ?, Product_Code = ?, Product_Qty = ? where Order_ID = ?";

			pstat = conn.prepareStatement(sql);
			System.out.println(sql);

			pstat.setString(1, dto.getStore_id());
			pstat.setString(2, dto.getDue_id());
			pstat.setString(3, dto.getProduct_code());
			pstat.setString(4, dto.getProduct_Qty());
			
			pstat.setString(5, dto.getOrder_id());
			
			return pstat.executeUpdate();

		} catch (Exception e) {

			System.out.println("오더 내역 수정에 실패하였습니다.");
			e.printStackTrace();
		}
		return 0;
	}

}
