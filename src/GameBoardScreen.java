import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.UIManager;


public class GameBoardScreen extends JPanel
{
	private Game thisGame;
	
	
	public GameBoardScreen (Game thisGame)
	{
		this.thisGame = thisGame;
		
		this.setLayout( new GridLayout(thisGame.dimension, thisGame.dimension) );
		this.setUpGameBoard();
		
		// Now, let the computer make the opening move.
		// Because no moves have been made yet, we use easyMode to randomly select a spot:
		ComputerMakeAMove.easyMode(thisGame);
		
	} // End of Constructor.
	
	
	private void setUpGameBoard ()
	{
		GameTile currentTile;
		
		
		// Keep track of all the GameTiles on the game board:
		thisGame.gameBoard = new GameTile [thisGame.dimension] [thisGame.dimension];
		// Rows:
		for (int row = 0; row < thisGame.dimension; ++row)
		{
			// Columns:
			for (int column = 0; column < thisGame.dimension; ++column)
			{
				// For the current row and column location, create, add, and keep track of a new GameTile:
				currentTile = new GameTile(row, column);
				currentTile.setText("" + row + ", " + column);
				currentTile.addActionListener( new ButtonListener() );
				thisGame.gameBoard [row] [column] = currentTile;
				this.add(currentTile);
			}
		}
		
		this.revalidate();
		this.repaint();
		
		UIManager.getDefaults().put("Button.disabledText", Color.BLACK);
		
	} // End of method setUpGameBoard.
	
	private class ButtonListener implements ActionListener
	{
		@Override
		public void actionPerformed (ActionEvent event)
		{
			GameTile clickedTile;
			
			
			// Find the GameTile the player clicked on, and claim it for them.
			clickedTile = (GameTile) event.getSource();
			clickedTile.setText(thisGame.playerSymbol);
			clickedTile.setEnabled(false);
			++thisGame.numberOfSpotsClaimed;
			thisGame.isPlayersTurn = false;
			
			//System.out.println( thisGame.gameBoard [clickedTile.row] [clickedTile.column].getText() );
			
			System.out.println("Inside actionPerformed");
			thisGame.checkGameStatus(clickedTile);
			
			// Now, let the computer make a move in response to the player:
			if ( thisGame.difficulty.equals("EASY") )
			{
				System.out.println("Inside actionPerformed EASY");
				ComputerMakeAMove.easyMode(thisGame);
			}
			else if ( thisGame.difficulty.equals("MEDIUM") )
			{
				ComputerMakeAMove.mediumMode(thisGame);
			}
			else //  thisGame.difficulty.equals("HARD") 
			{
				ComputerMakeAMove.hardMode(thisGame);
			}
			
		} // End of method actionPerformed.
		
	} // End of class ButtonListener.
	
} // End of class GameBoard.
