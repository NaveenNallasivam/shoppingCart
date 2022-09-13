package com.java.shoppingCart;
import java.util.*;

public class ShoppingCart {
	
	private String vegSelected;
	private String fruitSelected;
	private String spicesSelected;
	private String packagedFoodsSelected;
	private List <String> vegetablesList=new ArrayList<String>();
	private List <String> fruitsList=new ArrayList();
	private List <String> spicesList=new ArrayList();
	private List <String> packagedFoodsList=new ArrayList();
	private int vegetableQuantity;
	private int fruitsQuantity;
	private int spicesQuantity;
	private int packagedFoodQuantity;
	private int[] quantity= {1,2,3,4,5,6,7,8,9,10};
	
	public ShoppingCart() {
		vegetablesList.add("--select--");
		vegetablesList.add("Tomatoes");
		vegetablesList.add("Chillies");
		vegetablesList.add("Brinjal");
		fruitsList.add("--select--");
		fruitsList.add("Apples");
		fruitsList.add("Oranges");
		fruitsList.add("Grapes");
		spicesList.add("--select--");
		spicesList.add("Pepper");
		spicesList.add("ChillyPowder");
		spicesList.add("BriyaniLeaf");
		packagedFoodsList.add("--select--");
		packagedFoodsList.add("Maggie");
		packagedFoodsList.add("Pasta");
		packagedFoodsList.add("Sause");
		
	}


	public List<String> getVegetablesList() {
		return vegetablesList;
	}



	public String getVegSelected() {
		return vegSelected;
	}

	public void setVegSelected(String vegSelected) {
		this.vegSelected = vegSelected;
	}


	public String getFruitSelected() {
		return fruitSelected;
	}


	public void setFruitSelected(String fruitSelected) {
		this.fruitSelected = fruitSelected;
	}


	public String getSpicesSelected() {
		return spicesSelected;
	}


	public void setSpicesSelected(String spicesSelected) {
		this.spicesSelected = spicesSelected;
	}


	public String getPackagedFoodsSelected() {
		return packagedFoodsSelected;
	}


	public void setPackagedFoodsSelected(String packagedFoodsSelected) {
		this.packagedFoodsSelected = packagedFoodsSelected;
	}


	public List<String> getFruitsList() {
		return fruitsList;
	}


	public List<String> getSpicesList() {
		return spicesList;
	}


	public List<String> getPackagedFoodsList() {
		return packagedFoodsList;
	}


	public int getVegetableQuantity() {
		return vegetableQuantity;
	}


	public void setVegetableQuantity(int vegetableQuantity) {
		this.vegetableQuantity = vegetableQuantity;
	}


	public int getFruitsQuantity() {
		return fruitsQuantity;
	}


	public void setFruitsQuantity(int fruitsQuantity) {
		this.fruitsQuantity = fruitsQuantity;
	}


	public int getSpicesQuantity() {
		return spicesQuantity;
	}


	public void setSpicesQuantity(int spicesQuantity) {
		this.spicesQuantity = spicesQuantity;
	}


	public int getPackagedFoodQuantity() {
		return packagedFoodQuantity;
	}


	public void setPackagedFoodQuantity(int packageedFoodQuantity) {
		this.packagedFoodQuantity = packageedFoodQuantity;
	}


	public int[] getQuantity() {
		return quantity;
	}

	/*public void setVegetables(List<String> vegetables) {
		this.vegetables = vegetables;
	}*/

}
