package view.core.panel;

import javax.swing.*;
import java.awt.*;

public class ActionPanel extends JPanel {


    public ActionPanel(){
        Button button = new Button("HOla");
        button.setName("btnAction");
        this.add(button);
    }

}
