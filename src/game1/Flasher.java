package game1;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.Timer;

public class Flasher {
	boolean flash = true, flashing = true;
    Color buttonBlue = new Color(36, 108, 242, 128);
    JButton button;
    Timer buttonFlash;

	public Flasher(JButton button) {
		this.button = button;
	}
	
	public void start() {
		if (flashing) {
			buttonFlash = new Timer(1000, new ActionListener() {
		    	public void actionPerformed(ActionEvent e){
		    		if (flash) {
		    			button.setBackground(buttonBlue);
		    		} else {
		    			button.setBackground(null);
		    		}
		    		flash = !flash;
		    	}
		    });
			buttonFlash.start();	
		}
	}
	
	public void stop() {
		flashing = false;
		button.setBackground(null);
		//buttonFlash.stop();
	}
}
