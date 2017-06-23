package svlt;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
@WebServlet(name = "AcctManagerSvlt", urlPatterns = {"/acct"})
public class AcctManagerSvlt extends HttpServlet {

	/**
	 * trans_code:query 精确查询 add 添加 del 删除 edit 修改
	 */
	private static final long serialVersionUID = -5115091395802691609L;
	AcctManager acctmng = null;
	/**
	 * 空参数构造
	 * 
	 */
	public AcctManagerSvlt() {
		acctmng = new AcctManager();

	}
	/**
	 * doGet()方法
	 * 
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 准备数据
		/**
		 * trans_code:query 精确查询 add 添加 del 删除 edit 修改
		 */

		String transCode = req.getParameter("trans");

		if (transCode == null || "".equals(transCode)) {
			RequestDispatcher dispatcher = req.getRequestDispatcher("Err.jsp");
			dispatcher.forward(req, resp);
			return;
		}

		if (transCode.equals("add")) {
			System.out.println("add...");
			 doAdd(req, resp);
		} else if (transCode.equals("del")) {
			doDel(req, resp);
		} else if (transCode.equals("edit")) {
			doEdit(req, resp);
		} else if (transCode.equals("query")) {
			String query_type = req.getParameter("hazys");
			if (query_type == null)
				query_type = "";
			if (query_type.equals("on")) {
				doHazyQuery(req, resp);
			} else {
				doQuery(req, resp);
			}

		} else {
			RequestDispatcher dispatcher = req.getRequestDispatcher("Err.jsp");
			dispatcher.forward(req, resp);
			return;
		}
		
	

	}
	/**
	 * 
	 * doPost()方法
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		doGet(req, resp);
	}

	/**
	 * 模糊查询
	 * 
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void doHazyQuery(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String acctNo = req.getParameter("acctNo");
		String acctName = req.getParameter("acctName");
		String balance = req.getParameter("balance");
		String acctStatus = req.getParameter("acctStatus");

		if (acctNo == null)
			acctNo = "";
		if (acctName == null)
			acctName = "";
		if (balance == null)
			balance = "";
		if (acctStatus == null)
			acctStatus = "";
		Double Bal = (!balance.equals("")) ? Double.parseDouble(balance) : 0.00;

		Acct a = new Acct(acctNo, acctName, Bal, acctStatus);

		List<Acct> accts = acctmng.queryHazy(a);

		req.setAttribute("accts", accts);
	
		// 实现转发
		RequestDispatcher dispatcher = req.getRequestDispatcher("/AcctMain.jsp");

		dispatcher.forward(req, resp);
	}

	/**
	 * 精确查询
	 * 
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void doQuery(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String acctNo = req.getParameter("acctNo");
		String acctName = req.getParameter("acctName");
		String balance = req.getParameter("balance");
		String acctStatus = req.getParameter("acctStatus");

		if (acctNo == null)
			acctNo = "";
		if (acctName == null)
			acctName = "";
		if (balance == null)
			balance = "";
		if (acctStatus == null)
			acctStatus = "";
		Double Bal = (!balance.equals("")) ? Double.parseDouble(balance) : 0.00;

		Acct a = new Acct(acctNo, acctName, Bal, acctStatus);

		List<Acct> accts = acctmng.selectAcctByno(a);

		req.setAttribute("accts", accts);
		// req.setAttribute("accts", acctInfo);
		// 实现转发
		RequestDispatcher dispatcher = req.getRequestDispatcher("AcctMain.jsp");

		dispatcher.forward(req, resp);
	}

	/**
	 * 添加
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void doAdd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String acctNo = req.getParameter("acctNo");
		String acctName = req.getParameter("acctName");
		String balance = req.getParameter("balance");
		String acctStatus = req.getParameter("acctStatus");

		if (acctNo == null)
			acctNo = "";
		if (acctName == null)
			acctName = "";
		if (balance == null)
			balance = "";
		if (acctStatus == null)
			acctStatus = "";
		Double Bal = (!balance.equals("")) ? Double.parseDouble(balance) : 0.00;

		Acct a = new Acct(acctNo, acctName, Bal, acctStatus);

		
		try {
			int rs = acctmng.addAcct(a);
			// 实现转发
			String url = "";
			if (rs > 0)
				url = "AcctMain.jsp";
			else
				url = "Err.jsp";
			req.getRequestDispatcher(url).forward(req, resp);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * delete 删除
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void doDel(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String acctNo = req.getParameter("acctNo");
		if (acctNo == null)
			acctNo = "";
		Acct a = new Acct(acctNo, "", 0.00, "");
		try {
			int rs = acctmng.addDelete(a);
			// 实现转发
			String url = "";
			if (rs > 0)
				url = "AcctMain.jsp";
			else
				url = "Err.jsp";
			req.getRequestDispatcher(url).forward(req, resp);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * 修改
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void doEdit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String acctNo = req.getParameter("acctNo");
		String acctName = req.getParameter("acctName");
		String balance = req.getParameter("balance");
		String acctStatus = req.getParameter("acctStatus");

		if (acctNo == null)
			acctNo = "";
		if (acctName == null)
			acctName = "";
		if (balance == null)
			balance = "";
		if (acctStatus == null)
			acctStatus = "";
		Double Bal = (!balance.equals("")) ? Double.parseDouble(balance) : 0.00;

		Acct a = new Acct(acctNo, acctName, Bal, acctStatus);
		try {
			int rs = acctmng.updateAcct(a);
			// 实现转发
			String url = "";
			if (rs > 0)
				url = "AcctMain.jsp";
			else
				url = "Err.jsp";
			req.getRequestDispatcher(url).forward(req, resp);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
