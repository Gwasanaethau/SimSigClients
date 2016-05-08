// +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+

package simsigClients;
import strampáil.Constants;
import strampáil.Printer;
import simsigGatewayInterface.Harness;
import simsigGatewayInterface.MessageType;
import simsigGatewayInterface.SimSigClient;
import simsigGatewayInterface.SimSigMessage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

// +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+

/**
 * Basic CLI-based programme that displays the aspects of signals on the
 * <a href=http://www.simsig.co.uk/dokuwiki/doku.php?id=simulations:lancing>
 * Lancing</a> simulation in response to trains passing. This was originally
 * the main test programme used during development of the
 * {@link SimSigClient} class and is released here for completeness.
 *
 * @author Mark David Pokorny
 * @version Dé Domhnaigh, 8ú Bealtaine 2016
 * @since Déardaoin, 21ú Aibreán 2016
 */
public class LancingSignals implements Harness
{

// ---------------------------------------- LancingSignals Class ---------------

  private static Scanner keyboard;
  private ArrayList<Signal> signals;
  private static SimSigClient client;

// ---------------------------------------- LancingSignals Class ---------------

  public void connected(boolean success)
  {

    if (success)
    {
      signals = new ArrayList<Signal>();
      for (int i = 1; i <= 21; i++)
        signals.add(new Signal("LG" + String.format("%02d", i)));

      System.out.println("\033[2J"); // Clear screen.
      printSignalChart();

      boolean quit = false;

      while (!quit)
        if (keyboard.nextLine().charAt(0) == 'q')
          quit = true;
    } // End if

  } // End ‘connected()’ Method

// ---------------------------------------- LancingSignals Class ---------------

  public void transferMessage(SimSigMessage message)
  {
    HashMap<String, String> messageParameters = message.getParameters();
    if (message.getType() == MessageType.SIGNAL &&
      messageParameters.get("obj_type").equals("signal"))
    {
      for (int i = 0; i < signals.size(); i++)
      {
        Signal thisSignal = signals.get(i);
        if (messageParameters.get("obj_id").equals("S" + thisSignal.getName()))
        {
          if (messageParameters.get("aspect").equals("0"))
            thisSignal.setAspect("\033[31m");
          else if (messageParameters.get("aspect").equals("2"))
            thisSignal.setAspect("\033[33m");
          else if (messageParameters.get("aspect").equals("6"))
            thisSignal.setAspect("\033[32m");
          signals.set(i, thisSignal);
        } // End if

      } // End for

    } // End if

    printSignalChart();

  } // End ‘sendMessage(SimSigMessage)’ Method

// ---------------------------------------- LancingSignals Class ---------------

  private void printSignalChart()
  {

    System.out.print("\033[0;0f"); // Reset cursor to beginning of console.

    for (int i = 20; i > 0; i -= 2)
      System.out.print(signals.get(i-1).getAspect() + " ");
    System.out.println();
    for (int i = 21; i > 0; i -= 2)
      System.out.print(signals.get(i-1).getAspect() + " ");
    System.out.println();
    System.out.println("Type ‘q’ to quit:");

  } // End ‘printSignalChart()’ Method

// ---------------------------------------- LancingSignals Class ---------------

  /**
   * Sets up and initialises a test STOMP client, sends a request to port 51515
   * for a connection, receives test messages and then shuts itself down.
   *
   * @param args Not used (ignored).
   */
  public static void main(String[] args)
  {

    keyboard = new Scanner(System.in);
    int port;
    System.out.print("Which port is the SimSig server on? [51515] ");
    String portString = keyboard.nextLine();
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
    System.out.print(
      "What level of messages do you want to display?\n" +
      "Debug → " + Constants.DEBUG + "\n" +
      "Info → " + Constants.INFO + "\n" +
      "Warning → " + Constants.WARNING + "\n" +
      "Error → " + Constants.ERROR + "\n" +
      "None → " + Constants.NONE + "\n" +
      "[Warning] ");
    String debugString = keyboard.nextLine();
    if (debugString.equals(""))
      debug = Constants.WARNING;
    else
    {
      try
      {
        debug = Integer.parseInt(debugString);
      } // End try
      catch (NumberFormatException nfe)
      {
        Printer.printError(debugString + " is not a valid number."
          + " Defaulting to warning message level.");
          debug = Constants.WARNING;
      } // End ‘NumberFormatException’ catch
    } // End else

    System.out.print(
      "What is the address/host name of the SimSig server? [127.0.0.1] ");
    String hostName = keyboard.nextLine();
    if (hostName.equals(""))
      hostName = "127.0.0.1";

    client = new SimSigClient(
      hostName, port, debug, "simgsig", new LancingSignals());

    /*
     * NB: the ‘quit’ loop in ‘connected()’ holds the constructor progress.
     * This is poor Java, I know, but it was merely used to test functionality
     * of the simsigClient, not necessarily to provide a stable test platform.
     */
    client.close();

  } // End ‘main(String[] args)’ Method

// ---------------------------------------- LancingSignals Class ---------------
// +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+

  private class Signal
  {

// ------------------------------------------ Signal Inner Class ---------------

    private String name;
    private String aspectColour;

// ------------------------------------------ Signal Inner Class ---------------

    Signal(String name)
    {
      this.name = name;
      aspectColour = "\033[1;30m";
    } // End ‘Signal(String)’ Constructor

// ------------------------------------------ Signal Inner Class ---------------

    void setAspect(String aspectColour) { this.aspectColour = aspectColour; }
    String getAspect() { return aspectColour + "●\033[0m"; }
    String getName() { return name; }

// ------------------------------------------ Signal Inner Class ---------------

  } // End ‘Signal’ Inner Class

// +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
// ---------------------------------------- LancingSignals Class ---------------

} // End ‘LancingSignals’ Class

// +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
