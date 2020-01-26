package programLogic;

import oldPackage.Game;
import oldPackage.GameTile;

public class GameBoard
{
	private static GameTile [] [] gameBoard;
	
	
	public static int getDimension()
	{
		return gameBoard.length;
	}
	
	
	public static void passInNewGameBoard(GameTile [] [] gameBoard)
	{
		GameBoard.gameBoard = gameBoard;
	}


	public static boolean isChosenSpotAlreadyTaken(int chosenRow, int chosenColumn)
	{
		GameTile gameBoardSpotClaimed = gameBoard [chosenRow] [chosenColumn];
		return gameBoardSpotClaimed.getText().isBlank();
	}
	
	
	public static boolean isThisSpotClaimedByPlayer(int row, int column)
	{
		GameTile gameBoardSpotClaimed = gameBoard [row] [column];
		return gameBoardSpotClaimed.getText().equals(Game.thisGame().playerSymbol);
	}
	
	
	public static boolean isThisSpotClaimedByComputer(int row, int column)
	{
		GameTile gameBoardSpotClaimed = gameBoard [row] [column];
		return gameBoardSpotClaimed.getText().equals(Game.thisGame().computerSymbol);
	}
	
	
	public static void letComputerClaimSpotOnGameBoard(int row, int column)
	{
		GameTile gameBoardSpotClaimed = gameBoard [row] [column];
		gameBoardSpotClaimed.setText(Game.thisGame().computerSymbol);
		gameBoardSpotClaimed.setEnabled(false);
		++Game.thisGame().numberOfSpotsClaimed;
		Game.thisGame().isPlayersTurn = true;
		Game.thisGame().checkGameStatus(gameBoardSpotClaimed);
	}
	
	
	public static boolean canThisRowColumnOrDiagonalBeCompletedThisTurn(int computerTileCount)
	{
		int numberOfSpotsInEveryRowColumnAndDiagonal = Game.thisGame().dimension;
		return computerTileCount == (numberOfSpotsInEveryRowColumnAndDiagonal - 1);
	}
	
} // End of class.
