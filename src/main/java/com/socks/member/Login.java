package com.socks.member;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.socks.dto.brandDTO;
import com.socks.dto.dutyfreeDTO;

@WebServlet("/memeber/login.do")
public class Login extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// 셀렉트 박스 목록 띄우기
		
		// 브랜드 상자
		// brandDTO bdto = new brandDTO();
		// 매장 상자
		// dutyfreeDTO ddto = new dutyfreeDTO();
		
		// 브랜드와 매장 관련 DAO
		StoreDAO dao = new StoreDAO();
		
		// 브랜드 상자 배열
		ArrayList<brandDTO> brandlist = new ArrayList<brandDTO>();
		
		// 매장 상자 배열
		// ArrayList<dutyfreeDTO> dutylist = new ArrayList<dutyfreeDTO>();
		
		
		// brandlist = dao.brandlist();
		
		// sdutylist = dao.dutylist();
		
		req.setAttribute("brandlist", brandlist);
		// req.setAttribute("dutylist", dutylist);
		
		
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/member/login.jsp");
		dispatcher.forward(req, resp);

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// Post로 보내서 인코딩
		req.setCharacterEncoding("UTF-8");
		
		HttpSession session = req.getSession();
		
		String Store_ID = req.getParameter(""
				+ ""
				+ ""
				+ ""
				+ "")
		
		// Brand_ID 와 DF_Store_ID 받아오기
		String Brand = req.getParameter("Brand");
		String DF_Store = req.getParameter("DF_Store");
		
		// 포장하는 곳 꺼내서 포장하기
		brandDTO bdto = new brandDTO();
		dutyfreeDTO ddto = new dutyfreeDTO();
		bdto.setBrand_code(Brand);
		ddto.setDF_Store_ID(DF_Store);
		
		
		
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/member/login.jsp");
		dispatcher.forward(req, resp);

	}
}
