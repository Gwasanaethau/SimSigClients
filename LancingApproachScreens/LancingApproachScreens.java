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
 * @version Dé Sathairn, 7ú Bealtaine 2016
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
    ewr1app, ewr1arr, ewr1dep, ewr2app, ewr2arr, ewr2dep,
    lac1app, lac1arr, lac1dep, lac2app, lac2arr, lac2dep,
    sse1app, sse1arr, sse1dep, sse2app, sse2arr, sse2dep,
    swk1app, swk1arr, swk1dep, swk2app, swk2arr, swk2dep,
    fsg1app, fsg1arr, fsg1dep, fsg2app, fsg2arr, fsg2dep;
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
      "/home/mark/Ríomhchlár/Java/Sources/simsigClients/" +
      "LancingApproachScreens/lancing.png").getImage());

    // Add the logo:
    logo = new Picture(
      "/home/mark/Ríomhchlár/Java/Sources/simsigClients/" +
      "LancingApproachScreens/lancing.png");
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
    configureButton(eastWorthing);
    eastWorthing.setLocation(10, BUTTON_LEVEL);
    eastWorthing.setToolTipText("View East Worthing");
    eastWorthing.addMouseListener(new EWRListener(eastWorthing, this));
    buttons.add(eastWorthing);

    JLabel lancing = new JLabel("Lancing", JLabel.CENTER);
    configureButton(lancing);
    lancing.setLocation(170, BUTTON_LEVEL);
    lancing.setToolTipText("View Lancing");
    lancing.addMouseListener(new LACListener(lancing, this));
    buttons.add(lancing);

    JLabel shoreham = new JLabel("Shoreham-by-Sea", JLabel.CENTER);
    configureButton(shoreham);
    shoreham.setLocation(330, BUTTON_LEVEL);
    shoreham.setToolTipText("View Shoreham-by-Sea");
    shoreham.addMouseListener(new SSEListener(shoreham, this));
    buttons.add(shoreham);

    JLabel southwick = new JLabel("Southwick", JLabel.CENTER);
    configureButton(southwick);
    southwick.setLocation(330, BUTTON_LEVEL);
    southwick.setToolTipText("View Southwick");
    southwick.addMouseListener(new SWKListener(southwick, this));
    buttons.add(southwick);

    JLabel fishersgate = new JLabel("Fishersgate", JLabel.CENTER);
    configureButton(fishersgate);
    fishersgate.setLocation(330, BUTTON_LEVEL);
    fishersgate.setToolTipText("View Fishersgate");
    fishersgate.addMouseListener(new FSGListener(fishersgate, this));
    buttons.add(fishersgate);

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

    ewr2arr = new JLabel("", JLabel.LEFT);
    configureBerthLabel(ewr2arr);
    ewr2arr.setLocation(WINDOW_WIDTH / 2, LEVEL_7);

    ewr2dep = new JLabel("", JLabel.LEFT);
    configureBerthLabel(ewr2dep);
    ewr2dep.setLocation(WINDOW_WIDTH / 2, LEVEL_8);

    // Lancing info boxes:
    lac1app = new JLabel("", JLabel.LEFT);
    configureBerthLabel(lac1app);
    lac1app.setLocation(WINDOW_WIDTH / 2, LEVEL_2);

    lac1arr = new JLabel("", JLabel.LEFT);
    configureBerthLabel(lac1arr);
    lac1arr.setLocation(WINDOW_WIDTH / 2, LEVEL_3);

    lac1dep = new JLabel("", JLabel.LEFT);
    configureBerthLabel(lac1dep);
    lac1dep.setLocation(WINDOW_WIDTH / 2, LEVEL_4);

    lac2app = new JLabel("", JLabel.LEFT);
    configureBerthLabel(lac2app);
    lac2app.setLocation(WINDOW_WIDTH / 2, LEVEL_6);

    lac2arr = new JLabel("", JLabel.LEFT);
    configureBerthLabel(lac2arr);
    lac2arr.setLocation(WINDOW_WIDTH / 2, LEVEL_7);

    lac2dep = new JLabel("", JLabel.LEFT);
    configureBerthLabel(lac2dep);
    lac2dep.setLocation(WINDOW_WIDTH / 2, LEVEL_8);

    // Shoreham-by-Sea info boxes:
    sse1app = new JLabel("", JLabel.LEFT);
    configureBerthLabel(sse1app);
    sse1app.setLocation(WINDOW_WIDTH / 2, LEVEL_2);

    sse1arr = new JLabel("", JLabel.LEFT);
    configureBerthLabel(sse1arr);
    sse1arr.setLocation(WINDOW_WIDTH / 2, LEVEL_3);

    sse1dep = new JLabel("", JLabel.LEFT);
    configureBerthLabel(sse1dep);
    sse1dep.setLocation(WINDOW_WIDTH / 2, LEVEL_4);

    sse2app = new JLabel("", JLabel.LEFT);
    configureBerthLabel(sse2app);
    sse2app.setLocation(WINDOW_WIDTH / 2, LEVEL_6);

    sse2arr = new JLabel("", JLabel.LEFT);
    configureBerthLabel(sse2arr);
    sse2arr.setLocation(WINDOW_WIDTH / 2, LEVEL_7);

    sse2dep = new JLabel("", JLabel.LEFT);
    configureBerthLabel(sse2dep);
    sse2dep.setLocation(WINDOW_WIDTH / 2, LEVEL_8);

    // Southwick info boxes:
    swk1app = new JLabel("", JLabel.LEFT);
    configureBerthLabel(swk1app);
    swk1app.setLocation(WINDOW_WIDTH / 2, LEVEL_2);

    swk1arr = new JLabel("", JLabel.LEFT);
    configureBerthLabel(swk1arr);
    swk1arr.setLocation(WINDOW_WIDTH / 2, LEVEL_3);

    swk1dep = new JLabel("", JLabel.LEFT);
    configureBerthLabel(swk1dep);
    swk1dep.setLocation(WINDOW_WIDTH / 2, LEVEL_4);

    swk2app = new JLabel("", JLabel.LEFT);
    configureBerthLabel(swk2app);
    swk2app.setLocation(WINDOW_WIDTH / 2, LEVEL_6);

    swk2arr = new JLabel("", JLabel.LEFT);
    configureBerthLabel(swk2arr);
    swk2arr.setLocation(WINDOW_WIDTH / 2, LEVEL_7);

    swk2dep = new JLabel("", JLabel.LEFT);
    configureBerthLabel(swk2dep);
    swk2dep.setLocation(WINDOW_WIDTH / 2, LEVEL_8);

    // Fishersgate info boxes:
    fsg1app = new JLabel("", JLabel.LEFT);
    configureBerthLabel(fsg1app);
    fsg1app.setLocation(WINDOW_WIDTH / 2, LEVEL_2);

    fsg1arr = new JLabel("", JLabel.LEFT);
    configureBerthLabel(fsg1arr);
    fsg1arr.setLocation(WINDOW_WIDTH / 2, LEVEL_3);

    fsg1dep = new JLabel("", JLabel.LEFT);
    configureBerthLabel(fsg1dep);
    fsg1dep.setLocation(WINDOW_WIDTH / 2, LEVEL_4);

    fsg2app = new JLabel("", JLabel.LEFT);
    configureBerthLabel(fsg2app);
    fsg2app.setLocation(WINDOW_WIDTH / 2, LEVEL_6);

    fsg2arr = new JLabel("", JLabel.LEFT);
    configureBerthLabel(fsg2arr);
    fsg2arr.setLocation(WINDOW_WIDTH / 2, LEVEL_7);

    fsg2dep = new JLabel("", JLabel.LEFT);
    configureBerthLabel(fsg2dep);
    fsg2dep.setLocation(WINDOW_WIDTH / 2, LEVEL_8);

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
      String td = parameters.get("descr");

      if (parameters.get("to") != null)
      {

        // Up Brighton:
        if (parameters.get("to").equals("0020"))
          ewr1app.setText(td);
        if (parameters.get("to").equals("0018"))
        {
          ewr1arr.setText(td);
          lac1app.setText(td);
        }
        if (parameters.get("to").equals("0016"))
        {
          ewr1dep.setText(td);
          lac1arr.setText(td);
        }
        if (parameters.get("to").equals("0014"))
          lac1dep.setText(td);
        if (parameters.get("to").equals("0012"))
          sse1app.setText(td);
        if (parameters.get("to").equals("0010"))
          sse1arr.setText(td);
        if (parameters.get("to").equals("0008"))
        {
          sse1dep.setText(td);
          swk1app.setText(td);
        }
        if (parameters.get("to").equals("0006"))
        {
          swk1arr.setText(td);
          fsg1app.setText(td);
        }
        if (parameters.get("to").equals("0004"))
        {
          swk1dep.setText(td);
          fsg1arr.setText(td);
        }
        if (parameters.get("to").equals("0002"))
          fsg1dep.setText(td);

        // Down Brighton:
        if (parameters.get("to").equals("0007"))
          fsg2app.setText(td);
        if (parameters.get("to").equals("0009"))
        {
          swk2app.setText(td);
          fsg2arr.setText(td);
        }
        if (parameters.get("to").equals("0011"))
        {
          sse2app.setText(td);
          swk2arr.setText(td);
          fsg2dep.setText(td);
        }
        if (parameters.get("to").equals("0013"))
        {
          sse2arr.setText(td);
          swk2dep.setText(td);
        }
        if (parameters.get("to").equals("0015"))
          sse2dep.setText(td);
        if (parameters.get("to").equals("0017"))
          lac2app.setText(td);
        if (parameters.get("to").equals("0019"))
          lac2arr.setText(td);
        if (parameters.get("to").equals("0021"))
        {
          ewr2app.setText(td);
          lac2dep.setText(td);
        }
        if (parameters.get("to").equals("0023"))
          ewr2arr.setText(td);
        if (parameters.get("to").equals("0025"))
          ewr2dep.setText(td);

      } // End if

      if (parameters.get("from") != null)
      {

        // Up Brighton:
        if (parameters.get("from").equals("0020"))
          ewr1app.setText("");
        if (parameters.get("from").equals("0018"))
        {
          ewr1arr.setText("");
          lac1app.setText("");
        }
        if (parameters.get("from").equals("0016"))
        {
          ewr1dep.setText("");
          lac1arr.setText("");
        }
        if (parameters.get("from").equals("0014"))
          lac1dep.setText("");
        if (parameters.get("from").equals("0012"))
          sse1app.setText("");
        if (parameters.get("from").equals("0010"))
          sse1arr.setText("");
        if (parameters.get("from").equals("0008"))
        {
          sse1dep.setText("");
          swk1app.setText("");
        }
        if (parameters.get("from").equals("0006"))
        {
          swk1arr.setText("");
          fsg1app.setText("");
        }
        if (parameters.get("from").equals("0004"))
        {
          swk1dep.setText("");
          fsg1arr.setText("");
        }
        if (parameters.get("from").equals("0002"))
          fsg1dep.setText("");

        // Down Brighton:
        if (parameters.get("from").equals("0007"))
          fsg2app.setText("");
        if (parameters.get("from").equals("0009"))
        {
          swk2app.setText("");
          fsg2arr.setText("");
        }
        if (parameters.get("from").equals("0011"))
        {
          sse2app.setText("");
          swk2arr.setText("");
          fsg2dep.setText("");
        }
        if (parameters.get("from").equals("0013"))
        {
          sse2arr.setText("");
          swk2dep.setText("");
        }
        if (parameters.get("from").equals("0015"))
          sse2dep.setText("");
        if (parameters.get("from").equals("0017"))
          lac2app.setText("");
        if (parameters.get("from").equals("0019"))
          lac2arr.setText("");
        if (parameters.get("from").equals("0021"))
        {
          ewr2app.setText("");
          lac2dep.setText("");
        }
        if (parameters.get("from").equals("0023"))
          ewr2arr.setText("");
        if (parameters.get("from").equals("0025"))
          ewr2dep.setText("");

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
    mainWindow.add(arrived2);
    mainWindow.add(departed2);

    // Add info boxes:
    mainWindow.add(ewr1app);
    mainWindow.add(ewr1arr);
    mainWindow.add(ewr1dep);
    mainWindow.add(ewr2app);
    mainWindow.add(ewr2arr);
    mainWindow.add(ewr2dep);

    // Refresh the portal:
    mainWindow.repaint();

  } // End ‘eastWorthing()’ Method

// -------------------------------- LancingApproachScreens Class ---------------

  void lancing()
  {

    refreshUI();

    // Add labels
    title.setText("Lancing");
    mainWindow.add(title);
    mainWindow.add(platform1);
    mainWindow.add(approach1);
    mainWindow.add(arrived1);
    mainWindow.add(departed1);
    mainWindow.add(platform2);
    mainWindow.add(approach2);
    mainWindow.add(arrived2);
    mainWindow.add(departed2);

    // Add info boxes:
    mainWindow.add(lac1app);
    mainWindow.add(lac1arr);
    mainWindow.add(lac1dep);
    mainWindow.add(lac2app);
    mainWindow.add(lac2arr);
    mainWindow.add(lac2dep);

    // Refresh the portal:
    mainWindow.repaint();

  } // End ‘lancing()’ Method

// -------------------------------- LancingApproachScreens Class ---------------

  void shoreham()
  {

    refreshUI();

    // Add labels
    title.setText("Shoreham-by-Sea");
    mainWindow.add(title);
    mainWindow.add(platform1);
    mainWindow.add(approach1);
    mainWindow.add(arrived1);
    mainWindow.add(departed1);
    mainWindow.add(platform2);
    mainWindow.add(approach2);
    mainWindow.add(arrived2);
    mainWindow.add(departed2);

    // Add info boxes:
    mainWindow.add(sse1app);
    mainWindow.add(sse1arr);
    mainWindow.add(sse1dep);
    mainWindow.add(sse2app);
    mainWindow.add(sse2arr);
    mainWindow.add(sse2dep);

    // Refresh the portal:
    mainWindow.repaint();

  } // End ‘shoreham()’ Method

// -------------------------------- LancingApproachScreens Class ---------------

  void southwick()
  {

    refreshUI();

    // Add labels
    title.setText("Southwick");
    mainWindow.add(title);
    mainWindow.add(platform1);
    mainWindow.add(approach1);
    mainWindow.add(arrived1);
    mainWindow.add(departed1);
    mainWindow.add(platform2);
    mainWindow.add(approach2);
    mainWindow.add(arrived2);
    mainWindow.add(departed2);

    // Add info boxes:
    mainWindow.add(swk1app);
    mainWindow.add(swk1arr);
    mainWindow.add(swk1dep);
    mainWindow.add(swk2app);
    mainWindow.add(swk2arr);
    mainWindow.add(swk2dep);

    // Refresh the portal:
    mainWindow.repaint();

  } // End ‘southwick()’ Method

// -------------------------------- LancingApproachScreens Class ---------------

  void fishersgate()
  {

    refreshUI();

    // Add labels
    title.setText("Fishersgate");
    mainWindow.add(title);
    mainWindow.add(platform1);
    mainWindow.add(approach1);
    mainWindow.add(arrived1);
    mainWindow.add(departed1);
    mainWindow.add(platform2);
    mainWindow.add(approach2);
    mainWindow.add(arrived2);
    mainWindow.add(departed2);

    // Add info boxes:
    mainWindow.add(fsg1app);
    mainWindow.add(fsg1arr);
    mainWindow.add(fsg1dep);
    mainWindow.add(fsg2app);
    mainWindow.add(fsg2arr);
    mainWindow.add(fsg2dep);

    // Refresh the portal:
    mainWindow.repaint();

  } // End ‘fishersgate()’ Method

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

  private static void configureButton(JLabel button)
  {
    button.setFont(new Font("Garamond", Font.PLAIN, 22));
    button.setSize(160, 26);
    button.setForeground(HIGHLIGHT);
    button.setBackground(CHARCOAL);
    button.setBorder(new LineBorder(HIGHLIGHT, 2));
    button.setOpaque(true);
  } // End ‘configureButton(JLabel)’ Method

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
