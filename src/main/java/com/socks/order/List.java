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
import javax.servlet.http.HttpSession;

import com.socks.dto.brandDTO;
import com.socks.dto.duty_freeDTO;
import com.socks.dto.orderDTO;
import com.socks.dto.orderdueDTO;
import com.socks.dto.productDTO;

@WebServlet("/order/list.do")
public class List extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// 1. 세션을 가져오기
		HttpSession session = req.getSession();
		System.out.println(session);

		req.setCharacterEncoding("UTF-8");

		String Product_name = req.getParameter("Product_name");
		String Product_size = req.getParameter("Product_size");
		String Product_color = req.getParameter("Product_color");
		String BrDescription = req.getParameter("BrDescription");
		String DuDescription = req.getParameter("DuDescription");
		String year = req.getParameter("year");
		String month = req.getParameter("month");

		// 검색을 하고 있는 지 안하고 있는 지 구분하는 변수 하나 만들기
		// 기본 디폴트는 'n'으로 하여 그냥 목록인것으로 설정하기
		String isSearch = "n";

		// 검색하는 지 안하는 지 구분하는 if문 사용하기

		// 검색하지 않을 때
		if ((Product_name == null && Product_size == null && Product_color == null && BrDescription == null && DuDescription == null && year == null && month == null)
				|| (Product_name == "" && Product_size == "" && Product_color == "" && BrDescription == "" && DuDescription == "" && year == "" && month == "")) {
			isSearch = "n";
		} else {
			// 검색하고 있을 때
			isSearch = "y";
			
			// HashMap 선언
			HashMap<String, String> map = new HashMap<String, String>();
			
			// HashMap 값 추가
			map.put("Product_name", Product_name);
			map.put("Product_size", Product_size);
			map.put("Product_color", Product_color);
			map.put("BrDescription", BrDescription);
			map.put("DuDescription", DuDescription);
			map.put("year", year);
			map.put("month", month);
			map.put("isSearch", isSearch);
			
			OrderDAO dao = new OrderDAO();

			// -----------------------------------------------------------------------------------------

			// 목록리스트 조회하기
			ArrayList<orderDTO> list = new ArrayList<orderDTO>();

			list = dao.list(map);

			req.setAttribute("list", list);
		}

		
		OrderDAO dao = new OrderDAO();
		
		// 년도 가져오기
		ArrayList<orderdueDTO> ylist = dao.ylist();

		req.setAttribute("ylist", ylist);

		// 월 가져오기
		ArrayList<orderdueDTO> mlist = dao.mlist();

		req.setAttribute("mlist", mlist);

		// 상품명
		ArrayList<productDTO> nlist = dao.nlist();

		req.setAttribute("nlist", nlist);

		// 사이즈
		ArrayList<productDTO> slist = dao.slist();

		req.setAttribute("slist", slist);

		// 색상
		ArrayList<productDTO> clist = dao.clist();

		req.setAttribute("clist", clist);

		// 브랜드
		ArrayList<brandDTO> blist = dao.blist();
		req.setAttribute("blist", blist);

		// dutyFree
		ArrayList<duty_freeDTO> dflist = dao.dflist();
		req.setAttribute("dflist", dflist);

		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/order/list.jsp");
		dispatcher.forward(req, resp);

	}

}
