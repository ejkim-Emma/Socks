package com.socks.order;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.socks.dto.brandDTO;
import com.socks.dto.dutyfreeDTO;
import com.socks.dto.orderDTO;
import com.socks.dto.productDTO;
import com.socks.dutyfree.DutyfreeDAO;
import com.socks.member.StoreDAO;
import com.socks.product.ProductDAO;

@WebServlet("/order/add.do")
public class Add extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// 1. 브랜드 / 매장 가져오기
		// 브랜드와 매장 관련 DAO
		StoreDAO dao = new StoreDAO();
		// 브랜드 상자 배열
		ArrayList<brandDTO> brandlist = new ArrayList<brandDTO>();
		// StoreDAO에서 만든 리스트를 가져온다.
		brandlist = dao.brandlist();
		// System.out.println("브랜드 리스트 담기");
		// set으로 넘겨서 jsp에 뿌릴 수 있도록 하는 것
		req.setAttribute("brandlist", brandlist);

		// 2. 상품 가져오기
		ProductDAO pdao = new ProductDAO();

		// ArrayList<productDTO>를 'list'라고 메소드 붙이기
		ArrayList<productDTO> plist = pdao.plist();

		// jsp에 ArrayList<productDTO>를 통째로 넘기기 위해 setAttribute 사용
		req.setAttribute("plist", plist);

		// 3. 오더 날짜 가져오기
		// 월분 리스트 가져오기
		DutyfreeDAO ddao = new DutyfreeDAO();

		ArrayList<dutyfreeDTO> dlist = ddao.plist();

		req.setAttribute("dlist", dlist);

		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/order/add.jsp");
		dispatcher.forward(req, resp);

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");

		String store = req.getParameter("store");
		String period = req.getParameter("period");
		String product = req.getParameter("product");
		String product_qty = req.getParameter("product_qty");

		dutyfreeDTO dto = new dutyfreeDTO();

		dto.setStore(store);
		dto.setDue_id(period);
		dto.setProduct(product);
		dto.setProduct_Qty(product_qty);

		OrderDAO dao = new OrderDAO();

		int result = dao.add(dto);

		req.setAttribute("result", result);

		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/order/addok.jsp");
		dispatcher.forward(req, resp);
	}

}
