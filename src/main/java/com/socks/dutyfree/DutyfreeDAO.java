package com.socks.dutyfree;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import com.socks.DBUtil;
import com.socks.dto.dutyfreeDTO;
import com.socks.dto.orderDTO;
import com.socks.dto.productDTO;
import com.socks.dto.qtyDTO;
import com.sun.javafx.collections.MappingChange.Map;

public class DutyfreeDAO {

	private Connection conn;
	private Statement stat;
	private PreparedStatement pstat;
	private ResultSet rs;

	public DutyfreeDAO() {
		conn = DBUtil.open();
	}

	public ArrayList<dutyfreeDTO> plist() {
		try {

			conn = DBUtil.open();

			String sql = "select Due_ID, Period\r\n" + "   from bbsupply_order_due";

			stat = conn.createStatement();

			rs = stat.executeQuery(sql);

			ArrayList<dutyfreeDTO> plist = new ArrayList<dutyfreeDTO>();

			while (rs.next()) {

				// System.out.println("while 출력");
				dutyfreeDTO pdto = new dutyfreeDTO();

				pdto.setDue_id(rs.getString("due_id"));
				pdto.setPeriod(rs.getString("period"));

				plist.add(pdto);

			}

			rs.close();
			stat.close();
			conn.close();

			// 1번
			return plist;

		} catch (Exception e) {
			System.out.println("기간이 담기지 않았습니다.");
			e.printStackTrace();

		}

		return null;
	}

	public ArrayList<qtyDTO> list(HashMap<String, String> per) {
		try {

			String sql = "select od.Order_ID, pr.product_name, pr.product_size, pr.product_color,pr.order_unit, od.Product_Qty, st.Store_ID\r\n"
					+ "  from bbsupply_order od\r\n" + " inner join bbis_store st\r\n"
					+ "    on od.Store_ID = st.Store_ID\r\n" + " inner join bbsupply_order_due odu\r\n"
					+ "    on od.Due_ID = odu.Due_ID\r\n" + "  inner join bbsupply_product pr\r\n"
					+ "    on od.Product_Code = pr.product_code\r\n" + " where st.Store_ID = ?\r\n"
					+ "   and odu.Due_ID = ?";

			pstat = conn.prepareStatement(sql);

			pstat.setString(1, per.get("store_id"));
			pstat.setString(2, per.get("due_id"));

			rs = pstat.executeQuery();

			ArrayList<qtyDTO> qlist = new ArrayList<qtyDTO>();

			while (rs.next()) {

				qtyDTO dto = new qtyDTO();

				dto.setOrder_id(Integer.parseInt(rs.getString("order_id")));
				
				dto.setProduct_name(rs.getString("product_name"));
				
				dto.setProduct_size(rs.getString("product_size"));
				dto.setProduct_qty(rs.getString("product_qty"));
				dto.setProduct_color(rs.getString("product_color"));
				dto.setOrder_unit(Integer.parseInt(rs.getString("order_unit")));
				
				qlist.add(dto);

			}

			rs.close();
			pstat.close();
			conn.close();

			return qlist;

		} catch (Exception e) {
			System.out.println("매장의 신청목록을 불러올 수 없다.");
			e.printStackTrace();
		}
		return null;
	}

	

}
