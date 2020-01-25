package programLogic;

import oldPackage.Game;

public class PreventPlayerFromWinning
{
	private static int playerTileCount;
	private static int emptyColumnIndex, emptyRowIndex;
	
	
	public static void blockPlayerIfTheyAreAboutToWin()
	{
		checkRows();
		checkColumns();
		checkDiagonals();
	}
	
	
	private static void checkRows ()
	{
		// In each row, scan through it and check if the player is 1 turn away from completing it,
		// thus winning the game:
		// Search through each row on the game board:
		for (int row = 0; row < Game.thisGame().dimension; ++row)
		{
			searchColumnsInCurrentRow(row);
			// Now that we have searched the whole row, check if the player is 1 turn away from completing it:
			if ( playerTileCount == (Game.thisGame().dimension - 1) )
			{
				// BLOCK THEM!
				 GameBoard.letComputerClaimSpotOnGameBoard (row, emptyColumnIndex);
			}
		}
		
	} // End of method checkRows.
	
	
	private static void searchColumnsInCurrentRow (int currentRow)
	{
		playerTileCount = 0;
		emptyColumnIndex = 0;
		for (int column = 0; column < Game.thisGame().dimension; ++column)
		{
			// Check if we have already placed a tile in this row, thus permanently blocking the player from
			// completing it.
			// This prevents the computer from trying to block the player, in the same row, multiple times:
			if ( GameBoard.isThisSpotClaimedByComputer(currentRow, column) )
			{
				// Move onto the next row:
				playerTileCount = -1;
				return;
			}
			// Keep track of all of the player's tiles we find in this row:
			if ( GameBoard.isThisSpotClaimedByPlayer(currentRow, column) )
			{
				++playerTileCount;
			}
			// Otherwise, this spot in this row is empty, so keep track of it in case it's the only one left
			// and we must fill it:
			else
			{
				emptyColumnIndex = column;
			}
		}
		
	} // End of method searchColumnsInCurrentRow.
	
	
	private static void checkColumns ()
	{
		// In each column, scan through it and check if the player is 1 turn away from completing it,
		// thus winning the game:
		// Search through each column on the game board:
		for (int column = 0; column < Game.thisGame().dimension; ++column)
		{
			searchRowsInCurrentColumn(column);
			// Now that we have searched the whole column, check if the player is 1 turn away from completing it:
			if ( playerTileCount == (Game.thisGame().dimension - 1) )
			{
				// BLOCK THEM!
				 GameBoard.letComputerClaimSpotOnGameBoard (emptyRowIndex, column);
			}
		}
		
	} // End of method checkColumns.
	
	
	private static void searchRowsInCurrentColumn (int currentColumn)
	{
		playerTileCount = 0;
		emptyRowIndex = 0;
		for (int row = 0; row < Game.thisGame().dimension; ++row)
		{
			// Check if we have already placed a tile in this column, thus permanently blocking the player from
			// completing it.
			// This prevents the computer from trying to block the player, in the same column, multiple times:
			if ( GameBoard.isThisSpotClaimedByComputer(row, currentColumn) )
			{
				// Move onto the next column:
				playerTileCount = -1;
				return;
			}
			// Keep track of all of the player's tiles we find in this column:
			if ( GameBoard.isThisSpotClaimedByPlayer(row, currentColumn) )
			{
				++playerTileCount;
			}
			// Otherwise, this spot in this column is empty, so keep track of it in case it's the only one left
			// and we must fill it:
			else
			{
				emptyRowIndex = row;
			}
		}
		
	} // End of method searchRowsInCurrentColumn.
	
	
	private static void checkDiagonals ()
	{
		// In each diagonal, scan through it and check if the player is 1 turn away from completing it,
		// thus winning the game:
		checkDownRightDiagonal();
		// Now that we have scanned the first diagonal, check if the player is 1 turn away from completing it.
		// We skip this if playerTileCount is set to -1, because the above diagonal was already blocked:
		if ( playerTileCount == (Game.thisGame().dimension - 1) )
		{
			// BLOCK THEM!
			GameBoard.letComputerClaimSpotOnGameBoard(emptyRowIndex, emptyColumnIndex);
		}
		checkUpRightDiagonal();
		// Now that we have scanned the second diagonal, check if the player is 1 turn away from completing it:
		if ( playerTileCount == (Game.thisGame().dimension - 1) )
		{
			GameBoard.letComputerClaimSpotOnGameBoard(emptyRowIndex, emptyColumnIndex);
		}
		
	} // End of method checkDiagonals.
	
	
	private static void checkDownRightDiagonal ()
	{
		// Scan the first diagonal, which starts at the top left and moves down-right:
		playerTileCount = 0;
		emptyRowIndex = 0;
		emptyColumnIndex = 0;
		for (int row = 0, column = 0; row < Game.thisGame().dimension && column < Game.thisGame().dimension; ++row, ++column)
		{
			// Check if we have already placed a tile in this diagonal, thus permanently blocking the player from
			// completing it.
			// This prevents the computer from trying to block the player, in the same diagonal, multiple times:
			if ( GameBoard.isThisSpotClaimedByComputer(row, column) )
			{
				// Move onto scanning the next diagonal:
				playerTileCount = -1;
				break;
			}
			// Keep track of all of the player's tiles we find in this diagonal:
			if ( GameBoard.isThisSpotClaimedByPlayer(row, column) )
			{
				++playerTileCount;
			}
			// Otherwise, this spot in this diagonal is empty, so keep track of it in case it's the only one left
			// and we must fill it:
			else
			{
				emptyRowIndex = row;
				emptyColumnIndex = column;
			}
		}
		
	} // End of method checkDownRightDiagonal.
	
	
	private static void checkUpRightDiagonal ()
	{
		// Then, scan the second diagonal, which starts at the bottom left and moves up-right:
		playerTileCount = 0;
		emptyRowIndex = 0;
		emptyColumnIndex = 0;
		for (int row = Game.thisGame().dimension - 1, column = 0; row >= 0 && column < Game.thisGame().dimension; --row, ++column)
		{
			// Check if we have already placed a tile in this diagonal, thus permanently blocking the player from
			// completing it.
			// This prevents the computer from trying to block the player, in the same diagonal, multiple times:
			if ( GameBoard.isThisSpotClaimedByComputer(row, column) )
			{
				// We now know both diagonals have already been blocked by the computer, so we exit the method:
				return;
			}
			// Keep track of all of the player's tiles we find in this diagonal:
			if ( GameBoard.isThisSpotClaimedByPlayer(row, column) )
			{
				++playerTileCount;
			}
			// Otherwise, this spot in this diagonal is empty, so keep track of it in case it's the only one left
			// and we must fill it:
			else
			{
				emptyRowIndex = row;
				emptyColumnIndex = column;
			}
		}
		
	} // End of method checkUpRightDiagonal.
	
} // End of class.
