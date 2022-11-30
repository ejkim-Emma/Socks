package com.socks.dutyfree;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.socks.dto.dutyfreeDTO;
import com.socks.dto.orderDTO;
import com.socks.dto.qtyDTO;
import com.socks.order.OrderDAO;

@WebServlet("/dutyfree/list.do")
public class List extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// 1. 세션을 가져오기
		HttpSession session = req.getSession();

		req.getParameter("UTF-8");
		
		String id = (String)session.getAttribute("auth");
		System.out.println("session을 아이디로 가져오기: " + id);
		String period = req.getParameter("period");

		if ((period == null) || (period == "")) {
			
		} else {
			HashMap<String, String> per = new HashMap<String, String>();
			per.put("store_id", id);
			per.put("due_id", period);
			
			System.out.println(per);
			
			DutyfreeDAO dao = new DutyfreeDAO();
			
			ArrayList<qtyDTO> qty = new ArrayList<qtyDTO>();
			
			qty = dao.list(per);
			
			req.setAttribute("qty", qty);
		}
		DutyfreeDAO dao = new DutyfreeDAO();
		
		ArrayList<dutyfreeDTO> list = dao.plist();
		
		req.setAttribute("list", list);
		
		
		// 월분 리스트 가져오기
		ArrayList<dutyfreeDTO> plist = dao.plist();
		
		req.setAttribute("plist", plist);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/dutyfree/list.jsp");
		dispatcher.forward(req, resp);

	}

}
