package programLogic.computerOpponentLogic.mediumMode.subComponents;

import java.util.Random;

import oldPackage.Game;
import programLogic.GameBoard;

public class PickSpotRandomly
{
	private static int chosenRow, chosenColumn;
	
	
	public static void onGameBoard()
	{
		Random randomIndex = new Random();
		do
		{
			chosenRow = randomIndex.nextInt(Game.thisGame().dimension);
			chosenColumn = randomIndex.nextInt(Game.thisGame().dimension);
		}
		while (GameBoard.isChosenSpotAlreadyTaken(chosenRow, chosenColumn));
		GameBoard.letComputerClaimSpotOnGameBoard(chosenRow, chosenColumn);
	}
	
	
	public static void inChosenRow(int chosenRow)
	{
		Random randomIndex = new Random();
		do
		{
			chosenColumn = randomIndex.nextInt(Game.thisGame().dimension);
		}
		while (GameBoard.isChosenSpotAlreadyTaken(chosenRow, chosenColumn));
		GameBoard.letComputerClaimSpotOnGameBoard(chosenRow, chosenColumn);
	}
	
	
	public static void inChosenColumn(int chosenColumn)
	{
		Random randomIndex = new Random();
		do
		{
			chosenRow = randomIndex.nextInt(Game.thisGame().dimension);
		}
		while (GameBoard.isChosenSpotAlreadyTaken(chosenRow, chosenColumn));
		GameBoard.letComputerClaimSpotOnGameBoard(chosenRow, chosenColumn);
	}
	
	
	public static void inChosenDiagonal(int chosenDiagonalRowLocation)
	{
		Random randomIndex = new Random();
		if (chosenDiagonalRowLocation == 0)
		{
			do
			{
				chosenRow = randomIndex.nextInt(Game.thisGame().dimension);
				chosenColumn = chosenRow;
			}
			while (GameBoard.isChosenSpotAlreadyTaken(chosenRow, chosenColumn));
		}
		else // chosenDiagonalRowLocation == bottom row index
		{
			do
			{
				// The relationship between the row index and the column index is row + column = dimension - 1.
				// Or, given a row, we can rewrite this relationship as column = dimension - 1 - row.
				chosenRow = randomIndex.nextInt(Game.thisGame().dimension);
				chosenColumn = Game.thisGame().dimension - 1 - chosenRow;
			}
			while (GameBoard.isChosenSpotAlreadyTaken(chosenRow, chosenColumn));
		}
		GameBoard.letComputerClaimSpotOnGameBoard(chosenRow, chosenColumn);
	}

} // End of class.
