

public class Game
{
	protected final int dimension;
	protected final String playerSymbol, computerSymbol;
	protected GameTile [] [] gameBoard;
	protected boolean isPlayersTurn;
	protected String difficulty; // Set to either “EASY”, “MEDIUM”, “HARD”, etc.
	protected GameBoardScreen gameBoardGUI;
	
	
	public Game (int dimension, String playerSymbol, String computerSymbol, String difficulty)
	{
		this.dimension = dimension;
		this.playerSymbol = playerSymbol;
		this.computerSymbol = computerSymbol;
		this.difficulty = difficulty;
		gameBoardGUI = new GameBoardScreen(this);
		
	} // End of Constructor.
	
	
	/**
	 * This method checks the row, column, and potentially the diagonal(s) that run through the passed in
	 * selectedTile for completion by either the player, or the computer (but not both in the same row, column,
	 * or diagonal).
	 * 
	 * @param selectedTile (either from the player or the computer)
	 */
	public void checkGameStatus (GameTile selectedTile)
	{
		String opponent;
		int rowTileCount, columnTileCount, bottomLeftdiagonalTileCount, topLeftdiagonalTileCount;
		
		
		// There's no reason to re-scan the whole game board, because we know where the most recent change occurred
		// (selectedTile).
		// Check for a completed row, column, or diagonal by either the player or the computer (but not both):
		
		// If the player made the most recent move, then the computer is the opponent, who's GameTiles
		// we want to avoid running into during our search:
		if ( selectedTile.getText().equals(playerSymbol) )
		{
			opponent = computerSymbol;
		}
		// Otherwise, the computer made the most recent move, so we will need to be wary of any of the player's
		// GameTiles when searching:
		else
		{
			opponent = playerSymbol;
		}
		
		// Starting from the left, scan horizontally for X GameTiles in the row the selectedTile is in:
		rowTileCount = 0;
		for (int currentColumn = 0; currentColumn < dimension; ++currentColumn)
		{
			// If we find a GameTile that is the opposite of the selectedTile, then abandon the rest of the search:
			if ( gameBoard [selectedTile.row] [currentColumn].getText().equals(opponent) )
			{
				break;
			}
			
			// If we find another GameTile that is from the same side (player or computer) as the selectedTile,
			// then increment the count:
			if ( gameBoard [selectedTile.row] [currentColumn].getText().equals( selectedTile.getText() ) )
			{
				++rowTileCount;
			}
			
			// Else, move right one spot.
		}
		
		// Starting from the bottom, scan vertically for X GameTiles in the column the selectedTile is in:
		columnTileCount = 0;
		for (int currentRow = 0; currentRow < dimension; ++currentRow)
		{
			// If we find a GameTile that is the opposite of the selectedTile, then abandon the rest of the search:
			if ( gameBoard [currentRow] [selectedTile.column].getText().equals(opponent) )
			{
				break;
			}
			
			// If we find another GameTile that is from the same side (player or computer) as the selectedTile,
			// then increment the count:
			if ( gameBoard [currentRow] [selectedTile.column].getText().equals( selectedTile.getText() ) )
			{
				++columnTileCount;
			}
			
			// Else, move up one spot.
		}
		
		// Check both of the diagonals for completion:
		
		// Starting from the bottom-left corner, moving up-right, through the center, to the top-right corner,
		// check for X GameTiles in a row diagonally:
		bottomLeftdiagonalTileCount = 0;
		for (int row = dimension - 1, column = 0; row >= 0 && column < dimension; --row, ++column)
		{
			// If we find a GameTile that is the opposite of the selectedTile, then abandon the rest of the search:
			if ( gameBoard [row] [column].getText().equals(opponent) )
			{
				break;
			}
			
			// If we find another GameTile that is from the same side (player or computer) as the selectedTile,
			// then increment the count:
			if ( gameBoard [row] [column].getText().equals( selectedTile.getText() ) )
			{
				++bottomLeftdiagonalTileCount;
			}
			
		}
		// Starting from the top-left corner, moving down-right, through the center, to the bottom-right corner,
		// check for X GameTiles in a row diagonally:
		topLeftdiagonalTileCount = 0;
		for (int row = 0, column = 0; row < dimension && column < dimension; ++row, ++column)
		{
			// If we find a GameTile that is the opposite of the selectedTile, then abandon the rest of the search:
			if ( gameBoard [row] [column].getText().equals(opponent) )
			{
				break;
			}
			
			// If we find another GameTile that is from the same side (player or computer) as the selectedTile,
			// then increment the count:
			if ( gameBoard [row] [column].getText().equals( selectedTile.getText() ) )
			{
				++bottomLeftdiagonalTileCount;
			}
		}
		
		// Finally, check to see if the player/computer has completed a row, column, and/or one of the two diagonals:
		if (rowTileCount == dimension || columnTileCount == dimension ||
			bottomLeftdiagonalTileCount == dimension || topLeftdiagonalTileCount == dimension)
		{
			System.out.println(selectedTile.getText() + "has won!");
			new GameEndWindow();
		}
		
	} // End of method checkGameStatus.
	
} // End of class Game.
