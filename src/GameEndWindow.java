import javax.swing.BoxLayout;
import javax.swing.JFrame;

public class GameEndWindow extends JFrame
{
	public GameEndWindow ()
	{
		this.setTitle("Tic Tac Toe Ultimate");
		this.setSize(200, 400);
		this.setResizable(false);
		this.setLayout( new BoxLayout(this.getContentPane(), BoxLayout.X_AXIS) );
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		this.setVisible(true);
		
	} // End of Constructor.
	
} // End of class GameEndWindow.
