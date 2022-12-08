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

		// 세션변수 생성
		HttpSession session = req.getSession();
		
		// 'store' 이라는 변수 생성해서 파라미터로 받았다.
		String store = req.getParameter("store");
		// System.out.println("변수를 파라미터로 받기");
		
		// DTO에 변수명을 달아서 새로운 상자를 생성
		storeDTO dto = new storeDTO();
		//브랜드가 매니저면 setAttribute의 auth 값에  BrandID를 넣고 아래 코드를 따로 돌지 않고 관리자페이지로 이동 그러나 브랜드 매니저가 아니면 아래 코드만큼 돌고 
		// DTO에 set스토어
		dto.setStore_ID(store);
		
		// DB와 연결되는 DAO
		StoreDAO dao = new StoreDAO();
		
		storeDTO result = dao.login(dto);
		// System.out.println("dao안에서 프로그래밍하기");
		
		if (result != null) {
			
			// 인증티켓 발급
			session.setAttribute("auth", store);
			
			// dutyfree 테이블의 description을 가져온다.
			session.setAttribute("description", result.getDescription());
			
		}
		System.out.println("auth");
		System.out.println(store);
		System.out.println(result.getDescription());
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/member/loginok.jsp");
		dispatcher.forward(req, resp);

	}

}
