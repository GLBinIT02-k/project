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

			// �α��� �������� �̵��ϴ� �ڵ� �־��ּ���.
			RequestDispatcher rd = req.getRequestDispatcher("/login.jsp");
			rd.forward(req, resp);

		} else if (action.equals("login")) {
			MemberDAO dao = new MemberDAOImpl();
			Member member = new Member();
			
			// ȭ�鿡�� id,password �� �޾ƿ���
			// DAO���� �ش� id member��ü���� �޾ƿ���
			// ȭ�� id,password��
			// DB���� ������ id,password�� ���ؼ�
			// ��ġ�ϸ� session�� member ��ü�� set�ϰ� index.jsp�� �̵�
			
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
						// ��ġ���� ������ request ��ü�� ��ġ���� ���� �޼����� set�ϰ�
						// �ٽ� �α��� �������� �޼����� �����ش�. (�޼��� ����ؾ� ��)
						req.setAttribute("message", "���̵�� ��й�ȣ�� ��ġ�����ʽ��ϴ�.");
						RequestDispatcher rd = req.getRequestDispatcher("/login.jsp");
						rd.forward(req, resp);
					}

				} else {
					req.setAttribute("message", "��й�ȣ�� �Է����ּ���.");
					RequestDispatcher rd = req.getRequestDispatcher("/login.jsp");
					rd.forward(req, resp);
				}

			} else {
				req.setAttribute("message", "���̵� �Է����ּ���.");
				RequestDispatcher rd = req.getRequestDispatcher("/login.jsp");
				rd.forward(req, resp);
			}

		} else if (action.equals("logout")) {
			// session ��ü���� �ش� member �Ӽ��� �����Ѵ�.
			// index.jsp �������� �̵��Ѵ�.
			HttpSession session = req.getSession();
			session.removeAttribute("member");
			
			req.setAttribute("message", "�α׾ƿ� �Ǿ����ϴ�.");
			RequestDispatcher rd = req.getRequestDispatcher("/index.jsp");
			rd.forward(req, resp);
		}
	}
}