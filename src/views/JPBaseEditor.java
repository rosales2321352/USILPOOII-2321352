package views;

import views.core.CustomButton;
import views.core.table.ManageTableHeader;
import views.core.table.TableCallback;
import views.core.table.TableModel;

import javax.security.auth.callback.Callback;
import javax.swing.*;
import javax.swing.table.JTableHeader;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import java.awt.*;

public abstract class JPBaseEditor extends JPanel {
    public JLabel lblTitle;
    public JPanel titlePanel;
    public JTable table;
    public TableModel model;
    public JPBaseEditor(){
        this.setBackground(Color.WHITE);
        this.setLayout(new BorderLayout());
    }
    public void makeTitle(String title){
        titlePanel = new JPanel();
        titlePanel.setBackground(Color.WHITE);
        titlePanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        lblTitle = new JLabel(title);
        lblTitle.setFont(new Font("Arial", Font.BOLD,24));
        titlePanel.add(lblTitle);
    }
    public void makeTableHeader(String[] titles, int len, boolean editable, TableCallback callback){
        Object[][] objects = new Object[len][];
        for (int i = 0; i < objects.length; i++) {
            objects[i] = new Object[]{""};
        }
        model = new TableModel(objects,titles,editable);
        table.setModel(model);
        table.getTableHeader().setReorderingAllowed(false);
        table.setRowHeight(25);
        table.setGridColor(new java.awt.Color(216,216,216));

        if(callback != null){
            callback.apply(table);
        }


        JTableHeader jTableHeader = table.getTableHeader();
        jTableHeader.setDefaultRenderer(new ManageTableHeader());
        table.setTableHeader(jTableHeader);
    }
    public static void setMaxLength(JTextField textField, int maxLength) {
        ((AbstractDocument) textField.getDocument()).setDocumentFilter(new DocumentFilter() {
            @Override
            public void insertString(DocumentFilter.FilterBypass fb, int offset, String string, AttributeSet attr)
                    throws BadLocationException {
                if ((fb.getDocument().getLength() + string.length()) <= maxLength) {
                    super.insertString(fb, offset, string, attr);
                }
            }

            @Override
            public void replace(DocumentFilter.FilterBypass fb, int offset, int length, String text, AttributeSet attrs)
                    throws BadLocationException {
                int currentLength = fb.getDocument().getLength();
                int newLength = currentLength - length + text.length();

                if (newLength <= maxLength) {
                    super.replace(fb, offset, length, text, attrs);
                }
            }
        });
    }
    public abstract void drawControls();


}
