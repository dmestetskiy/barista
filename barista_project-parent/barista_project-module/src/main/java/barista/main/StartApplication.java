package barista.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import barista.objects.drinks.interfaces.DrinksList;
import barista.objects.ingredients.interfaces.IngredientsList;
import barista.utils.Constants;
import barista.utils.UserInputReader;

public class StartApplication {

	public static void main(String[] args) {
		ApplicationContext context = new FileSystemXmlApplicationContext("config/spring/applicationContext.xml");
		
		DrinksList di = (DrinksList) context.getBean("drinks");
		IngredientsList ingredientList = (IngredientsList) context.getBean("ingredients");
		ingredientList.printInventory();
		di.printDrinks();
		String command = null;
		while((command = UserInputReader.readCommand())!=null){
		
			if (UserInputReader.checkIfInteger(command)){
				di.orderDrink(Integer.valueOf(command)); 
			}else if (UserInputReader.checkIfValidCommand(command)){
				di.restock();
			}else if(command.equals(Constants.Q)){
				break;
			}
			else{
				System.out.println(Constants.INVALID+command);
			}
			ingredientList.printInventory();
			di.printDrinks();
		}
	}
	
}
