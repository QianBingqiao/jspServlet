package svlt;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class LoginCheckFilter
 */
@WebFilter(filterName = "ALoginCheckFilter",  servletNames  = {"AcctManagerSvlt"})
public class ALoginCheckFilter implements Filter {

    /**
     * Default constructor. 
     */
    public ALoginCheckFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		//判断是否登录
				System.out.println("LoginCheckFilter.doFilter...");
				HttpServletRequest req = (HttpServletRequest)request;
				HttpServletResponse resp = (HttpServletResponse)response;
				System.out.println("LoginCheckFilter.doFilter00001...");
				HttpSession session = req.getSession();
				System.out.println("LoginCheckFilter.doFilter0000000001...");
				Object o = session.getAttribute("isLogin");
				String isLogin = null;
				if (o == null)
					isLogin = "";
				else {
					isLogin = (String)o;
				}
				if (!isLogin.equals("true")){  //已登录 
					req.getRequestDispatcher("login.jsp").forward(req, resp);
					return;
				}
				chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		
		System.out.println("init..........");
		// TODO Auto-generated method stub
	}

}
