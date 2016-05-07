// +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+

package simsigClients;
import javax.swing.JLabel;
import javax.swing.JTextField;

// +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+

/**
 * <code>ConnectListener</code> provides the basic mouse functions for the
 * connect button. It also interfaces with the controller to attempt to connect
 * to a SimSig server using the details provided.
 *
 * @author Mark David Pokorny
 * @version Dé Céadaoin, 4ú Bealtaine 2016
 * @since Dé Sathairn, 30ú Aibreán 2016
 */
class ConnectListener extends ButtonListener
{

// --------------------------------------- ConnectListener Class ---------------

  private JTextField addressBox, portBox, debugBox;
  private LancingApproachScreens ui;

// --------------------------------------- ConnectListener Class ---------------

  ConnectListener(
    JLabel button, JTextField addressBox,
    JTextField portBox, JTextField debugBox,
    LancingApproachScreens ui)
  {

    super(button);
    this.addressBox = addressBox;
    this.portBox = portBox;
    this.debugBox = debugBox;
    this.ui = ui;

  } // End ‘ConnectListener(JLabel, JTextField…)’ Constructor

// --------------------------------------- ConnectListener Class ---------------

  public void action()
  {

    (new Thread(new Runnable(){public void run(){
      ui.connect(
      addressBox.getText(),
      portBox.getText(),
      debugBox.getText());
    }})).start();

  } // End ‘action()’ Method

// --------------------------------------- ConnectListener Class ---------------

} // End ‘ConnectListener’ Class

// +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
