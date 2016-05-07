// +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+

package simsigClients;
import javax.swing.JLabel;

// +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+

/**
 * <code>PLDListener</code> shows details of Portslade station.
 *
 * @author Mark David Pokorny
 * @version Dé Sathairn, 7ú Bealtaine 2016
 * @since Dé Sathairn, 7ú Bealtaine 2016
 */
class PLDListener extends ButtonListener
{

// ------------------------------------------- PLDListener Class ---------------

  private LancingApproachScreens ui;

// ------------------------------------------- PLDListener Class ---------------

  PLDListener(JLabel button, LancingApproachScreens ui)
  {

    super(button);
    this.ui = ui;

  } // End ‘PLDListener(JLabel, LancingApproachScreens)’ Constructor

// ------------------------------------------- PLDListener Class ---------------

  public void action()
  {
    ui.portslade();
  } // End ‘action()’ Method

// ------------------------------------------- PLDListener Class ---------------

} // End ‘PLDListener’ Class

// +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
