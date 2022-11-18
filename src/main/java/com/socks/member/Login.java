package com.socks.member;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.socks.dto.brandDTO;
import com.socks.dto.dutyfreeDTO;
import com.test.toy.member.MemberDAO;
import com.test.toy.member.MemberDTO;
import com.test.toy.member.String;

@WebServlet("/member/Login.do")
public class Login extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// brand
		brandDTO bdto = new brandDTO();

		// dutyfree
		dutyfreeDTO ddto = new dutyfreeDTO();

		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/member/Login.jsp");
		dispatcher.forward(req, resp);

	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		HttpSession session = req.getSession();

		// 1.
		String id = req.getParameter("id");
		String pw = req.getParameter("pw");

		// 2.
		MemberDTO dto = new MemberDTO();

		dto.setId(id);
		dto.setPw(pw);

		MemberDAO dao = new MemberDAO();

		MemberDTO result = dao.login(dto);

		// 3.
		if (result != null) {

			// 인증 티켓 발급
			session.setAttribute("auth", id);

			session.setAttribute("name", result.getName());
			session.setAttribute("lv", result.getLv());

		}

		// 4.
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/member/loginok.jsp");
		dispatcher.forward(req, resp);
	}
}