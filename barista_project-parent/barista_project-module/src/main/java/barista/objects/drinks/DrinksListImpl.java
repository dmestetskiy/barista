package barista.objects.drinks;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

import barista.objects.IngredientsPerDrink;
import barista.objects.drinks.interfaces.Drink;
import barista.objects.drinks.interfaces.DrinksList;
import barista.objects.ingredients.interfaces.IngredientsList;
import barista.utils.Constants;
import barista.utils.DrinkComparator;
import barista.utils.PropertiesReader;

public class DrinksListImpl implements DrinksList {

	private File drinksFile;
	private List<Drink> drinksList;
	private IngredientsList ingredients;
	
	public DrinksListImpl(){
		drinksList = new ArrayList<Drink>();
	}
	
	public void setDrinksFile(File drinksFile) {
		this.drinksFile = drinksFile;
	}
	
	public void setIngredients(IngredientsList ingredients) {
		this.ingredients = ingredients;
	}

	public void processFile() {
		Set<Map.Entry<Object, Object>> properties = PropertiesReader.readProperties(drinksFile);
		
		for(Object propObject:properties){
			@SuppressWarnings("unchecked")
			Map.Entry<Object, Object> entry = (Map.Entry<Object, Object>)propObject;
			addToMap(entry);
		}
		Collections.sort(drinksList, new DrinkComparator());
	
	}
	
	private void addToMap(Map.Entry<Object, Object> entry){
		String propName = PropertiesReader.getPropertyName((String)entry.getKey(),"_");
		Drink drink = new DrinkImpl();
		drink.setDrinkName(propName);
		String ingredientsPerDrink = (String)entry.getValue();
		List<String> ingredientsList = (List<String>) Arrays.asList(ingredientsPerDrink.split(","));

		for (String ingredientLine:ingredientsList){
			drink.addToIngredientsList(processIngredientLine(ingredientLine));
		}
		
		drinksList.add(drink);
	}
	
	private IngredientsPerDrink processIngredientLine(String ingredientLine){
		String newLine = ingredientLine.toLowerCase().replaceAll("units of", "\\+").replaceAll("unit of", "\\+");
		List<String> ingredientLineSplit = (List<String>) Arrays.asList(newLine.split("\\+"));
		Integer numberOfIngredients = Integer.valueOf(0);
		IngredientsPerDrink ingredientPerDrink = new IngredientsPerDrink();
		for (String partOfLine:ingredientLineSplit){
			String partOfLineTrimmed = partOfLine.trim();
			if(ingredients.doesIngredientExist(partOfLineTrimmed)){
				ingredientPerDrink.setIngredient(ingredients.getIngredientFromName(partOfLineTrimmed));
			}else{
				try {
					numberOfIngredients = Integer.valueOf(partOfLineTrimmed);
					ingredientPerDrink.setAmountOfIngredients(numberOfIngredients);
				} catch (Exception e) {
					numberOfIngredients = Integer.valueOf(0);
				}
			}
		}
		return ingredientPerDrink;
	}

	public void printDrinks() {
		System.out.println("Menu:");
		for (int i=0; i < drinksList.size();i++){
			int index = Integer.valueOf(i+1);
			Drink drink = drinksList.get(i);
			System.out.println(index+","+drink.getDrinkName()+","+drink.getDrinkPriceString()+"," + drink.checkAvailability(ingredients.getIngredientsMap()));
		}
	}

	public void orderDrink(Integer drinkNumber) {
		Drink drinkToOrder = null;
		if (drinkNumber>0 && drinkNumber<=drinksList.size()){
			drinkToOrder = drinksList.get(drinkNumber-1);
			if(drinkToOrder.checkAvailability(ingredients.getIngredientsMap())){
				for(IngredientsPerDrink idp : drinkToOrder.getIngredientsPerDrinkList())
				{
					String key = idp.getIngredientName().toLowerCase();
					Integer total = ingredients.getIngredientsMap().get(key);
					total -= idp.getAmountOfIngredients();
					ingredients.getIngredientsMap().put(key, total);
				}
				System.out.println(">>>>>> "+Constants.DISPENSING + drinkToOrder.getDrinkName());
			}else{
				System.out.println(">>>>>> "+Constants.OUT+ drinkToOrder.getDrinkName());
			}
		}else{
			System.out.println(">>>>>> "+Constants.INVALID+drinkNumber);
		}
		
	}

	public void restock() {
		ingredients.restock();
		
	}

	
}
