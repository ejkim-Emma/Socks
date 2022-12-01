package com.socks.period;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.socks.dto.orderdueDTO;
import com.socks.dto.periodDTO;

import sun.dc.pr.PRError;

@WebServlet("/period/list.do")
public class List extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// 1. 세션을 가져오기
		HttpSession session = req.getSession();
		System.out.println(session);

		// (1) 목록리스트 조회하기
		// 2. ProductDAO 안에서 불러온 데이터를 가공하기 위해 ProductDAO를 'dao'로 변수를 선언한다.
		PeriodDAO dao = new PeriodDAO();

		// ArrayList<productDTO>를 'list'라고 메소드 붙이기
		ArrayList<orderdueDTO> list = dao.list();

		// jsp에 ArrayList<productDTO>를 통째로 넘기기 위해 setAttribute 사용
		req.setAttribute("list", list);
		System.out.println("상자 이름 짓기");

		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/period/list.jsp");
		dispatcher.forward(req, resp);

	}

}
