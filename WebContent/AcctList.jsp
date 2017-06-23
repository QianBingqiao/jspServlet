<%@page
	import="org.eclipse.jdt.internal.compiler.ast.InstanceOfExpression"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@page import="java.util.*"%>
<%@page import="svlt.*"%>
<%@page import="java.lang.reflect.*"%>
<%@page import="java.lang.Object.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>

	<table border="1px" align="center">
		<%
			//	Acct accts = null;
			List<Acct> list = null;
			Object o = request.getAttribute("accts");
			//	Class c = request.getClass();
			//Constructor con=.getDeclaredConstructor("getAttribute");
			//	Method method = c.getDeclaredMethod("getAttribute", String.class);
			//	method.setAccessible(true);
			//	Object o1=method.invoke("accts");
			System.out.println("8888888888888888");

			if (o != null) {
				System.out.println("999999999999999999");

				System.out.println("bbbbbbbbbbbbbbbbbbbbbb");
				list = (List<Acct>) o;
		%>
		<tr align="center">
			<td width="200px">AcctNo</td>
			<td width="200px">AcctName</td>
			<td width="200px">Balance</td>
			<td width="200px">AcctStatus</td>
		</tr>
		<%
			for (Acct a : list) {
		%>
		<tr align="center">
			<td width="200px"><%=a.getAcctNo()%></td>
			<td width="200px"><%=a.getAcctName()%></td>
			<td width="200px"><%=a.getBalance()%></td>
			<td width="200px"><%=a.getAcctStatus()%></td>
		</tr>


		<%
			}
			}
		%>


	</table>




</body>
</html>