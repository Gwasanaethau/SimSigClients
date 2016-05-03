// +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+

package simsigClients;
import strampáil.Constants;
import strampáil.Printer;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

// +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+

/**
 * <code>ConnectListener</code> provides the basic mouse functions for the
 * connect button. It also interfaces with the controller to attempt to connect
 * to a SimSig server using the details provided.
 *
 * @author Mark David Pokorny
 * @version Dé Máirt, 3ú Bealtaine 2016
 * @since Dé Sathairn, 30ú Aibreán 2016
 */
class ConnectListener implements MouseListener
{

// --------------------------------------- ConnectListener Class ---------------

  private JLabel button;
  private JTextField addressBox, portBox, debugBox;
  private LancingControl control;
  private boolean isCursorInBounds, connected;

// --------------------------------------- ConnectListener Class ---------------

  ConnectListener(
    JLabel button, JTextField addressBox,
    JTextField portBox, JTextField debugBox, LancingControl control)
  {

    this.button = button;
    this.addressBox = addressBox;
    this.portBox = portBox;
    this.debugBox = debugBox;
    this.control = control;
    isCursorInBounds = false;

  } // End ‘ConnectListener(JLabel, JTextField…)’ Constructor

// --------------------------------------- ConnectListener Class ---------------

  public void mouseClicked(MouseEvent e){}

// --------------------------------------- ConnectListener Class ---------------

  public void mousePressed(MouseEvent e)
  {

    button.setForeground(LancingUI.CHARCOAL);
    button.setBackground(LancingUI.HIGHLIGHT);
    button.repaint();

  } // End ‘mousePressed(MouseEvent)’ Method

// --------------------------------------- ConnectListener Class ---------------

  public void mouseReleased(MouseEvent e)
  {

    button.setForeground(LancingUI.HIGHLIGHT);
    button.setBackground(LancingUI.CHARCOAL);
    button.repaint();

    if (isCursorInBounds)
      (new Thread(new Runnable(){public void run(){
        control.connect(
        addressBox.getText(),
        portBox.getText(),
        debugBox.getText());
      }})).start();

  } // End ‘mouseReleased(MouseEvent)’ Method

// --------------------------------------- ConnectListener Class ---------------

  public void mouseEntered(MouseEvent e)
  {

    isCursorInBounds = true;
    button.setBorder(new LineBorder(LancingUI.HIGHLIGHT_DARK, 2));
    button.repaint();

  } // End ‘mouseEntered(MouseEvent)’ Method

// --------------------------------------- ConnectListener Class ---------------

  public void mouseExited(MouseEvent e)
  {

    isCursorInBounds = false;
    button.setBorder(new LineBorder(LancingUI.HIGHLIGHT, 2));
    button.repaint();

  } // End ‘mouseExited(MouseEvent)’ Method

// --------------------------------------- ConnectListener Class ---------------

} // End ‘ConnectListener’ Class

// +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
