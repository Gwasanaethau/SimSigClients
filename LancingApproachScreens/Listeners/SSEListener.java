// +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+

package simsigClients;
import javax.swing.JLabel;

// +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+

/**
 * <code>SSEListener</code> shows details of East Worthing station.
 *
 * @author Mark David Pokorny
 * @version Dé Sathairn, 7ú Bealtaine 2016
 * @since Dé Sathairn, 7ú Bealtaine 2016
 */
class SSEListener extends ButtonListener
{

// ------------------------------------------- SSEListener Class ---------------

  private LancingApproachScreens ui;

// ------------------------------------------- SSEListener Class ---------------

  SSEListener(JLabel button, LancingApproachScreens ui)
  {

    super(button);
    this.ui = ui;

  } // End ‘SSEListener(JLabel, LancingApproachScreens)’ Constructor

// ------------------------------------------- SSEListener Class ---------------

  public void action()
  {
    ui.shoreham();
  } // End ‘action()’ Method

// ------------------------------------------- SSEListener Class ---------------

} // End ‘SSEListener’ Class

// +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
