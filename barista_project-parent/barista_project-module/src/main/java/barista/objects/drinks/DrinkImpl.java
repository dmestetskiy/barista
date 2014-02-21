package barista.objects.drinks;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import barista.objects.IngredientsPerDrink;
import barista.objects.drinks.interfaces.Drink;
import barista.utils.PropertiesReader;

public class DrinkImpl implements Drink {

	private String drinkName;
	private Double drinkPrice;
	private List<IngredientsPerDrink> ingredientsPerDrinkList;

	public DrinkImpl() {
		drinkPrice = Double.valueOf(0);
		ingredientsPerDrinkList = new ArrayList<IngredientsPerDrink>();
	}

	public String getDrinkName() {
		return drinkName;
	}

	public Double getDrinkPrice() {
		if (drinkPrice.equals(Double.valueOf(0))) {
			for (IngredientsPerDrink ipd : ingredientsPerDrinkList) {
				drinkPrice += ipd.getTotalPrice();
			}
		}
		return drinkPrice;
	}

	public void setDrinkName(String name) {
		drinkName = name;

	}

	public void setDrinkPrice(Double price) {
		drinkPrice = price;
	}

	public void addToIngredientsList(IngredientsPerDrink ingredientsPerDrink) {
		ingredientsPerDrinkList.add(ingredientsPerDrink);
	}

	public void printDrink() {
		System.out.println("Drink Name:" + drinkName + " price:"
				+ PropertiesReader.getFormatValue(getDrinkPrice()));
		for (IngredientsPerDrink idp : ingredientsPerDrinkList) {
			System.out.println(" Ingredient:" + idp.getIngredientName()
					+ " amount:" + idp.getAmountOfIngredients()
					+ " totalPrice:"
					+ PropertiesReader.getFormatValue(idp.getTotalPrice()));
		}

	}

	public String getDrinkPriceString() {
		return "$" + PropertiesReader.getFormatValue(getDrinkPrice());
	}

	public boolean checkAvailability(TreeMap<String, Integer> ingredientsMap) {
		boolean enoughIngredients = true;
		for (IngredientsPerDrink idp : ingredientsPerDrinkList) {
			if (!(idp.getIngredientName() != null && ingredientsMap.containsKey(idp.getIngredientName()
					.toLowerCase()) && ingredientsMap.get(idp
					.getIngredientName().toLowerCase()) >= idp
					.getAmountOfIngredients())) {
				enoughIngredients = false;
				break;
			}
		}
		return enoughIngredients;
	}

	public List<IngredientsPerDrink> getIngredientsPerDrinkList() {
		return ingredientsPerDrinkList;
	}

}
