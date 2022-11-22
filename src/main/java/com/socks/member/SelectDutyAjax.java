package com.socks.member;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.socks.dto.storeDTO;

@WebServlet("/ajax/store.do")
public class SelectDutyAjax extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		
		// System.out.println("ajax 안에 들어오기");
		String Brand_ID = req.getParameter("Brand_ID");

		System.out.println("내가 고른 브랜드 아이디: " + Brand_ID);

		StoreDAO dao = new StoreDAO();

		ArrayList<storeDTO> storelist = new ArrayList<storeDTO>();

		storelist = dao.store(Brand_ID);

		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json");
		
		
		PrintWriter writer = resp.getWriter();

		int i = 0;

		writer.print("[");

		//System.out.println("for문 들어가기 전");
		// System.out.println(storelist);
		for (storeDTO item : storelist) {
			writer.print("{");
			writer.printf("\"Store_ID\": \"%s\",", item.getStore_ID());
			writer.printf("\"Description\": \"%s\"", item.getDescription());
			writer.print("}");

			//System.out.println(item.getDescription());
			if (i < storelist.size() - 1) {
				writer.print(",");
			}

			i++;
		}

		writer.print("]");

		writer.close();

	}

}
