package viewer;

import javax.swing.*;
import com.toedter.calendar.JDateChooser;

import controller.Actions;

import java.awt.*;
import java.awt.event.ActionListener;

public class PrealertEntryView extends JFrame {

    private static final long serialVersionUID = 1L;

    private JTextField nameField, guideField, collectedField, originField;
    private JComboBox<String> statusCombo;
    private JDateChooser creationDateChooser;
    private JButton submitButton;

    public PrealertEntryView(ActionListener listener) {
        this.initComponents(listener);
        this.setTitle("Nuevo Registro de Prealerta");
        this.setSize(400, 300);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null); 
    }
    
    private void initComponents(ActionListener listener) {
        JPanel mainPanel = new JPanel(new GridLayout(7, 2, 10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        mainPanel.setBackground(Color.WHITE);

        nameField = createStyledTextField();
        guideField = createStyledTextField();
        collectedField = createStyledTextField();
        originField = createStyledTextField();
        
        statusCombo = new JComboBox<>(new String[]{"CARGADO", "PENDIENTE"});
        statusCombo.setFont(new Font("Arial", Font.PLAIN, 14));
        statusCombo.setBackground(Color.WHITE);
        
        creationDateChooser = new JDateChooser();
        creationDateChooser.setDateFormatString("dd/MM/yyyy");
        creationDateChooser.setFont(new Font("Arial", Font.PLAIN, 14));
        
        submitButton = createStyledButton("Insertar");

        mainPanel.add(createStyledLabel("Nombre:"));
        mainPanel.add(nameField);
        mainPanel.add(createStyledLabel("Guía:"));
        mainPanel.add(guideField);
        mainPanel.add(createStyledLabel("Estado:"));
        mainPanel.add(statusCombo);
        mainPanel.add(createStyledLabel("Fecha de Creación:"));
        mainPanel.add(creationDateChooser);
        mainPanel.add(createStyledLabel("Recogido:"));
        mainPanel.add(collectedField);
        mainPanel.add(createStyledLabel("Origen:"));
        mainPanel.add(originField);
        mainPanel.add(new JLabel("")); 
        
        submitButton.setActionCommand(Actions.SUBMIT_PREALERT.toString());
        submitButton.addActionListener(listener); 
        mainPanel.add(submitButton);
        
        this.add(mainPanel);
    }

    private JTextField createStyledTextField() {
        JTextField textField = new JTextField();
        textField.setFont(new Font("Arial", Font.PLAIN, 14));
        return textField;
    }

    private JLabel createStyledLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Arial", Font.PLAIN, 14));
        label.setForeground(Color.BLACK);
        return label;
    }

    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        Font buttonFont = new Font("Arial", Font.PLAIN, 14);
        Color buttonColor = new Color(60, 120, 180);
        Color textColor = Color.WHITE;

        button.setFont(buttonFont);
        button.setBackground(buttonColor);
        button.setForeground(textColor);
        button.setFocusPainted(false);
        button.setOpaque(true);
        return button;
    }
    
    public void setVisibleFrame(boolean visible) {
        this.setVisible(visible);
    }
    
    public JButton getSubmitButton() {
        return submitButton;
    }
}
