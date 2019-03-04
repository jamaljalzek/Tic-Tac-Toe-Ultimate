import java.util.Random;

/**
 * This class implements the behavior of the computer that plays against the player.
 * Specifically, it has multiple methods (one for each difficulty) that makes a move
 * on behalf of the computer. 
 */
public class ComputerMakeAMove
{
	private static Game game;
	
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
	public static void easyMode (Game thisGame)
	{
		// Randomly choose a spot on the game board:
		do
		{
			randomIndex = new Random();
			chosenRow = randomIndex.nextInt(thisGame.dimension);
			chosenColumn = randomIndex.nextInt(thisGame.dimension);
			
		// If that spot was already claimed by the player or computer, then try again:
		} while ( thisGame.gameBoard [chosenRow] [chosenColumn].getText().equals(thisGame.playerSymbol) ||
				  thisGame.gameBoard [chosenRow] [chosenColumn].getText().equals(thisGame.computerSymbol) );
		
		// Claim that GameTile.
		claimAndCheck(thisGame);
		
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
	public static void mediumMode (Game thisGame)
	{
		// 1. Find the row that is closest to completion by the computer:
		searchRows(thisGame);
		
		// 2. Find the column that is closest to completion by the computer:
		searchColumns(thisGame);
		
		// 3. Find the diagonal that is closest to completion by the computer:
		searchDiagonals(thisGame);
		
		// 4. Next, pick the row, column, or diagonal that has the shortest path to victory:
		selectBestPath(thisGame);
		
	} // End of method mediumMode.
	
	
	/**
	 * During this difficulty, the computer does use a strategy to win. Specifically, it will try
	 * to place its tiles in a row, column, or diagonal to win.
	 * 
	 * @param thisGame
	 */
	public static void hardMode (Game thisGame)
	{
		// Claim that GameTile.
		claimAndCheck(thisGame);
		
	} // End of method hardMode.
	
	
	//////////////////////////
	//						//
	// Helper methods below //
	//						//
	//////////////////////////
	
	
	private static void searchRows (Game thisGame)
	{
		/// 1. Look for the row that is closest to completion by the computer:
		
		// Starting from the top row and moving down, scan through each row, finding the row with
		// the most computer tiles and NO player tiles (if there is a player’s tile in the row, then
		// that row can no longer be won by the computer):
		bestRowLocation = -1;
		bestRowCount = 0;
		SearchRows: for (int row = 0; row < thisGame.dimension; ++row)
		{
			// For the current row, starting from the left and moving to the right, scan
			// through all of the spots:
			computerTileCount = 0;
			for (int column = 0; column < thisGame.dimension; ++column)
			{
				// If we find that the current spot in this row has been claimed by the player, then move
				// onto the next row:
				if ( thisGame.gameBoard [row] [column].getText().equals(thisGame.playerSymbol) )
				{
					continue SearchRows;
				}
				
				// Otherwise, check if the current spot is already claimed by the computer:
				if ( thisGame.gameBoard [row] [column].getText().equals(thisGame.computerSymbol) )
				{
					// Increment the count:
					++computerTileCount;
				}
				
				// Else, it's just an empty spot, so move onto the next spot in this row.
			}
			
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
		//System.out.println("best row index: " + bestRowLocation);
		
	} // End of method searchRows.
	
	private static void searchColumns (Game thisGame)
	{
		/// 2. Look for the column that is closest to completion by the computer:
		
		// Starting from the left column and moving right, scan through each column, finding the column with
		// the most computer tiles and NO player tiles (if there is a player’s tile in the column, then
		// that column can no longer be won by the computer):
		bestColumnLocation = -1;
		bestColumnCount = 0;
		SearchColumns: for (int column = 0; column < thisGame.dimension; ++column)
		{
			// For the current column, starting from the top and moving down, scan
			// through all of the spots:
			computerTileCount = 0;
			for (int row = 0; row < thisGame.dimension; ++row)
			{
				// If we find that the current spot has been claimed by the player, then move
				// onto the next column:
				if ( thisGame.gameBoard [row] [column].getText().equals(thisGame.playerSymbol) )
				{
					continue SearchColumns;
				}
				
				// Otherwise, check if the current spot is already claimed by the computer:
				if ( thisGame.gameBoard [row] [column].getText().equals(thisGame.computerSymbol) )
				{
					// Increment the count:
					++computerTileCount;
				}
				
				// Else, it's just an empty spot, so move onto the next spot in this column.
			}
			
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
		//System.out.println("best column index: " + bestColumnLocation);
		
	} // End of method searchColumns.
	
	private static void searchDiagonals (Game thisGame)
	{
		// These are the indexes that the two for loops use to search each spot in each diagonal:
		int row, column;
		
		
		/// 3. Look for the diagonal that is closest to completion by the computer:
		
		// Reset the best diagonal location, since it may have changed:
		bestDiagonalLocation = -1;
		
		// Scan through each of the two diagonals that go through the center of the game board, finding the diagonal
		// with the most computer tiles and NO player tiles (if there is a player’s tile in the diagonal, then that
		// diagonal can no longer be won).
		
		// Starting from the bottom-left corner, move up-right, through the center, to the top-right corner:
		computerTileCount = 0;
		for (row = thisGame.dimension - 1, column = 0; row >= 0 && column < thisGame.dimension; --row, ++column)
		{
			System.out.println("First diagonal position: " + row + ", " + column);
			// If we find that the current spot has been claimed by the player, then abandon searching
			// the rest of this diagonal:
			if ( thisGame.gameBoard [row] [column].getText().equals(thisGame.playerSymbol) )
			{
				// Don't use this diagonal, so we use -1 to indicate this:
				computerTileCount = -1;
				break;
			}
			
			// Otherwise, check if the current spot is already claimed by the computer:
			if ( thisGame.gameBoard [row] [column].getText().equals(thisGame.computerSymbol) )
			{
				// Increment the count:
				++computerTileCount;
			}

			// Else, it's just an empty spot, so move onto the next spot in this diagonal.
		}
		
		// Make sure row and column are correct:
		++row;
		--column;
		System.out.println("First diagonal (4, 0) ending position: " + row + ", " + column);
		
		// At this point, we either scanned the entire first diagonal (and found no player tiles in it),
		// or we found one or more player tiles and abandoned the rest of the search.
		// Verify that this diagonal is still able to be won by checking that we made it to the end:
		if ( row == 0 && column == (thisGame.dimension - 1) ) // use computerTileCount != -1 ???????
		{
			// Since we fully scanned the first diagonal, which starts at row (dimension - 1) and moves up-right,
			// record the count:
			bestDiagonalLocation = thisGame.dimension - 1;
			bestDiagonalCount = computerTileCount;
		}
		
		// Starting from the top-left corner, move down-right, through the center, to the bottom-right corner:
		computerTileCount = 0;
		for (row = 0, column = 0; row < thisGame.dimension && column < thisGame.dimension; ++row, ++column)
		{
			System.out.println("Second diagonal position: " + row + ", " + column);
			// If we find that the current spot has been claimed by the player, then abandon searching
			// the rest of this diagonal:
			if ( thisGame.gameBoard [row] [column].getText().equals(thisGame.playerSymbol) )
			{
				// Don't use this diagonal, so we use -1 to indicate this:
				computerTileCount = -1;
				break;
			}
			
			// Otherwise, check if the current spot is already claimed by the computer:
			if ( thisGame.gameBoard [row] [column].getText().equals(thisGame.computerSymbol) )
			{
				// Increment the count:
				++computerTileCount;
			}
			
			// Else, it's just an empty spot, so move onto the next spot in this diagonal.
		}
		
		// Make sure row and column are correct:
		//--row;
		//--column;
		System.out.println("Second diagonal (0, 0) ending position: " + row + ", " + column);
		
		// At this point, we either scanned the entire second diagonal (and found no player tiles in it),
		// or we found one or more player tiles and abandoned the rest of the search.
		// Verify that this diagonal is still able to be won by checking that we made it to the end:
		if ( row == (thisGame.dimension - 1) && column == (thisGame.dimension - 1) )
		{
			// Now that we scanned the second diagonal, which starts at row 0 and moves down-right,
			// compare the count to the previous diagonal we searched:
			if (computerTileCount > bestDiagonalCount)
			{
				// Update the start location of the second diagonal.
				bestDiagonalLocation = 0;
			}
		}
		
		// Else, the first diagonal is the better choice or equal choice, or both diagonals are unwinnable,
		// so both == -1.
		
		chosenDiagonal = bestDiagonalLocation;
		
		// We have now searched both of the diagonals on the game board.
		//System.out.println("best diagonal index: " + bestDiagonalLocation);
		
	} // End of method searchDiagonals.
	
	
	private static void selectBestPath (Game thisGame)
	{
		int randomChoice;
		
		
		System.out.println("best row count: " + bestRowCount + '\n' + 
				   "best row index: " + bestRowLocation);
		
		System.out.println("best column count: " + bestColumnCount + '\n' + 
				   "best column index: " + bestColumnLocation);
		
		System.out.println("best diagonal count: " + bestDiagonalCount + '\n' + 
				   "best diagonal index: " + bestDiagonalLocation);
		
		
		/// 4. Next, pick the row, column, or diagonal that has the shortest path to victory:
		
		// Compare the best row, best column, and best diagonal found:
		
		// -1 == -1 == -1
		if (bestRowLocation == -1 && bestColumnLocation == -1 && bestDiagonalLocation == -1)
		{
			// Randomly choose any spot on the game board:
			easyMode(thisGame);
			System.out.println("-1 -1 -1");
		}
		// 1 == 2 == 3
		else if (bestRowCount == bestColumnCount && bestColumnCount == bestDiagonalCount)
		{
			// Randomly choose between the 3:
			randomIndex = new Random();
			randomChoice = randomIndex.nextInt(3);
			
			if (randomChoice == 0) // Choose the row:
			{
				// Randomly choose a spot in the best row:
				chooseSpotInRow(thisGame);
			}
			else if (randomChoice == 1) // Choose the column:
			{
				// Randomly choose a spot in the best column:
				chooseSpotInColumn(thisGame);
			}
			else // randomChoice == 2, so choose the diagonal:
			{
				// Randomly choose a spot in the best diagonal:
				chooseSpotInDiagonal(thisGame);
			}
		}
		// 1 > 2 >= 3
		else if (bestRowCount >= bestColumnCount && bestColumnCount >= bestDiagonalCount)
		{
			// Randomly choose a spot in the best row:
			chooseSpotInRow(thisGame);
		}
		// 1 > 3 >= 2
		else if (bestRowCount >= bestDiagonalCount && bestDiagonalCount >= bestColumnCount)
		{
			// Randomly choose a spot in the best row:
			chooseSpotInRow(thisGame);
		}
		// 2 > 1 >= 3
		else if (bestColumnCount >= bestRowCount && bestRowCount >= bestDiagonalCount)
		{
			// Randomly choose a spot in the best column:
			chooseSpotInColumn(thisGame);
		}
		// 2 > 3 >= 1
		else if (bestColumnCount >= bestDiagonalCount && bestDiagonalCount >= bestRowCount)
		{
			// Randomly choose a spot in the best column:
			chooseSpotInColumn(thisGame);
		}
		// 3 > 1 >= 2
		else if (bestDiagonalCount >= bestRowCount && bestRowCount >= bestColumnCount)
		{
			// Randomly choose a spot in the best diagonal:
			chooseSpotInDiagonal(thisGame);
		}
		// 3 > 2 >= 1
		else if (bestDiagonalCount >= bestColumnCount && bestColumnCount >= bestRowCount)
		{
			// Randomly choose a spot in the best diagonal:
			chooseSpotInDiagonal(thisGame);
		}
		

		
		// Claim that GameTile.
		claimAndCheck(thisGame);
		
	} // End of method selectBestPath.
	
	
	private static void chooseSpotInRow (Game thisGame)
	{
		// Randomly choose a spot in the best row:
		chosenRow = bestRowLocation;
		do
		{
			randomIndex = new Random();
			chosenColumn = randomIndex.nextInt(thisGame.dimension);
			
		// If that GameTile was already claimed by the player or computer, then try again:
		} while ( thisGame.gameBoard [chosenRow] [chosenColumn].getText().equals(thisGame.playerSymbol) ||
				  thisGame.gameBoard [chosenRow] [chosenColumn].getText().equals(thisGame.computerSymbol) );
		
	} // End of method
	
	
	private static void chooseSpotInColumn (Game thisGame)
	{
		// Randomly choose a spot in the best column:
		chosenColumn = bestColumnLocation;
		do
		{
			randomIndex = new Random();
			chosenRow = randomIndex.nextInt(thisGame.dimension);
			
		// If that GameTile was already claimed by the player or computer, then try again:
		} while ( thisGame.gameBoard [chosenRow] [chosenColumn].getText().equals(thisGame.playerSymbol) ||
				  thisGame.gameBoard [chosenRow] [chosenColumn].getText().equals(thisGame.computerSymbol) );
		
	} // End of method
	
	
	private static void chooseSpotInDiagonal (Game thisGame)
	{
		// Randomly choose a spot in the best diagonal.
		// Since there are two diagonals, first determine the chosen one (by its starting row):
		if (chosenDiagonal == 0)
		{
			// Randomly choose a spot in the diagonal that moves from the top-left corner (0, 0),
			// through the center, to the bottom-right corner (dimension - 1, dimension - 1):
			do
			{
				// Since the row index and the column index both start at 0, and increase by 1 each time we move down-right,
				// they are always equal to each other as we travel the diagonal:
				randomIndex = new Random();
				chosenRow = randomIndex.nextInt(thisGame.dimension);
				chosenColumn = chosenRow;
				
			// If that GameTile was already claimed by the player or computer, then try again:
			} while ( thisGame.gameBoard [chosenRow] [chosenColumn].getText().equals(thisGame.playerSymbol) ||
					  thisGame.gameBoard [chosenRow] [chosenColumn].getText().equals(thisGame.computerSymbol) );
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
				randomIndex = new Random();
				chosenRow = randomIndex.nextInt(thisGame.dimension);
				chosenColumn = thisGame.dimension - 1 - chosenRow;
				
			// If that GameTile was already claimed by the player or computer, then try again:
			} while ( thisGame.gameBoard [chosenRow] [chosenColumn].getText().equals(thisGame.playerSymbol) ||
					  thisGame.gameBoard [chosenRow] [chosenColumn].getText().equals(thisGame.computerSymbol) );
		}
		
	} // End of method
	
	
	private static void claimAndCheck (Game thisGame)
	{
		// Claim that GameTile.
		thisGame.gameBoard [chosenRow] [chosenColumn].setText(thisGame.computerSymbol);
		thisGame.gameBoard [chosenRow] [chosenColumn].setEnabled(false);
		
		thisGame.checkGameStatus( thisGame.gameBoard [chosenRow] [chosenColumn] );
		
	} // End of method claimAndCheck.
	
} // End of class ComputerMakeAMove.
