package com.socks.period;


import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.socks.dto.periodDTO;

@WebServlet("/period/editok.do")
public class EditOk extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		
		int year = Integer.parseInt(req.getParameter("year"));
		int month = Integer.parseInt(req.getParameter("month"));
		String start = req.getParameter("start");
		String end = req.getParameter("end");
		String due_id = req.getParameter("due_ID");
		
		periodDTO dto = new periodDTO();
		
		dto.setYear(year);
		dto.setMonth(month);
		dto.setSta_Date(start);
		dto.setClosing_Date(end);
		dto.setDue_ID(due_id);
		
		PeriodDAO dao = new PeriodDAO();
		
		int result = dao.edit(dto);
		
		req.setAttribute("result", result);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/period/editok.jsp");
		dispatcher.forward(req, resp);

	}

}

