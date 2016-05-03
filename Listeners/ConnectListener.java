// +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+

package simsigClients;
import strampáil.Constants;
import strampáil.Printer;
import java.awt.event.MouseEvent;
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
class ConnectListener extends ButtonListener
{

// --------------------------------------- ConnectListener Class ---------------

  private JTextField addressBox, portBox, debugBox;
  private LancingControl control;
  private boolean connected;

// --------------------------------------- ConnectListener Class ---------------

  ConnectListener(
    JLabel button, JTextField addressBox,
    JTextField portBox, JTextField debugBox, LancingControl control)
  {

    super(button);
    this.addressBox = addressBox;
    this.portBox = portBox;
    this.debugBox = debugBox;
    this.control = control;

  } // End ‘ConnectListener(JLabel, JTextField…)’ Constructor

// --------------------------------------- ConnectListener Class ---------------

  public void action()
  {

    (new Thread(new Runnable(){public void run(){
      control.connect(
      addressBox.getText(),
      portBox.getText(),
      debugBox.getText());
    }})).start();

  } // End ‘action()’ Method

// --------------------------------------- ConnectListener Class ---------------

} // End ‘ConnectListener’ Class

// +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
