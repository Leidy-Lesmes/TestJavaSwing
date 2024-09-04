package controller;

import model.DeviceType;
import model.DeviceTypeDAO;
import model.Prealert;
import model.PrealertDAO;
import viewer.DeviceTypeEntryView;
import viewer.PrealertView;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.List;

public class Control implements ActionListener {

    private PrealertView view;
    private PrealertDAO prealertDAO;
    private DefaultTableModel tableModel;
    private DeviceTypeDAO deviceTypeDAO;
    private DeviceTypeEntryView deviceTypeEntryView;

    public Control(PrealertView view) {
        this.view = view;
        this.prealertDAO = new PrealertDAO();
        this.tableModel = (DefaultTableModel) view.getDeviceTable().getModel(); // Inicialización del tableModel
        populateTable();

        view.getSearchButton().addActionListener(this);
        view.getAddButton().addActionListener(this);
        view.getEditButton().addActionListener(this);
        view.getDeleteButton().addActionListener(this);
        view.getNewTypeButton().addActionListener(this);
        view.getNewDeviceButton().addActionListener(this);
        view.getAssignButton().addActionListener(this);
        view.getCollectButton().addActionListener(this);
        view.getSubmitButton().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Actions actions = Actions.valueOf(e.getActionCommand());
        switch (actions) {
            case SEARCH:
                performSearch();
                break;
            case ADD:
                performAdd();
                break;
            case EDIT:
                performEdit();
                break;
            case DELETE:
                performDelete();
                break;
            case NEW_TYPE:
                performNewType();
                break;
            case NEW_DEVICE:
                performNewDevice();
                break;
            case ASSIGN:
                performAssign();
                break;
            case COLLECT:
                performCollect();
                break;
            case SUBMIT_PREALERT:
            	String name = view.getNameField().getText();
                String guide = view.getGuideField().getText();
                String status = (String) view.getStatusCombo().getSelectedItem();
                Date creationDate = view.getDatePicker().getDate();
                String collected = (String) view.getCollectedCombo().getSelectedItem();
                String origin = view.getOriginField().getText();
                
                if (creationDate == null) {
                    JOptionPane.showMessageDialog(view, "La fecha de creación es obligatoria.");
                    return; 
                }
                
                Prealert prealert = new Prealert(0, name, guide, status, creationDate, collected, origin);
                
                prealertDAO.insertPrealert(prealert);
                
                populateTable();
                break;
                
            case ACCEPT_NEW_TYPE:
            	System.out.println("se guardo");
            	this.performedInserNewType(e);
            	break;
            	
            case CLOSE_NEW_TYPE:
            	System.out.println(" cerro");
            	break;
        }
    }

    private void performSearch() {
        String name = view.getNameField().getText().trim();
        String guide = view.getGuideField().getText().trim();
        String status = (String) view.getStatusCombo().getSelectedItem();
        Date startDate = view.getDatePicker().getDate();
        Date endDate = view.getEndDatePicker().getDate();

        List<Prealert> prealerts = prealertDAO.searchPrealerts(name, guide, status, startDate, endDate);

        tableModel.setRowCount(0);

        for (Prealert prealert : prealerts) {
            tableModel.addRow(new Object[]{
                    prealert.getId(),
                    prealert.getName(),
                    prealert.getGuide(),
                    prealert.getStatus(),
                    prealert.getCreationDate(),
                    prealert.getCollected(),
                    prealert.getOrigin()
            });
        }
    }

    private void performAdd() {
        view.setVisiblePrealertEntry(true); 
    }

    private void performEdit() {
        int selectedRow = view.getDeviceTable().getSelectedRow();
        if (selectedRow >= 0) {
            int id = (int) tableModel.getValueAt(selectedRow, 0);
            String name = view.getNameField().getText();
            String status = (String) view.getStatusCombo().getSelectedItem();
            String guide = view.getGuideField().getText();
            Date creationDate = view.getDatePicker().getDate();
            String collected = (String) view.getCollectedCombo().getSelectedItem();
            String origin = view.getOriginField().getText();

            Prealert prealert = new Prealert(id, name, status, guide, creationDate, collected, origin);
            prealertDAO.updatePrealert(prealert);
            populateTable();
        } else {
            JOptionPane.showMessageDialog(view, "Por favor, seleccione una prealerta para editar.");
        }
    }

    private void performDelete() {
        int selectedRow = view.getDeviceTable().getSelectedRow();
        if (selectedRow >= 0) {
            int id = (int) tableModel.getValueAt(selectedRow, 0);
            prealertDAO.deletePrealert(id);
            populateTable(); 
        } else {
            JOptionPane.showMessageDialog(view, "Por favor, seleccione una prealerta para eliminar.");
        }
    }
    

    public void performedInserNewType(ActionEvent e) {
    	System.out.println("entree");
        String actionCommand = e.getActionCommand();
        
        if (Actions.ACCEPT_NEW_TYPE.toString().equals(actionCommand)) {

            DeviceTypeEntryView deviceTypeEntryView = (DeviceTypeEntryView) e.getSource();
            
            String name = deviceTypeEntryView.getNameField().getText();
            int serialLength = Integer.parseInt(deviceTypeEntryView.getSerialLengthField().getText());
            int macLength = Integer.parseInt(deviceTypeEntryView.getMacLengthField().getText());

            DeviceType newDeviceType = new DeviceType(name, serialLength, macLength);

            deviceTypeDAO.insertDeviceType(newDeviceType);
            
            deviceTypeEntryView.setVisible(false);
        }
    }


    private void performNewType() {
        view.setVisibleNewType(true);
    }

    private void performNewDevice() {
        view.setVisibleNewDevice(true);
    }

    private void performAssign() {
        view.setVisibleDeviceAssignment(true);
    }

    private void performCollect() {
        view.setVisibleCollection(true);
    }

    private void populateTable() {
        tableModel.setRowCount(0); 

        List<Prealert> prealerts = prealertDAO.getAllPrealerts();
        for (Prealert prealert : prealerts) {
            tableModel.addRow(new Object[]{
                    prealert.getId(),
                    prealert.getName(),
                    prealert.getGuide(),
                    prealert.getStatus(),
                    prealert.getCreationDate(),
                    prealert.getCollected(),
                    prealert.getOrigin()
            });
        }
    }
}
