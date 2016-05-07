// +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+

package simsigClients;
import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.JFrame;
import javax.swing.JPanel;

// +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+

/**
 * <code>MotionBarListener</code> provides a means for the window to be moved.
 *
 * @author Mark David Pokorny
 * @version Dé Sathairn, 30ú Aibreán 2016
 * @since Dé hAoine, 29ú Aibreán 2016
 */
class MotionBarListener implements MouseListener, MouseMotionListener
{

// ------------------------------------- MotionBarListener Class ---------------

	private JPanel motionBar;
	private JFrame parent;
	private int x, y;
	private boolean isClickHeld, isCursorInBounds;

// ------------------------------------- MotionBarListener Class ---------------

	MotionBarListener(JPanel motionBar, JFrame parent)
	{

		this.motionBar = motionBar;
		this.parent = parent;
		isClickHeld = false;
		isCursorInBounds = false;

	} // End ‘MotionBarListener(JPanel, JFrame)’ Constructor

// ------------------------------------- MotionBarListener Class ---------------

	public void mouseClicked(MouseEvent e){}

// ------------------------------------- MotionBarListener Class ---------------

	public void mousePressed(MouseEvent e)
	{

		x = e.getX();
		y = e.getY();
		isClickHeld = true;

	} // End ‘mousePressed(MouseEvent)’ Method

// ------------------------------------- MotionBarListener Class ---------------

	public void mouseReleased(MouseEvent e)
	{

		isClickHeld = false;

		if (!isCursorInBounds)
		{
			motionBar.setOpaque(false);
			motionBar.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			motionBar.repaint();
		} // End if

	} // End ‘mouseReleased(MouseEvent)’ Method

// ------------------------------------- MotionBarListener Class ---------------

	public void mouseMoved(MouseEvent e){}

// ------------------------------------- MotionBarListener Class ---------------

	public void mouseDragged(MouseEvent e)
	{

		parent.setLocation(e.getXOnScreen() - x, e.getYOnScreen() - y);

	} // End ‘mouseDragged(MouseEvent)’ Method

// ------------------------------------- MotionBarListener Class ---------------

	public void mouseEntered(MouseEvent e)
	{

		isCursorInBounds = true;
		motionBar.setOpaque(true);
		motionBar.setCursor(Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR));
		motionBar.repaint();

	} // End ‘mouseEntered(MouseEvent)’ Method

// ------------------------------------- MotionBarListener Class ---------------

	public void mouseExited(MouseEvent e)
	{

		isCursorInBounds = false;

		if (!isClickHeld)
		{
			motionBar.setOpaque(false);
			motionBar.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			motionBar.repaint();
		} // End if

	} // End ‘mouseExited(MouseEvent)’ Method

// ------------------------------------- MotionBarListener Class ---------------

} // End ‘MotionBarListener’ Class

// +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
