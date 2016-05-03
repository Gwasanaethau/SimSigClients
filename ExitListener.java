// +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+

package simsigClients;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JFrame;
import javax.swing.JPanel;

// +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+

/**
 * <code>ExitListener</code> provides the basic functions for the exit button.
 *
 * @author Mark David Pokorny
 * @version Dé Máirt, 3ú Bealtaine 2016
 * @since Dé Sathairn, 30ú Aibreán 2016
 */
class ExitListener implements MouseListener
{

// ------------------------------------------ ExitListener Class ---------------

	private JPanel button;
	private LancingUI ui;
	private boolean isCursorInBounds, isClickHeld;

// ------------------------------------------ ExitListener Class ---------------

	ExitListener(JPanel button, LancingUI ui)
	{

		this.button = button;
		this.ui = ui;
		isCursorInBounds = false;
		isClickHeld = false;

	} // End ‘ExitListener(JPanel, LancingUI)’ Constructor

// ------------------------------------------ ExitListener Class ---------------

	public void mouseClicked(MouseEvent e){}

// ------------------------------------------ ExitListener Class ---------------

	public void mousePressed(MouseEvent e)
	{

		isClickHeld = true;

	} // End ‘mousePressed(MouseEvent)’ Method

// ------------------------------------------ ExitListener Class ---------------

	public void mouseReleased(MouseEvent e)
	{

		isClickHeld = false;

		if (!isCursorInBounds)
		{
			button.setOpaque(false);
			button.repaint();
		} // End if
		else
			ui.close();

	} // End ‘mouseReleased(MouseEvent)’ Method

// ------------------------------------------ ExitListener Class ---------------

	public void mouseEntered(MouseEvent e)
	{

		isCursorInBounds = true;
		button.setOpaque(true);
		button.repaint();

	} // End ‘mouseEntered(MouseEvent)’ Method

// ------------------------------------------ ExitListener Class ---------------

	public void mouseExited(MouseEvent e)
	{

		isCursorInBounds = false;

		if (!isClickHeld)
		{
			button.setOpaque(false);
			button.repaint();
		} // End if

	} // End ‘mouseExited(MouseEvent)’ Method

// ------------------------------------------ ExitListener Class ---------------

} // End ‘ExitListener’ Class

// +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
