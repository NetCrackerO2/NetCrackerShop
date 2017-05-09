<c:if test="${not clientInfo.getAdmin()}">
    <%
        response.sendRedirect("/");
    %>
</c:if>
