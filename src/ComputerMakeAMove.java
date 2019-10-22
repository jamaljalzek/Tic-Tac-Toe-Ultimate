import java.util.Random;

/**
 * This class implements the behavior of the computer that plays against the player.
 * Specifically, it has multiple methods (one for each difficulty) that makes a move
 * on behalf of the computer. 
 */
public class ComputerMakeAMove
{
	protected static Game thisGame; // Initialized in the Game Constructor.
	
	private static Random randomIndex;
	private static int computerTileCount;
	private static int bestRowLocation, bestRowCount, chosenRow;
	private static int bestColumnLocation, bestColumnCount, chosenColumn;
	private static int bestDiagonalLocation, bestDiagonalCount, chosenDiagonal;
	
	
	/**
	 * During this difficulty, the computer uses no strategy to win. Instead, it will search
	 * for an open spot on the game board randomly, and select the first one it finds.
	 * 
	 * @param thisGame
	 */
	public static void easyMode ()
	{
		if (thisGame.hasEnded)
		{
			return;
		}
		// Else:
		chooseRandomSpotOnGameBoard();
		claimSpotOnGameBoard(chosenRow, chosenColumn);
		
	} // End of method easyMode.
	
	
	/**
	 * During this difficulty, the computer does use a strategy to win. Specifically, it will try
	 * to place its tiles in a row, column, or diagonal to win.
	 * The strategy can be described as follows:
	 * 1. Find the row that is closest to being completed (by the computer).
	 * 2. Find the column that is closest to being completed.
	 * 3. Find the diagonal (out of the 2 that go through the center) that is closest to being completed.
	 * 4. Finally, select the most favorable row, column, or diagonal, and randomly choose an available spot inside.
	 *  
	 * @param thisGame
	 */
	public static void mediumMode ()
	{
		if (thisGame.hasEnded)
		{
			return;
		}
		searchForRowClosestToCompletionByComputer();
		searchForColumnClosestToCompletionByComputer();
		searchForDiagonalClosestToCompletionByComputer();		
		selectPathClosestToCompletionByComputer();
		
	} // End of method mediumMode.
	
	
	/**
	 * During this difficulty, the computer will use the same strategy to win as described in mediumMode. However, unlike
	 * mediumMode, the computer additionally will block the player from completing a row, column, or diagonal. In this game
	 * mode, the player must have a strategy that leads them to a situation where they have two different ways of
	 * winning in a single turn.
	 * 
	 * @param thisGame
	 */
	public static void hardMode ()
	{
		if (thisGame.hasEnded)
		{
			return;
		}
		
		// First, we will check if we are in the position to win the game during this turn:
		if ( canTheComputerWinTheGameThisTurn() )
		{
			return;
		}
		
		// At this point we know we are not in the position to immediately win, so we must check if the player
		// is in the position of being 1 turn away from completing a row, column, or diagonal, and winning the game:
		PreventPlayerFromWinning.checkRows();
		PreventPlayerFromWinning.checkColumns();
		PreventPlayerFromWinning.checkDiagonals();
		
		// If they were, we have blocked them and will now end our turn:
		if (thisGame.isPlayersTurn)
		{
			return;
		}
		
		// Otherwise, at this point we may aim to complete a row, column, or diagonal ourselves.
		// So, we use the same strategy as in mediumMode:
		mediumMode();
		
	} // End of method hardMode.
	
	
	//////////////////////////
	//                      //
	// HELPER METHODS BELOW //
	//                      //
	//////////////////////////
	
	
	private static void chooseRandomSpotOnGameBoard()
	{
		randomIndex = new Random();
		do
		{
			chosenRow = randomIndex.nextInt(thisGame.dimension);
			chosenColumn = randomIndex.nextInt(thisGame.dimension);
		} while ( isChosenSpotAlreadyTaken() );
		
	} // End of method chooseRandomSpotOnGameBoard.
	
	
	private static boolean isChosenSpotAlreadyTaken ()
	{
		return ( thisGame.gameBoard [chosenRow] [chosenColumn].getText().equals(thisGame.playerSymbol) ||
				 thisGame.gameBoard [chosenRow] [chosenColumn].getText().equals(thisGame.computerSymbol) );
		
	} // End of method isChosenSpotAlreadyTaken.
	
	
	private static void searchForRowClosestToCompletionByComputer ()
	{
		// Starting from the top row and moving down, scan through each row, finding the row with
		// the most computer tiles and NO player tiles (if there is a player’s tile in the row, then
		// that row can no longer be won by the computer):
		bestRowLocation = -1;
		bestRowCount = 0;
		for (int row = 0; row < thisGame.dimension; ++row)
		{
			searchColumnsInCurrentRow (row);
			// We have searched through all the spots in the current row.
			// Finally, check if we found more spots claimed by the computer in this current row than
			// the previous row we have recorded with the most spots.
			// If we did, then update our records:
			if (computerTileCount > bestRowCount)
			{
				bestRowLocation = row;
				bestRowCount = computerTileCount;
			}
			// Otherwise, move onto the next row in hopes of something better.
		}
		// We have now searched all of the rows on the game board.
		// bestRowLocation will be equal to -1 if all rows have at least 1 spot that is claimed by the player.
		// In this case, we set bestRowCount to -1 so rows will not be considered in selectBestPath:
		if (bestRowLocation == -1)
		{
			bestRowCount = -1;
		}
		
	} // End of method searchRows.
	
	
	private static void searchColumnsInCurrentRow (int currentRow)
	{
		// For the current row, starting from the left and moving to the right, scan
		// through all of the spots:
		computerTileCount = 0;
		for (int column = 0; column < thisGame.dimension; ++column)
		{
			// If we find that the current spot in this row has been claimed by the player, then move
			// onto the next row:
			if ( isThisSpotClaimedByPlayer(currentRow, column) )
			{
				computerTileCount = -1;
				return;
			}
			// Otherwise, check if the current spot is already claimed by the computer:
			if ( isThisSpotClaimedByComputer(currentRow, column) )
			{
				// Increment the count:
				++computerTileCount;
			}
			// Else, it's just an empty spot, so move onto the next spot in this row.
		}
		
	} // End of method searchColumnsInCurrentRow.
	
	
	private static boolean isThisSpotClaimedByPlayer (int row, int column)
	{
		return thisGame.gameBoard [row] [column].getText().equals(thisGame.playerSymbol);
		
	} // End of method isThisSpotClaimedByPlayer.
	
	
	private static boolean isThisSpotClaimedByComputer (int row, int column)
	{
		return thisGame.gameBoard [row] [column].getText().equals(thisGame.computerSymbol);
		
	} // End of method isThisSpotClaimedByComputer.
	
	
	private static void searchForColumnClosestToCompletionByComputer ()
	{
		// Starting from the left column and moving right, scan through each column, finding the column with
		// the most computer tiles and NO player tiles (if there is a player’s tile in the column, then
		// that column can no longer be won by the computer):
		bestColumnLocation = -1;
		bestColumnCount = 0;
		for (int column = 0; column < thisGame.dimension; ++column)
		{
			searchRowsInCurrentColumn(column);
			// We have searched through all the spots in the current column.
			// Finally, check if we found more spots claimed by the computer in this current column than
			// the previous column we have recorded with the most spots.
			// If we did, then update our records:
			if (computerTileCount > bestColumnCount)
			{
				bestColumnLocation = column;
				bestColumnCount = computerTileCount;
			}
			// Otherwise, move onto the next column in hopes of something better.
		}
		// We have now searched all of the columns on the game board.
		// bestColumnLocation will be equal to -1 if all columns have at least 1 spot that is claimed by the player.
		// In this case, we set bestColumnCount to -1 so columns will not be considered in selectBestPath:
		if (bestColumnLocation == -1)
		{
			bestColumnCount = -1;
		}
		
	} // End of method searchColumns.
	
	
	private static void searchRowsInCurrentColumn (int currentColumn)
	{
		// For the current column, starting from the top and moving down, scan
		// through all of the spots:
		computerTileCount = 0;
		for (int row = 0; row < thisGame.dimension; ++row)
		{
			// If we find that the current spot has been claimed by the player, then move
			// onto the next column:
			if ( isThisSpotClaimedByPlayer(row, currentColumn) )
			{
				computerTileCount = -1;
				return;
			}
			// Otherwise, check if the current spot is already claimed by the computer:
			if ( isThisSpotClaimedByComputer(row, currentColumn) )
			{
				// Increment the count:
				++computerTileCount;
			}
			// Else, it's just an empty spot, so move onto the next spot in this column.
		}
		
	} // End of method searchRowsInCurrentColumn.

	
	private static void searchForDiagonalClosestToCompletionByComputer ()
	{				
		// Reset the best diagonal location, since it may have changed:
		bestDiagonalLocation = -1;
		// Scan through each of the two diagonals that go through the center of the game board, finding the diagonal
		// with the most computer tiles and NO player tiles (if there is a player’s tile in the diagonal, then that
		// diagonal can no longer be won).
		searchUpRightDiagonal();
		searchDownRightDiagonal();
		// Else, the first diagonal is the better choice or equal choice, or both diagonals are unwinnable,
		// so both == -1.
		chosenDiagonal = bestDiagonalLocation;
		// We have now searched both of the diagonals on the game board.
		// bestDiagonalLocation will be equal to -1 if both diagonals have at least 1 spot that is claimed by the player.
		// In this case, we set bestDiagonalCount to -1 so both diagonals will not be considered in selectBestPath:
		if (bestDiagonalLocation == -1)
		{
			bestDiagonalCount = -1;
		}
		
	} // End of method searchDiagonals.
	
	
	private static void searchDownRightDiagonal ()
	{
		int row, column;
		
		
		// Starting from the top-left corner, move down-right, through the center, to the bottom-right corner:
		computerTileCount = 0;
		for (row = 0, column = 0; row < thisGame.dimension && column < thisGame.dimension; ++row, ++column)
		{
			// If we find that the current spot has been claimed by the player, then abandon searching
			// the rest of this diagonal:
			if ( isThisSpotClaimedByPlayer(row, column) )
			{
				// Don't use this diagonal, so we use -1 to indicate this:
				computerTileCount = -1;
				break;
			}
			// Otherwise, check if the current spot is already claimed by the computer:
			if ( isThisSpotClaimedByComputer(row, column) )
			{
				// Increment the count:
				++computerTileCount;
			}
			// Else, it's just an empty spot, so move onto the next spot in this diagonal.
		}
		// At this point, we either scanned the entire second diagonal (and found no player tiles in it),
		// or we found one or more player tiles and abandoned the rest of the search.
		// Verify that this diagonal is still able to be won by checking that we made it to the end:
		if (computerTileCount != -1)
		{
			// Now that we scanned the second diagonal, which starts at row 0 and moves down-right,
			// compare the count to the previous diagonal we searched:
			if (computerTileCount > bestDiagonalCount)
			{
				// Update the start location of the second diagonal.
				bestDiagonalLocation = 0;
			}
		}
		
	} // End of method searchDownRightDiagonal.
	
	
	private static void searchUpRightDiagonal ()
	{
		int row, column;
		
		
		// Starting from the bottom-left corner, move up-right, through the center, to the top-right corner:
		computerTileCount = 0;
		for (row = thisGame.dimension - 1, column = 0; row >= 0 && column < thisGame.dimension; --row, ++column)
		{
			// If we find that the current spot has been claimed by the player, then abandon searching
			// the rest of this diagonal:
			if ( isThisSpotClaimedByPlayer(row, column) )
			{
				// Don't use this diagonal, so we use -1 to indicate this:
				computerTileCount = -1;
				break;
			}
			// Otherwise, check if the current spot is already claimed by the computer:
			if ( isThisSpotClaimedByComputer(row, column) )
			{
				// Increment the count:
				++computerTileCount;
			}
			// Else, it's just an empty spot, so move onto the next spot in this diagonal.
		}
		// At this point, we either scanned the entire first diagonal (and found no player tiles in it),
		// or we found one or more player tiles and abandoned the rest of the search.
		// Verify that this diagonal is still able to be won by checking that we made it to the end:
		if (computerTileCount != -1)
		{
			// Since we fully scanned the first diagonal, which starts at row (dimension - 1) and moves up-right,
			// record the count:
			bestDiagonalLocation = thisGame.dimension - 1;
			bestDiagonalCount = computerTileCount;
		}
		
	} // End of method searchUpRightDiagonal.
	
	
	private static void selectPathClosestToCompletionByComputer ()
	{
		/////////////////////////////////// DEBUGGING ///////////////////////////////////
		System.out.println("best row count: " + bestRowCount + '\n' + 
				   "best row index: " + bestRowLocation);
		
		System.out.println("best column count: " + bestColumnCount + '\n' + 
				   "best column index: " + bestColumnLocation);
		
		System.out.println("best diagonal count: " + bestDiagonalCount + '\n' + 
				   "best diagonal index: " + bestDiagonalLocation);
		/////////////////////////////////////////////////////////////////////////////////
		
		determineIfAllOptionsAreEqual();
		determineIfWeWillSelectARow();
		determineIfWeWillSelectAColumn();
		determineIfWeWillSelectADiagonal();
		claimSpotOnGameBoard(chosenRow, chosenColumn);
		
	} // End of method selectBestPath.
	
	
	private static void determineIfAllOptionsAreEqual ()
	{
		// -1 == -1 == -1 or R == C == D
		if (bestRowCount == -1 && bestColumnCount == -1 && bestDiagonalCount == -1 ||
			bestRowCount == bestColumnCount && bestColumnCount == bestDiagonalCount)
		{
			chooseRandomSpotOnGameBoard();
		}
		
	} // End of method determineIfAllOptionsAreEqual.
	
	
	private static void determineIfWeWillSelectARow ()
	{
		// R > C >= D or R > D >= C
		if (bestRowCount >= bestColumnCount && bestColumnCount >= bestDiagonalCount ||
			bestRowCount >= bestDiagonalCount && bestDiagonalCount >= bestColumnCount)
		{
			chooseRandomSpotInBestRow();
		}
		
	} // End of method determineIfWeWillSelectARow.
	
	
	private static void determineIfWeWillSelectAColumn()
	{
		// C > R >= D or C > D >= R
		if (bestColumnCount >= bestRowCount && bestRowCount >= bestDiagonalCount ||
			bestColumnCount >= bestDiagonalCount && bestDiagonalCount >= bestRowCount)
		{
			chooseRandomSpotInBestColumn();
		}
		
	} // End of method determineIfWeWillSelectAColumn.
	
	
	private static void determineIfWeWillSelectADiagonal ()
	{
		// D > R >= C or D > C >= R
		if (bestDiagonalCount >= bestRowCount && bestRowCount >= bestColumnCount ||
			bestDiagonalCount >= bestColumnCount && bestColumnCount >= bestRowCount)
		{
			chooseRandomSpotInBestDiagonal();
		}

	} // End of method determineIfWeWillSelectADiagonal.
	
	
	private static boolean canTheComputerWinTheGameThisTurn ()
	{
		if (canTheComputerWinARowThisTurn() ||
			canTheComputerWinAColumnThisTurn() ||
			canTheComputerWinADiagonalThisTurn())
		{
			return true;
		}
		// Else:
		return false;
		
	} // End of method canComputerWinTheGameThisTurn.
	
	
	private static boolean canTheComputerWinARowThisTurn ()
	{
		searchForRowClosestToCompletionByComputer();
		if ( bestRowCount == (thisGame.dimension - 1) )
		{
			chooseRandomSpotInBestRow();
			claimSpotOnGameBoard(chosenRow, chosenColumn);
			return true;
		}
		// Else:
		return false;
		
	} // End of method canTheComputerWinARowThisTurn.
	
	
	private static boolean canTheComputerWinAColumnThisTurn ()
	{
		searchForColumnClosestToCompletionByComputer();
		if ( bestColumnCount == (thisGame.dimension - 1) )
		{
			chooseRandomSpotInBestColumn();
			claimSpotOnGameBoard(chosenRow, chosenColumn);
			return true;
		}
		// Else:
		return false;
		
	} // End of method canTheComputerWinAColumnThisTurn.
	
	
	private static boolean canTheComputerWinADiagonalThisTurn ()
	{
		searchForDiagonalClosestToCompletionByComputer();
		if ( bestDiagonalCount == (thisGame.dimension - 1) )
		{
			chooseRandomSpotInBestDiagonal();
			claimSpotOnGameBoard(chosenRow, chosenColumn);
			return true;
		}
		// Else:
		return false;
		
	} // End of method canTheComputerWinADiagonalThisTurn.
	
	
	private static void chooseRandomSpotInBestRow ()
	{
		randomIndex = new Random();
		chosenRow = bestRowLocation;
		do
		{
			chosenColumn = randomIndex.nextInt(thisGame.dimension);
		} while ( isChosenSpotAlreadyTaken() );
		
	} // End of method chooseRandomSpotInBestRow.
	
	
	private static void chooseRandomSpotInBestColumn ()
	{
		chosenColumn = bestColumnLocation;
		randomIndex = new Random();
		do
		{
			chosenRow = randomIndex.nextInt(thisGame.dimension);
		} while ( isChosenSpotAlreadyTaken() );
		
	} // End of method chooseRandomSpotInBestColumn.
	
	
	private static void chooseRandomSpotInBestDiagonal ()
	{
		// Randomly choose a spot in the best diagonal.
		randomIndex = new Random();
		
		// Since there are two diagonals, first determine the chosen one (by its starting row):
		if (chosenDiagonal == 0)
		{
			// Randomly choose a spot in the diagonal that moves from the top-left corner (0, 0),
			// through the center, to the bottom-right corner (dimension - 1, dimension - 1):
			do
			{
				// Since the row index and the column index both start at 0, and increase by 1 each time we move down-right,
				// they are always equal to each other as we travel the diagonal:
				chosenRow = randomIndex.nextInt(thisGame.dimension);
				chosenColumn = chosenRow;
			} while ( isChosenSpotAlreadyTaken() );
		}
		else // chosenDiagonal == thisGame.dimension - 1
		{
			// Randomly choose a spot in the diagonal that moves from the bottom-left corner (dimension - 1, 0),
			// through the center, to the top-right corner (0, dimension - 1):
			chosenColumn = bestColumnLocation;
			do
			{
				// The row index starts at dimension - 1 and the column index starts at 0.
				// Each time we move up-right, the row index decreases by 1 while the column index increases by 1.
				// Therefore, the relationship between the row index and the column index is row + column = dimension - 1.
				// Or, given a row, we can rewrite this relationship as column = dimension - 1 - row.
				chosenRow = randomIndex.nextInt(thisGame.dimension);
				chosenColumn = thisGame.dimension - 1 - chosenRow;
			} while ( isChosenSpotAlreadyTaken() );
		}
		
	} // End of method chooseRandomSpotInBestDiagonal.
	
	
	private static void claimSpotOnGameBoard (int row, int column)
	{
		thisGame.gameBoard [row] [column].setText(thisGame.computerSymbol);
		thisGame.gameBoard [row] [column].setEnabled(false);
		++thisGame.numberOfSpotsClaimed;
		thisGame.isPlayersTurn = true;
		
		System.out.println("Inside claimAndCheck");
		thisGame.checkGameStatus( thisGame.gameBoard [row] [column] );
		
	} // End of method claimAndCheck.
	
	
	private static class PreventPlayerFromWinning
	{
		private static void checkRows ()
		{
			int playerTileCount;
			int emptyColumnIndex;
			
			
			// In each row, scan through it and check if the player is 1 turn away from completing it,
			// thus winning the game:
			// Search through each row on the game board:
			SearchRows: for (int row = 0; row < thisGame.dimension; ++row)
			{
				playerTileCount = 0;
				emptyColumnIndex = 0;
				for (int column = 0; column < thisGame.dimension; ++column)
				{
					// Check if we have already placed a tile in this row, thus permanently blocking the player from
					// completing it.
					// This prevents the computer from trying to block the player, in the same row, multiple times:
					if ( isThisSpotClaimedByComputer(row, column) )
					{
						// Move onto the next row:
						continue SearchRows;
					}
					// Keep track of all of the player's tiles we find in this row:
					if ( isThisSpotClaimedByPlayer(row, column) )
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
				// Now that we have searched the whole row, check if the player is 1 turn away from completing it:
				if ( playerTileCount == (thisGame.dimension - 1) )
				{
					// BLOCK THEM!
					 claimSpotOnGameBoard (row, emptyColumnIndex);
				}
			}
			
		} // End of method checkRows.
		
		
		private static void checkColumns ()
		{
			int playerTileCount;
			int emptyRowIndex;
			
			
			// In each column, scan through it and check if the player is 1 turn away from completing it,
			// thus winning the game:
			// Search through each column on the game board:
			SearchColumns: for (int column = 0; column < thisGame.dimension; ++column)
			{
				playerTileCount = 0;
				emptyRowIndex = 0;
				for (int row = 0; row < thisGame.dimension; ++row)
				{
					// Check if we have already placed a tile in this column, thus permanently blocking the player from
					// completing it.
					// This prevents the computer from trying to block the player, in the same column, multiple times:
					if ( isThisSpotClaimedByComputer(row, column) )
					{
						// Move onto the next column:
						continue SearchColumns;
					}
					// Keep track of all of the player's tiles we find in this column:
					if ( isThisSpotClaimedByPlayer(row, column) )
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
				// Now that we have searched the whole column, check if the player is 1 turn away from completing it:
				if ( playerTileCount == (thisGame.dimension - 1) )
				{
					// BLOCK THEM!
					 claimSpotOnGameBoard (emptyRowIndex, column);
				}
			}
			
		} // End of method checkColumns.
		
		
		private static void checkDiagonals ()
		{
			int playerTileCount;
			int emptyRowIndex, emptyColumnIndex;
			
			
			// In each diagonal, scan through it and check if the player is 1 turn away from completing it,
			// thus winning the game:
			// Scan the first diagonal, which starts at the top left and moves down-right:
			playerTileCount = 0;
			emptyRowIndex = 0;
			emptyColumnIndex = 0;
			for (int row = 0, column = 0; row < thisGame.dimension && column < thisGame.dimension; ++row, ++column)
			{
				// Check if we have already placed a tile in this diagonal, thus permanently blocking the player from
				// completing it.
				// This prevents the computer from trying to block the player, in the same diagonal, multiple times:
				if ( isThisSpotClaimedByComputer(row, column) )
				{
					// Move onto scanning the next diagonal:
					playerTileCount = -1;
					break;
				}
				// Keep track of all of the player's tiles we find in this diagonal:
				if ( isThisSpotClaimedByPlayer(row, column) )
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
			// Now that we have scanned the first diagonal, check if the player is 1 turn away from completing it.
			// We skip this if playerTileCount is set to -1, because the above diagonal was already blocked:
			if ( playerTileCount == (thisGame.dimension - 1) )
			{
				// BLOCK THEM!
				claimSpotOnGameBoard(emptyRowIndex, emptyColumnIndex);
			}
			// Then, scan the second diagonal, which starts at the bottom left and moves up-right:
			playerTileCount = 0;
			emptyRowIndex = 0;
			emptyColumnIndex = 0;
			for (int row = thisGame.dimension - 1, column = 0; row >= 0 && column < thisGame.dimension; --row, ++column)
			{
				// Check if we have already placed a tile in this diagonal, thus permanently blocking the player from
				// completing it.
				// This prevents the computer from trying to block the player, in the same diagonal, multiple times:
				if ( isThisSpotClaimedByComputer(row, column) )
				{
					// We now know both diagonals have already been blocked by the computer, so we exit the method:
					return;
				}
				// Keep track of all of the player's tiles we find in this diagonal:
				if ( isThisSpotClaimedByPlayer(row, column) )
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
			// Now that we have scanned the second diagonal, check if the player is 1 turn away from completing it:
			if ( playerTileCount == (thisGame.dimension - 1) )
			{
				claimSpotOnGameBoard(emptyRowIndex, emptyColumnIndex);
			}
			
		} // End of method checkDiagonals.
		
	} // End of class PreventPlayerFromWinning.
	
} // End of class ComputerMakeAMove.
