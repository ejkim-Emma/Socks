package com.socks.product;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/product/edit.do")
public class Edit extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// Edit.do
		
		/*req.getCharacterEncoding("UTF-8");*/
		
		// 1. 데이터 가져오기
		String pCode = req.getParameter("product_code");
		String pName = req.getParameter("product_name");
		// System.out.println("상품명입니다." + pName);
		String pSize = req.getParameter("product_size");
		String pColor = req.getParameter("product_color");
		String pPrice = req.getParameter("unit_price");
		
		// 2. DB 작업 > DAO 위임 > select
		// ProductDAO dao = new ProductDAO();
		
		// 3. JSP 호출하기 + 결과 진단하기
		// productDTO dto = dao.list(map);
		
		HashMap<String, String> edit = new HashMap<String, String>();
		
		edit.put("product_code", pCode);
		edit.put("product_name", pName);
		edit.put("product_size", pSize);
		edit.put("product_color", pColor);
		edit.put("unit_price", pPrice);
		// System.out.println("해시맵에 담겼는지?"+edit);
		
		
		// 4. request에 담아서 jsp로 넘기기
		req.setAttribute("edit", edit);
		
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/product/edit.jsp");
		dispatcher.forward(req, resp);

	}

}
