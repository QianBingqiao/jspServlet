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
	<form action="acct?trans=query" method="post" id="from1" name="from1">
		<table>
			<tr>
				<td>AcctNo:<input type="text" name="acctNo" /><br>
				</td>
			</tr>
			<tr>
				<td>AcctName:<input type="text" name="acctName" /><br>
				</td>
			</tr>
			<tr>
				<td>AcctStatus:<input type="text" name="acctStatus" /><br>
				</td>
			</tr>
			<tr>
				<td>Balance:<input type="text" name="balance" /><br>
				</td>
			</tr>
			<tr>
				<td><input type="submit" value="Query_Go" /> <input
					type="checkbox" name="hazys" /> <input type="submit" value=" Add "
					onclick="document.from1.action='acct?trans=add'" /> <br></td>
			</tr>
		</table>
	</form>
	<table border="1px" align="center">
		<%
			//	Acct accts = null;
			List<Acct> list = null;
			Object o = request.getAttribute("accts");

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
			<td width="200px">Operation</td>
		</tr>
		<%
			for (Acct a : list) {
		%>
		<tr align="center">
			<td width="200px"><%=a.getAcctNo()%></td>
			<td width="200px"><%=a.getAcctName()%></td>
			<td width="200px"><%=a.getBalance()%></td>
			<td width="200px"><%=a.getAcctStatus()%></td>
			<td width="200px"><a onclick="return confirm('确认删除吗？')"
				href="acct?trans=del&acctNo=<%=a.getAcctNo()%>">Delete</a>
				 <a
				href="Modify.jsp?acctNo=<%=a.getAcctNo()%>&acctName=<%=a.getAcctName()%>&acctStatus=<%=a.getAcctStatus()%>&balance=<%=a.getBalance()%>">Edit</a>
			</td>
		</tr>
		<%
			}
			}
		%>
	</table>


</body>
</html>