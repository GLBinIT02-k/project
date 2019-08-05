package controller;

import java.io.IOException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.MemberDAO;
import dao.MemberDAOImpl;
import model.Member;

@WebServlet(name = "MemberController", urlPatterns = { "/login_input", "/login", "/logout" })

public class MemberController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		process(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		process(req, resp);

	}

	private void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");

		String uri = req.getRequestURI();
		int lastIndex = uri.lastIndexOf("/");
		String action = uri.substring(lastIndex + 1);

		if (action.equals("login_input")) {

			// 로그인 페이지로 이동하는 코드 넣어주세요.
			RequestDispatcher rd = req.getRequestDispatcher("/login.jsp");
			rd.forward(req, resp);

		} else if (action.equals("login")) {
			MemberDAO dao = new MemberDAOImpl();
			Member member = new Member();
			
			// 화면에서 id,password 값 받아오기
			// DAO에서 해당 id member객체에서 받아오기
			// 화면 id,password와
			// DB에서 가져온 id,password와 비교해서
			// 일치하면 session에 member 객체를 set하고 index.jsp로 이동
			
			if (req.getParameter("id") != "") {

				if (req.getParameter("password") != "") {

					String id = req.getParameter("id");
					String password = req.getParameter("password");
					
					member = dao.selectById(req.getParameter("id"));
					String loginId = dao.selectById(id).getId();
					String loginPassword = dao.selectById(id).getPassword();

					if (id.equals(loginId) && password.equals(loginPassword)) {
						HttpSession session = req.getSession();
						session.setAttribute("member", member);

						RequestDispatcher rd = req.getRequestDispatcher("/index.jsp");
						rd.forward(req, resp);
					} else {
						// 일치하지 않으면 request 객체에 일치하지 않음 메세지를 set하고
						// 다시 로그인 페이지에 메세지를 보여준다. (메세지 출력해야 함)
						req.setAttribute("message", "아이디와 비밀번호가 일치하지않습니다.");
						RequestDispatcher rd = req.getRequestDispatcher("/login.jsp");
						rd.forward(req, resp);
					}

				} else {
					req.setAttribute("message", "비밀번호를 입력해주세요.");
					RequestDispatcher rd = req.getRequestDispatcher("/login.jsp");
					rd.forward(req, resp);
				}

			} else {
				req.setAttribute("message", "아이디를 입력해주세요.");
				RequestDispatcher rd = req.getRequestDispatcher("/login.jsp");
				rd.forward(req, resp);
			}

		} else if (action.equals("logout")) {
			// session 객체에서 해당 member 속성을 제거한다.
			// index.jsp 페이지로 이동한다.
			HttpSession session = req.getSession();
			session.removeAttribute("member");
			
			req.setAttribute("message", "로그아웃 되었습니다.");
			RequestDispatcher rd = req.getRequestDispatcher("/index.jsp");
			rd.forward(req, resp);
		}
	}
}