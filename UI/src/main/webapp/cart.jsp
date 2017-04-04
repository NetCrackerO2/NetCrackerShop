<%@include file="env.jsp"%>

<%@include file="reqauth.jsp"%>

<c:set var="title" value="Shopping Cart" scope="page"  />
<c:set var="detailsPrefix">/product.jsp?id=</c:set>

<%@include file="template_start.jsp"%>
	<c:forEach items="${cartBean.cart}" var="cartItem">
		<c:set var="item" value="${productBean.get(cartItem.productId)}" />
		<article class="z1">
			<a href="<c:url value="${detailsPrefix}${item.id}"/>">
				<h2><c:out value="${item.name}" /></h2>
			</a>
			<desc><c:out value="${item.description}" /></desc>
			<price>$<c:out value="${item.price}" /> x<c:out value="${cartItem.count}" /></price>

		</article>
	</c:forEach>
<%@include file="template_end.jsp"%>
