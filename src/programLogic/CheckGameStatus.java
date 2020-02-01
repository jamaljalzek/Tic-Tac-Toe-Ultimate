package programLogic;

import gui.gameScreen.GameTile;
import gui.programWindow.GameEndWindow;

public class CheckGameStatus
{
	private static String whoseTurnItIs, opponent;
	private static int selectedTileRowLocation, selectedTileColumnLocation;
	
	
	/**
	 * This method checks the row, column, and potentially the diagonal(s) that run through the passed in
	 * selectedTile for completion by either the player, or the computer, depending on whose turn it is.
	 * It does not re-scan the whole game board, because we know where the most recent change occurred:
	 * the row and column location of the selectedTile.
	 * 
	 * @param selectedTile (either from the player or the computer)
	 */
	public static void checkGameStatus(GameTile selectedTile)
	{
		extractSelectedTileInformation(selectedTile);
		determineWhoIsTheOpponentThisTurn();
		checkForACompletedRow();
		checkForACompletedColumn();
		checkForACompletedDiagonal();
		checkForAStalemate();
	}
	
	
	private static void extractSelectedTileInformation(GameTile selectedTile)
	{
		whoseTurnItIs = selectedTile.getText();
		selectedTileRowLocation = selectedTile.getRowLocation();
		selectedTileColumnLocation = selectedTile.getColumnLocation();
	}
	
	
	private static void determineWhoIsTheOpponentThisTurn()
	{
		if (whoseTurnItIs.equals(Game.getPlayerSymbol()))
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
		int rowTileCount = 0;
		for (int currentColumn = 0; currentColumn < GameBoard.getDimension(); ++currentColumn)
		{
			if (isThisSpotBlockedByTheOpponent(selectedTileRowLocation, currentColumn))
			{
				return;
			}
			if (isThisSpotClaimedByWhoseTurnItIs(selectedTileRowLocation, currentColumn))
			{
				++rowTileCount;
			}
		}
		if (rowTileCount == GameBoard.getDimension())
		{
			endThisGameAndDisplayItsOutcome(whoseTurnItIs + " has won a row!");
		}
	}
	
	
	private static boolean isThisSpotBlockedByTheOpponent(int row, int column)
	{
		GameTile gameTileAtThisSpot = GameBoard.getGameTileAt(row, column);
		String gameTileText = gameTileAtThisSpot.getText();
		return gameTileText.equals(opponent);
	}
	
	
	private static boolean isThisSpotClaimedByWhoseTurnItIs(int row, int column)
	{
		GameTile gameTileAtThisSpot = GameBoard.getGameTileAt(row, column);
		String gameTileText = gameTileAtThisSpot.getText();
		return gameTileText.equals(whoseTurnItIs);
	}
	
	
	private static void endThisGameAndDisplayItsOutcome(String gameOutcome)
	{
		Game.setHasEnded(true);
		new GameEndWindow(gameOutcome);
	}
	
	
	private static void checkForACompletedColumn()
	{
		int columnTileCount = 0;
		for (int currentRow = 0; currentRow < GameBoard.getDimension(); ++currentRow)
		{
			if (isThisSpotBlockedByTheOpponent(currentRow, selectedTileColumnLocation))
			{
				return;
			}
			if (isThisSpotClaimedByWhoseTurnItIs(currentRow, selectedTileColumnLocation))
			{
				++columnTileCount;
			}
		}
		if (columnTileCount == GameBoard.getDimension())
		{
			endThisGameAndDisplayItsOutcome(whoseTurnItIs + " has won a column!");
		}
	}
	
	
	private static void checkForACompletedDiagonal()
	{
		checkForCompletedUpRightDiagonal();
		checkForCompletedDownRightDiagonal();
	}
	
	
	private static void checkForCompletedUpRightDiagonal()
	{
		int upRightDiagonalTileCount = 0;
		for (int row = GameBoard.getDimension() - 1, column = 0; row >= 0 && column < GameBoard.getDimension(); --row, ++column)
		{
			if (isThisSpotBlockedByTheOpponent(row, column))
			{
				break;
			}
			if (isThisSpotClaimedByWhoseTurnItIs(row, column))
			{
				++upRightDiagonalTileCount;
			}
		}
		if (upRightDiagonalTileCount == GameBoard.getDimension())
		{
			endThisGameAndDisplayItsOutcome(whoseTurnItIs + " has won the up-right diagonal!");
		}
	}
	
	
	private static void checkForCompletedDownRightDiagonal()
	{
		int downRightDiagonalTileCount = 0;
		for (int row = 0, column = 0; row < GameBoard.getDimension() && column < GameBoard.getDimension(); ++row, ++column)
		{
			if (isThisSpotBlockedByTheOpponent(row, column))
			{
				break;
			}
			if (isThisSpotClaimedByWhoseTurnItIs(row, column))
			{
				++downRightDiagonalTileCount;
			}
		}
		if (downRightDiagonalTileCount == GameBoard.getDimension())
		{
			endThisGameAndDisplayItsOutcome(whoseTurnItIs + " has won the down-right diagonal!");
		}
	}
	
	
	private static void checkForAStalemate()
	{
		int totalNumberOfSpotsOnGameBoard = GameBoard.getDimension() * GameBoard.getDimension();
		if (Game.getNumberOfSpotsClaimed() == totalNumberOfSpotsOnGameBoard)
		{
			endThisGameAndDisplayItsOutcome("DRAW!");
		}		
	}

} // End of class.
