package com.socks.member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.socks.DBUtil;
import com.socks.dto.brandDTO;
import com.socks.dto.dutyfreeDTO;

public class StoreDAO {

	private Connection conn;
	private Statement stat;
	private PreparedStatement pstat;
	private ResultSet rs;


	// 브랜드 리스트
	public ArrayList<brandDTO> brandlist() {
		
		try {

			String sql = "select Brand_ID, Description, Brand_code from bbis_brand";

			stat = conn.createStatement();

			rs = stat.executeQuery(sql);

			ArrayList<brandDTO> blist = new ArrayList<brandDTO>();

			while (rs.next()) {

				brandDTO bdto = new brandDTO();

				bdto.setBrand_ID(rs.getString("Brand_ID"));
				bdto.setDescription(rs.getString("Description"));

				blist.add(bdto);
			}

			rs.close();
			stat.close();
			conn.close();

			return blist;

		} catch (Exception e) {
			System.out.println(" brand 오류입니다.");
			e.printStackTrace();
		}
		return null;
	}

	// 매장 리스트

	public ArrayList<dutyfreeDTO> dutylist() {
		try {

			String sql = "select DF_Store_ID, Description, location_code from bbis_dutyfree;";

			stat = conn.createStatement();

			rs = stat.executeQuery(sql);

			ArrayList<dutyfreeDTO> dlist = new ArrayList<dutyfreeDTO>();

			while (rs.next()) {

				dutyfreeDTO ddto = new dutyfreeDTO();

				ddto.setDF_Store_ID("DF_Store_ID");
				ddto.setDescription("Description");

				dlist.add(ddto);
			}

			rs.close();
			stat.close();
			conn.close();

			return dlist;

		} catch (Exception e) {
			System.out.println("duty_free 오류입니다.");
			e.printStackTrace();
		}
		return null;
	}

}
