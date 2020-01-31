package gui.menuScreen.selectGameSettingsPanel;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

import gui.menuScreen.selectGameSettingsPanel.subComponents.SelectComputerOpponentDifficultyPanel;
import gui.menuScreen.selectGameSettingsPanel.subComponents.SelectSymbolsAndDimensionPanel;

public class SelectGameSettingsPanel extends JPanel
{
	public SelectGameSettingsPanel()
	{
		this.setLayout(new BoxLayout (this, BoxLayout.X_AXIS));
		this.add(Box.createHorizontalStrut(30));
		this.add(new SelectSymbolsAndDimensionPanel());
		this.add(Box.createHorizontalStrut(40));
		this.add(new SelectComputerOpponentDifficultyPanel());
		this.add(Box.createHorizontalStrut(30));
	}

} // End of class.
