package com.socks.member;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.socks.dto.storeDTO;

@WebServlet("/member/loginok.do")
public class LoginOk extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// 세션 생성
		HttpSession session = req.getSession();
		
		// 'store' 이라는 변수 생성해서 파라미터로 받았다.
		String store = req.getParameter("store");
		
		// DTO에 변수명을 달아서 새로운 상자를 생성
		storeDTO dto = new storeDTO();
		
		// DTO에 set스토어
		dto.setStore_ID(Store_ID);
		
		// DB와 연결되는 DAO
		StoreDAO dao = new StoreDAO();
		
		storeDTO result = dao.login(dto);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/member/loginok.jsp");
		dispatcher.forward(req, resp);

	}

}
