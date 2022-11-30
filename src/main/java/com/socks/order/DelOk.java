package com.socks.order;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.socks.dutyfree.DutyfreeDAO;
import com.socks.product.ProductDAO;

@WebServlet("/order/delok.do")
public class DelOk extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");

		HttpSession session = req.getSession();
		// 데이터 가져오기
		String order_id = req.getParameter("order_id");
		System.out.println("받아오는 코드" + order_id);

		// 디비 작업 > dao 위임 > product_code
		OrderDAO dao = new OrderDAO();

		int result = dao.del(order_id);
		System.out.println(result);

		req.setAttribute("result", result);

		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/order/delok.jsp");
		dispatcher.forward(req, resp);

	}

}
