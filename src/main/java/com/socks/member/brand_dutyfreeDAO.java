package com.socks.member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.project.care.main.user.mypage.animal.String;
import com.socks.DBUtil;
import com.socks.dto.brandDTO;

public class brand_dutyfreeDAO {
	
	private Connection conn;
	private Statement stat;
	private PreparedStatement pstat;
	private ResultSet rs;
	
	public brand_dutyfreeDAO() {
		conn = DBUtil.open();
	}
	
	public ArrayList<brandDTO> brand() {
		try {
			conn = DBUtil.open();
			
			String sql = "select Brand_ID, Description, Brand_code\r\n" + 
						 "from bbis_brand";
			
			stat = conn.createStatement();
			
			rs = stat.executeQuery(sql);
			
			ArrayList<brandDTO> brandlist = new ArrayList<brandDTO>();
		
			while (rs.next()) {
				brandDTO bdto = new brandDTO();
				
				bdto.setBrand_ID(rs.getString("Brand_ID"));
				bdto.setDescription(rs.getString("Description"));
				
				brandlist.add(bdto);
				
			}
			
			rs.close();
            stat.close();
            conn.close();
            
            return brandlist;
		}
		catch (Exception e)
		{
			System.out.println("brand_dutyfreeDAO.brand");
			e.printStackTrace();
		}
		
		return null;
		
	}
}
