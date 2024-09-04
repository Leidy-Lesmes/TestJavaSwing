package viewer;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import java.awt.*;

public class CollectionView extends JFrame {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int totalToCollect = 10;
    private int collectedCount = 0;
    private JLabel counterLabel;
    private JTextField serialField, macField;
    private JButton collectButton;
    private JTable collectionTable;
    private DefaultTableModel tableModel;

    public CollectionView() {
        this.initComponents();
        this.setTitle("Módulo de Recogidas");
        this.setSize(600, 400);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    private void initComponents() {
        setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        inputPanel.setBackground(Color.WHITE);

        inputPanel.add(new JLabel("Serial:"));
        serialField = new JTextField();
        serialField.setFont(new Font("Arial", Font.PLAIN, 14));
        inputPanel.add(serialField);

        inputPanel.add(new JLabel("MAC:"));
        macField = new JTextField();
        macField.setFont(new Font("Arial", Font.PLAIN, 14));
        inputPanel.add(macField);

        collectButton = createStyledButton("Recoger");
        inputPanel.add(collectButton);

        counterLabel = new JLabel("Has recogido 0 de 10", JLabel.CENTER);
        counterLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        counterLabel.setForeground(Color.BLACK);
        inputPanel.add(counterLabel);

        add(inputPanel, BorderLayout.NORTH);

        String[] columnNames = {"ID", "Serial", "MAC", "Tipo", "Observaciones"};
        tableModel = new DefaultTableModel(columnNames, 0);
        collectionTable = new JTable(tableModel);
        collectionTable.setFont(new Font("Arial", Font.PLAIN, 14));
        collectionTable.setGridColor(Color.LIGHT_GRAY);
        collectionTable.setSelectionBackground(new Color(220, 240, 255));
        collectionTable.setRowHeight(25);

        JTableHeader header = collectionTable.getTableHeader();
        header.setBackground(Color.decode("#c6f0fe"));  
        header.setFont(new Font("Arial", Font.BOLD, 14)); 
        header.setForeground(Color.BLACK); 

        DefaultTableCellRenderer headerRenderer = new DefaultTableCellRenderer();
        headerRenderer.setHorizontalAlignment(JLabel.CENTER);
        headerRenderer.setBackground(Color.decode("#bf98a0"));
        headerRenderer.setForeground(Color.BLACK);
        header.setDefaultRenderer(headerRenderer);

        JScrollPane tableScrollPane = new JScrollPane(collectionTable);
        add(tableScrollPane, BorderLayout.CENTER);

        collectButton.addActionListener(e -> {
            String serial = serialField.getText();
            String mac = macField.getText();
            if (!serial.isEmpty() && !mac.isEmpty()) {
                tableModel.addRow(new Object[]{tableModel.getRowCount() + 1, serial, mac, "Tipo Ejemplo", "Sin Observaciones"});
                collectedCount++;
                updateCounter();
                serialField.setText("");
                macField.setText("");
            }
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

    private void updateCounter() {
        counterLabel.setText("Has recogido " + collectedCount + " de " + totalToCollect);
    }

    public void setVisibleCollection(boolean visible) {
        this.setVisible(visible);
    }
}
