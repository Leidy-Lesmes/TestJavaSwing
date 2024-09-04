package viewer;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import java.awt.*;
import java.io.File;
import javax.swing.filechooser.FileNameExtensionFilter;

public class DeviceAssignmentView extends JFrame {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JComboBox<String> prealertComboBox;
    private JButton fileButton, oneByOneButton;
    private JTable assignmentTable;
    private DefaultTableModel tableModel;
    private JPanel inputPanel;
    private JScrollPane tableScrollPane;

    public DeviceAssignmentView() {
        this.initComponents();
        this.setTitle("Asignación de Equipos");
        this.setSize(600, 400);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    private void initComponents() {
        setLayout(new BorderLayout());

        inputPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        inputPanel.setBackground(Color.WHITE); 
        
        inputPanel.add(new JLabel("Seleccionar Prealerta:"));
        prealertComboBox = new JComboBox<>(new String[]{"Prealerta 1", "Prealerta 2", "Prealerta 3"});
        prealertComboBox.setFont(new Font("Arial", Font.PLAIN, 14));
        inputPanel.add(prealertComboBox);

        inputPanel.add(new JLabel("Seleccionar archivo:"));
        fileButton = createStyledButton("Seleccionar archivo");
        inputPanel.add(fileButton);

        oneByOneButton = createStyledButton("Uno a uno");
        inputPanel.add(oneByOneButton);

        add(inputPanel, BorderLayout.NORTH);

        String[] columnNames = {"ID", "Serial", "MAC", "Tipo", "Observaciones"};
        tableModel = new DefaultTableModel(columnNames, 0);
        assignmentTable = new JTable(tableModel);
        assignmentTable.setFont(new Font("Arial", Font.PLAIN, 14));
        assignmentTable.setGridColor(Color.LIGHT_GRAY);
        assignmentTable.setSelectionBackground(new Color(220, 240, 255));
        assignmentTable.setRowHeight(25);

        JTableHeader header = assignmentTable.getTableHeader();
        header.setBackground(Color.decode("#c6f0fe"));  
        header.setFont(new Font("Arial", Font.BOLD, 14));  
        header.setForeground(Color.BLACK);  

        DefaultTableCellRenderer headerRenderer = new DefaultTableCellRenderer();
        headerRenderer.setHorizontalAlignment(JLabel.CENTER);
        headerRenderer.setBackground(Color.decode("#bf98a0"));
        headerRenderer.setForeground(Color.BLACK);
        header.setDefaultRenderer(headerRenderer);

        tableScrollPane = new JScrollPane(assignmentTable);
        add(tableScrollPane, BorderLayout.CENTER);

        fileButton.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileFilter(new FileNameExtensionFilter("Archivos CSV", "csv"));
            int result = fileChooser.showOpenDialog(null);
            if (result == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                System.out.println("Archivo seleccionado: " + selectedFile.getAbsolutePath());
            }
        });

        oneByOneButton.addActionListener(e -> {
            System.out.println("Asignar equipos uno a uno");
        });
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

    public JComboBox<String> getPrealertComboBox() {
        return prealertComboBox;
    }

    public JButton getFileButton() {
        return fileButton;
    }

    public JButton getOneByOneButton() {
        return oneByOneButton;
    }

    public JTable getAssignmentTable() {
        return assignmentTable;
    }

    public DefaultTableModel getTableModel() {
        return tableModel;
    }
    
    public void setVisbleDeviceAssignment(boolean visible) {
    	this.setVisible(visible);
    }
}
