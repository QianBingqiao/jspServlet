<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
	<%-- <!-- EL四则运算表达式 -->
	<h3>${1+1}</h3>
	<h3>${2-3}</h3>
	<h3>${2*3}</h3>
	<h3>${5/2}</h3>
	
	<!-- EL逻辑表达式 -->
	<h3>${1==1}</h3>
	<h3>${1==2}</h3>
	<h3>${3==(1+2)}</h3>
	<h3>${true||false}</h3>
	<h3>${empty null}</h3>
	<h3>${empty ''}</h3>
	<h3>${empty 'abc'}</h3>
	<h3>${1<2 ? 'yes': 'no'}</h3> --%>

	<!-- EL表达式取参数 -->
	<!-- 等价于request.getParameter("userName") -->
	<h3>${param.userName}</h3>
	<h3>${param.passWord}</h3>

	<!-- EL表达式访问Bean对象 -->
	<!-- 1.Employee emp = new Employee() -->
	<jsp:useBean id="emp" class="svlt.Employee" scope="page"></jsp:useBean>

	<!-- emp.setName(request.getParameter("userName")) -->
	<jsp:setProperty property="name" name="emp" param="userName" />
	<jsp:setProperty property="age" name="emp" param="passWord" />

	<!-- emp.getName() -->
	<h3>name:${emp.name}</h3>
	<h3>age:${emp.age}</h3>


	<!--  EL表达式 从不同的对象中取值-->
	
	<%
	/* 向不同范围内写入参数*/
	//request.setAttribute("user_name", "request user_name");
	session.setAttribute("user_name", "session user_name");
	application.setAttribute("user_name", "application user_name");
	
	%>

   <h3>Request: ${requestScope['user_name']} </h3>
	<h3>Session: ${sessionScope['user_name']} </h3>
	<h3>Not Set Scope: ${user_name} </h3>
		
</body>
</html>