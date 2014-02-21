barista
=======
to run the program (requires java 1.6)
run java -jar barista_project-module-0.0.1-jar-with-dependencies.jar

to change drink prices or drinks go to config folder
to change max number of drinks go to config/spring/applicationContext.xml, change value under bean maxAmountOfIngredient
config folder must be in the same directory as the jar
The menu consists of :
	menu #, menu item, if can order or not
 drink properties indicates how to make each drink
 ingredients - indicates the price of each ingridient
	

source is provided under barista_project-parent

accepted commands are: 1-6, r - to restock, q -to quit
