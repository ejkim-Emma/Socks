package com.socks.dutyfree;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
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

			String sql = "select Due_ID, Period, Closing_Date, (case when DATEDIFF(day, GETDATE(),Closing_Date) > 0 then 'Y' else 'N' end) as 'Day'\r\n" + 
					"  from bbsupply_order_due\r\n" + 
					" order by Closing_Date desc";

			stat = conn.createStatement();

			rs = stat.executeQuery(sql);

			ArrayList<dutyfreeDTO> plist = new ArrayList<dutyfreeDTO>();

			while (rs.next()) {

				// System.out.println("while 출력");
				dutyfreeDTO pdto = new dutyfreeDTO();

				pdto.setDue_id(rs.getString("due_id"));
				pdto.setPeriod(rs.getString("period"));
				pdto.setClosing_date(rs.getDate("closing_date"));
				pdto.setDay(rs.getString("day"));

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

			String sql = "select isnull(table1.Due_ID, ?) as Due_ID, isnull(table1.Order_ID,0) as Order_ID, pr.product_code, pr.product_name, pr.product_size, pr.product_color, pr.order_unit, isnull(table1.Product_Qty,0) as Product_Qty, isnull(table1.Store_ID, ?) as Store_ID\r\n" + 
					" from (select odu.Due_ID, od.Order_ID, pr.product_code, pr.product_name, pr.product_size, pr.product_color,pr.order_unit, od.Product_Qty, st.Store_ID\r\n" + 
					"					  from bbsupply_order od  \r\n" + 
					"					  inner join bbis_store st\r\n" + 
					"					    on od.Store_ID = st.Store_ID  \r\n" + 
					"						inner join bbsupply_order_due odu\r\n" + 
					"					    on od.Due_ID = odu.Due_ID   \r\n" + 
					"						inner join bbsupply_product pr\r\n" + 
					"					    on od.Product_Code = pr.product_code  where st.Store_ID = ?\r\n" + 
					"					   and odu.Due_ID = ?) as table1\r\n" + 
					" right outer join bbsupply_product pr\r\n" + 
					"    on table1.product_code = pr.product_code\r\n" + 
					" order by pr.product_code asc";

			pstat = conn.prepareStatement(sql);

			pstat.setString(1, per.get("due_id"));
			pstat.setString(2, per.get("store_id"));
			pstat.setString(3, per.get("store_id"));
			pstat.setString(4, per.get("due_id"));

			rs = pstat.executeQuery();

			ArrayList<qtyDTO> qlist = new ArrayList<qtyDTO>();

			while (rs.next()) {

				qtyDTO dto = new qtyDTO();

				dto.setDue_id(rs.getString("due_id"));
				dto.setOrder_id(Integer.parseInt(rs.getString("order_id")));
				dto.setStore_id(rs.getString("store_id"));

				dto.setProduct_code(rs.getNString("product_code"));
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

	public int edit(qtyDTO dto) {

		/*
		 * for (int i = 0; i < dto.getDueId().length; i++) {
		 * System.out.println("데이터를 넘겨 받는 지" + dto.getDueId()[i]); }
		 */

		// db 관련된 것들을 초기화 하지 않는다.
		// conn = null;
		// pstat = null;

		// 잘 돌았는지 데이터를 남기기 위해 > 잘 돌은 것이 '1' 인지 해놓고 잘 돌았는 지 확인 해보기
		int count[] = new int[dto.getOrderId().length];
		
		int odId = dto.getOrderId()[0];
		System.out.println("아이디값:" + odId);
		
		if ( odId == 0) {
			
			try {

				// System.out.println("안으로 들어왓는지1");
				String sql = "insert into bbsupply_order (Store_ID, Due_ID, Product_Code, Product_Qty, TIMESTAMP) values (?,?,?,?,getdate())";

				// conn.setAutoCommit(false);

				pstat = conn.prepareStatement(sql);

				for (int i = 0; i < dto.getOrderId().length; i++) {

					pstat.setString(1, dto.getStoreId()[i]);
					System.out.println("상품코드 get:" + dto.getStoreId()[i]);

					pstat.setString(2, dto.getDueId()[i]);
					System.out.println("주문아이디 get:" + dto.getDueId()[i]);
					
					pstat.setString(3, dto.getProductCode()[i]);
					System.out.println("주문아이디 get:" + dto.getProductCode()[i]);
					
					pstat.setInt(4, dto.getProductQty()[i]);
					System.out.println("주문아이디 get:" + dto.getProductQty()[i]);

					pstat.addBatch();
					System.out.println("배치가 사용되었나?");
					pstat.clearParameters();

				}

				count = pstat.executeBatch();
				System.out.println("카운트 담았는지?" + count);
				System.out.println("길이" + dto.getOrderId().length + "/" + count.length);

			} catch (Exception e) {

				System.out.println("상품수량 수정에 실패하였습니다.");
				e.printStackTrace();
				return 0;

			} 
			
		} else {
			try {
				
				// System.out.println("안으로 들어왓는지1");
				String sql = "update bbsupply_order set Product_Qty=?, TIMESTAMP = getdate() where Order_ID = ?";

				// conn.setAutoCommit(false);

				pstat = conn.prepareStatement(sql);

				for (int i = 0; i < dto.getOrderId().length; i++) {

					// System.out.println("안으로 들어왓는지2");
					// String sql = "update bbsupply_order set Product_Qty=? where Order_ID = ?";

					// update bbsupply_order set Store_ID=?, Due_ID=?,Product_Code=?, Product_Qty=?
					// where Order_ID = ?
					// pstat = conn.prepareStatement(sql);
					// System.out.println("sql보여주기 : " + sql);

					// pstat.setString(1, dto.getStoreId()[i]);
					// System.out.println("store get:" + dto.getStoreId()[i]);

					// pstat.setString(2, dto.getDueId()[i]);
					// System.out.println("due_id get:" + dto.getDueId()[i]);

					// pstat.setString(3, dto.getProductCode()[i]);
					// System.out.println("상품코드 get:" + dto.getProductCode()[i]);

					pstat.setInt(1, dto.getProductQty()[i]);
					System.out.println("상품수량 get:" + dto.getProductQty()[i]);

					pstat.setInt(2, dto.getOrderId()[i]);
					System.out.println("주문아이디 get:" + dto.getOrderId()[i]);

					pstat.addBatch();
					System.out.println("배치가 사용되었나?");
					pstat.clearParameters();

					// pstat.executeUpdate();

				}

				count = pstat.executeBatch();
				// return pstat;
				// conn.commit();
				System.out.println("카운트 담았는지?" + count);
				System.out.println("길이" + dto.getOrderId().length + "/" + count.length);

			} catch (Exception e) {

				System.out.println("상품수량 수정에 실패하였습니다.");
				e.printStackTrace();
				return 0;

			} finally {

				// boolean result = true;
				/*
				 * for (int i = 0; i < count.length; i++) {
				 * 
				 * System.out.println(i+"결과값:" + count[i]);
				 * 
				 * if (count[i] != -2) { result = false; break; }
				 * 
				 * 
				 * }
				 */

				/*
				 * try { conn.setAutoCommit(true); } catch (Exception e) { e.printStackTrace();
				 * }
				 */

				// 항상 무조건 나오기 때문에 return 값을 줄 수 없음
				// return 1;

			}

		}
		
		
		return 1;
	}

}
