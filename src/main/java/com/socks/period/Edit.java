package com.socks.period;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.socks.dto.dateDTO;
import com.socks.dto.orderdueDTO;
import com.socks.order.OrderDAO;

@WebServlet("/period/edit.do")
public class Edit extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		PeriodDAO pdao = new PeriodDAO();

		// 년 가져오기
		ArrayList<dateDTO> ylist = new ArrayList<dateDTO>();
		ylist = pdao.date();
		req.setAttribute("ylist", ylist);

		OrderDAO dao = new OrderDAO();
		// 월 가져오기
		ArrayList<orderdueDTO> mlist = dao.mlist();
		req.setAttribute("mlist", mlist);
		
		
		String due_id = req.getParameter("due_ID");
		String year = req.getParameter("year");
		String month = req.getParameter("month");
		String Sta_Date = req.getParameter("start");
		String Closing_Date = req.getParameter("end");

		HashMap<String, String> edit = new HashMap<String, String>();

		edit.put("due_id", due_id);
		edit.put("year", year);
		edit.put("month", month);
		edit.put("sta_Date", Sta_Date);
		edit.put("closing_Date", Closing_Date);

		req.setAttribute("edit", edit);

		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/period/edit.jsp");
		dispatcher.forward(req, resp);

	}

}
