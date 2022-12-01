package com.socks.order;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.socks.dto.orderDTO;

@WebServlet("/order/editok.do")
public class EditOk extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");

		String store = req.getParameter("store");
		String period = req.getParameter("period");
		String product_code = req.getParameter("product_code");
		String product_qty = req.getParameter("product_qty");
		String order_id = req.getParameter("order_id");
		
		orderDTO dto = new orderDTO();
		
		dto.setStore_id(store);
		dto.setDue_id(period);
		dto.setProduct_code(product_code);
		dto.setProduct_Qty(product_qty);
		dto.setOrder_id(order_id);
		
		System.out.println("상자에 담은것 보여주기: " + dto);
		
		OrderDAO dao = new OrderDAO();
		
		int result = dao.edit(dto);
		
		req.setAttribute("result", result);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/order/editok.jsp");
		dispatcher.forward(req, resp);

	}

}

