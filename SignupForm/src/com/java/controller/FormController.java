package com.java.controller;

import java.sql.*;
import java.lang.*;
import java.util.*;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.java.DB.DBConnector;
import com.java.DB.ProductsCartsIO;
import com.java.customer.Student;
import com.java.shoppingCart.ShoppingCart;
import java.lang.*;
@Controller
public class FormController {
	private int rollNo;
	
	@RequestMapping("/")
	public String showMainPage(){
		return "mainPage";
		
	}

	@RequestMapping("/showForm")
	public String showSignupForm() {
		return "signup";
	}
	
	@RequestMapping("/showLoginPage")
	public String showLoginPage(){
		return "login";
	}
	
	@RequestMapping("/processForm")
	public String processSignupForm(@RequestParam("firstName") String firstName,@RequestParam("lastName") String lastName, @RequestParam("email") String email,@RequestParam("password") String password, Model model) {
		Student student=new Student();
		student.setFirstName(firstName);
		student.setLastName(lastName);
		student.setEmail(email);
		try {
			Connection con= DBConnector.openConnection();
			PreparedStatement ps=con.prepareStatement("Insert into credentials values(?,?,?,?,?)");
			ps.setString(1, firstName);
			ps.setString(2, lastName);
			ps.setString(3, email);
			ps.setString(4, password);
			ps.setLong(5, student.getRollNo());
			ps.execute();
			con.close();
	}
		catch(Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("student",student);
		model.addAttribute("rollno",student.getRollNo());
		return "detailsPage";
}
	@RequestMapping("/processLogin")
	public String processLoginRequest(@RequestParam("rollNo") int rollNo,@RequestParam("password") String password,Model model) {
		String correctPassword=null;
		String firstNameFromDB=null;
		ShoppingCart shoppingcart= new ShoppingCart();
		//set class member 'rollNo' to be used in other pages//
		this.rollNo=rollNo;
		try {
			Connection con=DBConnector.openConnection();
			PreparedStatement ps=con.prepareStatement("Select password from credentials where rollNo=(?)");
			ps.setLong(1, rollNo);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				correctPassword=rs.getString(1);
			}
			if(correctPassword.equals(password)) {
				ps=con.prepareStatement("select firstName from credentials where rollNo=(?)");
				ps.setLong(1, rollNo);
				rs=ps.executeQuery();
				while(rs.next()) {
					firstNameFromDB=rs.getString(1);
					System.out.println(firstNameFromDB);
				}
				model.addAttribute("name",firstNameFromDB);
				model.addAttribute("shoppingCartObj",shoppingcart);
				con.close();
				return "welcomePage";
			}
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return "errorPage";
	}
	@RequestMapping("processPurchase")
	public String calculatePrice(@ModelAttribute("shoppingCartObj") ShoppingCart shoppingcart,Model model,HttpServletRequest request) {
		String vegetables=shoppingcart.getVegSelected();
		int vegetableQuantity=shoppingcart.getVegetableQuantity();
		int vegetableAmount;
		int vegetableRate;
		String fruits=shoppingcart.getFruitSelected();
		int fruitsQuantity=shoppingcart.getFruitsQuantity();
		int fruitsAmount=0;
		int fruitsRate;
		String spices=shoppingcart.getSpicesSelected();
		int spicesQuantity=shoppingcart.getSpicesQuantity();
		int spicesAmount=0;
		int spicesRate;
		String packagedFood=shoppingcart.getPackagedFoodsSelected();
		int packagedFoodQuantity=shoppingcart.getPackagedFoodQuantity();
		int packagedFoodAmount=0;
		int packagedFoodRate;
		try {
			if(vegetables.contains("--select--")==false) {
				ResultSet rs=ProductsCartsIO.fetchProductPrice("vegetable_price",vegetables);
				while(rs.next()) {
					vegetableRate=rs.getInt(1);
					vegetableAmount=vegetableRate*vegetableQuantity;
					ProductsCartsIO.updateShoppingCart(rollNo, vegetables,vegetableRate,vegetableAmount);
					model.addAttribute("vegetable",vegetables);
					model.addAttribute("vegetableRate",vegetableRate);
					model.addAttribute("vegetableAmount", vegetableAmount);
				}
			}
			if(fruits.contains("--select--")==false) {
				ResultSet rs=ProductsCartsIO.fetchProductPrice("fruits_price", fruits);
				while(rs.next()) {
					fruitsRate=rs.getInt(1);
					fruitsAmount=fruitsRate*fruitsQuantity;
					ProductsCartsIO.updateShoppingCart(rollNo, fruits, fruitsRate, fruitsAmount);
					model.addAttribute("fruits",fruits);
					model.addAttribute("fruitsRate",fruitsRate);
					model.addAttribute("fruitsAmount", fruitsAmount);
				}
			}
			if(spices.contains("--select--")==false) {
				ResultSet rs=ProductsCartsIO.fetchProductPrice("spices_prices", spices);
				while(rs.next()) {
					spicesRate=rs.getInt(1);
					spicesAmount=spicesRate*spicesQuantity;
					ProductsCartsIO.updateShoppingCart(rollNo, spices, spicesRate, spicesAmount);
					model.addAttribute("spices",spices);
					model.addAttribute("spicesRate",spicesRate);
					model.addAttribute("spicesAmount",spicesAmount);
				}
			}
			if(packagedFood.contains("--select--")==false) {
				ResultSet rs=ProductsCartsIO.fetchProductPrice("packaged_food_prices", packagedFood);
				while(rs.next()) {
					packagedFoodRate=rs.getInt(1);
					packagedFoodAmount=packagedFoodRate*packagedFoodQuantity;
					ProductsCartsIO.updateShoppingCart(rollNo, packagedFood, packagedFoodRate, packagedFoodAmount);
					model.addAttribute("packagedFood",packagedFood);
					model.addAttribute("packagedFoodRate",packagedFoodRate);
					model.addAttribute("packagedFoodAmount",packagedFoodAmount);
					}
				
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return "billingPage";
		
	}
	@RequestMapping("/processCart")
	public String processCart(HttpServletRequest request,Model model) {
		String vegetable=request.getParameter("vegetables");
		Integer vegetableQuantity=Integer.parseInt(request.getParameter("vegetableQuantity"));
		int vegetableAmount;
		int vegetableRate;
		String fruits=request.getParameter("fruits");
		Integer fruitsQuantity=Integer.parseInt(request.getParameter("fruitsQuantity"));
		int fruitsAmount=0;
		int fruitsRate;
		String spices=request.getParameter("spices");
		Integer spicesQuantity=Integer.parseInt(request.getParameter("spicesQuantity"));
		int spicesAmount=0;
		int spicesRate;
		String packagedFood=request.getParameter("packagedFood");
		Integer packagedFoodQuantity=Integer.parseInt(request.getParameter("packagedFoodQuantity"));
		int packagedFoodAmount=0;
		int packagedFoodRate;
		try {
			Connection con= DBConnector.openConnection();
			if(vegetable.contains("--select--")==false) {
				ResultSet rs=ProductsCartsIO.fetchProductPrice("vegetable_price",vegetable);
				while(rs.next()) {
					vegetableRate=rs.getInt(1);
					vegetableAmount=vegetableRate*vegetableQuantity;
					ProductsCartsIO.updateShoppingCart(rollNo, vegetable,vegetableRate,vegetableAmount);
					model.addAttribute("product",vegetable);
					model.addAttribute("rate",vegetableRate);
					model.addAttribute("amount", vegetableAmount);
				}
			}
			if(fruits.contains("--select--")==false) {
				ResultSet rs=ProductsCartsIO.fetchProductPrice("fruits_price", fruits);
				while(rs.next()) {
					fruitsRate=rs.getInt(1);
					fruitsAmount=fruitsRate*fruitsQuantity;
					ProductsCartsIO.updateShoppingCart(rollNo, fruits, fruitsRate, fruitsAmount);
					model.addAttribute("product",fruits);
					model.addAttribute("rate",fruitsRate);
					model.addAttribute("amount", fruitsAmount);
				}
			}
			if(spices.contains("--select--")==false) {
				ResultSet rs=ProductsCartsIO.fetchProductPrice("spices_prices", spices);
				while(rs.next()) {
					spicesRate=rs.getInt(1);
					spicesAmount=spicesRate*spicesQuantity;
					ProductsCartsIO.updateShoppingCart(rollNo, spices, spicesRate, spicesAmount);
					model.addAttribute("products",spices);
					model.addAttribute("rate",spicesRate);
					model.addAttribute("amount",spicesAmount);
				}
			}
			if(packagedFood.contains("--select--")==false) {
				ResultSet rs=ProductsCartsIO.fetchProductPrice("packaged_food_prices", packagedFood);
				while(rs.next()) {
					packagedFoodRate=rs.getInt(1);
					packagedFoodAmount=packagedFoodRate*packagedFoodQuantity;
					ProductsCartsIO.updateShoppingCart(rollNo, packagedFood, packagedFoodRate, packagedFoodAmount);
					model.addAttribute("products",packagedFood);
					model.addAttribute("rate",packagedFoodRate);
					model.addAttribute("amount",packagedFoodAmount);
					}
				
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return "cartMessage";
	}
	@RequestMapping("displayCart")
	@ResponseBody
	public String displayCart() throws SQLException {
		ResultSet rs=ProductsCartsIO.retrieveFromCart(this.rollNo);
		List<String> products=new ArrayList();
		List<String> productsTobePassed=new ArrayList();
		List<Integer> amount=new ArrayList();
		List<Integer> rate=new ArrayList();
		List<Integer> amountTobePassed=new ArrayList();
		Map<String,Integer> productAmount=new HashMap<>();
		Map<String,Integer> productRateMapping=new HashMap<>();
		while(rs.next()) {
			//System.out.println(rs.getString(1));
			//System.out.println(rs.getLong(2));
			//System.out.println(rs.getLong(3));
			products.add(rs.getNString(1));
			rate.add((int)rs.getLong(2));
			amount.add((int) rs.getLong(3));
		}
		Iterator itr=amount.iterator();
		Iterator itr2=rate.iterator();
		for(String product:products) {
			if(productAmount.containsKey(product)==false) {
				productAmount.put(product, (Integer) itr.next());
				productRateMapping.put(product,(Integer) itr2.next());			}
			else {
				Integer newAmount=productAmount.get(product)+(Integer) itr.next();
				productAmount.replace(product, newAmount);
				itr2.next();
			}
		}
		productsTobePassed.addAll(0,productAmount.keySet());
		amountTobePassed.addAll(0,productAmount.values());
		//productsTobePassed=productsTobePassed+"||"+amountTobePassed;
		String json = new Gson().toJson(productsTobePassed );
		json=json+"||";
		json=json+new Gson().toJson(productRateMapping.values())+"||"+new Gson().toJson(amountTobePassed);
		System.out.println(json);
		return json;
		
	}
}

