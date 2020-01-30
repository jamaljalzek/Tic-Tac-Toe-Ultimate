package programLogic.computerOpponentLogic.veryHardMode;

import programLogic.Game;
import programLogic.computerOpponentLogic.hardMode.HardMode;
import programLogic.computerOpponentLogic.hardMode.subComponents.prioritizeWinningTheGameThisTurn.PrioritizeWinningTheGameThisTurn;

public class VeryHardMode
{
	/**
	 * During this difficulty, the computer will use essentially the same strategy to win as described in hard mode,
	 * but a little more aggressively. Unlike hard mode, the computer won't wait for the player to be one turn away from completing
	 * a row, column, or diagonal. Instead, it will block the player if they are two turns away from completing any of the three.
	 * Otherwise, it will default to hard mode.
	 * The player must have a pretty good strategy to defeat this mode.
	 */
	public static void veryHardMode()
	{
		PrioritizeWinningTheGameThisTurn.attemptToWinTheGameThisTurn();
		if (Game.isStillComputersTurn())
		{
			BlockPlayerTwoTurnsAheadOfWinningTheGame.blockPlayerIfTheyAreCloseToWinning();
		}
		if (Game.isStillComputersTurn())
		{
			HardMode.hardMode();
		}
	}

} // End of class.
