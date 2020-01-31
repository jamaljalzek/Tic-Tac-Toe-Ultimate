package gui.menuScreen;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

import gui.gameScreen.GameBoardScreen;
import gui.menuScreen.selectGameSettingsPanel.subComponents.SelectSymbolsAndDimensionPanel;
import gui.programWindow.MainWindow;

public class ButtonsPanel extends JPanel
{
	private JButton startButton, quitButton;
	
	
	public ButtonsPanel()
	{
		this.setLayout(new BoxLayout (this, BoxLayout.X_AXIS));
		this.add(Box.createHorizontalGlue());
		addButtons();
		this.add(Box.createHorizontalGlue());
	}
	
	
	private void addButtons()
	{
		addStartButton();
		this.add(Box.createHorizontalGlue());
		addQuitButton();
	}
	
	
	private void addStartButton()
	{
		startButton = new JButton("Start!");
		startButton.addActionListener(new ButtonListener());
		this.add(startButton);
	}
	
	
	private void addQuitButton()
	{
		quitButton = new JButton("Quit");
		quitButton.addActionListener(new ButtonListener());
		this.add(quitButton);
	}
	
	
	private class ButtonListener implements ActionListener
	{
		private JButton buttonClicked;
		
		
		@Override
		public void actionPerformed(ActionEvent event)
		{
			buttonClicked = (JButton) event.getSource();
			checkIfStartWasClicked();
			checkIfQuitWasClicked();
		}
		
		
		private void checkIfStartWasClicked()
		{
			if (buttonClicked == startButton)
			{
				startAndDisplayNewGame();
				MainWindow.resizeAndReCenterMainWindow();
			}
		}
		
		
		private void startAndDisplayNewGame()
		{
			SelectSymbolsAndDimensionPanel.recordEnteredPlayerAndComputerSymbols();
			MainWindow.displayNewScreen(new GameBoardScreen());
		}
		
		
		private void checkIfQuitWasClicked()
		{
			if (buttonClicked == quitButton)
			{
				System.exit(0);
			}
		}
		
	} // End of nested class.

} // End of class.
