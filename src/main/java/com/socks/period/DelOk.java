package com.socks.period;

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

@WebServlet("/period/delok.do")
public class DelOk extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");

		HttpSession session = req.getSession();
		// 데이터 가져오기
		String due_ID = req.getParameter("due_ID");
		System.out.println("받아오는 코드" + due_ID);

		// 디비 작업 > dao 위임 > product_code
		PeriodDAO dao = new PeriodDAO();

		int result = dao.del(due_ID);
		System.out.println(result);

		req.setAttribute("result", result);

		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/period/delok.jsp");
		dispatcher.forward(req, resp);

	}

}
