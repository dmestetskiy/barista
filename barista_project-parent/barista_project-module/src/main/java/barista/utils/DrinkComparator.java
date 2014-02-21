package barista.utils;

import java.util.Comparator;

import barista.objects.drinks.interfaces.Drink;

public class DrinkComparator implements Comparator<Drink>{

	public int compare(Drink d1, Drink d2) {
		return d1.getDrinkName().compareTo(d2.getDrinkName());
	}

}
