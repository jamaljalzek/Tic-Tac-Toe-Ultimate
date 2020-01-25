package programLogic;

import java.util.Random;

import oldPackage.Game;

public class EasyMode
{
	private static int chosenRow, chosenColumn;
	
	
	/**
	 * During this difficulty, the computer uses no strategy to win. Instead, it will search
	 * for an open spot on the game board randomly, and select the first one it finds.
	 * 
	 * @param Game.thisGame()
	 */
	public static void easyMode()
	{
		if (Game.thisGame().hasEnded)
		{
			return;
		}
		chooseRandomSpotOnGameBoard();
		GameBoard.letComputerClaimSpotOnGameBoard(chosenRow, chosenColumn);
	}
		
	
	private static void chooseRandomSpotOnGameBoard()
	{
		Random randomIndex = new Random();
		do
		{
			chosenRow = randomIndex.nextInt(Game.thisGame().dimension);
			chosenColumn = randomIndex.nextInt(Game.thisGame().dimension);
		}
		while (GameBoard.isChosenSpotAlreadyTaken(chosenRow, chosenColumn));
	}
	
} // End of class.
