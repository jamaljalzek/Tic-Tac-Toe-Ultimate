/**
 * @author Jamal Alzek
 * 
 * This the main class that is referenced when the program executes.
 * Specifically, a new MainWindow is created, which then immediately displays the StartNewGameScreen.
 */
public class StartHere
{
	public static void main (String [] args)
	{
		MainWindow mainWindow;
		StartNewGameScreen startNewGameScreen;
		
		
		mainWindow = new MainWindow();
		
		startNewGameScreen = new StartNewGameScreen(mainWindow);
		
		mainWindow.add(startNewGameScreen);
		//mainWindow.add(game.gameBoardGUI);
		
		mainWindow.setVisible(true);
		
	} // End of method main.
	
} // End of class StartHere.
