package com.socks.dutyfree;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.socks.dto.dutyfreeDTO;
import com.socks.dto.qtyDTO;

@WebServlet("/dutyfree/list.do")
public class List extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// 1. 세션을 가져오기
		HttpSession session = req.getSession();

		req.getParameter("UTF-8");

		String id = (String) session.getAttribute("auth");
		String period = req.getParameter("period");
		/* String day = req.getParameter("day"); */
		
		/* System.out.println("데이를 받아오나?" + day); */


		if ((period == null) || (period == "")) {

		} else {
			
			HashMap<String, String> per = new HashMap<String, String>();
			
			per.put("store_id", id);
			per.put("due_id", period);
			/* per.put("day", day); */
			/* System.out.println("day가 들어갔나?: "+per.put("day", day)); */

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

		// System.out.println("doGet");

		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/dutyfree/list.jsp");
		dispatcher.forward(req, resp);

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");

		// int due_id = req.getParameter("due_id");

		int wrong = Integer.parseInt(req.getParameter("wrong"));

		if (wrong < 0) {
			System.out.println("실패");
			resp.setContentType("text/html; charset=utf-8");
			PrintWriter out = resp.getWriter();
			out.write("<html><head><script type='text/javascript'>");
			out.write("alert('실패');history.go(-1);");
			out.write("</script></head></html>");
			out.flush();
		} else {
			/*
			 * System.out.println("성공"); // 배열에 담기
			 * 
			 * String[] input = (req.getParameterValues("input"));
			 * 
			 * for (int i = 0; i < input.length; i++) { System.out.println("배열 출력" +
			 * input[0]); }
			 * 
			 * qtyDTO dto = new qtyDTO();
			 * 
			 * dto.setData(input);
			 * 
			 * DutyfreeDAO dao = new DutyfreeDAO();
			 */

			/*
			 * String[] result = dao.edit(dto);
			 * 
			 * req.setAttribute("result", result);
			 */

			/*
			 * Enumeration<String> enu = req.getParameterNames();
			 * 
			 * while (enu.hasMoreElements()) {
			 * 
			 * String name = (String) enu.nextElement(); String[] values =
			 * req.getParameterValues(name);
			 * 
			 * for (int i = 0; i < values.length; i++) { System.out.println(name + "["+ i +
			 * "]:" + values[i]); }
			 * 
			 * qtyDTO dto = new qtyDTO();
			 * 
			 * dto.setData(values);
			 * 
			 * DutyfreeDAO dao = new DutyfreeDAO();
			 * 
			 * int result = dao.edit(dto);
			 * 
			 * req.setAttribute("result", result);
			 * 
			 * }
			 */

			/*
			 * String[] input = req.getParameterValues("input"); for (int i = 0; i <
			 * input.length; i++) {
			 * 
			 * System.out.println("배열" + input[i]); }
			 */

			/*
			 * String store_id = req.getParameter("store_id"); String due_id =
			 * req.getParameter("due_id"); String product_code =
			 * req.getParameter("product_code"); String product_qty =
			 * req.getParameter("product_qty");
			 * 
			 * productDTO dto = new productDTO();
			 * 
			 * dto.setProduct_code(store_id); System.out.println(store_id);
			 * dto.setProduct_name(due_id); System.out.println(due_id);
			 * dto.setProduct_color(product_code); System.out.println(product_code);
			 * dto.setProduct_size(product_qty); System.out.println(product_qty);
			 * 
			 * DutyfreeDAO dao = new DutyfreeDAO();
			 * 
			 * int result = dao.edit(dto);
			 * 
			 * req.setAttribute("result", result);
			 */

			int[] order_id = Arrays.stream(req.getParameterValues("order_id")).mapToInt(Integer::parseInt).toArray();
			// Arrays.stream(req.getParameterValues("order_id")).mapToInt(Integer::parseInt).toArray();
			String[] store_id = req.getParameterValues("store_id");
			System.out.println("스토어 아이디?" + store_id);
			String[] due_id = req.getParameterValues("due_id");
			String[] product_code = req.getParameterValues("product_code");
			int[] product_qty = Arrays.stream(req.getParameterValues("product_qty")).mapToInt(Integer::parseInt)
					.toArray();

			/*
			 * for (int i = 0; i < input.length; i++) {
			 * 
			 * System.out.println("배열" + input[i]); }
			 */

			qtyDTO dto = new qtyDTO();

			dto.setOrderId(order_id);
			dto.setStoreId(store_id);
			dto.setDueId(due_id);
			dto.setProductCode(product_code);
			dto.setProductQty(product_qty);

			DutyfreeDAO dao = new DutyfreeDAO();

			int result = dao.edit(dto);
			System.out.println("result 값:" + result);

			req.setAttribute("result", result);

		}

		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/dutyfree/list.jsp");
		dispatcher.forward(req, resp);

	}
}
