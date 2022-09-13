<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<script></script>
</head>
<body>
	<div class="row" style="text-align: center">
		<div class="col-sm-12" style="text-align:center">Products added to cart</div>
		<table class="table table-bordered">
			<thead>
				<tr>
					<th>Item</th>
					<th>Rate</th>
					<th>Amount</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td id="rowOne">${product }</td>
					<td id="rowOne">${rate }</td>
					<td id="rowOne">${amount }</td>
				</tr>
			</tbody>
		</table>
	</div>
</body>
</html>