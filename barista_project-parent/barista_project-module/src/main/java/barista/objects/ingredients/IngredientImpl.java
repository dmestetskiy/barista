package barista.objects.ingredients;

import barista.objects.ingredients.interfaces.Ingredient;


public class IngredientImpl implements Ingredient {

	private String ingredientName;
	private Double ingredientPrice;
	
	
	public String getIngredientName() {
		return ingredientName;
	}
	public Double getIngredientPrice() {
		return ingredientPrice;
	}
	public void setIngredientName(String name) {
		ingredientName = name;
	}
	public void setIngredientPrice(Double price) {
		ingredientPrice = price;
	}
	
	
	
}
