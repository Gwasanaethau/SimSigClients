// +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+

package simsigClients;
import strampáil.Constants;
import strampáil.Printer;
import simsigGatewayInterface.Harness;
import simsigGatewayInterface.MessageType;
import simsigGatewayInterface.SimSigClient;
import simsigGatewayInterface.SimSigMessage;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

// +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+

/**
 * Basic CLI-based programme simply prints out the SimSig messages received
 * in a human-readable format.
 *
 * @author Mark David Pokorny
 * @version Dé Domhnaigh, 8ú Bealtaine 2016
 * @since Dé Domhnaigh, 8ú Bealtaine 2016
 */
public class MessagePrinter implements Harness
{

// ---------------------------------------- MessagePrinter Class ---------------

  private static Scanner keyboard;
  private static SimSigClient client;

// ---------------------------------------- MessagePrinter Class ---------------

  public void connected(boolean success)
  {

    if (success)
    {
      boolean quit = false;

      while (!quit)
        if (keyboard.nextLine().charAt(0) == 'q')
          quit = true;
    } // End if

  } // End ‘connected()’ Method

// ---------------------------------------- MessagePrinter Class ---------------

  public void transferMessage(SimSigMessage message)
  {

    System.out.println("\033[1;36m→→→\033[0m");
    System.out.println("Message type: " + message.getType().toString());
    System.out.println("Simulation: " + message.getSimulation() + "\n");

    for (Iterator i = message.getParameters().entrySet().iterator();
      i.hasNext(); )
    {
      // Suppress the warning of the Object → Map.Entry cast:
      @SuppressWarnings("unchecked")
      Map.Entry<String, String> currentEntry = (Map.Entry<String, String>) i.next();
      System.out.println(currentEntry.getKey() +
        " → " + currentEntry.getValue());
    } // End for

    System.out.println("\033[1;36m→→→\033[0m\n");

  } // End ‘sendMessage(SimSigMessage)’ Method

// ---------------------------------------- MessagePrinter Class ---------------

  /**
   * Sets up and initialises a test STOMP client, sends a request to the
   * specified port for a connection, receives test messages and then
   * shuts down when asked.
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

    System.out.print(
      "What is the address/host name of the SimSig server? [127.0.0.1] ");
    String hostName = keyboard.nextLine();
    if (hostName.equals(""))
      hostName = "127.0.0.1";

    client = new SimSigClient(
      hostName, port, Constants.ERROR, "simgsig", new MessagePrinter());

    /*
     * NB: the ‘quit’ loop in ‘connected()’ holds the constructor progress.
     * This is poor Java, I know, but it was merely used to test functionality
     * of the simsigClient, not necessarily to provide a stable test platform.
     */
    client.close();

  } // End ‘main(String[] args)’ Method

// ---------------------------------------- MessagePrinter Class ---------------

} // End ‘MessagePrinter’ Class

// +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
