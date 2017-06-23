package svlt;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserQueryAll extends HttpServlet {

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("service()....");
		// 1.取出客户端提交的数据
		req.setCharacterEncoding("utf-8");
		// 2.执行查询
		
		resp.setContentType("text/html;charset=utf-8");
		ServletOutputStream out = resp.getOutputStream();
		OutputStreamWriter os = new OutputStreamWriter(out, "utf-8");
		PrintWriter pt = new PrintWriter(os, true);
		UserHandler handler = new UserHandler();
		List<User> lists = handler.getAllUser();
		pt.println("<html>");
		pt.println("<body>");
		pt.println("<h1>查询成功 </h1>");
		pt.println("<table width=500 border=1 align=\"center\" bordercolor=\"#000000\" style=\"background:#eeffdd\">");
		pt.println("<tr>");
		pt.println("<td>" + "姓名" + "</td>");
		pt.println("<td>" + "年龄" + "</td>");
		pt.println("<td>" + "职业" + "</td>");
		pt.println("</tr>");
		for (User user : lists) {
			pt.println("<tr>");
			pt.println("<td>" + user.getName() + "</td>");
			pt.println("<td>" + user.getAge() + "</td>");
			pt.println("<td>" + user.getGander() + "</td>");
			pt.println("</tr>");
		}
		pt.println("</table>");
		pt.println("</body>");
		pt.println("</html>");
	}
}
