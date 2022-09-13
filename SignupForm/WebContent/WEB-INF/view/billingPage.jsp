<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta charset="ISO-8859-1">
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/css/bootstrap.min.css"
	rel="stylesheet">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/js/bootstrap.bundle.min.js"></script>
<title>Insert title here</title>
</head>
<body>
	<div class="row" style="text-align: center">
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
					<td id="rowOne">${vegetable }</td>
					<td id="rowOne">${vegetableRate }</td>
					<td id="rowOne">${vegetableAmount }</td>
				</tr>
				<tr>
					<td id="rowTwo">${fruits }</td>
					<td id="rowTwo">${fruitsRate }</td>
					<td id="rowTwo">${fruitsAmount }</td>
				</tr>
				<tr>
					<td id="rowThree">${spices }</td>
					<td id="rowThree">${spicesRate }</td>
					<td id="rowThree">${spicesAmount }</td>
				</tr>
				<tr>
					<td id="rowFour">${packagedFood }</td>
					<td id="rowFour">${packagedFoodRate }</td>
					<td id="rowFour">${packagedFoodAmount }</td>
				</tr>
			</tbody>
		</table>
	</div>
</body>
</html>