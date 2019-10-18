

public class Game
{
	protected final int dimension;
	protected int numberOfSpotsClaimed;
	protected final String playerSymbol, computerSymbol;
	protected GameTile [] [] gameBoard;
	protected boolean hasEnded;
	protected boolean isPlayersTurn;
	protected String difficulty; // Set to either “EASY”, “MEDIUM”, “HARD”, etc.
	
	
	public Game (int dimension, String playerSymbol, String computerSymbol, String difficulty)
	{
		this.dimension = dimension;
		this.numberOfSpotsClaimed = 0;
		this.playerSymbol = playerSymbol;
		this.computerSymbol = computerSymbol;
		this.hasEnded = false;
		this.difficulty = difficulty;
		ComputerMakeAMove.thisGame = this;
		
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
		
		
		/////////////////////////////////// DEBUGGING ///////////////////////////////////
		System.out.println("Number of tiles claimed: " + numberOfSpotsClaimed);
		/////////////////////////////////////////////////////////////////////////////////
		
		// There's no reason to re-scan the whole game board, because we know where the most recent change occurred
		// (selectedTile).
		opponent = determineWhoIsTheOpponentThisTurn(selectedTile);
		checkForACompletedRow(selectedTile, opponent);
		checkForACompletedColumn(selectedTile, opponent);
		checkForACompletedDiagonal(selectedTile, opponent);
		checkForAStalemate();
		
	} // End of method checkGameStatus.
	
	
	private String determineWhoIsTheOpponentThisTurn (GameTile selectedTile)
	{
		if ( selectedTile.getText().equals(playerSymbol) )
		{
			return computerSymbol;
		}
		else
		{
			return playerSymbol;
		}

	} // End of method determineWhoIsTheOpponentThisTurn.
	
	
	private void checkForACompletedRow (GameTile selectedTile, String opponent)
	{
		int rowTileCount;
		
		
		// Starting from the left, scan horizontally for X GameTiles in the row the selectedTile is in:
		rowTileCount = 0;
		for (int currentColumn = 0; currentColumn < dimension; ++currentColumn)
		{
			// If we find a GameTile that is the opposite of the selectedTile, then abandon the rest of the search:
			if ( gameBoard [selectedTile.row] [currentColumn].getText().equals(opponent) )
			{
				return;
			}
			
			// If we find another GameTile that is from the same side (player or computer) as the selectedTile,
			// then increment the count:
			if ( gameBoard [selectedTile.row] [currentColumn].getText().equals( selectedTile.getText() ) )
			{
				++rowTileCount;
			}
			
			// Else, move right one spot.
		}
		
		if (rowTileCount == dimension)
		{
			this.hasEnded = true;
			new GameEndWindow(selectedTile.getText() + " has won a row!");
		}

	} // End of method checkForACompletedRow.
	
	
	private void checkForACompletedColumn (GameTile selectedTile, String opponent)
	{
		int columnTileCount;
		
		
		// Starting from the top, scan vertically for X GameTiles in the column the selectedTile is in:
		columnTileCount = 0;
		for (int currentRow = 0; currentRow < dimension; ++currentRow)
		{
			// If we find a GameTile that is the opposite of the selectedTile, then abandon the rest of the search:
			if ( gameBoard [currentRow] [selectedTile.column].getText().equals(opponent) )
			{
				return;
			}
			
			// If we find another GameTile that is from the same side (player or computer) as the selectedTile,
			// then increment the count:
			if ( gameBoard [currentRow] [selectedTile.column].getText().equals( selectedTile.getText() ) )
			{
				++columnTileCount;
			}
			
			// Else, move up one spot.
		}
		
		if (columnTileCount == dimension)
		{
			this.hasEnded = true;
			new GameEndWindow(selectedTile.getText() + " has won a column!");
		}
		
	} // End of method checkForACompletedColumn.
	
	
	private void checkForACompletedDiagonal (GameTile selectedTile, String opponent)
	{
		checkForCompletedUpRightDiagonal(selectedTile, opponent);
		checkForCompletedDownRightDiagonal(selectedTile, opponent);
		
	} // End of method checkForACompletedDiagonal.
	
	
	private void checkForCompletedUpRightDiagonal (GameTile selectedTile, String opponent)
	{
		int upRightDiagonalTileCount;
		
		
		// Starting from the bottom-left corner, moving up-right, through the center, to the top-right corner,
		// check for X GameTiles in a row diagonally:
		upRightDiagonalTileCount = 0;
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
				++upRightDiagonalTileCount;
			}
			
		}
		
		if (upRightDiagonalTileCount == dimension)
		{
			this.hasEnded = true;
			new GameEndWindow(selectedTile.getText() + " has won the NE diagonal!");
		}

	} // End of method checkForCompletedUpRightDiagonal.
	
	
	private void checkForCompletedDownRightDiagonal (GameTile selectedTile, String opponent)
	{
		int downRightDiagonalTileCount;
		
		
		// Starting from the top-left corner, moving down-right, through the center, to the bottom-right corner,
		// check for X GameTiles in a row diagonally:
		downRightDiagonalTileCount = 0;
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
				++downRightDiagonalTileCount;
			}
		}
		
		if (downRightDiagonalTileCount == dimension)
		{
			this.hasEnded = true;
			new GameEndWindow(selectedTile.getText() + " has won the SW diagonal!");
		}
		
	} // End of method checkForCompletedDownRightDiagonal.
	
	
	private void checkForAStalemate ()
	{
		// Finally, it is possible that the game has ended in a draw/stale mate. Check if all spots on the game board
		// have been filled:
		if ( numberOfSpotsClaimed == (dimension * dimension) )
		{
			this.hasEnded = true;
			new GameEndWindow("DRAW!");
		}
				
	} // End of method checkForAStalemate.
	
} // End of class Game.
