package view.core.layout;

import javax.swing.*;
import javax.swing.plaf.basic.BasicScrollBarUI;
import java.awt.*;

public class CustomScrollBarUI extends BasicScrollBarUI {



    @Override
    protected void paintTrack(Graphics g, JComponent c, Rectangle trackBounds) {
        // Personaliza la apariencia de la pista (fondo) del scrollbar
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(trackBounds.x, trackBounds.y, trackBounds.width, trackBounds.height);
    }

    @Override
    protected void paintThumb(Graphics g, JComponent c, Rectangle thumbBounds) {
        // Personaliza la apariencia del thumb (barra deslizante) del scrollbar
        g.setColor(Color.DARK_GRAY);
        g.fillRect(thumbBounds.x, thumbBounds.y, thumbBounds.width, thumbBounds.height);
    }
}
