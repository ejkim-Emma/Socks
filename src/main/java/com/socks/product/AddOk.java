package com.socks.product;


import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.socks.dto.productDTO;

@WebServlet("/product/addok.do")
public class AddOk extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		
		String code = req.getParameter("code");
		String name = req.getParameter("name");
		String size = req.getParameter("size");
		String color = req.getParameter("color");
		String price = req.getParameter("price");
		
		productDTO dto = new productDTO();
		
		dto.setProduct_code(code);
		dto.setProduct_name(name);
		dto.setProduct_color(color);
		dto.setProduct_size(size);
		dto.setUnit_price(price);
		
		ProductDAO dao = new ProductDAO();
		
		int result = dao.add(dto);
		
		req.setAttribute("result", result);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/product/addok.jsp");
		dispatcher.forward(req, resp);

	}

}

