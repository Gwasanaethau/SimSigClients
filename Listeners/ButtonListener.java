// +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+

package simsigClients;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JLabel;
import javax.swing.border.LineBorder;

// +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+

/**
 * <code>ButtonListener</code> provides the basic UI functions for a
 * generic button.
 *
 * @author Mark David Pokorny
 * @version Dé Céadaoin, 4ú Bealtaine 2016
 * @since Dé Máirt, 3ú Bealtaine 2016
 */
abstract class ButtonListener implements MouseListener
{

// ---------------------------------------- ButtonListener Class ---------------

  private JLabel button;
  private boolean isCursorInBounds;

// ---------------------------------------- ButtonListener Class ---------------

  ButtonListener(JLabel button)
  {

    this.button = button;
    isCursorInBounds = false;

  } // End ‘ButtonListener(JLabel)’ Constructor

// ---------------------------------------- ButtonListener Class ---------------

  public void mouseClicked(MouseEvent e){}

// ---------------------------------------- ButtonListener Class ---------------

  public void mousePressed(MouseEvent e)
  {

    button.setForeground(LancingApproachScreens.CHARCOAL);
    button.setBackground(LancingApproachScreens.HIGHLIGHT);
    button.repaint();

  } // End ‘mousePressed(MouseEvent)’ Method

// ---------------------------------------- ButtonListener Class ---------------

  public void mouseReleased(MouseEvent e)
  {

    button.setForeground(LancingApproachScreens.HIGHLIGHT);
    button.setBackground(LancingApproachScreens.CHARCOAL);
    button.repaint();

    if (isCursorInBounds)
      action();

  } // End ‘mouseReleased(MouseEvent)’ Method

// ---------------------------------------- ButtonListener Class ---------------

  public void mouseEntered(MouseEvent e)
  {

    isCursorInBounds = true;
    button.setBorder(new LineBorder(LancingApproachScreens.HIGHLIGHT_DARK, 2));
    button.repaint();

  } // End ‘mouseEntered(MouseEvent)’ Method

// ---------------------------------------- ButtonListener Class ---------------

  public void mouseExited(MouseEvent e)
  {

    isCursorInBounds = false;
    button.setBorder(new LineBorder(LancingApproachScreens.HIGHLIGHT, 2));
    button.repaint();

  } // End ‘mouseExited(MouseEvent)’ Method

// ---------------------------------------- ButtonListener Class ---------------

  public abstract void action();

// ---------------------------------------- ButtonListener Class ---------------

} // End ‘ButtonListener’ Class

// +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
