package barista.objects.drinks.interfaces;

public interface DrinksList {

	public void processFile();
	public void printDrinks();
	public void orderDrink(Integer drinkNumber);
	public void restock();
}
