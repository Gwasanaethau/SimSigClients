// +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+

package simsigClients;
import javax.swing.JLabel;

// +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+

/**
 * <code>LACListener</code> shows details of Lancing station.
 *
 * @author Mark David Pokorny
 * @version Dé Céadaoin, 4ú Bealtaine 2016
 * @since Dé Céadaoin, 4ú Bealtaine 2016
 */
class LACListener extends ButtonListener
{

// ------------------------------------------- LACListener Class ---------------

  private LancingApproachScreens ui;

// ------------------------------------------- LACListener Class ---------------

  LACListener(JLabel button, LancingApproachScreens ui)
  {

    super(button);
    this.ui = ui;

  } // End ‘LACListener(JLabel, LancingApproachScreens)’ Constructor

// ------------------------------------------- LACListener Class ---------------

  public void action()
  {
    ui.lancing();
  } // End ‘action()’ Method

// ------------------------------------------- LACListener Class ---------------

} // End ‘LACListener’ Class

// +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
