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

public class StartNewGameScreen extends JPanel
{
	private MainWindow mainWindow;
	
	private JTextField enterPlayerSymbol, enterComputerSymbol;
	private JComboBox <Integer> selectDimensionList;
	private int selectedDimension;
	private String selectedDifficulty;
	private JRadioButton easyMode, mediumMode, hardMode;
	
	
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
		
		// Initialize the default values for:
		enterPlayerSymbol.setText("X"); // The player's symbol.
		enterComputerSymbol.setText("O"); // The computer's symbol.
		selectDimensionList.setSelectedIndex(0); // The default dimension to be 3.
		easyMode.setSelected(true); // The game difficulty.
		
	} // End of Constructor.
	
	
	private void createContents ()
	{
		JPanel settingsPanel, chooseSymbolsAndDimensionPanel, selectDifficultyPanel, buttonsPanel;
		Integer [] dimensionsToChoose;
		JButton start, quit;
		
		
		// This screen will be split into two main halves: an upper half (settingsPanel)
		// and a lower half (buttonsPanel).
		// settingsPanel will be further split into two halves, with the left half being the
		// selectDimensionPanel, and the right half being the selectDifficultyPanel.
		
		// Create the JList of dimensions that the player can select:
		dimensionsToChoose = new Integer [] {3, 4, 5, 6, 7, 8, 9, 10};
		selectDimensionList = new JComboBox <Integer>(dimensionsToChoose);
		selectDimensionList.addActionListener( new SelectionListener() );
		
		// Create the two areas that the player can enter in a symbol to represent them self, as well as the computer:
		enterPlayerSymbol = new JTextField();
		enterComputerSymbol = new JTextField();
		
		// Build the chooseSymbolsAndDimensionPanel and add its components:
		chooseSymbolsAndDimensionPanel = new JPanel();
		chooseSymbolsAndDimensionPanel.setLayout( new GridLayout (0, 1) );
		chooseSymbolsAndDimensionPanel.add( new JLabel("<html>Enter the player's symbol:</html>") );
		chooseSymbolsAndDimensionPanel.add(enterPlayerSymbol);
		chooseSymbolsAndDimensionPanel.add( new JLabel("<html>Enter the computer's symbol:</html>") );
		chooseSymbolsAndDimensionPanel.add(enterComputerSymbol);
		chooseSymbolsAndDimensionPanel.add( new JLabel("<html>Select game board dimension (number of rows/columns):</html>") );
		chooseSymbolsAndDimensionPanel.add(selectDimensionList);
		
		// Create the difficulty options that can be selected:
		easyMode = new JRadioButton("EASY");
		easyMode.addItemListener( new RadioButtonListener() );
		mediumMode = new JRadioButton("MEDIUM");
		mediumMode.addItemListener( new RadioButtonListener() );
		hardMode = new JRadioButton("HARD");
		hardMode.addItemListener( new RadioButtonListener() );
		
		// Create the selectDifficultyPanel and add its components:
		selectDifficultyPanel = new JPanel();
		selectDifficultyPanel.setLayout( new BoxLayout (selectDifficultyPanel, BoxLayout.Y_AXIS) );
		selectDifficultyPanel.add( new JLabel("<html>Select the game difficulty:</html>") );
		selectDifficultyPanel.add(easyMode);
		selectDifficultyPanel.add(mediumMode);
		selectDifficultyPanel.add(hardMode);
		
		// Assemble the above panels into the settingsPanel:
		settingsPanel = new JPanel();
		settingsPanel.setLayout( new BoxLayout (settingsPanel, BoxLayout.X_AXIS) );
		settingsPanel.add( new JPanel() );
		settingsPanel.add(chooseSymbolsAndDimensionPanel);
		settingsPanel.add( new JPanel() );
		settingsPanel.add(selectDifficultyPanel);
		settingsPanel.add( new JPanel() );
		
		// Create and activate the two buttons:
		start = new JButton("Start!");
		start.addActionListener( new ButtonListener() );
		quit = new JButton("Quit");
		quit.addActionListener( new ButtonListener() );
		
		// Assemble the buttonsPanel with the above buttons:
		buttonsPanel = new JPanel();
		buttonsPanel.setLayout( new BoxLayout (buttonsPanel, BoxLayout.X_AXIS) );
		buttonsPanel.add( new JPanel() );
		buttonsPanel.add(start);
		buttonsPanel.add( new JPanel() );
		buttonsPanel.add(quit);
		buttonsPanel.add( new JPanel() );
		
		// Finally, assemble our StartNewGameScreen:
		this.add( new JPanel() );
		this.add(settingsPanel);
		this.add( new JPanel() );
		this.add(buttonsPanel);
		this.add( new JPanel() );
		
	} // End of method createContents.
	
	
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
		@Override
		public void actionPerformed (ActionEvent event)
		{
			JButton buttonClicked;
			
			
			// Get the button that was clicked, then determine what action to take:
			buttonClicked = (JButton) event.getSource();
			
			if ( buttonClicked.getText().equals("Start!") )
			{
				// Start a new game, and display it on the mainWindow:
				mainWindow.displayNewScreen(new GameBoardScreen(
				new Game(selectedDimension, enterPlayerSymbol.getText(), enterComputerSymbol.getText(), selectedDifficulty) ) );
				
				// Automatically resize the mainWindow so that each GameTile is 100 x 100 pixels each:
				mainWindow.setSize(selectedDimension * 100, selectedDimension * 100);
				
				// Recenter the mainWindow:
				mainWindow.setLocationRelativeTo(null);
			}
			else // Otherwise, "Quit" was selected.
			{
				// Terminate this program:
				System.exit(0);
			}
			
		} // End of method actionPerformed.
		
	} // End of class ButtonListener.
	
	
	private class RadioButtonListener implements ItemListener
	{

		@Override
		public void itemStateChanged (ItemEvent event)
		{
			JRadioButton selectedRadioButton;
			
			
			// Check if this JRadioButton instance has been selected or deselected.
			if (event.getStateChange() == ItemEvent.SELECTED)
			{
				// Because it has been selected, find out which game difficulty it represents:
				selectedRadioButton = (JRadioButton) event.getItem();
				selectedDifficulty = selectedRadioButton.getText();
				
				// Make sure the other JRadioButtons are unselected:
				if ( selectedDifficulty.equals("EASY") )
				{
					mediumMode.setSelected(false);
					hardMode.setSelected(false);
				}
				else if ( selectedDifficulty.equals("MEDIUM") )
				{
					easyMode.setSelected(false);
					hardMode.setSelected(false);
				}
				else // selectedDifficulty.equals("HARD")
				{
					easyMode.setSelected(false);
					mediumMode.setSelected(false);
				}
			}
			
			// Otherwise, do nothing.
			
		} // End of method itemStateChanged.
		
	} // End of class RadioButtonListener.
	
} // End of class StartNewGameScreen.
