<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%><%@ taglib
	prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/css/bootstrap.min.css"
	rel="stylesheet">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/js/bootstrap.bundle.min.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>

<title>Insert title here</title>
<style>
.verticalLine {
	border-left: 4px solid #4b42f5;
	height: 50px;
}
</style>
<script>
	function addToCart() {
		var vegetables = $("#vegetableOption").val();
		var fruits = $("#fruitsOption").val();
		var spices = $("#spicesOption").val();
		var packagedFood = $("#packagedFoodOption").val();
		$.ajax({
			type : "POST",
			url : "/SignupForm/processCart",
			data : {
				vegetables : vegetables,
				vegetableQuantity : $("#vegetableQuantity").val(),
				fruits : $("#fruitsOption").val(),
				fruitsQuantity : $("#fruitsQuantity").val(),
				spices : $("#spicesOption").val(),
				spicesQuantity : $("#spicesQuantity").val(),
				packagedFood : $("#packagedFoodOption").val(),
				packagedFoodQuantity : $("#packagedFoodQuantity").val()
			},
			success : function(response) {
				//$("#newfile").empty();
				$("#cartfile").css("display","none");
				$("#newfile").css("display", "block");
				$("#newfile").append(response);
			}
		});
	}
	function viewCart() {
		$.ajax({
			type : "POST",
			url : "/SignupForm/displayCart",
			data : {

			},
			success : function(response) {
				//addRow();
				//$("#newfile").empty();
				//$("#newfile").css("display", "block");
				//$("#newfile").append(response);
				$("#newfile").empty();
				$("#cartfile").css("display","block");
				$("#myTable").empty();
				var data = response.split('||');
				alert(data);
				var products = data[0].split(',');
				var rate = data[1].split(',');
				var amount = data[2].split(',');
				for (var i = 0; i < products.length; i++) {
					var quantity = amount[i] / rate[i];
					addRow(products[i], quantity, rate[i], amount[i]);
				}

			}
		});
	}
	function addRow(product, quantity, rate, amount) {
		var table = document.getElementById("myTable");
		var row = table.insertRow(table.rows.length);
		var cell1 = row.insertCell(0);
		var cell2 = row.insertCell(1);
		var cell3 = row.insertCell(2);
		var cell4 = row.insertCell(3);
		cell1.innerHTML = product;
		cell2.innerHTML = quantity;
		cell3.innerHTML = rate;
		cell4.innerHTML = amount;

	}
</script>
</head>
<body>
	Welcome ${name}!
	<br>
	<br>
	<button id="mybtn" onclick="javascript:viewCart()">Cart</button>
	<br>
	<h5>Products</h5>
	<hr>
	<form:form method="get" action="processPurchase" id="shoppingForm"
		modelAttribute="shoppingCartObj">
		<div class="row">
			<div class="col-sm-3 verticalLine" id="vegetables" value="vegetables">
				Vegetables:
				<form:select id="vegetableOption" path="vegSelected">
					<form:options items="${shoppingCartObj.vegetablesList}" />
					<br>
				</form:select>
				Quantity(Kg's):
				<form:select id="vegetableQuantity" path="vegetableQuantity">
					<form:options items="${shoppingCartObj.quantity }" />
				</form:select>
			</div>
			<div class="col-sm-3 verticalLine" id="fruits">
				Fruits:
				<form:select id="fruitsOption" path="fruitSelected">
					<form:options items="${shoppingCartObj.fruitsList}" />
					<br>
				</form:select>
				Quantity(Kg's):
				<form:select id="fruitsQuantity" path="fruitsQuantity">
					<form:options items="${shoppingCartObj.quantity }" />
				</form:select>
			</div>
			<div class="col-sm-3 verticalLine" id="spices">
				Spices:
				<form:select id="spicesOption" path="spicesSelected">
					<form:options items="${shoppingCartObj.spicesList}" />
					<br>
				</form:select>
				Quantity(Kg's):
				<form:select id="spicesQuantity" path="spicesQuantity">
					<form:options items="${shoppingCartObj.quantity }" />
				</form:select>
			</div>
			<div class="col-sm-3 verticalLine" id="packagedFood">
				Packaged Food:
				<form:select id="packagedFoodOption" path="packagedFoodsSelected">
					<form:options items="${shoppingCartObj.packagedFoodsList}" />
					<br>
				</form:select>
				Quantity(Kg's):
				<form:select id="packagedFoodQuantity" path="packagedFoodQuantity">
					<form:options items="${shoppingCartObj.quantity }" />
				</form:select>
			</div>
			<br> <br> <br>
		</div>
		<div class="row" style="text-align: center">
			<div class="col-sm-12" style="text-align: center">
				<input type="submit" class="btn btn-sm btn-primary submit"
					value="Proceed To Bill" style="text-align: center" />
			</div>
		</div>
		<br>
	</form:form>
	<div class="row" style="text-align: center">
		<div class="col-sm-12" style="text-align: center">
			<button class="btn btn-sm btn-primary" value="Add to Cart"
				onclick="javascript:addToCart()">Add to Cart</button>
		</div>
	</div>
	<div id="newfile" style="display: none"></div>
	<div id="cartfile" style="display: block">
		<table class="table table-bordered">
			<thead>
				<tr>
					<td>Item</td>
					<td>Quantity</td>
					<td>Rate</td>
					<td>Amount</td>
				</tr>
			</thead>
			<tbody id="myTable"></tbody>
		</table>
	</div>
</body>
</html>