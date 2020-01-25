package programLogic;

import java.util.Random;

import oldPackage.Game;

public class MediumMode
{
	private static Random randomIndex;
	private static int computerTileCount;
	private static int bestRowLocation, bestRowCount, chosenRow;
	private static int bestColumnLocation, bestColumnCount, chosenColumn;
	private static int bestDiagonalLocation, bestDiagonalCount, chosenDiagonal;
	
	
	/**
	 * During this difficulty, the computer does use a strategy to win. Specifically, it will try
	 * to place its tiles in a row, column, or diagonal to win.
	 * The strategy can be described as follows:
	 * 1. Find the row that is closest to being completed (by the computer).
	 * 2. Find the column that is closest to being completed.
	 * 3. Find the diagonal (out of the 2 that go through the center) that is closest to being completed.
	 * 4. Finally, select the most favorable row, column, or diagonal, and randomly choose an available spot inside.
	 *  
	 * @param Game.thisGame()
	 */
	public static void mediumMode ()
	{
		if (Game.thisGame().hasEnded)
		{
			return;
		}
		searchForRowClosestToCompletionByComputer();
		searchForColumnClosestToCompletionByComputer();
		searchForDiagonalClosestToCompletionByComputer();		
		selectPathClosestToCompletionByComputer();
		
	} // End of method mediumMode.
	
	
	private static void searchForRowClosestToCompletionByComputer ()
	{
		// Starting from the top row and moving down, scan through each row, finding the row with
		// the most computer tiles and NO player tiles (if there is a player’s tile in the row, then
		// that row can no longer be won by the computer):
		bestRowLocation = -1;
		bestRowCount = 0;
		for (int row = 0; row < Game.thisGame().dimension; ++row)
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
		for (int column = 0; column < Game.thisGame().dimension; ++column)
		{
			// If we find that the current spot in this row has been claimed by the player, then move
			// onto the next row:
			if ( GameBoard.isThisSpotClaimedByPlayer(currentRow, column) )
			{
				computerTileCount = -1;
				return;
			}
			// Otherwise, check if the current spot is already claimed by the computer:
			if ( GameBoard.isThisSpotClaimedByComputer(currentRow, column) )
			{
				// Increment the count:
				++computerTileCount;
			}
			// Else, it's just an empty spot, so move onto the next spot in this row.
		}
		
	} // End of method searchColumnsInCurrentRow.
	
	
	private static void searchForColumnClosestToCompletionByComputer ()
	{
		// Starting from the left column and moving right, scan through each column, finding the column with
		// the most computer tiles and NO player tiles (if there is a player’s tile in the column, then
		// that column can no longer be won by the computer):
		bestColumnLocation = -1;
		bestColumnCount = 0;
		for (int column = 0; column < Game.thisGame().dimension; ++column)
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
		for (int row = 0; row < Game.thisGame().dimension; ++row)
		{
			// If we find that the current spot has been claimed by the player, then move
			// onto the next column:
			if ( GameBoard.isThisSpotClaimedByPlayer(row, currentColumn) )
			{
				computerTileCount = -1;
				return;
			}
			// Otherwise, check if the current spot is already claimed by the computer:
			if ( GameBoard.isThisSpotClaimedByComputer(row, currentColumn) )
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
		for (row = 0, column = 0; row < Game.thisGame().dimension && column < Game.thisGame().dimension; ++row, ++column)
		{
			// If we find that the current spot has been claimed by the player, then abandon searching
			// the rest of this diagonal:
			if ( GameBoard.isThisSpotClaimedByPlayer(row, column) )
			{
				// Don't use this diagonal, so we use -1 to indicate this:
				computerTileCount = -1;
				break;
			}
			// Otherwise, check if the current spot is already claimed by the computer:
			if ( GameBoard.isThisSpotClaimedByComputer(row, column) )
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
		for (row = Game.thisGame().dimension - 1, column = 0; row >= 0 && column < Game.thisGame().dimension; --row, ++column)
		{
			// If we find that the current spot has been claimed by the player, then abandon searching
			// the rest of this diagonal:
			if ( GameBoard.isThisSpotClaimedByPlayer(row, column) )
			{
				// Don't use this diagonal, so we use -1 to indicate this:
				computerTileCount = -1;
				break;
			}
			// Otherwise, check if the current spot is already claimed by the computer:
			if ( GameBoard.isThisSpotClaimedByComputer(row, column) )
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
			bestDiagonalLocation = Game.thisGame().dimension - 1;
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
		GameBoard.letComputerClaimSpotOnGameBoard(chosenRow, chosenColumn);
		
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
	
	
	private static void chooseRandomSpotOnGameBoard()
	{
		randomIndex = new Random();
		do
		{
			chosenRow = randomIndex.nextInt(Game.thisGame().dimension);
			chosenColumn = randomIndex.nextInt(Game.thisGame().dimension);
		}
		while (GameBoard.isChosenSpotAlreadyTaken(chosenRow, chosenColumn));
	}
	
	
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
	
	
	private static void chooseRandomSpotInBestRow ()
	{
		randomIndex = new Random();
		chosenRow = bestRowLocation;
		do
		{
			chosenColumn = randomIndex.nextInt(Game.thisGame().dimension);
		} while (GameBoard.isChosenSpotAlreadyTaken(chosenRow, chosenColumn));
		
	} // End of method chooseRandomSpotInBestRow.
	
	
	private static void chooseRandomSpotInBestColumn ()
	{
		chosenColumn = bestColumnLocation;
		randomIndex = new Random();
		do
		{
			chosenRow = randomIndex.nextInt(Game.thisGame().dimension);
		} while (GameBoard.isChosenSpotAlreadyTaken(chosenRow, chosenColumn));
		
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
				chosenRow = randomIndex.nextInt(Game.thisGame().dimension);
				chosenColumn = chosenRow;
			} while (GameBoard.isChosenSpotAlreadyTaken(chosenRow, chosenColumn));
		}
		else // chosenDiagonal == Game.thisGame().dimension - 1
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
				chosenRow = randomIndex.nextInt(Game.thisGame().dimension);
				chosenColumn = Game.thisGame().dimension - 1 - chosenRow;
			} while (GameBoard.isChosenSpotAlreadyTaken(chosenRow, chosenColumn));
		}
		
	} // End of method chooseRandomSpotInBestDiagonal.

} // End of class.
