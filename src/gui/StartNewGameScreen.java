package gui;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import gui.gameScreen.GameBoardScreen;
import gui.programWindow.MainWindow;
import programLogic.Game;
import programLogic.GameBoard;

public class StartNewGameScreen extends JPanel
{
	private MainWindow mainWindow;
	
	private JTextField enterPlayerSymbol, enterComputerSymbol;
	private JComboBox <Integer> selectDimensionList;
	private int selectedDimension;
	private String selectedDifficulty;
	private JRadioButton easyMode, mediumMode, hardMode, veryHardMode;
	private JPanel settingsPanel, chooseSymbolsAndDimensionPanel, selectDifficultyPanel, buttonsPanel;
	private Integer [] dimensionsToChoose;
	private JButton start, quit;
	
	
	/**
	 * This is the first screen that is displayed when the program launches. It is here where the player
	 * chooses the dimension of the game board, as well as the difficulty the computer will play as.
	 * Once they click on the "Start!" button, the mainWindow then displays the GameBoardScreen.
	 * 
	 * @param mainWindow
	 */
	public StartNewGameScreen (MainWindow mainWindow)
	{
		this.mainWindow =  mainWindow;
		
		
		this.setLayout( new BoxLayout(this, BoxLayout.Y_AXIS) );
		this.createContents();
		this.initializeDefaultValues();
		
	} // End of Constructor.
	
	
	private void initializeDefaultValues ()
	{
		enterPlayerSymbol.setText("X");
		enterComputerSymbol.setText("O");
		selectDimensionList.setSelectedIndex(0); // The default game board dimension is 3 x 3.
		easyMode.setSelected(true);
		
	} // End of method initializeDefaultValues.
	
	
	private void createContents ()
	{
		// This screen will be split into two main halves: an upper half (settingsPanel)
		// and a lower half (buttonsPanel).
		// settingsPanel will be further split into two halves, with the left half being the
		// selectDimensionPanel, and the right half being the selectDifficultyPanel.
		createDimenionsJList();
		createTextFieldsAndButtons();
		createChooseSymbolsAndDimensionsPanel();
		createDifficultyRadioButtons();
		createSelectDifficultyPanel();
		createSettingsPanel();
		createButtonsPanel();
		assembleStartNewGameScreen();
		
	} // End of method createContents.
	
	
	private void createDimenionsJList ()
	{
		dimensionsToChoose = new Integer [] {3, 4, 5, 6, 7, 8, 9, 10};
		selectDimensionList = new JComboBox <Integer>(dimensionsToChoose);
		selectDimensionList.addActionListener( new SelectionListener() );
		
	} // End of method createDimenionsJList.
	
	
	private void createTextFieldsAndButtons ()
	{
		enterPlayerSymbol = new JTextField();
		enterComputerSymbol = new JTextField();
		start = new JButton("Start!");
		start.addActionListener( new ButtonListener() );
		quit = new JButton("Quit");
		quit.addActionListener( new ButtonListener() );
		
	} // End of method createTextFieldsAndButtons.
	
	
	private void createChooseSymbolsAndDimensionsPanel ()
	{
		chooseSymbolsAndDimensionPanel = new JPanel();
		chooseSymbolsAndDimensionPanel.setLayout( new GridLayout (0, 1) );
		chooseSymbolsAndDimensionPanel.add( new JLabel("<html>Enter the player's symbol:</html>") );
		chooseSymbolsAndDimensionPanel.add(enterPlayerSymbol);
		chooseSymbolsAndDimensionPanel.add( new JLabel("<html>Enter the computer's symbol:</html>") );
		chooseSymbolsAndDimensionPanel.add(enterComputerSymbol);
		chooseSymbolsAndDimensionPanel.add( new JLabel("<html>Select game board dimension (number of rows/columns):</html>") );
		chooseSymbolsAndDimensionPanel.add(selectDimensionList);
		
	} // End of method createChooseSymbolsAndDimensionsPanel.
	
	
	private void createDifficultyRadioButtons ()
	{
		easyMode = new JRadioButton("EASY");
		easyMode.addItemListener(new RadioButtonListener());
		mediumMode = new JRadioButton("MEDIUM");
		mediumMode.addItemListener(new RadioButtonListener());
		hardMode = new JRadioButton("HARD");
		hardMode.addItemListener(new RadioButtonListener());
		veryHardMode = new JRadioButton("VERY HARD");
		veryHardMode.addItemListener(new RadioButtonListener());
	}
	
	
	private void createSelectDifficultyPanel ()
	{
		selectDifficultyPanel = new JPanel();
		selectDifficultyPanel.setLayout( new BoxLayout (selectDifficultyPanel, BoxLayout.Y_AXIS) );
		selectDifficultyPanel.add( new JLabel("<html>Select the game difficulty:</html>") );
		selectDifficultyPanel.add(easyMode);
		selectDifficultyPanel.add(mediumMode);
		selectDifficultyPanel.add(hardMode);
		selectDifficultyPanel.add(veryHardMode);
	}
	
	
	private void createSettingsPanel ()
	{
		settingsPanel = new JPanel();
		settingsPanel.setLayout( new BoxLayout (settingsPanel, BoxLayout.X_AXIS) );
		settingsPanel.add( new JPanel() );
		settingsPanel.add(chooseSymbolsAndDimensionPanel);
		settingsPanel.add( new JPanel() );
		settingsPanel.add(selectDifficultyPanel);
		settingsPanel.add( new JPanel() );
		
	} // End of method createSettingsPanel.
	
	
	private void createButtonsPanel ()
	{
		buttonsPanel = new JPanel();
		buttonsPanel.setLayout( new BoxLayout (buttonsPanel, BoxLayout.X_AXIS) );
		buttonsPanel.add( new JPanel() );
		buttonsPanel.add(start);
		buttonsPanel.add( new JPanel() );
		buttonsPanel.add(quit);
		buttonsPanel.add( new JPanel() );
		
	} // End of method createButtonsPanel.
	
	
	private void assembleStartNewGameScreen ()
	{
		this.add( new JPanel() );
		this.add(settingsPanel);
		this.add( new JPanel() );
		this.add(buttonsPanel);
		this.add( new JPanel() );
		
	} // End of method assembleStartNewGameScreen.
	
	
	private class SelectionListener implements ActionListener
	{
		@Override
		public void actionPerformed (ActionEvent event)
		{
			selectedDimension = (Integer) selectDimensionList.getSelectedItem();
			
		} // End of method actionPerformed.
		
	} // End of class SelectionListener.
	
	
	private class ButtonListener implements ActionListener
	{
		private ActionEvent event;
		private JButton buttonClicked;
		
		
		@Override
		public void actionPerformed (ActionEvent event)
		{
			this.event = event;
			
			
			determineWhatButtonWasClicked();
			
		} // End of method actionPerformed.
		
		
		private void determineWhatButtonWasClicked ()
		{
			buttonClicked = (JButton) event.getSource();
			checkIfStartWasClicked();
			checkIfQuitWasClicked();
			
		} // End of method determineWhatButtonWasClicked.
		
		
		private void checkIfStartWasClicked ()
		{
			if ( buttonClicked.getText().equals("Start!") )
			{
				startAndDisplayNewGame();
				resizeAndReCenterMainWindow();
			}
			
		} // End of method checkIfStartWasClicked.
		
		
		private void startAndDisplayNewGame ()
		{
			GameBoard.setDimension(selectedDimension);
			initializeGameInformation();
			mainWindow.displayNewScreen(new GameBoardScreen());
		}
		
		
		private void initializeGameInformation()
		{
			Game.setPlayerSymbol(enterPlayerSymbol.getText());
			Game.setComputerSymbol(enterComputerSymbol.getText());
			Game.setDifficulty(selectedDifficulty);
		}
		
		
		private void resizeAndReCenterMainWindow ()
		{
			// Automatically resize the mainWindow so that each GameTile is 100 x 100 pixels each:
			mainWindow.setSize(selectedDimension * 100, selectedDimension * 100);
			// Re-center the mainWindow:
			mainWindow.setLocationRelativeTo(null);
			
		} // End of method resizeAndReCenterMainWindow.
		
		
		private void checkIfQuitWasClicked ()
		{
			if ( buttonClicked.getText().equals("Quit") )
			{
				System.exit(0);
			}
			
		} // End of method checkIfQuitWasClicked.
		
	} // End of class ButtonListener.
	
	
	private class RadioButtonListener implements ItemListener
	{
		private ItemEvent event;
		private JRadioButton selectedRadioButton;
		
		
		@Override
		public void itemStateChanged(ItemEvent event)
		{
			this.event = event;
			if (isThisRadioButtonSelected())
			{
				determineGameDifficultySelected();
				unselectOtherRadioButtons();
			}
		}
		
		
		private boolean isThisRadioButtonSelected()
		{
			return event.getStateChange() == ItemEvent.SELECTED;
		}
		
		
		private void determineGameDifficultySelected()
		{
			selectedRadioButton = (JRadioButton) event.getItem();
			selectedDifficulty = selectedRadioButton.getText();
		}
		
		
		private void unselectOtherRadioButtons()
		{
			if (selectedRadioButton == easyMode)
			{
				mediumMode.setSelected(false);
				hardMode.setSelected(false);
				veryHardMode.setSelected(false);
			}
			else if (selectedRadioButton == mediumMode)
			{
				easyMode.setSelected(false);
				hardMode.setSelected(false);
				veryHardMode.setSelected(false);
			}
			else if (selectedRadioButton == hardMode)
			{
				easyMode.setSelected(false);
				mediumMode.setSelected(false);
				veryHardMode.setSelected(false);
			}
			else // selectedRadioButton == veryHardMode
			{
				easyMode.setSelected(false);
				mediumMode.setSelected(false);
				hardMode.setSelected(false);
			}
		}
		
	} // End of nested class.
	
} // End of class.
