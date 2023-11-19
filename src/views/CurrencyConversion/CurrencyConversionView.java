package views.CurrencyConversion;

import controllers.CurrencyConversion.CurrencyConversionController;
import views.CurrencyConversion.partials.CurrencyConversionList;
import views.CurrencyConversion.partials.CurrencyConversionEditor;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class CurrencyConversionView extends JPanel{

    public CurrencyConversionController controller;
    public CardLayout cardLayout;
    public JPanel tabContent;
    public CurrencyConversionList CurrencyConversionList;
    public CurrencyConversionEditor CurrencyConversionEditor;


    public CurrencyConversionView(){
        this.setBackground(Color.WHITE);
        this.controller = new CurrencyConversionController(this);
        this.CurrencyConversionList = new CurrencyConversionList(this.controller);
        this.CurrencyConversionEditor = new CurrencyConversionEditor(this.controller);
        this.setLayout(new BorderLayout());
        this.cardLayout = new CardLayout();
        this.tabContent = new JPanel(cardLayout);

        int paddingSize = 20;
        EmptyBorder paddingBorder = new EmptyBorder(paddingSize, paddingSize, paddingSize, paddingSize);
        this.setBorder(paddingBorder);

        tabContent.add(this.CurrencyConversionList, "List");
        tabContent.add(this.CurrencyConversionEditor, "Action");

        this.add(tabContent, BorderLayout.CENTER);

        this.controller.renderObjects();
        this.controller.loadDataTableAsync("");
    }
}
