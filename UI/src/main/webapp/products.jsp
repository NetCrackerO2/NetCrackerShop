<%@include file="env.jsp"%>
<c:set var="title" value="test" scope="page"  />
<%@include file="template_start.jsp"%>
	<%-- <c:forEach items="${data}" var="item">
		<article class="z1">
			<h2><c:out value="${item.title}" /></h2>
			<desc><c:out value="${item.description}" /></desc>
			<price>$<c:out value="${item.price}" /></price>
		</article>
	</c:forEach> --%>
<%@include file="template_end.jsp"%>
