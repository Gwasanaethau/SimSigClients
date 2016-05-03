// +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+

package simsigClients;
import javax.swing.JLabel;

// +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+

/**
 * <code>StationListener</code> shows details of the selected station.
 *
 * @author Mark David Pokorny
 * @version Dé Máirt, 3ú Bealtaine 2016
 * @since Dé Máirt, 3ú Bealtaine 2016
 */
class StationListener extends ButtonListener
{

// --------------------------------------- StationListener Class ---------------

  private JLabel title;
  private boolean isCursorInBounds;

// --------------------------------------- StationListener Class ---------------

  StationListener(JLabel button, JLabel title)
  {

    super(button);
    this.title = title;

  } // End ‘StationListener(JLabel, JLabel)’ Constructor

// --------------------------------------- StationListener Class ---------------

  public void action()
  {
    title.setVisible(true);
  } // End ‘action()’ Method

// --------------------------------------- StationListener Class ---------------

} // End ‘StationListener’ Class

// +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
