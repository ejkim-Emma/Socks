package com.socks.period;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.socks.dto.periodDTO;

@WebServlet("/period/addok.do")
public class AddOk extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");

		/* String Period = req.getParameter("period"); */
		int Year = Integer.parseInt(req.getParameter("year"));
		int Month = Integer.parseInt(req.getParameter("month"));
		String Sta_Date = req.getParameter("start");
		String Closing_Date = req.getParameter("end");
		
		periodDTO dto = new periodDTO();
		
		/* dto.setPeriod(Period); */
		dto.setYear(Year);
		dto.setMonth(Month);
		dto.setSta_Date(Sta_Date);
		dto.setClosing_Date(Closing_Date);
		
		System.out.println(dto);
		
		PeriodDAO dao = new PeriodDAO();
		
		int result = dao.add(dto);
		
		req.setAttribute("result", result);
		
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/period/addok.jsp");
		dispatcher.forward(req, resp);

	}

}

