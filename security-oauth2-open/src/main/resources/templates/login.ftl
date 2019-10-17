<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<h1>Jpsite-v1--微服务开放平台授权系统</h1>
	<form action="/login" method="post">
		<span>用户名称</span><input type="text" name="username" /> <br>
		<span>用户密码</span><input type="password" name="password" /> <br>
		<input type="submit" value="登陆"> 

	</form>
	
<#if RequestParameters['error']??>
用户名称或者密码错误
</#if>




</body>
</html>