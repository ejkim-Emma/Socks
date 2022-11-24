package com.socks;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.socks.dto.storeDTO;
import com.socks.member.StoreDAO;

@WebServlet("/template.do")
public class Template extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// Template.java
		// 1. PK를 알아야하고
		// 2. DB 작업 > DAO 위임 > select > DB작업을 하는 이유는? > session 안에서의 정보만 이용하는 것이 아니기 때문

		// 내가 가져오고 싶은 것은 BBIS_DUTYFREE 테이블에 있는 Store_ID 와 Description

		// 원하는 데이터가 모두 세션안에 들어있기 때문에 HttpSession을 사용
		HttpSession session = req.getSession();
		System.out.println("list 화면의 세션을 발급받나?");

		String Store_ID = (String) session.getAttribute("auth");

		StoreDAO dao = new StoreDAO();
		System.out.println("dao에 대한 새로운 변수 설정을 하나?");

		storeDTO dto = dao.get(Store_ID);
		System.out.println("스토어 dto를 스토어 아이디로 받는 다는 선언을 하는가?");

		req.setAttribute("dto", dto);

		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/template.jsp");
		dispatcher.forward(req, resp);

	}

}
