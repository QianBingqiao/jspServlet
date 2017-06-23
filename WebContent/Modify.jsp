<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
	<%
		String isLogin = null;
		Object obj = session.getAttribute("isLogin");
		if (obj == null)
			isLogin = "";
		else {
			isLogin = (String) obj;
		}
		if (isLogin.equals("true")) {

			//登陆成功
		} else {
			response.sendRedirect("login.jsp");
		}
		String acctNo = (request.getParameter("acctNo") == null) ? "" : request.getParameter("acctNo");
		String acctName = (request.getParameter("acctName") == null) ? "" : request.getParameter("acctName");
		String balance = (request.getParameter("balance") == null) ? "" : request.getParameter("balance");
		String acctStatus = (request.getParameter("acctStatus") == null) ? "" : request.getParameter("acctStatus");
	%>
	<form action="acct?trans=edit" method="post">
		<table>
			<tr>
				<td>AcctNo:<input type="text" name="acctNo" readonly="true"
					value="<%=acctNo %>"/><br>
				</td>
			</tr>
			<tr>
				<td>AcctName:<input type="text" name="acctName" value="<%=acctName %>"/><br>
				</td>
			</tr>
			<tr>
				<td>AcctStatus:<input type="text" name="acctStatus" value="<%=acctStatus %>"/><br>
				</td>
			</tr>
			<tr>
				<td>Balance:<input type="text" name="balance" value="<%=balance %>"/><br>
				</td>
			</tr>
			<tr>
				<td><input type="submit" value="Update" />
			</tr>
		</table>
	</form>
</body>
</html>