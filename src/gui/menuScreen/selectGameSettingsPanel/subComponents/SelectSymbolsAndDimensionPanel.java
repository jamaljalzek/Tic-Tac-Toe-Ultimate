package gui.menuScreen.selectGameSettingsPanel.subComponents;

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
		addEnterPlaySymbolTextFieldAndLabel();
		addEnterComputerSymbolTextFieldAndLabel();
		addSelectGameBoardDimensionLabel();
		this.add(new SelectGameBoardDimensionDropDownList());
	}
	
	
	private void addEnterPlaySymbolTextFieldAndLabel()
	{
		this.add(new JLabel("<html>Enter the player's symbol:</html>"));
		enterPlayerSymbolField = new JTextField();
		this.add(enterPlayerSymbolField);
		enterPlayerSymbolField.setText("X");
	}
	
	
	private void addEnterComputerSymbolTextFieldAndLabel()
	{
		this.add(new JLabel("<html>Enter the computer's symbol:</html>"));
		enterComputerSymbolField = new JTextField();
		this.add(enterComputerSymbolField);
		enterComputerSymbolField.setText("O");
	}
	
	
	private void addSelectGameBoardDimensionLabel()
	{
		JLabel selectGameBoardDimensionLabel = new JLabel("<html>Select the gameboard's dimension (rows & columns):</html>");
		this.add(selectGameBoardDimensionLabel);
	}
	
	
	public static void recordEnteredPlayerAndComputerSymbols()
	{
		reduceAndRecordEnteredPlayerSymbol();
		reduceAndRecordEnteredComputerSymbol();
	}
	
	
	private static void reduceAndRecordEnteredPlayerSymbol()
	{
		String enteredPlayerSymbol = selectSymbolsAndDimensionPanel.enterPlayerSymbolField.getText();
		if (enteredPlayerSymbol.length() == 0)
		{
			enteredPlayerSymbol = "X";
		}
		String reducedPlayerSymbol = "" + enteredPlayerSymbol.charAt(0);
		Game.setPlayerSymbol(reducedPlayerSymbol);
	}
	
	
	private static void reduceAndRecordEnteredComputerSymbol()
	{
		String enteredComputerSymbol = selectSymbolsAndDimensionPanel.enterComputerSymbolField.getText();
		if (enteredComputerSymbol.length() == 0)
		{
			enteredComputerSymbol = "O";
		}
		String reducedComputerSymbol = "" + enteredComputerSymbol.charAt(0);
		Game.setComputerSymbol(reducedComputerSymbol);
	}
		
} // End of class.
