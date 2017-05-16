<c:if test="${not clientBean.get(clientInfo.id).getAdmin()}">
    <%
        response.sendRedirect("/");
    %>
</c:if>
