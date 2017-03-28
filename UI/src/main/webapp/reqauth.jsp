<c:if test="${not clientInfo.loggedIn}" >
	<%
		response.sendRedirect("/auth.jsp");
	%>
</c:if>
