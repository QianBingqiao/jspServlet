package svlt;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserQuery extends HttpServlet {

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("service()....");
		// 1.取出客户端提交的数据
		req.setCharacterEncoding("utf-8");
		String userName = req.getParameter("userName");
		System.out.println(userName);
		if (userName == null)
			userName = "";

		// 2.执行查询
		resp.setCharacterEncoding("utf-8");
		resp.setContentType("text/html");
		ServletOutputStream out = resp.getOutputStream();
		OutputStreamWriter os = new OutputStreamWriter(out, "utf-8");
		PrintWriter pt = new PrintWriter(os, true);
		UserHandler handler = new UserHandler();
//		List<Map<String,String>> lists = handler.getAllUser();
		
		if (handler.queryUserInfo(userName)) {
			String userInfo = handler.getUserInfo(userName);
			pt.println("<html>");
			pt.println("<body>");
			pt.println("<h1>查询成功" +"/n"+ userInfo + "</h1>");
			pt.println("<h2>I am a boy!</h2>");
			pt.println("</body>");
			pt.println("</html>");
		} else {
			pt.println("<html>");
			pt.println("<body>");
			pt.println("<h1>当前人物数据不存在！</h1>");
			pt.println("</body>");
			pt.println("</html>");
		}

	}

}
