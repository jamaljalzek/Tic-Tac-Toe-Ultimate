package gui.gameScreen;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import programLogic.CheckGameStatus;
import programLogic.Game;
import programLogic.GameBoard;
import programLogic.computerOpponentLogic.EasyMode;
import programLogic.computerOpponentLogic.hardMode.HardMode;
import programLogic.computerOpponentLogic.mediumMode.MediumMode;

public class GameTile extends JButton
{
	public final int row, column;
	
	
	public GameTile (int row, int column)
	{
		this.row = row;
		this.column = column;
		this.addActionListener(new ButtonListener());
	}
	
	private class ButtonListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent event)
		{
			GameTile clickedTile = (GameTile) event.getSource();
			GameBoard.letPlayerClaimSpotOnGameBoard(clickedTile);
			CheckGameStatus.checkGameStatus(clickedTile);
			letTheComputerMakeACounterMove();
		}
		
		
		private void letTheComputerMakeACounterMove()
		{
			if (Game.hasEnded())
			{
				return;
			}
			if (Game.getDifficulty().equals("EASY"))
			{
				EasyMode.easyMode();
			}
			else if (Game.getDifficulty().equals("MEDIUM"))
			{
				MediumMode.mediumMode();
			}
			else // Game.difficulty().equals("HARD") 
			{
				HardMode.hardMode();
			}
		}

	} // End of nested class.

} // End of class.
