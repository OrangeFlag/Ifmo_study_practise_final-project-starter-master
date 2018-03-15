package ui;

import org.openstreetmap.gui.jmapviewer.Coordinate;
import org.openstreetmap.gui.jmapviewer.Layer;
import org.openstreetmap.gui.jmapviewer.MapMarkerCircle;
import twitter4j.Status;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.RoundRectangle2D;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class MapMarkerPretty extends MapMarkerCircle {

    public static final double defaultMarkerSize = 5.0;
    private Image image;
    private Status status;

    public MapMarkerPretty(Layer layer, Color color, Status s) {
        super(layer, null, util.Util.statusCoordinate(s), defaultMarkerSize, STYLE.FIXED, getDefaultStyle());
        setColor(color);
        setBackColor(color);
        status = s;
        try{
            image = ImageIO.read(new URL(s.getUser().getProfileBackgroundImageURL()));
        }catch (IOException e)
        {
            try {
                image = ImageIO.read(new File("data/boring.png"));
            }
            catch (IOException ex) {

            }
        }
    }

    public Status getStatus() {return status;};

    @Override
    public String toString() {
        return status.getUser().getName() + ": " + status.getText();
    };

    @Override
    public void paint(Graphics g, Point position, int radius) {
        int size = radius * 5;
        if (g instanceof Graphics2D && this.getBackColor() != null) {
            Graphics2D g2 = (Graphics2D)g;
            Composite oldComposite = g2.getComposite();
            g2.setComposite(AlphaComposite.getInstance(3));
            g2.setPaint(this.getBackColor());
            g.fillOval(position.x - radius, position.y - radius, size, size);
            g2.setComposite(oldComposite);
        }


        g.setColor(this.getColor());
        g.drawOval(position.x - radius, position.y - radius, size, size);
        if (this.getLayer() == null || this.getLayer().isVisibleTexts().booleanValue()) {
            this.paintText(g, position);
        }

        double r = this.getRadius();
        int width = (int) (r*4);
        int height = (int) (r*4);
        int w2 = width/8;
        int h2 = height/8;
        g.setClip(new RoundRectangle2D.Double(position.x-w2, position.y - h2, width, height, 90, 90));
        g.drawImage(this.image, position.x-w2, position.y - h2, width, height, null);
        g.setClip(null);

        if (this.getLayer() == null || this.getLayer().isVisibleTexts().booleanValue()) {
            this.paintText(g, position);
        };

    };
}
