package com.socks.product;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/product/delok.do")
public class DelOk extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		
		HttpSession session = req.getSession();
		// 데이터 가져오기
		String product_code = req.getParameter("product_code");
		System.out.println("받아오는 코드" + product_code);
		
		// 디비 작업 > dao 위임 > product_code
		ProductDAO dao = new ProductDAO();
		
		int result = dao.del(product_code);
		System.out.println(result);
		
		req.setAttribute("result", result);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/product/delok.jsp");
		dispatcher.forward(req, resp);

	}

}
