package viewer;

import javax.swing.*;

import controller.Actions;

import java.awt.*;
import java.awt.event.ActionListener;

public class DeviceTypeEntryView extends JFrame {

    private static final long serialVersionUID = 1L;

    private JTextField nameField, serialLengthField, macLengthField;
    private JButton acceptButton, cancelButton;
    private JPanel mainPanel, buttonPanel;
    private JLabel titleLabel;

    public DeviceTypeEntryView(ActionListener listener) {
        this.initComponents(listener);
        this.setTitle("Ingreso de Tipo de Equipo");
        this.setSize(400, 300);
        this.setBackground(Color.WHITE);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    private void initComponents(ActionListener listener) {
        mainPanel = new JPanel(new BorderLayout(8, 20));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        mainPanel.setBackground(Color.WHITE);

        titleLabel = new JLabel("INGRESO DE TIPO DE EQUIPO", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setForeground(new Color(30, 70, 130));
        mainPanel.add(titleLabel, BorderLayout.NORTH);

        JPanel formPanel = createFormPanel();
        mainPanel.add(formPanel, BorderLayout.CENTER);

        buttonPanel = createButtonPanel(listener);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        this.add(mainPanel, BorderLayout.CENTER);
    }

    private JPanel createFormPanel() {
        JPanel formPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        formPanel.setBackground(Color.WHITE);

        nameField = new JTextField();
        serialLengthField = new JTextField();
        macLengthField = new JTextField();

        Font fieldFont = new Font("Arial", Font.PLAIN, 14);
        nameField.setFont(fieldFont);
        serialLengthField.setFont(fieldFont);
        macLengthField.setFont(fieldFont);

        formPanel.add(new JLabel("Nombre:"));
        formPanel.add(nameField);
        formPanel.add(new JLabel("Largo del Serial:"));
        formPanel.add(serialLengthField);
        formPanel.add(new JLabel("Largo del MAC:"));
        formPanel.add(macLengthField);

        return formPanel;
    }

    private JPanel createButtonPanel(ActionListener listener) {
        buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        buttonPanel.setBackground(Color.WHITE);

        acceptButton = createStyledButton("Aceptar", listener, Actions.ACCEPT_NEW_TYPE.toString());
        cancelButton = createStyledButton("Cancelar", listener, Actions.CLOSE_NEW_TYPE.toString());

        
        buttonPanel.add(acceptButton);
        buttonPanel.add(cancelButton);

        return buttonPanel;
    }

    private JButton createStyledButton(String text, ActionListener listener,  String actionCommand) {
        JButton button = new JButton(text);
        Font buttonFont = new Font("Arial", Font.PLAIN, 14);
        Color buttonColor = new Color(60, 120, 180);
        Color textColor = Color.WHITE;

        button.setFont(buttonFont);
        button.setBackground(buttonColor);
        button.setForeground(textColor);
        button.setFocusPainted(false);
        button.setOpaque(true);
        button.addActionListener(listener);
        return button;
    }

    public JTextField getNameField() {
        return nameField;
    }

    public JTextField getSerialLengthField() {
        return serialLengthField;
    }

    public JTextField getMacLengthField() {
        return macLengthField;
    }

    public JButton getAcceptButton() {
        return acceptButton;
    }

    public JButton getCancelButton() {
        return cancelButton;
    }
    
    public void setVisbleDeviceType(boolean visible) {
    	this.setVisible(visible);
    }

}
