package game1;


import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class ResourceBar {
    int currentResourceValue, maxResourceValue, x, y;
    Color color;
    double ratio;
    
    public ResourceBar(int startResourceValue, int startMaxResourceValue, Color startColor, int startX, int startY) {
        currentResourceValue = startResourceValue;
        maxResourceValue = startMaxResourceValue;

        color = startColor;
        x = startX;
        y = startY;
    }
    
    public void draw(Graphics surface) {
        ratio = (currentResourceValue*1.0)/(maxResourceValue*1.0);
        //System.out.println(ratio);
        surface.setColor(color);
        surface.fillRect(x, y, (int)(ratio * 100), 10);
        surface.setColor(Color.BLACK);
        ((Graphics2D) surface).setStroke(new BasicStroke(3.0f));
        surface.drawRect(x, y, 100, 10);
    }
    

    public void drawBig(Graphics surface) {
        ratio = (currentResourceValue*1.0)/(maxResourceValue*1.0);
        surface.setColor(color);
        surface.fillRect(x, y, (int)(ratio*200), 25);
        surface.setColor(Color.BLACK);
        ((Graphics2D) surface).setStroke(new BasicStroke(3.0f));
        surface.drawRect(x, y, 200, 25);
    }
    
    public void drawText(Graphics surface, String text) {
        surface.setColor(Color.WHITE);
        surface.setFont(new Font("ShowcardGothic", Font.PLAIN, 18)); 
        surface.drawString(text + currentResourceValue, 322, y+20);
    }
}