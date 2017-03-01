<%@include file="template_start.jsp"%>
	<style>
		article {
			min-width: 200px;
			min-height: 20%;
			width: 20%;
			padding: 30px 30px 30px 30px;
			margin-bottom: 30px;
			margin-left: 10px;
			margin-right: 10px;
		}
		article:last-of-type {
			margin-bottom: 30px;
		}
		desc {
			word-wrap: break-word;
			display: block;
		}
		price {
			color: green;
			display: block;
			font-size: 1.2em;
		}
	</style>
	<c:forEach items="${data}" var="item">
		<article class="z1">
			<h2><c:out value="${item.title}" /></h2>
			<desc><c:out value="${item.description}" /></desc>
			<price>$<c:out value="${item.price}" /></price>
		</article>
	</c:forEach>
<%@include file="template_end.jsp"%>
