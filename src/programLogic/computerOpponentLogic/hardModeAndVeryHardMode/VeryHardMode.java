package programLogic.computerOpponentLogic.hardModeAndVeryHardMode;

import programLogic.Game;
import programLogic.computerOpponentLogic.hardModeAndVeryHardMode.subComponents.PreventPlayerFromWinning;
import programLogic.computerOpponentLogic.hardModeAndVeryHardMode.subComponents.prioritizeWinningTheGameThisTurn.PrioritizeWinningTheGameThisTurn;
import programLogic.computerOpponentLogic.mediumMode.MediumMode;

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
		orAttemptToBlockThePlayerIfTheyAreTwoTurnsAwayFromWinning();
		orAttemptToBlockThePlayerIfTheyAreOneTurnAwayFromWinning();
		orTheComputerWillAttemptToGetCloserToWinningTheGame();
	}
	
	
	private static void orAttemptToBlockThePlayerIfTheyAreTwoTurnsAwayFromWinning()
	{
		if (Game.isStillComputersTurn())
		{
			PreventPlayerFromWinning.blockPlayerIfTheyXTurnsAwayFromWinning(2);
		}
	}
	
	
	private static void orAttemptToBlockThePlayerIfTheyAreOneTurnAwayFromWinning()
	{
		if (Game.isStillComputersTurn())
		{
			PreventPlayerFromWinning.blockPlayerIfTheyXTurnsAwayFromWinning(1);
		}
	}
	
	
	private static void orTheComputerWillAttemptToGetCloserToWinningTheGame()
	{
		if (Game.isStillComputersTurn())
		{
			MediumMode.mediumMode();
		}
	}

} // End of class.
