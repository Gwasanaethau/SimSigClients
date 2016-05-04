// +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+

package simsigClients;
import strampáil.Constants;
import strampáil.Printer;
import simsigGatewayInterface.Harness;
import simsigGatewayInterface.MessageType;
import simsigGatewayInterface.SimSigClient;
import simsigGatewayInterface.SimSigMessage;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

// +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+

/**
 * An airport-style series of information screens showing arrival/departure of
 * trains at different stations on the
 * <a href=http://www.simsig.co.uk/dokuwiki/doku.php?id=simulations:lancing>
 * Lancing</a> simulation.
 *
 * @author Mark David Pokorny
 * @version Dé Céadaoin, 4ú Bealtaine 2016
 * @since Dé hAoine, 29ú Aibreán 2016
 */
public class LancingApproachScreens implements Harness
{

// -------------------------------- LancingApproachScreens Class ---------------

  private static final int WINDOW_WIDTH = 1024;
  private static final int WINDOW_HEIGHT = 768;

  static final Color CHARCOAL = new Color(0x333333);
  static final Color MOTION_BAR = new Color(0x8CC63E);
  static final Color EXIT_BUTTON = new Color(0xEE1C23);
  static final Color HIGHLIGHT = new Color(0x3C78C0);
  static final Color HIGHLIGHT_DARK = new Color(0x244873);
  static final Color GOLD = new Color(0xC3872D);

  private static final int BUTTON_LEVEL = 34;
  private static final int TITLE_LEVEL = 80;
  private static final int LEVEL_1 = 122;
  private static final int LEVEL_2 = 160;
  private static final int LEVEL_3 = 196;
  private static final int LEVEL_4 = 232;
  private static final int LEVEL_5 = 278;
  private static final int LEVEL_6 = 316;
  private static final int LEVEL_7 = 352;
  private static final int LEVEL_8 = 388;

  private SimSigClient client;

  private JFrame mainWindow;
  private Picture logo;
  private JPanel motionBar;
  private JPanel quit;
  private JLabel failureMessage;

  private JLabel title, platform1, approach1, arrived1, departed1,
    platform2, approach2, arrived2, departed2,
    ewr1app, ewr1arr, ewr1dep, ewr2app;
  private ArrayList<JLabel> buttons;

// -------------------------------- LancingApproachScreens Class ---------------

  private LancingApproachScreens(){

    buttons = new ArrayList<JLabel>();

    // Set up the window:
    mainWindow = new JFrame("Lancing Approach Information");
    mainWindow.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
    mainWindow.setLocationRelativeTo(null);
    mainWindow.setLayout(null);
    mainWindow.setUndecorated(true);
    mainWindow.getRootPane().setDoubleBuffered(true);
    mainWindow.setBackground(CHARCOAL);
    mainWindow.getContentPane().setBackground(CHARCOAL);

    // Set up the programme icon:
    mainWindow.setIconImage(new ImageIcon(
      "/home/mark/Ríomhchlár/Java/Sources/simsigClients/lancing.png").getImage());

    // Add the logo:
    logo = new Picture(
      "/home/mark/Ríomhchlár/Java/Sources/simsigClients/lancing.png");
    logo.setLocation(0, 0);
    logo.setToolTipText("Lancing Approach Information");

    // Add the motion bar:
    motionBar = new JPanel();
    motionBar.setSize(WINDOW_WIDTH - 48, 24);
    motionBar.setLocation(24, 0);
    motionBar.setBackground(MOTION_BAR);
    motionBar.setOpaque(false);
    motionBar.setToolTipText("Move");
    MotionBarListener motionBarListener = new MotionBarListener(
      motionBar, mainWindow);
    motionBar.addMouseListener(motionBarListener);
    motionBar.addMouseMotionListener(motionBarListener);

    // Add the quit button:
    quit = new JPanel();
    quit.setSize(24, 24);
    quit.setLocation(WINDOW_WIDTH - 24, 0);
    quit.setBackground(EXIT_BUTTON);
    quit.setOpaque(false);
    quit.setToolTipText("Quit");
    ExitListener quitListener = new ExitListener(quit, this);
    quit.addMouseListener(quitListener);

    // Add the approach screen buttons:
    JLabel eastWorthing = new JLabel("East Worthing", JLabel.CENTER);
    eastWorthing.setFont(new Font("Garamond", Font.PLAIN, 22));
    eastWorthing.setSize(150, 26);
    eastWorthing.setLocation(10, BUTTON_LEVEL);
    eastWorthing.setForeground(HIGHLIGHT);
    eastWorthing.setBackground(CHARCOAL);
    eastWorthing.setBorder(new LineBorder(HIGHLIGHT, 2));
    eastWorthing.setOpaque(true);
    eastWorthing.setToolTipText("View East Worthing");
    eastWorthing.addMouseListener(new EWRListener(eastWorthing, this));
    buttons.add(eastWorthing);

    // Add approach screen title:
    title = new JLabel("", JLabel.CENTER);
    title.setFont(new Font("Garamond", Font.PLAIN, 28));
    title.setSize(WINDOW_WIDTH, 32);
    title.setLocation(0, TITLE_LEVEL);
    title.setForeground(GOLD);
    title.setBorder(new LineBorder(GOLD, 2));

    // Add platform 1 label:
    platform1 = new JLabel("Platform 1", JLabel.CENTER);
    configurePlatformLabel(platform1);
    platform1.setLocation(0, LEVEL_1);

    // Add approach label:
    approach1 = new JLabel("On Approach:", JLabel.RIGHT);
    configureBerthLabel(approach1);
    approach1.setLocation(0, LEVEL_2);

    // Add arrived label:
    arrived1 = new JLabel("Arrived:", JLabel.RIGHT);
    configureBerthLabel(arrived1);
    arrived1.setLocation(0, LEVEL_3);

    // Add departed label:
    departed1 = new JLabel("Departed:", JLabel.RIGHT);
    configureBerthLabel(departed1);
    departed1.setLocation(0, LEVEL_4);

    // Add platform 2 label:
    platform2 = new JLabel("Platform 2", JLabel.CENTER);
    configurePlatformLabel(platform2);
    platform2.setLocation(0, LEVEL_5);

    // Add approach label:
    approach2 = new JLabel("On Approach:", JLabel.RIGHT);
    configureBerthLabel(approach2);
    approach2.setLocation(0, LEVEL_6);

    // Add arrived label:
    arrived2 = new JLabel("Arrived:", JLabel.RIGHT);
    configureBerthLabel(arrived2);
    arrived2.setLocation(0, LEVEL_7);

    // Add departed label:
    departed2 = new JLabel("Departed:", JLabel.RIGHT);
    configureBerthLabel(departed2);
    departed2.setLocation(0, LEVEL_8);

    // East Worthing info boxes:
    ewr1app = new JLabel("", JLabel.LEFT);
    configureBerthLabel(ewr1app);
    ewr1app.setLocation(WINDOW_WIDTH / 2, LEVEL_2);

    ewr1arr = new JLabel("", JLabel.LEFT);
    configureBerthLabel(ewr1arr);
    ewr1arr.setLocation(WINDOW_WIDTH / 2, LEVEL_3);

    ewr1dep = new JLabel("", JLabel.LEFT);
    configureBerthLabel(ewr1dep);
    ewr1dep.setLocation(WINDOW_WIDTH / 2, LEVEL_4);

    ewr2app = new JLabel("", JLabel.LEFT);
    configureBerthLabel(ewr2app);
    ewr2app.setLocation(WINDOW_WIDTH / 2, LEVEL_6);

  } // End ‘LancingApproachScreens()’ Constructor

// -------------------------------- LancingApproachScreens Class ---------------

  public static void main(String[] args)
  {

    (new LancingApproachScreens()).start();

  } // End ‘main(String[])’ Method

// -------------------------------- LancingApproachScreens Class ---------------

  private void start()
  {
    EventQueue.invokeLater(new Runnable(){public void run(){
      entryPortal();
    }});
  } // End ‘start()’ Method

// -------------------------------- LancingApproachScreens Class ---------------

// ============================================= Harness Methods ===============

// -------------------------------- LancingApproachScreens Class ---------------

  public void transferMessage(SimSigMessage message)
  {

    if (
      message.getSimulation().equals("lancing") &&
      message.getType() == MessageType.TD)
    {

      HashMap<String, String> parameters = message.getParameters();

      if (parameters.get("to") != null)
      {
        if (parameters.get("to").equals("0020"))
          ewr1app.setText(parameters.get("descr"));
        if (parameters.get("to").equals("0018"))
          ewr1arr.setText(parameters.get("descr"));
        if (parameters.get("to").equals("0016"))
          ewr1dep.setText(parameters.get("descr"));
        if (parameters.get("to").equals("0021"))
          ewr2app.setText(parameters.get("descr"));
      } // End if

      if (parameters.get("from") != null)
      {
        if (parameters.get("from").equals("0020"))
          ewr1app.setText("");
        if (parameters.get("from").equals("0018"))
          ewr1arr.setText("");
        if (parameters.get("from").equals("0016"))
          ewr1dep.setText("");
        if (parameters.get("from").equals("0021"))
          ewr2app.setText("");
      } // End if

    } // End if

    EventQueue.invokeLater(new Runnable(){public void run(){
      mainWindow.repaint();
    }});

  } // End ‘transferMessage(SimSigMessage)’ Method

// -------------------------------- LancingApproachScreens Class ---------------

  public void connected(boolean success)
  {

    if (success)
      EventQueue.invokeLater(new Runnable(){public void run(){
        refreshUI();
      }});
    else
      EventQueue.invokeLater(new Runnable(){public void run(){
        failureMessage.setVisible(true);
        mainWindow.repaint();
      }});

  } // End ‘connection(boolean)’ Method

// -------------------------------- LancingApproachScreens Class ---------------

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

// -------------------------------- LancingApproachScreens Class ---------------

// ================================================== UI Methods ===============

// -------------------------------- LancingApproachScreens Class ---------------

  private void entryPortal()
  {

    // Add the top bar:
    mainWindow.add(logo);
    mainWindow.add(motionBar);
    mainWindow.add(quit);

    // Add the address boxes:
    JLabel addressLabel = new JLabel(
      "Address/host name of SimSig server:", JLabel.RIGHT);
    addressLabel.setFont(new Font("Garamond", Font.PLAIN, 22));
    addressLabel.setSize(WINDOW_WIDTH / 2 - 10, 26);
    addressLabel.setLocation(
      0,
      WINDOW_HEIGHT / 2 - addressLabel.getHeight() * 3);
    addressLabel.setForeground(HIGHLIGHT);
    mainWindow.add(addressLabel);

    JTextField addressBox = new JTextField("127.0.0.1");
    addressBox.setFont(new Font("Garamond", Font.PLAIN, 20));
    addressBox.setSize(WINDOW_WIDTH / 4, 26);
    addressBox.setLocation(
      WINDOW_WIDTH / 2 + 10,
      WINDOW_HEIGHT / 2 - addressBox.getHeight() * 3);
    addressBox.setCaretColor(GOLD);
    addressBox.setForeground(GOLD);
    addressBox.setBackground(CHARCOAL);
    addressBox.setSelectionColor(GOLD);
    addressBox.setSelectedTextColor(CHARCOAL);
    addressBox.setBorder(new LineBorder(GOLD, 2));
    mainWindow.add(addressBox);

    // Add the port boxes:
    JLabel portLabel = new JLabel(
      "Port number of SimSig server:", JLabel.RIGHT);
    portLabel.setFont(new Font("Garamond", Font.PLAIN, 22));
    portLabel.setSize(WINDOW_WIDTH / 2 - 10, 26);
    portLabel.setLocation(
      0,
      WINDOW_HEIGHT / 2 - portLabel.getHeight() / 2);
    portLabel.setForeground(HIGHLIGHT);
    mainWindow.add(portLabel);

    JTextField portBox = new JTextField("51515");
    portBox.setFont(new Font("Garamond", Font.PLAIN, 20));
    portBox.setSize(WINDOW_WIDTH / 4, 26);
    portBox.setLocation(
      WINDOW_WIDTH / 2 + 10,
      WINDOW_HEIGHT / 2 - portBox.getHeight() / 2);
    portBox.setCaretColor(GOLD);
    portBox.setForeground(GOLD);
    portBox.setBackground(CHARCOAL);
    portBox.setSelectionColor(GOLD);
    portBox.setSelectedTextColor(CHARCOAL);
    portBox.setBorder(new LineBorder(GOLD, 2));
    mainWindow.add(portBox);

    // Add the debug boxes:
    JLabel debugLabel = new JLabel(
      "Debug level [Debug/Info/Warning/Error/None]:", JLabel.RIGHT);
    debugLabel.setFont(new Font("Garamond", Font.PLAIN, 22));
    debugLabel.setSize(WINDOW_WIDTH / 2 - 10, 26);
    debugLabel.setLocation(
      0,
      WINDOW_HEIGHT / 2 + debugLabel.getHeight() * 2);
    debugLabel.setForeground(HIGHLIGHT);
    mainWindow.add(debugLabel);

    JTextField debugBox = new JTextField("Warning");
    debugBox.setFont(new Font("Garamond", Font.PLAIN, 20));
    debugBox.setSize(WINDOW_WIDTH / 4, 26);
    debugBox.setLocation(
      WINDOW_WIDTH / 2 + 10,
      WINDOW_HEIGHT / 2 + debugBox.getHeight() * 2);
    debugBox.setCaretColor(GOLD);
    debugBox.setForeground(GOLD);
    debugBox.setBackground(CHARCOAL);
    debugBox.setSelectionColor(GOLD);
    debugBox.setSelectedTextColor(CHARCOAL);
    debugBox.setBorder(new LineBorder(GOLD, 2));
    mainWindow.add(debugBox);

    // Add the connect button:
    JLabel connect = new JLabel("Connect", JLabel.CENTER);
    connect.setFont(new Font("Garamond", Font.PLAIN, 28));
    connect.setSize(120, 32);
    connect.setLocation(WINDOW_WIDTH / 2 - connect.getWidth() / 2,
      WINDOW_HEIGHT - connect.getHeight() - 32);
    connect.setForeground(HIGHLIGHT);
    connect.setBackground(CHARCOAL);
    connect.setBorder(new LineBorder(HIGHLIGHT, 2));
    connect.setOpaque(true);
    connect.setToolTipText("Connect to SimSig server");
    connect.addMouseListener(new ConnectListener(
      connect, addressBox, portBox, debugBox, this));
    mainWindow.add(connect);

    // Add the connection failure message:
    failureMessage = new JLabel(
      "Cannot to connect to server. Please check server details and try again.",
      JLabel.CENTER);
    failureMessage.setFont(new Font("Garamond", Font.PLAIN, 26));
    failureMessage.setSize(WINDOW_WIDTH, 30);
    failureMessage.setLocation(
      0,
      WINDOW_HEIGHT - connect.getHeight() - failureMessage.getHeight() - 46);
    failureMessage.setForeground(EXIT_BUTTON);
    failureMessage.setVisible(false);
    mainWindow.add(failureMessage);

    // Show the window:
    mainWindow.setVisible(true);

  } // End ‘entryPortal()’ Method

// -------------------------------- LancingApproachScreens Class ---------------

  void eastWorthing()
  {

    refreshUI();

    // Add labels
    title.setText("East Worthing");
    mainWindow.add(title);
    mainWindow.add(platform1);
    mainWindow.add(approach1);
    mainWindow.add(arrived1);
    mainWindow.add(departed1);
    mainWindow.add(platform2);
    mainWindow.add(approach2);

    // Add info boxes:
    mainWindow.add(ewr1app);
    mainWindow.add(ewr1arr);
    mainWindow.add(ewr1dep);
    mainWindow.add(ewr2app);

    // Refresh the portal:
    mainWindow.repaint();

  } // End ‘eastWorthing()’ Method

// -------------------------------- LancingApproachScreens Class ---------------

  private void refreshUI()
  {

    // Remove all components:
    mainWindow.getContentPane().removeAll();

    // Re-add top bar:
    mainWindow.add(logo);
    mainWindow.add(motionBar);
    mainWindow.add(quit);

    // (Re-)add the buttons:
    for (int i = 0; i < buttons.size(); i++)
      mainWindow.add(buttons.get(i));

    // Refresh the portal:
    mainWindow.repaint();

  } // End ‘refreshUI()’ Method

// -------------------------------- LancingApproachScreens Class ---------------

  private static void configureBerthLabel(JLabel label)
  {
    label.setFont(new Font("Garamond", Font.PLAIN, 22));
    label.setSize(WINDOW_WIDTH / 2, 26);
    label.setForeground(GOLD);
  } // End ‘configureBerthLabel(JLabel)’ Method

// -------------------------------- LancingApproachScreens Class ---------------


  private static void configurePlatformLabel(JLabel label)
  {
    label.setFont(new Font("Garamond", Font.PLAIN, 24));
    label.setSize(WINDOW_WIDTH, 28);
    label.setForeground(GOLD);
  } // End ‘configurePlatformLabel(JLabel)’ Method

// -------------------------------- LancingApproachScreens Class ---------------

  void close()
  {
    mainWindow.dispose();
    if (client != null)
      client.close();
  } // End ‘close()’ Method

// -------------------------------- LancingApproachScreens Class ---------------

} // End ‘LancingApproachScreens’ Class

// +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
