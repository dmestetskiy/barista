package barista.objects.ingredients;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import barista.objects.ingredients.interfaces.Ingredient;
import barista.objects.ingredients.interfaces.IngredientsList;
import barista.utils.PropertiesReader;

public class IngredientsListImpl implements IngredientsList {

	private File ingredientsFile;
	private HashMap<String, Ingredient> ingredientsMap;
	private static TreeMap<String, Integer> ingredientsLeftOver;
	private Integer maxAmount;

	public IngredientsListImpl(){
		ingredientsMap = new HashMap<String, Ingredient>();
		ingredientsLeftOver = new TreeMap<String, Integer>();
	}

	public void setIngredientsFile(File ingredientsFile) {
		this.ingredientsFile = ingredientsFile;
	}
	
	public void setMaxAmount(Integer maxAmount) {
		this.maxAmount = maxAmount;
	}

	public void processFile() {
		Set<Map.Entry<Object, Object>> properties = PropertiesReader
				.readProperties(ingredientsFile);
		
		for(Object propObject:properties){
			@SuppressWarnings("unchecked")
			Map.Entry<Object, Object> entry = (Map.Entry<Object, Object>)propObject;
			addToMap(entry);
		}
	}
	
	private void addToMap(Map.Entry<Object, Object> entry){
		String propName = PropertiesReader.getPropertyName((String)entry.getKey(),"_");
		Ingredient ingredient = new IngredientImpl();
		ingredient.setIngredientName(propName);
		ingredient.setIngredientPrice(Double.valueOf((String)entry.getValue()));
		ingredientsMap.put(propName.toLowerCase(), ingredient);
		ingredientsLeftOver.put(propName.toLowerCase(), maxAmount);
	}
	
	public Double getIngredientPrice(String ingredientName){
		return ingredientsMap.get(ingredientName.toLowerCase()).getIngredientPrice();
		
	}

	public boolean doesIngredientExist(String possibleIngredientName) {
		if (ingredientsMap.containsKey(possibleIngredientName.toLowerCase())){
			return true;
		}
		return false;
	}

	public Ingredient getIngredientFromName(String ingredientName) {
		return ingredientsMap.get(ingredientName.toLowerCase());
	}

	public void printInventory() {
		System.out.println("Inventory:");
		for (Map.Entry<String, Integer> ingredient:ingredientsLeftOver.entrySet()){
			System.out.println("\t"+PropertiesReader.getPropertyName(ingredient.getKey()," ")+","+ingredient.getValue());
		}
		
	}

	public TreeMap<String, Integer> getIngredientsMap() {
		return ingredientsLeftOver;
	}

	public void restock() {
		for (String key : ingredientsLeftOver.keySet()){
			ingredientsLeftOver.put(key, maxAmount);
		}
		
	}


}
