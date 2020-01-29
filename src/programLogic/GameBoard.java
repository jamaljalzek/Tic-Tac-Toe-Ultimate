package programLogic;

import oldPackage.GameTile;

public class GameBoard
{
	private static GameTile [] [] gameBoard;
	private static int dimension;
	
	
	public static void setUpNewGameBoard()
	{
		gameBoard = new GameTile [dimension] [dimension];
		for (int currentRow = 0; currentRow < dimension; ++currentRow)
		{
			for (int currentColumn = 0; currentColumn < dimension; ++currentColumn)
			{
				addNewGameTimeToTheGameBoard(currentRow, currentColumn);
			}
		}
	}
	
	
	private static void addNewGameTimeToTheGameBoard(int currentRow, int currentColumn)
	{
		GameTile currentTile = new GameTile(currentRow, currentColumn);
		gameBoard [currentRow] [currentColumn] = currentTile;
	}
	
	
	public static GameTile getGameTileAt(int row, int column)
	{
		return gameBoard [row] [column];
	}
	
	
	public static int getDimension()
	{
		return dimension;
	}
	
	
	public static void setDimension(int dimension)
	{
		 GameBoard.dimension = dimension;
	}
	
	
	public static void passInNewGameBoard(GameTile [] [] gameBoard)
	{
		GameBoard.gameBoard = gameBoard;
	}


	public static boolean isChosenSpotAlreadyTaken(int chosenRow, int chosenColumn)
	{
		GameTile gameBoardSpotClaimed = gameBoard [chosenRow] [chosenColumn];
		return !gameBoardSpotClaimed.getText().isBlank();
	}
	
	
	public static boolean isThisSpotClaimedByPlayer(int row, int column)
	{
		GameTile gameBoardSpotClaimed = gameBoard [row] [column];
		return gameBoardSpotClaimed.getText().equals(Game.getPlayerSymbol());
	}
	
	
	public static boolean isThisSpotClaimedByComputer(int row, int column)
	{
		GameTile gameBoardSpotClaimed = gameBoard [row] [column];
		return gameBoardSpotClaimed.getText().equals(Game.getComputerSymbol());
	}
	
	
	public static void letComputerClaimSpotOnGameBoard(int row, int column)
	{
		GameTile gameBoardSpotClaimed = gameBoard [row] [column];
		gameBoardSpotClaimed.setText(Game.getComputerSymbol());
		gameBoardSpotClaimed.setEnabled(false);
		Game.incrementNumberOfSpotsClaimed();
		Game.setPlayersTurn(true);
		CheckGameStatus.checkGameStatus(gameBoardSpotClaimed);
	}
	
	
	public static boolean canThisRowColumnOrDiagonalBeCompletedThisTurn(int computerTileCount)
	{
		int numberOfSpotsInEveryRowColumnAndDiagonal = dimension;
		return computerTileCount == (numberOfSpotsInEveryRowColumnAndDiagonal - 1);
	}
	
	
	public static void letPlayerClaimSpotOnGameBoard(GameTile clickedTile)
	{
		clickedTile.setText(Game.getPlayerSymbol());
		clickedTile.setEnabled(false);
		Game.incrementNumberOfSpotsClaimed();
		Game.setPlayersTurn(false);
	}
	
} // End of class.
