// +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+

package simsigClients;
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
 * User interface for the {@link LancingApproachScreens} programme.
 *
 * @author Mark David Pokorny
 * @version Dé Máirt, 3ú Bealtaine 2016
 * @since Dé hAoine, 29ú Aibreán 2016
 */
class LancingUI
{

// --------------------------------------------- LancingUI Class ---------------

  private static final int WINDOW_WIDTH = 1024;
  private static final int WINDOW_HEIGHT = 768;

  static final Color CHARCOAL = new Color(0x333333);
  static final Color MOTION_BAR = new Color(0x8CC63E);
  static final Color EXIT_BUTTON = new Color(0xEE1C23);
  static final Color HIGHLIGHT = new Color(0x3C78C0);
  static final Color HIGHLIGHT_DARK = new Color(0x244873);
  static final Color GOLD = new Color(0xC3872D);

  private LancingControl control;

  private JFrame mainWindow;
  private Picture logo;
  private JPanel motionBar;
  private JPanel quit;
  private JLabel failureMessage;

// --------------------------------------------- LancingUI Class ---------------

  LancingUI(LancingControl control)
  {
    this.control = control;
  } // End ‘LancingUI(LancingControl)’ Constructor

// --------------------------------------------- LancingUI Class ---------------

  void entryPortal()
  {

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
    mainWindow.add(logo);

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
    mainWindow.add(motionBar);

    // Add the quit button:
    quit = new JPanel();
    quit.setSize(24, 24);
    quit.setLocation(WINDOW_WIDTH - 24, 0);
    quit.setBackground(EXIT_BUTTON);
    quit.setOpaque(false);
    quit.setToolTipText("Quit");
    ExitListener quitListener = new ExitListener(quit, this);
    quit.addMouseListener(quitListener);
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
      connect, addressBox, portBox, debugBox, control));
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

// --------------------------------------------- LancingUI Class ---------------

  void mainPortal()
  {

    // Remove all components:
    mainWindow.getContentPane().removeAll();

    // Re-add top bar:
    mainWindow.add(logo);
    mainWindow.add(motionBar);
    mainWindow.add(quit);

    // Add East Worthing button:
    JLabel eastWorthing = new JLabel("East Worthing", JLabel.CENTER);
    eastWorthing.setFont(new Font("Garamond", Font.PLAIN, 22));
    eastWorthing.setSize(80, 26);
    eastWorthing.setLocation(0, 34);
    eastWorthing.setForeground(HIGHLIGHT);
    eastWorthing.setBackground(CHARCOAL);
    eastWorthing.setBorder(new LineBorder(HIGHLIGHT, 2));
    eastWorthing.setOpaque(true);
    eastWorthing.setToolTipText("View East Worthing");
    eastWorthing.addMouseListener(new StationListener(eastWorthing));
    mainWindow.add(eastWorthing);

    // Refresh the portal:
    mainWindow.repaint();

  } // End ‘mainPortal()’ Method

// --------------------------------------------- LancingUI Class ---------------

  void connectionFailure()
  {
    failureMessage.setVisible(true);
    mainWindow.repaint();
  } // End ‘connectionFailure()’ Method

// --------------------------------------------- LancingUI Class ---------------

  void close()
  {
    mainWindow.dispose();
    control.close();
  } // End ‘close()’ Method

// --------------------------------------------- LancingUI Class ---------------

} // End ‘LancingUI’ Class

// +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
