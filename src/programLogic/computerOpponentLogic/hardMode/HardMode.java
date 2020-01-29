package programLogic.computerOpponentLogic.hardMode;

import programLogic.Game;
import programLogic.computerOpponentLogic.hardMode.subComponents.PreventPlayerFromWinning;
import programLogic.computerOpponentLogic.hardMode.subComponents.prioritizeWinningTheGameThisTurn.PrioritizeWinningTheGameThisTurn;
import programLogic.computerOpponentLogic.mediumMode.MediumMode;

public class HardMode
{
	/**
	 * During this difficulty, the computer will use the same strategy to win as described in mediumMode. However, unlike
	 * mediumMode, the computer additionally will block the player from completing a row, column, or diagonal. In this game
	 * mode, the player must have a strategy that leads them to a situation where they have two different ways of
	 * winning in a single turn.
	 * 
	 * @param Game.thisGame()
	 */
	public static void hardMode()
	{
		if (!Game.hasEnded())
		{
			PrioritizeWinningTheGameThisTurn.attemptToWinTheGameThisTurn();
		}
		if (Game.isStillComputersTurn())
		{
			PreventPlayerFromWinning.blockPlayerIfTheyAreAboutToWin();
		}
		if (Game.isStillComputersTurn())
		{
			MediumMode.mediumMode();
		}
	}

} // End of class.
