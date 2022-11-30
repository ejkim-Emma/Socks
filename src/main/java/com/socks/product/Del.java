package com.socks.product;


import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/product/del.do")
public class Del extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// 파라미터(변수명)를 가져오는데 이를 String으로 받고 product_code(노란글씨)로 받는 다.
		String product_code =  req.getParameter("product_code");
		System.out.println(product_code);
		
		
		req.setAttribute("product_code", product_code);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/product/del.jsp");
		dispatcher.forward(req, resp);

	}

}

