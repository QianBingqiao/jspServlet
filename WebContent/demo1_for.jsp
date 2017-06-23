<%@page import="org.eclipse.jdt.internal.compiler.batch.Main"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@page import="java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
	<%
		class Emp {
			String userName;
			int age;
			String job;

			public Emp() {

			}

			public Emp(String userName, int age, String job) {
				this.userName = userName;
				this.age = age;
				this.job = job;
			}

			public void setJob(String job) {
				this.job = job;
			}

			public String getJob() {
				return job;
			}

			public void setUserName(String userName) {
				this.userName = userName;
			}

			public void setAge(int age) {
				this.age = age;
			}

			public String getUserName() {
				return userName;
			}

			public int getAge() {
				return age;
			}

			//toString方法
			public String toString() {
				return "Emp [userName=" + userName + ",age=" + age + ",job=" + job + "]";
			}

		}
		//Emp emp = new Emp("json", 18, "Teacher");
		//	emp.setUserName("Json");
		//	emp.setAge(18);
	%>

	<%
		List<Emp> list = new ArrayList<Emp>();
		Emp emp1 = new Emp("Json", 18, "Teacher");
		Emp emp2 = new Emp("Henrry", 22, "Doctor");
		Emp emp3 = new Emp("Jack", 24, "Student");
		list.add(emp1);
		list.add(emp2);
		list.add(emp3);
	%>
	<table border="1px" align="center">
		<tr>
			<th>UserName</th>
			<th>Age</th>
			<th>Job</th>
		</tr>
		<%
			for (Emp e : list) {
		%>
		<tr align="center">
			<td width="200px"><%=e.getUserName()%></td>
			<td width="200px"><%=e.getAge()%></td>
			<td width="200px"><%=e.getJob()%></td>
		</tr>
		<%
			}
		%>
	</table>
</body>
</html>