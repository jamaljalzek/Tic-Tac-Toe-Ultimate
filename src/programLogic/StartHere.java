package programLogic;

import gui.StartNewGameScreen;
import gui.programWindow.MainWindow;

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
		MainWindow mainWindow = new MainWindow();
		StartNewGameScreen startNewGameScreen = new StartNewGameScreen(mainWindow);
		mainWindow.add(startNewGameScreen);
		mainWindow.setVisible(true);
	}
	
} // End of class.
