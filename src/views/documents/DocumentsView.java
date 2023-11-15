package views.documents;

import controllers.product.ProductController;
import controllers.Documents.DocumentsController;
import views.category.partials.CategoryList;
import views.product.partials.ProductEditor;
import views.product.partials.ProductList;
import views.documents.partials.DocumentsEditor;
import views.documents.partials.DocumentsList;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class DocumentsView extends JPanel{

    public DocumentsController controller;
    public CardLayout cardLayout;
    public JPanel tabContent;
    public DocumentsList documentsList;
    public DocumentsEditor documentsEditor;


    public DocumentsView(){
        this.setBackground(Color.WHITE);
        this.controller = new DocumentsController(this);
        this.documentsList = new DocumentsList(this.controller);
        this.documentsEditor = new DocumentsEditor(this.controller);
        this.setLayout(new BorderLayout());
        this.cardLayout = new CardLayout();
        this.tabContent = new JPanel(cardLayout);

        int paddingSize = 20;
        EmptyBorder paddingBorder = new EmptyBorder(paddingSize, paddingSize, paddingSize, paddingSize);
        this.setBorder(paddingBorder);

        tabContent.add(this.documentsList, "List");
        tabContent.add(this.documentsEditor, "Action");

        this.add(tabContent, BorderLayout.CENTER);

        this.controller.renderObjects();
        this.controller.loadDataTableAsync("");
    }
}
