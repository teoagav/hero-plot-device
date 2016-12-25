package game1;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Icons{
	Image strengthImage;
	Image agilityImage;
	Image enduranceImage;
	Image intelligenceImage;
	Image expImage;
	Image goldImage;

	public Icons(){
		try {
			strengthImage = ImageIO.read(new File("Graphics/Icons/strengthIcon.png"));
			agilityImage = ImageIO.read(new File("Graphics/Icons/agilityIcon.png"));
			enduranceImage = ImageIO.read(new File("Graphics/Icons/enduranceIcon.png"));			
			intelligenceImage = ImageIO.read(new File("Graphics/Icons/intelligenceIcon.png"));
			expImage = ImageIO.read(new File("Graphics/Icons/expIcon.png"));
			goldImage = ImageIO.read(new File("Graphics/Icons/goldIcon.png"));
		} catch (IOException e) {
	    }
	}
}
