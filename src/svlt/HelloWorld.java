package svlt;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/hello")
public class HelloWorld extends HttpServlet {

	@Override
	public void init() throws ServletException {
		System.out.println("Learn14.HelloWorld.init()");
	}

	public HelloWorld() {
		super();
		System.out.println("Learn14.HelloWorld.HelloWorld()");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("Learn14.HelloWorld.doPost()");

	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("Learn14.HelloWorld.doPut()");
	}

	@Override
	public void destroy() {
		System.out.println("Learn14.HelloWorld.destroy()");

	}

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		// TODO Auto-generated method stub
//		System.out.println("Learn14.HelloWorld.service()");
		PrintWriter out = resp.getWriter();
//		// doPut(req, resp);
//		// doPost(req, resp);
//		// resp.sendRedirect("http://www.baidu.com");
//		Cookie[] ck = req.getCookies();
//		if(ck != null){
//			for(Cookie c:ck){
//				System.out.println("name :" +c.getName() +"values :" +c.getValue());
//			}
//		}
//		
//		System.out.println("tag:----------------------------");
////		int times = 0;
////		if (ck != null) {
////			for (Cookie c : ck) {
////				if (c.getName().equals("currentTimes")) {
////					times = Integer.parseInt(c.getValue());
////					times++;
////					c.setValue(String.valueOf(times));
////					resp.addCookie(c);//!!
////					System.out.println(times + "");
////				}
////			}
////		}
//		Cookie c = new Cookie("name", "qbq");
//		Cookie c1 = new Cookie("isLogin", "true");
//		c.setMaxAge(10);
//		c1.setMaxAge(10);
////		if (times == 0) {
////			Cookie c2 = new Cookie("currentTimes", "1");
////			resp.addCookie(c2);
////		}
//		resp.addCookie(c);
//		resp.addCookie(c1);
		 //获取请求数据
     String content = "test!!!!";
        //保存到request
        req.setAttribute("content","Content: "+content);
        //转发
        System.out.println("keyizhuanfa ");
        req.getRequestDispatcher("META-INF/test.jsp").forward(req,resp);
//		out.println("<h1><a href=\"hello\"/>Hello World</h1>");
		
		
	}

	// @Override
	// protected void service(HttpServletRequest req, HttpServletResponse reqs)
	// throws ServletException, IOException {
	// // TODO Auto-generated method stub
	// ServletOutputStream out=reqs.getOutputStream();
	//// out.println("<html>");
	//// out.println("<body>");
	//// out.println("<h1>Hello,World!</h1>");
	//// out.println("<h2>I am a boy!</h2>");
	//// out.println("</body>");
	//// out.println("</html>");
	// out.println("Hello,World!");
	// }

}
