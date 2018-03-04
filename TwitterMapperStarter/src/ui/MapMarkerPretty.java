package ui;

import org.openstreetmap.gui.jmapviewer.Coordinate;
import org.openstreetmap.gui.jmapviewer.Layer;

import java.awt.*;

public class MapMarkerPretty extends MapMarkerSimple {
    public MapMarkerPretty(Layer layer, Coordinate coord, Color color, String imageUrl) {
        super(layer, coord);
        setBackColor(color);
        //TODO
    }
}
