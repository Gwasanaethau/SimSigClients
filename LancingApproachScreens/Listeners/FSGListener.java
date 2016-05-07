// +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+

package simsigClients;
import javax.swing.JLabel;

// +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+

/**
 * <code>FSGListener</code> shows details of Fishersgate station.
 *
 * @author Mark David Pokorny
 * @version Dé Sathairn, 7ú Bealtaine 2016
 * @since Dé Sathairn, 7ú Bealtaine 2016
 */
class FSGListener extends ButtonListener
{

// ------------------------------------------- FSGListener Class ---------------

  private LancingApproachScreens ui;

// ------------------------------------------- FSGListener Class ---------------

  FSGListener(JLabel button, LancingApproachScreens ui)
  {

    super(button);
    this.ui = ui;

  } // End ‘FSGListener(JLabel, LancingApproachScreens)’ Constructor

// ------------------------------------------- FSGListener Class ---------------

  public void action()
  {
    ui.fishersgate();
  } // End ‘action()’ Method

// ------------------------------------------- FSGListener Class ---------------

} // End ‘FSGListener’ Class

// +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
