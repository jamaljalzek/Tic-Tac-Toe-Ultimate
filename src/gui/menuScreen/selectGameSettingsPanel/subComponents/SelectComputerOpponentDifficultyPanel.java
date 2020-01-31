package gui.menuScreen.selectGameSettingsPanel.subComponents;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import programLogic.Game;

public class SelectComputerOpponentDifficultyPanel extends JPanel
{
	private JRadioButton easyMode, mediumMode, hardMode, veryHardMode;
	
	
	public SelectComputerOpponentDifficultyPanel()
	{
		this.setLayout(new BoxLayout (this, BoxLayout.Y_AXIS));
		addSelectGameDifficultyHeader();
		this.add(Box.createVerticalStrut(10));
		addDifficultyRadioButtonsToThisPanel();
		easyMode.setSelected(true);
	}
	
	
	private void addSelectGameDifficultyHeader()
	{
		this.add(new JLabel("<html>Select the game difficulty:</html>"));
	}
	
	
	private void addDifficultyRadioButtonsToThisPanel()
	{
		addEasyModeRadioButtonToThisPanel();
		addMediumModeRadioButtonToThisPanel();
		addHardModeRadioButtonToThisPanel();
		addVeryHardModeRadioButtonToThisPanel();
	}
	
	
	private void addEasyModeRadioButtonToThisPanel()
	{
		easyMode = new JRadioButton("EASY");
		easyMode.addItemListener(new RadioButtonListener());
		this.add(easyMode);
	}
	
	
	private void addMediumModeRadioButtonToThisPanel()
	{
		mediumMode = new JRadioButton("MEDIUM");
		mediumMode.addItemListener(new RadioButtonListener());
		this.add(mediumMode);
	}
	
	
	private void addHardModeRadioButtonToThisPanel()
	{
		hardMode = new JRadioButton("HARD");
		hardMode.addItemListener(new RadioButtonListener());
		this.add(hardMode);
	}
	
	
	private void addVeryHardModeRadioButtonToThisPanel()
	{
		veryHardMode = new JRadioButton("VERY HARD");
		veryHardMode.addItemListener(new RadioButtonListener());
		this.add(veryHardMode);
	}
	
	
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
			String selectedDifficulty = selectedRadioButton.getText();
			Game.setDifficulty(selectedDifficulty);
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
