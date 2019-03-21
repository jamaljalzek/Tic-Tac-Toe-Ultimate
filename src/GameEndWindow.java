import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class GameEndWindow extends JFrame
{
	private String gameEndMessage;
	
	
	public GameEndWindow (String gameEndMessage)
	{
		this.gameEndMessage = gameEndMessage;
		
		this.setTitle("Tic Tac Toe Ultimate");
		this.setSize(200, 200);
		this.setResizable(false);
		this.setLayout( new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS) );
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.createContents();
		this.setVisible(true);
		
	} // End of Constructor.
	
	
	private void createContents ()
	{
		JLabel displayGameEndMessage;
		
		
		// Display the outcome of the game:
		displayGameEndMessage = new JLabel(gameEndMessage);
		displayGameEndMessage.setHorizontalAlignment(SwingConstants.CENTER); // Move the text to the center of the label.
		displayGameEndMessage.setAlignmentX(CENTER_ALIGNMENT); // Move the label to the center of the screen.
		
		// Assemble everything together:
		this.add( new JPanel() );
		this.add(displayGameEndMessage);
		this.add( new JPanel() );
		
	} // End of method createContents.
	
} // End of class GameEndWindow.
