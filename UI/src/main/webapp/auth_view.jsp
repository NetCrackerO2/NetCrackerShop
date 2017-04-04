<%@include file="env.jsp"%>

<c:set var="title" value="Авторизация" scope="page"  />

<%@include file="template_start.jsp"%>
	<link rel="stylesheet" type="text/css" href="../css/login.css">
	<form name="login-form" class="login-form" method="POST" action="/auth.jsp">
		<div class="contentLogin">
			<input name="login" type="text" class="input username" value="Логин" onfocus="" />
		</div>
		<div class="footerLogin">
			<input type="submit" name="submit" value="ВОЙТИ" class="button" />
		</div>
	</form>
<%@include file="template_end.jsp"%>
