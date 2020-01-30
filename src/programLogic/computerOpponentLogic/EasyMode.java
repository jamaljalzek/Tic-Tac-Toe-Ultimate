package programLogic.computerOpponentLogic;

import programLogic.computerOpponentLogic.mediumMode.subComponents.PickSpotRandomly;

public class EasyMode
{
	/**
	 * During this difficulty, the computer uses no strategy to win. Instead, it will search
	 * for an open spot on the game board randomly, and select the first one it finds.
	 */
	public static void easyMode()
	{
		PickSpotRandomly.onGameBoard();
	}

} // End of class.
