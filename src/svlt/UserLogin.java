package svlt;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
@WebServlet(name = "UserLogin", urlPatterns = {"/login"})
public class UserLogin extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6204535326634592095L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub

		System.out.println("UserLogin.doGet()");
		// 1.取出客户端提交的数据

		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=utf-8");
		HttpSession session = req.getSession();

		String userName = req.getParameter("userName");
		String password = req.getParameter("passWord");
		if (userName == null)
			userName = "";
		if (password == null)
			password = "";
		// 判断是否登陆；
		Object o = session.getAttribute("isLogin");
		String isLogin = null;
		if (o == null)
			isLogin = "";
		else {
			isLogin = (String) o;
		}
		if(isLogin.equals("true")){
			req.getRequestDispatcher("AcctMain.jsp").forward(req, resp);
			return;
		}
//		session.setAttribute("isLogin", value);
		// Cookie[] ck = req.getCookies();
		// if (ck != null) {
		// for (Cookie c : ck) {
		// if (c.getName().equals("isLogin") && c.getValue().equals("true")) {
		// req.getRequestDispatcher("AcctMain.jsp").forward(req, resp);
		// return;
		// }
		// }
		// }

		// 2.数据验证并返回响应

		//

		// ServletOutputStream out = resp.getOutputStream();
		// OutputStreamWriter os = new OutputStreamWriter(out, "utf-8");
		// PrintWriter pt = new PrintWriter(os, true);
		// PrintWriter o = resp.getWriter();
		UserHandler handler = new UserHandler();

		if (handler.doLogin(userName, password)) {
			session.setAttribute("isLogin", "true");
			System.out.println("login success!");
			// Cookie c1 = new Cookie("isLogin", "true");
			// c1.setMaxAge(3);
			// resp.addCookie(c1);
			req.getRequestDispatcher("AcctMain.jsp").forward(req, resp);
			return;
			// resp.sendRedirect("AcctMain.jsp");

		} 
			else {
			session.setAttribute("isLogin", "false");
			req.getRequestDispatcher("login.jsp").forward(req, resp);
			return;
		}
//		 doPost(req, resp);

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("UserLogin.doPost()");
		doGet(req, resp);
	}

}
