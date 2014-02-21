package barista.objects.ingredients.interfaces;

import java.util.TreeMap;

public interface IngredientsList {

	public void processFile();
	
	public boolean doesIngredientExist(String possibleIngredientName);
	
	public Double getIngredientPrice(String ingredientName);
	
	public Ingredient getIngredientFromName(String ingredientName);
	
	public void printInventory();
	
	public TreeMap<String, Integer> getIngredientsMap();
	
	public void restock();
}
