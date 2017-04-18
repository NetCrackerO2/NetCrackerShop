<c:if test="${not clientInfo.loggedIn}">
    <c:set target="${clientInfo}" property="errorMessage" value="Need log in" />
    <%
        response.sendRedirect("/auth.jsp");
		return;
    %>
</c:if>
