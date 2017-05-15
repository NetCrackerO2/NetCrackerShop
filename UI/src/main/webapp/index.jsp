<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@include file="env.jsp" %>
<c:set var="title" value="Главная" scope="page"/>
<%@include file="template_start.jsp" %>

<c:set var="crumbs">
    <a href="index.jsp">Главная</a>
</c:set>
<jsp:include page="menu.jsp">
    <jsp:param name="crumbs" value="${crumbs}"/>
    <jsp:param name="title" value="${title}"/>
</jsp:include>

</nav>
<section id="content">
    <div class="container">
    </div>
</section>
<%@include file="template_end.jsp" %>