package viewer;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import com.toedter.calendar.JDateChooser;

import controller.Actions;

import java.awt.*;
import java.awt.event.ActionListener;

public class PrealertView extends JFrame {

    private static final long serialVersionUID = 1L;

    private JTextField nameField, guideField, originField;
    private JComboBox<String> statusCombo, collectedCombo;
    private JDateChooser datePicker, endDatePicker;
    private JTable deviceTable;
    private JButton searchButton, addButton, editButton, deleteButton;
    private JButton newTypeButton, newDeviceButton, assignButton, collectButton;
    private JPanel searchPanel, tablePanel, sidePanel, mainPanel, southPanel;
    private JLabel titleLabel;
    private PrealertEntryView prealertEntryView;
    private DeviceTypeEntryView deviceTypeEntryView;
    private DeviceEntryView deviceEntryView;
    private DeviceAssignmentView deviceAssignmentView;
    private CollectionView collectionView;

    public PrealertView(ActionListener listener) {
        this.initComponents(listener);
        this.setTitle("Prealertas");
        this.setSize(800, 600);
        this.setBackground(Color.WHITE);
        this.prealertEntryView = new PrealertEntryView(listener);
        this.deviceTypeEntryView = new DeviceTypeEntryView(listener);
        this.deviceEntryView = new DeviceEntryView(null);
        this.deviceAssignmentView = new DeviceAssignmentView();
        this.collectionView = new CollectionView();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    private void initComponents(ActionListener listener) {
        mainPanel = new JPanel(new BorderLayout(8, 20));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); 
        mainPanel.setBackground(Color.WHITE); 

        titleLabel = new JLabel("PREALERTAS", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(new Color(30, 70, 130));
        mainPanel.add(titleLabel, BorderLayout.NORTH);

        searchPanel = createSearchPanel(listener);
        mainPanel.add(searchPanel, BorderLayout.NORTH);

        tablePanel = createTablePanel();
        mainPanel.add(tablePanel, BorderLayout.CENTER);

        sidePanel = createSidePanel(listener);
        mainPanel.add(sidePanel, BorderLayout.EAST);

        southPanel = createSouthPanel(listener);
        mainPanel.add(southPanel, BorderLayout.SOUTH);

        searchButton.addActionListener(listener);
        addButton.addActionListener(listener);
        editButton.addActionListener(listener);
        deleteButton.addActionListener(listener);
        newTypeButton.addActionListener(listener);
        newDeviceButton.addActionListener(listener);
        assignButton.addActionListener(listener);
        collectButton.addActionListener(listener);

        this.add(mainPanel, BorderLayout.CENTER);
    }

    private JPanel createSearchPanel(ActionListener listener) {
        searchPanel = new JPanel(new GridLayout(2, 8, 10, 10)); 
        searchPanel.setBackground(Color.WHITE);

        nameField = new JTextField();
        guideField = new JTextField();
        statusCombo = new JComboBox<>(new String[]{"", "CARGADO", "PENDIENTE"});
        datePicker = new JDateChooser();
        endDatePicker = new JDateChooser();
        collectedCombo = new JComboBox<>(new String[]{"", "SI", "NO"});
        originField = new JTextField();
        searchButton = new JButton("Buscar");

        Font fieldFont = new Font("Arial", Font.PLAIN, 14);
        nameField.setFont(fieldFont);
        guideField.setFont(fieldFont);
        originField.setFont(fieldFont);
        statusCombo.setFont(fieldFont);
        collectedCombo.setFont(fieldFont);

        nameField.setPreferredSize(new Dimension(150, 25));  
        guideField.setPreferredSize(new Dimension(150, 25));
        originField.setPreferredSize(new Dimension(150, 25));

        searchPanel.add(new JLabel("Nombre:"));
        searchPanel.add(new JLabel("Guía:"));
        searchPanel.add(new JLabel("Estado:"));
        searchPanel.add(new JLabel("Fecha Inicial:"));
        searchPanel.add(new JLabel("Fecha Final:"));
        searchPanel.add(new JLabel("Recogido:"));
        searchPanel.add(new JLabel("Origen:"));
        searchPanel.add(new JLabel(""));

        searchPanel.add(nameField); 
        searchPanel.add(guideField);
        searchPanel.add(statusCombo);
        searchPanel.add(datePicker);
        searchPanel.add(endDatePicker);
        searchPanel.add(collectedCombo);
        searchPanel.add(originField);

        Font buttonFont = new Font("Arial", Font.PLAIN, 14);
        Color buttonColor = new Color(60, 120, 180);
        Color textColor = Color.WHITE;

        searchButton.setFont(buttonFont);
        searchButton.setBackground(buttonColor);
        searchButton.setForeground(textColor);
        searchButton.setFocusPainted(false);
        searchButton.setOpaque(true);
        searchButton.addActionListener(listener);
        searchButton.setActionCommand(Actions.SEARCH.toString());
        searchPanel.add(searchButton);

        return searchPanel;
    }

    private JPanel createTablePanel() {
        tablePanel = new JPanel(new BorderLayout());
        String[] columnNames = {"ID", "Nombre", "Guía", "Estado", "Fecha", "Recogido", "Origen"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        deviceTable = new JTable(tableModel);

        deviceTable.setFont(new Font("Arial", Font.PLAIN, 14));
        deviceTable.setGridColor(Color.LIGHT_GRAY);
        deviceTable.setSelectionBackground(new Color(220, 240, 255));
        deviceTable.setRowHeight(25);

        JTableHeader header = deviceTable.getTableHeader();
        header.setBackground(Color.decode("#c6f0fe"));  
        header.setFont(new Font("Arial", Font.BOLD, 14));  
        header.setForeground(Color.BLACK);  

        DefaultTableCellRenderer headerRenderer = new DefaultTableCellRenderer();
        headerRenderer.setHorizontalAlignment(JLabel.CENTER);
        headerRenderer.setBackground(Color.decode("#bf98a0"));
        headerRenderer.setForeground(Color.BLACK);
        header.setDefaultRenderer(headerRenderer);

        JScrollPane tableScrollPane = new JScrollPane(deviceTable);
        tablePanel.add(tableScrollPane, BorderLayout.CENTER);
        return tablePanel;
    }


    private JPanel createSidePanel(ActionListener listener) {
        sidePanel = new JPanel(new GridLayout(3, 1, 100, 100)); 
        sidePanel.setBackground(Color.WHITE);

        addButton = createStyledButton("Agregar", listener, Actions.ADD.toString());
        editButton = createStyledButton("Editar", listener, Actions.EDIT.toString());
        deleteButton = createStyledButton("Eliminar", listener, Actions.DELETE.toString());

        sidePanel.add(addButton);
        sidePanel.add(editButton);
        sidePanel.add(deleteButton);

        return sidePanel;
    }

    private JPanel createSouthPanel(ActionListener listener) {
        southPanel = new JPanel(new GridLayout(1, 4, 10, 10));
        southPanel.setBackground(Color.WHITE);

        newTypeButton = createStyledButton("Nuevo Tipo de Equipo", listener, Actions.NEW_TYPE.toString());
        newDeviceButton = createStyledButton("Nuevo Equipo", listener, Actions.NEW_DEVICE.toString());
        assignButton = createStyledButton("Asignación", listener, Actions.ASSIGN.toString());
        collectButton = createStyledButton("Recogida", listener, Actions.COLLECT.toString());

        southPanel.add(newTypeButton);
        southPanel.add(newDeviceButton);
        southPanel.add(assignButton);
        southPanel.add(collectButton);

        return southPanel;
    }

    private JButton createStyledButton(String text, ActionListener listener, String actionCommand) {
        JButton button = new JButton(text);
        Font buttonFont = new Font("Arial", Font.PLAIN, 14);
        Color buttonColor = new Color(60, 120, 180);
        Color textColor = Color.WHITE;

        button.setFont(buttonFont);
        button.setBackground(buttonColor);
        button.setForeground(textColor);
        button.setFocusPainted(false);
        button.setOpaque(true);
        button.setActionCommand(actionCommand);
        button.addActionListener(listener);
        return button;
    }

  

    public JTextField getNameField() {
        return nameField;
    }

    public JTextField getGuideField() {
        return guideField;
    }

    public JComboBox<String> getStatusCombo() {
        return statusCombo;
    }

    public JDateChooser getDatePicker() {
        return datePicker;
    }

    public JDateChooser getEndDatePicker() {
        return endDatePicker;
    }

    public JComboBox<String> getCollectedCombo() {
        return collectedCombo;
    }

    public JTextField getOriginField() {
        return originField;
    }

    public JTable getDeviceTable() {
        return deviceTable;
    }

    public JButton getSearchButton() {
        return searchButton;
    }

    public JButton getAddButton() {
        return addButton;
    }

    public JButton getEditButton() {
        return editButton;
    }

    public JButton getDeleteButton() {
        return deleteButton;
    }

    public JButton getNewTypeButton() {
        return newTypeButton;
    }

    public JButton getNewDeviceButton() {
        return newDeviceButton;
    }

    public JButton getAssignButton() {
        return assignButton;
    }

    public JButton getCollectButton() {
        return collectButton;
    }

    public void setVisiblePrealertEntry(boolean visible) {
        prealertEntryView.setVisibleFrame(visible);
    }
    
    public void setVisibleNewType(boolean visible) {
        deviceTypeEntryView.setVisbleDeviceType(visible);
    }
    
    public void setVisibleNewDevice(boolean visible) {
        deviceEntryView.setVisbleNewDevice(visible);
    }
    
    public void setVisibleDeviceAssignment(boolean visible) {
        deviceAssignmentView.setVisbleDeviceAssignment(visible);
    }
    
    public void setVisibleCollection(boolean visible) {
        collectionView.setVisibleCollection(visible);
    }
    
    public JButton getSubmitButton() {
        return prealertEntryView.getSubmitButton();
    }
}
