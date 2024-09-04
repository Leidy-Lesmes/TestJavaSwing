package viewer;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class DeviceEntryView extends JFrame {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField nameField, serialField, macField, observationsField;
    private JComboBox<String> typeComboBox;
    private JButton addButton, cancelButton;
    private JPanel mainPanel, buttonPanel;

    public DeviceEntryView(List<String> deviceTypes) {
    	if (deviceTypes == null) {
            deviceTypes = new ArrayList<>();
        }
        this.initComponents(deviceTypes);
        this.setTitle("Agregar Nuevo Dispositivo");
        this.setSize(400, 300);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    private void initComponents(List<String> deviceTypes) {
        // Panel principal
        mainPanel = new JPanel(new GridLayout(5, 2, 10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        mainPanel.setBackground(Color.WHITE); 

        nameField = new JTextField();
        serialField = new JTextField();
        macField = new JTextField();
        observationsField = new JTextField();
        typeComboBox = new JComboBox<>(deviceTypes.toArray(new String[0]));

        Font fieldFont = new Font("Arial", Font.PLAIN, 14);
        nameField.setFont(fieldFont);
        serialField.setFont(fieldFont);
        macField.setFont(fieldFont);
        observationsField.setFont(fieldFont);
        typeComboBox.setFont(fieldFont);

        mainPanel.add(new JLabel("Nombre:"));
        mainPanel.add(nameField);
        mainPanel.add(new JLabel("Tipo:"));
        mainPanel.add(typeComboBox);
        mainPanel.add(new JLabel("Serial:"));
        mainPanel.add(serialField);
        mainPanel.add(new JLabel("MAC:"));
        mainPanel.add(macField);
        mainPanel.add(new JLabel("Observaciones:"));
        mainPanel.add(observationsField);

        buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setBackground(Color.WHITE);

        addButton = createStyledButton("Agregar");
        cancelButton = createStyledButton("Cancelar");

        buttonPanel.add(addButton);
        buttonPanel.add(cancelButton);

        this.add(mainPanel, BorderLayout.CENTER);
        this.add(buttonPanel, BorderLayout.SOUTH);
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

    public JTextField getNameField() {
        return nameField;
    }

    public JTextField getSerialField() {
        return serialField;
    }

    public JTextField getMacField() {
        return macField;
    }

    public JTextField getObservationsField() {
        return observationsField;
    }

    public JComboBox<String> getTypeComboBox() {
        return typeComboBox;
    }

    public JButton getAddButton() {
        return addButton;
    }

    public JButton getCancelButton() {
        return cancelButton;
    }
    
    public void setVisbleNewDevice(boolean visible) {
    	this.setVisible(visible);
    }

}
