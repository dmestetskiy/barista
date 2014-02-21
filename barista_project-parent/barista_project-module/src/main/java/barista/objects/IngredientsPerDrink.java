package barista.objects;

import barista.objects.ingredients.interfaces.Ingredient;

public class IngredientsPerDrink {

	private Ingredient ingredient;
	private Integer amountOfIngredients;
	private Double totalPrice;
	private String ingredientName;
	
	public IngredientsPerDrink(){
		totalPrice = Double.valueOf(0);
	}
	
	public Ingredient getIngredient() {
		return ingredient;
	}
	public void setIngredient(Ingredient ingredient) {
		this.ingredient = ingredient;
		ingredientName = ingredient.getIngredientName();
	}
	public Integer getAmountOfIngredients() {
		return amountOfIngredients;
	}
	public void setAmountOfIngredients(Integer amountOfIngredients) {
		this.amountOfIngredients = amountOfIngredients;
	}
	public Double getTotalPrice() {
		if (totalPrice.equals(Double.valueOf(0)) && ingredient != null){
			totalPrice =amountOfIngredients*ingredient.getIngredientPrice();
		}
		return totalPrice;
	}
	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}
	public String getIngredientName() {
		if ((ingredientName == null || ingredientName.length() == 0) && ingredient != null ){
			ingredientName = ingredient.getIngredientName();
		}
		return ingredientName;
	}
	
}
