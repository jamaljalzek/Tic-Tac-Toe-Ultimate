package gui.menuScreen.selectGameSettingsPanel.subComponents;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;

import programLogic.GameBoard;

public class SelectGameBoardDimensionDropDownList extends JComboBox <Integer>
{
	private int selectedDimension;
	
	
	public SelectGameBoardDimensionDropDownList()
	{
		addListOfDimensionsToThisMenu();
		this.addActionListener(new SelectionListener());
		this.setSelectedIndex(0); // The default game board dimension is 3 x 3.
	}
	
	
	private void addListOfDimensionsToThisMenu()
	{
		for (int currentDimension = 3; currentDimension <= 10; ++currentDimension)
		{
			this.addItem(currentDimension);
		}
	}
	
	private class SelectionListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent event)
		{
			selectedDimension = (Integer) SelectGameBoardDimensionDropDownList.this.getSelectedItem();
			GameBoard.setDimension(selectedDimension);
		}
		
	} // End of nested class.
	
} // End of class.
