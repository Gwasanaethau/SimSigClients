// +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+

package simsigClients;
import strampáil.Constants;
import strampáil.Printer;
import simsigGatewayInterface.Harness;
import simsigGatewayInterface.SimSigClient;
import simsigGatewayInterface.SimSigMessage;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

// +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+

/**
 * Programme flow controller for {@link LancingApproachScreens}.
 *
 * @author Mark David Pokorny
 * @version Dé Máirt, 3ú Bealtaine 2016
 * @since Dé hAoine, 29ú Aibreán 2016
 */
class LancingControl implements Harness
{

// ---------------------------------------- LancingControl Class ---------------

  private SimSigClient client;
  private LancingUI ui;

// ---------------------------------------- LancingControl Class ---------------

  LancingControl()
  {
    ui = new LancingUI(this);
  } // End ‘LancingControl()’ Constructor

// ---------------------------------------- LancingControl Class ---------------

  void start()
  {
    EventQueue.invokeLater(new Runnable(){public void run(){
      ui.entryPortal();
    }});
  } // End ‘start()’ Method

// ---------------------------------------- LancingControl Class ---------------

  public void transferMessage(SimSigMessage message){}

// ---------------------------------------- LancingControl Class ---------------

  public void connected(final boolean success)
  {

    Printer.printInfo("1:" + success);
    if (success)
    {
      EventQueue.invokeLater(new Runnable(){public void run(){
        Printer.printInfo("2:" + success);
        ui.mainPortal();
      }});
    } // End if
    else
    {
      EventQueue.invokeLater(new Runnable(){public void run(){
        Printer.printInfo("3:" + success);
        ui.connectionFailure();
      }});
    } // End else

  } // End ‘connection(boolean)’ Method

// --------------------------------------- ConnectListener Class ---------------

  void connect(String hostName, String portString, String debugString)
  {

    if (hostName.equals(""))
      hostName = "127.0.0.1";

    int port;
    if (portString.equals(""))
      port = 51515;
    else
    {
      try
      {
        port = Integer.parseInt(portString);
      } // End try
      catch (NumberFormatException nfe)
      {
        Printer.printError(portString + " is not a valid port number."
          + " Defaulting to port 51515.");
          port = 51515;
      } // End ‘NumberFormatException’ catch
    } // End else

    int debug;
    if (debugString.equals("") || debugString.equals("Warning"))
      debug = Constants.WARNING;
    else if (debugString.equals("Error"))
      debug = Constants.ERROR;
    else if (debugString.equals("Info"))
      debug = Constants.INFO;
    else if (debugString.equals("Debug"))
      debug = Constants.DEBUG;
    else if (debugString.equals("None"))
      debug = Constants.NONE;
    else
    {
      Printer.printError(debugString + " is not a valid number message level."
        + " Defaulting to warning message level.");
      debug = Constants.WARNING;
    } // End else

    client = new SimSigClient(hostName, port, debug, "simgsig", this);

  } // End ‘connect()’ Method

// ---------------------------------------- LancingControl Class ---------------

  void close()
  {
    if (client != null)
      client.close();
  } // End ‘close()’ Method

// ---------------------------------------- LancingControl Class ---------------

} // End ‘LancingControl’ Class

// +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
