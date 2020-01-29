package programLogic;

import gui.gameScreen.GameTile;
import gui.programWindow.GameEndWindow;

public class CheckGameStatus
{
	private static GameTile selectedTile;
	private static String selectedTileText;
	private static int selectedTileRowLocation, selectedTileColumnLocation;
	private static String opponent;
	
	
	/**
	 * This method checks the row, column, and potentially the diagonal(s) that run through the passed in
	 * selectedTile for completion by either the player, or the computer (but not both in the same row, column,
	 * or diagonal).
	 * 
	 * @param selectedTile (either from the player or the computer)
	 */
	public static void checkGameStatus(GameTile selectedTile)
	{
		// There's no reason to re-scan the whole game board, because we know where the most recent change occurred
		// (selectedTile).
		extractSelectedTileInformation(selectedTile);
		determineWhoIsTheOpponentThisTurn();
		checkForACompletedRow();
		checkForACompletedColumn();
		checkForACompletedDiagonal();
		checkForAStalemate();
	}
	
	
	private static void extractSelectedTileInformation(GameTile selectedTile)
	{
		CheckGameStatus.selectedTile = selectedTile;
		selectedTileText = selectedTile.getText();
		selectedTileRowLocation = selectedTile.row;
		selectedTileColumnLocation = selectedTile.column;
	}
	
	
	private static void determineWhoIsTheOpponentThisTurn()
	{
		if (selectedTileText.equals(Game.getPlayerSymbol()))
		{
			opponent = Game.getComputerSymbol();
		}
		else
		{
			opponent = Game.getPlayerSymbol();
		}
	}
	
	
	private static void checkForACompletedRow()
	{
		int rowTileCount;
		
		
		// Starting from the left, scan horizontally for X GameTiles in the row the selectedTile is in:
		rowTileCount = 0;
		for (int currentColumn = 0; currentColumn < GameBoard.getDimension(); ++currentColumn)
		{
			// If we find a GameTile that is the opposite of the selectedTile, then abandon the rest of the search:
			if (GameBoard.getGameTileAt(selectedTileRowLocation, currentColumn).getText().equals(opponent))
			{
				return;
			}
			
			// If we find another GameTile that is from the same side (player or computer) as the selectedTile,
			// then increment the count:
			if (GameBoard.getGameTileAt(selectedTileRowLocation, currentColumn).getText().equals(selectedTileText))
			{
				++rowTileCount;
			}
			
			// Else, move right one spot.
		}
		
		if (rowTileCount == GameBoard.getDimension())
		{
			Game.setHasEnded(true);
			new GameEndWindow(selectedTileText + " has won a row!");
		}
	}
	
	
	private static void checkForACompletedColumn()
	{
		int columnTileCount;
		
		
		// Starting from the top, scan vertically for X GameTiles in the column the selectedTile is in:
		columnTileCount = 0;
		for (int currentRow = 0; currentRow < GameBoard.getDimension(); ++currentRow)
		{
			// If we find a GameTile that is the opposite of the selectedTile, then abandon the rest of the search:
			if (GameBoard.getGameTileAt(currentRow, selectedTileColumnLocation).getText().equals(opponent))
			{
				return;
			}
			
			// If we find another GameTile that is from the same side (player or computer) as the selectedTile,
			// then increment the count:
			if (GameBoard.getGameTileAt(currentRow, selectedTileColumnLocation).getText().equals( selectedTileText ))
			{
				++columnTileCount;
			}
			
			// Else, move up one spot.
		}
		
		if (columnTileCount == GameBoard.getDimension())
		{
			Game.setHasEnded(true);
			new GameEndWindow(selectedTileText + " has won a column!");
		}
	}
	
	
	private static void checkForACompletedDiagonal()
	{
		checkForCompletedUpRightDiagonal();
		checkForCompletedDownRightDiagonal();
	}
	
	
	private static void checkForCompletedUpRightDiagonal()
	{
		int upRightDiagonalTileCount;
		
		
		// Starting from the bottom-left corner, moving up-right, through the center, to the top-right corner,
		// check for X GameTiles in a row diagonally:
		upRightDiagonalTileCount = 0;
		for (int row = GameBoard.getDimension() - 1, column = 0; row >= 0 && column < GameBoard.getDimension(); --row, ++column)
		{
			// If we find a GameTile that is the opposite of the selectedTile, then abandon the rest of the search:
			if (GameBoard.getGameTileAt(row, column).getText().equals(opponent))
			{
				break;
			}
			
			// If we find another GameTile that is from the same side (player or computer) as the selectedTile,
			// then increment the count:
			if (GameBoard.getGameTileAt(row, column).getText().equals( selectedTileText ))
			{
				++upRightDiagonalTileCount;
			}
			
		}
		
		if (upRightDiagonalTileCount == GameBoard.getDimension())
		{
			Game.setHasEnded(true);
			new GameEndWindow(selectedTileText + " has won the NE diagonal!");
		}
	}
	
	
	private static void checkForCompletedDownRightDiagonal()
	{
		int downRightDiagonalTileCount;
		
		
		// Starting from the top-left corner, moving down-right, through the center, to the bottom-right corner,
		// check for X GameTiles in a row diagonally:
		downRightDiagonalTileCount = 0;
		for (int row = 0, column = 0; row < GameBoard.getDimension() && column < GameBoard.getDimension(); ++row, ++column)
		{
			// If we find a GameTile that is the opposite of the selectedTile, then abandon the rest of the search:
			if (GameBoard.getGameTileAt(row, column).getText().equals(opponent))
			{
				break;
			}
			
			// If we find another GameTile that is from the same side (player or computer) as the selectedTile,
			// then increment the count:
			if (GameBoard.getGameTileAt(row, column).getText().equals( selectedTileText ))
			{
				++downRightDiagonalTileCount;
			}
		}
		
		if (downRightDiagonalTileCount == GameBoard.getDimension())
		{
			Game.setHasEnded(true);
			new GameEndWindow(selectedTileText + " has won the SW diagonal!");
		}
	}
	
	
	private static void checkForAStalemate()
	{
		// Finally, it is possible that the game has ended in a draw/stale mate. Check if all spots on the game board
		// have been filled:
		int totalNumberOfSpotsOnGameBoard = GameBoard.getDimension() * GameBoard.getDimension();
		if (Game.getNumberOfSpotsClaimed() == totalNumberOfSpotsOnGameBoard)
		{
			Game.setHasEnded(true);
			new GameEndWindow("DRAW!");
		}		
	}

} // End of class.
