// +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+

package simsigClients;
import javax.swing.JLabel;

// +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+

/**
 * <code>SWKListener</code> shows details of Southwick station.
 *
 * @author Mark David Pokorny
 * @version Dé Sathairn, 7ú Bealtaine 2016
 * @since Dé Sathairn, 7ú Bealtaine 2016
 */
class SWKListener extends ButtonListener
{

// ------------------------------------------- SWKListener Class ---------------

  private LancingApproachScreens ui;

// ------------------------------------------- SWKListener Class ---------------

  SWKListener(JLabel button, LancingApproachScreens ui)
  {

    super(button);
    this.ui = ui;

  } // End ‘SWKListener(JLabel, LancingApproachScreens)’ Constructor

// ------------------------------------------- SWKListener Class ---------------

  public void action()
  {
    ui.southwick();
  } // End ‘action()’ Method

// ------------------------------------------- SWKListener Class ---------------

} // End ‘SWKListener’ Class

// +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
