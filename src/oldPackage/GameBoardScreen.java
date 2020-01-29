package oldPackage;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.UIManager;

import programLogic.*;
import programLogic.computerOpponentLogic.EasyMode;

public class GameBoardScreen extends JPanel
{
	public GameBoardScreen()
	{
		this.setLayout(new GridLayout(GameBoard.getDimension(), GameBoard.getDimension()));
		this.setUpGameBoard();
		// Now, let the computer make the opening move.
		// Because no moves have been made yet, we use easyMode to randomly select a spot:
		EasyMode.easyMode();
	}
	
	
	private void setUpGameBoard()
	{
		GameBoard.setUpNewGameBoard();
		displayGameBoardTilesOnScreen();
		this.revalidate();
		this.repaint();
		UIManager.getDefaults().put("Button.disabledText", Color.BLACK);
	}
	
	
	private void displayGameBoardTilesOnScreen()
	{
		for (int currentRow = 0; currentRow < GameBoard.getDimension(); ++currentRow)
		{
			for (int currentColumn = 0; currentColumn < GameBoard.getDimension(); ++currentColumn)
			{
				GameTile currentTile = GameBoard.getGameTileAt(currentRow, currentColumn);
				this.add(currentTile);
			}
		}
	}
		
} // End of class.
