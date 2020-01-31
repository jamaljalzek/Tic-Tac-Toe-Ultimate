package gui.programWindow;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class GameEndWindow extends JFrame
{
	private String gameEndMessage;
	
	
	public GameEndWindow(String gameEndMessage)
	{
		this.gameEndMessage = gameEndMessage;
		setUpWindow();
		addContentsToDisplayOnWindow();
		this.setVisible(true);
	}
	
	
	private void setUpWindow()
	{
		this.setTitle("Tic Tac Toe Ultimate");
		this.setSize(200, 200);
		this.setResizable(false);
		this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
	
	
	private void addContentsToDisplayOnWindow()
	{
		this.add(Box.createVerticalGlue());
		addLabelDisplayingGameEndMessage();
		this.add(Box.createVerticalGlue());
	}
	
	
	private void addLabelDisplayingGameEndMessage()
	{
		JLabel displayGameEndMessage = new JLabel(gameEndMessage);
		displayGameEndMessage.setHorizontalAlignment(SwingConstants.CENTER); // Move the text to the center of the label.
		displayGameEndMessage.setAlignmentX(CENTER_ALIGNMENT); // Move the label to the center of the screen.
		this.add(displayGameEndMessage);
	}
	
} // End of class.
