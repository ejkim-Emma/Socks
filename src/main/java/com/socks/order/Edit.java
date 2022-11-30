package com.socks.order;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.socks.dto.brandDTO;
import com.socks.dto.dutyfreeDTO;
import com.socks.dto.productDTO;
import com.socks.dutyfree.DutyfreeDAO;
import com.socks.member.StoreDAO;
import com.socks.product.ProductDAO;

@WebServlet("/order/edit.do")
public class Edit extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// 숨길 내용
		String order_id = req.getParameter("order_id");
		String due_id = req.getParameter("due_id");
		String product_code = req.getParameter("product_code");
		String product_name = req.getParameter("product_name");
		String product_size = req.getParameter("product_size");
		String product_color = req.getParameter("product_color");
		String brand_name = req.getParameter("brand_name");
		String store_name = req.getParameter("store_name");
		String product_qty = req.getParameter("product_qty");
		String period = req.getParameter("period");
		
		HashMap<String, String> oredit = new HashMap<String, String>();
		
		oredit.put("order_id", order_id);
		oredit.put("due_id", due_id);
		oredit.put("product_code", product_code);
		
		oredit.put("product_name", product_name);
		oredit.put("product_size", product_size);
		oredit.put("product_color", product_color);
		oredit.put("brand_name", brand_name);
		oredit.put("store_name", store_name);
		oredit.put("product_qty", product_qty);
		oredit.put("period", period);
		
		req.setAttribute("oredit", oredit);

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

		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/order/edit.jsp");
		dispatcher.forward(req, resp);

	}

}
