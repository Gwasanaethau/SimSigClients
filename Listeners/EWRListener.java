// +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+

package simsigClients;
import javax.swing.JLabel;

// +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+

/**
 * <code>EWRListener</code> shows details of East Worthing station.
 *
 * @author Mark David Pokorny
 * @version Dé Céadaoin, 4ú Bealtaine 2016
 * @since Dé Máirt, 3ú Bealtaine 2016
 */
class EWRListener extends ButtonListener
{

// ------------------------------------------- EWRListener Class ---------------

  private LancingApproachScreens ui;

// ------------------------------------------- EWRListener Class ---------------

  EWRListener(JLabel button, LancingApproachScreens ui)
  {

    super(button);
    this.ui = ui;

  } // End ‘EWRListener(JLabel, LancingApproachScreens)’ Constructor

// ------------------------------------------- EWRListener Class ---------------

  public void action()
  {
    ui.eastWorthing();
  } // End ‘action()’ Method

// ------------------------------------------- EWRListener Class ---------------

} // End ‘EWRListener’ Class

// +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
