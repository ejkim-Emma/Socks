package com.socks.member;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.socks.dto.brandDTO;

@WebServlet("/member/login.do")
public class Login extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// 셀렉트 박스 목록 띄우기
		
		// 브랜드와 매장 관련 DAO
		
		StoreDAO dao = new StoreDAO();
		// 브랜드 상자 배열
		ArrayList<brandDTO> brandlist = new ArrayList<brandDTO>();
		// StoreDAO에서 만든 리스트를 가져온다.
		brandlist = dao.brandlist();
		// System.out.println("브랜드 리스트 담기");
		
		// set으로 넘겨서 jsp에 뿌릴 수 있도록 하는 것
		req.setAttribute("brandlist", brandlist);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/member/login.jsp"); 
		dispatcher.forward(req, resp);

	}

}

