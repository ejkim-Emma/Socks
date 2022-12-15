package com.socks.period;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.socks.dto.orderdueDTO;
import com.socks.dto.periodDTO;

@WebServlet("/period/list.do")
public class List extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// 1. 세션을 가져오기
		HttpSession session = req.getSession();
		System.out.println(session);

		// (1) 목록리스트 조회하기
		// 2. ProductDAO 안에서 불러온 데이터를 가공하기 위해 ProductDAO를 'dao'로 변수를 선언한다.
		PeriodDAO dao = new PeriodDAO();

		// ArrayList<productDTO>를 'list'라고 메소드 붙이기
		ArrayList<orderdueDTO> list = dao.list();

		// jsp에 ArrayList<productDTO>를 통째로 넘기기 위해 setAttribute 사용
		req.setAttribute("list", list);
		System.out.println("상자 이름 짓기");

		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/period/list.jsp");
		dispatcher.forward(req, resp);

	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		System.out.println("넘어오나?");
		// 한글깨지지 않도록 인코딩
		req.setCharacterEncoding("UTF-8");

		// 1. 데이터 가져오기
		// 체크된 셀렉트 박스의 신청기간 PK
		String[] selectDueId = req.getParameterValues("selectPeriodId");
		
		// 전체 신청기간 PK
		String[] orderDueId = req.getParameterValues("allPeriodId");
		// 전체 기간리스트
		String[] orderPeriod = req.getParameterValues("allPeriod");
		// 전체 기간 시작일
		String[] orderStart = req.getParameterValues("allStart");
		// 전체 기간 종료일
		String[] orderClose = req.getParameterValues("allClose");
		
		int[] orderYear = Arrays.stream(req.getParameterValues("allYear")).mapToInt(Integer::parseInt).toArray();
		int[] orderMonth = Arrays.stream(req.getParameterValues("allMonth")).mapToInt(Integer::parseInt).toArray();
		
		/*
		 * for (int i = 0; i < orderDueId.length; i++) { System.out.println("년도: " +
		 * orderDueId[i]); System.out.println("기간: " + orderPeriod[i]);
		 * System.out.println("시작일: " + orderStart[i]); System.out.println("종료일: " +
		 * orderClose[i]); System.out.println("년: " + orderYear[i]);
		 * System.out.println("월: " + orderMonth[i]); }
		 */
		
		// 2. 가져온 데이터 넣을 상자 만들기
		periodDTO dto = new periodDTO();
		
		// 3. 상자 안에 배열들 세팅하기
		dto.setSelectDueId(selectDueId);
		dto.setOrderDueId(orderDueId);
		dto.setOrderPeriod(orderPeriod);
		dto.setOrderStart(orderStart);
		dto.setOrderClose(orderClose);
		dto.setOrderYear(orderYear);
		dto.setOrderMonth(orderMonth);
		
		// 4. 데이터 가공하기
		PeriodDAO dao = new PeriodDAO();
		
		int result = dao.periodSave(dto);
		
		// element.setAttribute(name, value);
		// 선택한 요소의 속성값을 정하는 것
		req.setAttribute("result", result);
		
		// RequestDispatcher
		// 클라이언트(서블릿)로부터 최초에 들어온 요청을 jsp 내에서 원하는 자원으로 요청을 넘기는 역할
		// 특정 자원에서 처리를 요청하고 처리 결과를 얻어오는 기능을 수행하는 클래스
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/period/list.jsp");
		dispatcher.forward(req, resp);
	}

}
