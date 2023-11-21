package views.Currency;

import controllers.Currency.CurrencyController;
import views.Currency.partials.CurrencyList;
import views.Currency.partials.CurrencyEditor;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class CurrencyView extends JPanel{

    public CurrencyController controller;
    public CardLayout cardLayout;
    public JPanel tabContent;
    public CurrencyList CurrencyList;
    public CurrencyEditor CurrencyEditor;


    public CurrencyView(){
        this.setBackground(Color.WHITE);
        this.controller = new CurrencyController(this);
        this.CurrencyList = new CurrencyList(this.controller);
        this.CurrencyEditor = new CurrencyEditor(this.controller);
        this.setLayout(new BorderLayout());
        this.cardLayout = new CardLayout();
        this.tabContent = new JPanel(cardLayout);

        int paddingSize = 20;
        EmptyBorder paddingBorder = new EmptyBorder(paddingSize, paddingSize, paddingSize, paddingSize);
        this.setBorder(paddingBorder);

        tabContent.add(this.CurrencyList, "List");
        tabContent.add(this.CurrencyEditor, "Action");

        this.add(tabContent, BorderLayout.CENTER);

        this.controller.renderObjects();
        this.controller.loadDataTableAsync("");
    }
}
