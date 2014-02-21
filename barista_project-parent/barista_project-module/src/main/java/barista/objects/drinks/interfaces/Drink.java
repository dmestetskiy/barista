package barista.objects.drinks.interfaces;

import java.util.List;
import java.util.TreeMap;

import barista.objects.IngredientsPerDrink;

public interface Drink {

	
	public String getDrinkName();
	
	public Double getDrinkPrice();
	
	public String getDrinkPriceString();
	
	public void setDrinkName(String name);
	
	public void setDrinkPrice(Double price);
	
	public void addToIngredientsList(IngredientsPerDrink ingredientsPerDrink);
	
	public void printDrink();
	
	public boolean checkAvailability(TreeMap<String, Integer> ingredientsMap);
	
	public List<IngredientsPerDrink> getIngredientsPerDrinkList();
}
