package gui.menuScreen;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import programLogic.Game;

public class SelectSymbolsAndDimensionPanel extends JPanel
{
	private static SelectSymbolsAndDimensionPanel selectSymbolsAndDimensionPanel;
	private JTextField enterPlayerSymbolField, enterComputerSymbolField;
	
	
	public SelectSymbolsAndDimensionPanel()
	{
		selectSymbolsAndDimensionPanel = this;
		this.setLayout(new GridLayout (0, 1));
		addTextFieldsAndTheirLabels();
		this.add(new JLabel("<html>Select game board dimension (number of rows/columns):</html>"));
		this.add(new SelectGameBoardDimensionDropDownList());
	}
	
	private void addTextFieldsAndTheirLabels()
	{
		this.add( new JLabel("<html>Enter the player's symbol:</html>") );
		enterPlayerSymbolField = new JTextField();
		this.add(enterPlayerSymbolField);
		enterPlayerSymbolField.setText("X");

		this.add( new JLabel("<html>Enter the computer's symbol:</html>") );
		enterComputerSymbolField = new JTextField();
		this.add(enterComputerSymbolField);
		enterComputerSymbolField.setText("O");
	}
	
	
	public static void recordEnteredPlayerAndComputerSymbols()
	{
		String enteredPlayerSymbol = selectSymbolsAndDimensionPanel.enterPlayerSymbolField.getText();
		Game.setPlayerSymbol(enteredPlayerSymbol);
		String enteredComputerSymbol = selectSymbolsAndDimensionPanel.enterComputerSymbolField.getText();
		Game.setComputerSymbol(enteredComputerSymbol);
	}
	
} // End of class.
