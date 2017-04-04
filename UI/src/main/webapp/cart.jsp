<%@include file="env.jsp"%>

<%@include file="reqauth.jsp"%>

<c:set var="title" value="Shopping Cart" scope="page"  />
<c:set var="detailsPrefix">/product.jsp?id=</c:set>

<%@include file="template_start.jsp"%>
<link rel="stylesheet" type="text/css" href="/css/backet.css">
	<form id="backetForm" name="orderButton" action="" method="post">
		<table class="simple-little-table" cellspacing='0'>
			<tr>
				<th>ID</th>
				<th>Наименование </th>
				<th>Количество</th>
				<th>Цена</th>
			</tr>
			<tbody>
				<c:forEach items="${cartBean.cart}" var="cartItem">
					<c:set var="item" value="${productBean.get(cartItem.productId)}" />
					<tr>
						<td>-inf</td>
						<td><a href="<c:url value="${detailsPrefix}${item.id}"/>"><c:out value="${item.name}" /></a></td>
						<td><c:out value="${item.description}" /></td>
						<td>$<c:out value="${item.price}" /> x<c:out value="${cartItem.count}" /></td>
					</tr>
				</c:forEach>
				<tr>
				<td></td>
				<td></td>
				<td>Итого:</td>
				<td>10000$</td>
				<td><div >
					<input type="submit" name="submit" class="button1" value="Оформить заказ" class="button" />
				</div></td>
				</tr>
			</tbody>
		</table>
	</form>
<%@include file="template_end.jsp"%>
