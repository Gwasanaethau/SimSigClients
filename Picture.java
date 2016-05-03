// +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+

package simsigClients;
import strampáil.Printer;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JComponent;

// +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+

/**
 * <code>Picture</code> inserts images into a {@link java.io.JComponent} to be
 * rendered as part of the UI.
 *
 * @author Mark David Pokorny
 * @version Dé Sathairn, 30ú Aibreán 2016
 * @since Dé Sathairn, 30ú Aibreán 2016
 */
class Picture extends JComponent
{

// ----------------------------------------------- Picture Class ---------------

	private BufferedImage picture;
	private int height, width;

// ----------------------------------------------- Picture Class ---------------

	Picture(String pictureFileName)
	{

		try
		{

			picture = ImageIO.read(new File(pictureFileName));
			height = picture.getHeight();
			width = picture.getWidth();
			setSize(width, height);

		} // End try
		catch (IOException ioe)
		{

			Printer.printError("‘" + pictureFileName + "’ not found!");
			picture = null;
			height = Integer.MIN_VALUE;
			width = Integer.MIN_VALUE;

		} // End catch (IOException)

	} // End ‘Picture(String)’ Constructor

// ----------------------------------------------- Picture Class ---------------

	public void paintComponent(Graphics g)
	{

		super.paintComponent(g);
		g.drawImage(picture, 0, 0, null);

	} // End ‘paintComponent(Graphics)’ Method

// ----------------------------------------------- Picture Class ---------------

} // End ‘Picture’ Class

// +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
