package gui.menuScreen;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

import gui.menuScreen.selectGameSettingsPanel.SelectGameSettingsPanel;

public class StartNewGameScreen extends JPanel
{
	/**
	 * This is the first screen that is displayed when the program launches. It is here where the player
	 * chooses the dimension of the game board, as well as the difficulty the computer will play as.
	 * Once they click on the "Start!" button, the mainWindow then displays the GameBoardScreen.
	 */
	public StartNewGameScreen()
	{
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.add(Box.createVerticalGlue());
		this.add(new SelectGameSettingsPanel());
		this.add(Box.createVerticalGlue());
		this.add(new ButtonsPanel());
		this.add(Box.createVerticalGlue());
	}
	
} // End of class.
