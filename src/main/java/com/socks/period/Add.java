package com.socks.period;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.socks.dto.dateDTO;
import com.socks.dto.orderdueDTO;
import com.socks.order.OrderDAO;

@WebServlet("/period/add.do")
public class Add extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		PeriodDAO pdao = new PeriodDAO();

		// 년 가져오기
		ArrayList<dateDTO> year = new ArrayList<dateDTO>();
		year = pdao.date();
		req.setAttribute("year", year);
		
		
		OrderDAO dao = new OrderDAO();
		// 월 가져오기
		ArrayList<orderdueDTO> month = dao.mlist();
		req.setAttribute("month", month);

		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/period/add.jsp");
		dispatcher.forward(req, resp);

	}

}
