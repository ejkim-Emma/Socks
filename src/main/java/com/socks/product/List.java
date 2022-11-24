package com.socks.product;

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

import com.socks.dto.productDTO;

@WebServlet("/product/list.do")
public class List extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		doTemp(req, resp);

	}

	private void doTemp(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// (5) 검색조건을 가지고 데이터 고르기

		// UTF-8로 인코딩 하기
		req.setCharacterEncoding("UTF-8");

		// 파라미터로 검색한 것들 받아서 가져와서 스트링으로 만들기
		String product_name = req.getParameter("product_name");
//		String product_size = req.getParameter("product_size");
//		String product_color = req.getParameter("product_color");

		// 검색을 하고 있는 지 안하고 있는 지 구분하는 변수 하나 만들기
		// 기본 디폴트는 'n'으로 하여 그냥 목록인것으로 설정하기
		String isSearch = "n";

		// 검색하는 지 안하는 지 구분하는 if문 사용하기

		// 검색하지 않을 때
		if ((product_name == null) || (product_name == "")) {
			isSearch = "n";
		} else {
			// 검색하고 있을 때
			isSearch = "y";
		}

		// HashMap 선언
		HashMap<String, String> map = new HashMap<String, String>();

		// HashMap 값 추가
		map.put("product_name", product_name);
//		map.put("product_size", product_size);
//		map.put("product_color", product_color);
		map.put("isSearch", isSearch);

		// -----------------------------------------------------------------------------------
		
		// 1. 세션을 가져오기
		HttpSession session = req.getSession();
		System.out.println(session);

		// (1) 목록리스트 조회하기
		// 2. ProductDAO 안에서 불러온 데이터를 가공하기 위해 ProductDAO를 'dao'로 변수를 선언한다.
		ProductDAO dao = new ProductDAO();
		System.out.println("dao 변수 만들기");

		// ArrayList<productDTO>를 'list'라고 메소드 붙이기
		ArrayList<productDTO> list = dao.list(map);
		System.out.println("배열 상자를 dao에서 가공");

		// jsp에 ArrayList<productDTO>를 통째로 넘기기 위해 setAttribute 사용
		req.setAttribute("list", list);
		System.out.println("상자 이름 짓기");

		// (2) 검색조건의 이름만 가져오기
		ProductDAO ndao = new ProductDAO();

		ArrayList<productDTO> nlist = ndao.nlist();

		req.setAttribute("nlist", nlist);

		// (3) 검색조건의 사이즈만 가져오기
		ProductDAO sdao = new ProductDAO();

		ArrayList<productDTO> slist = sdao.slist();

		req.setAttribute("slist", slist);

		// (4) 검색조건의 색만 가져오기

		ProductDAO cdao = new ProductDAO();

		ArrayList<productDTO> clist = cdao.clist();

		req.setAttribute("clist", clist);

		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/product/list.jsp");
		dispatcher.forward(req, resp);
	}

}
