package model;

import java.util.ArrayList;
import java.util.List;

public class Collection {
    private Prealert prealert;
    private List<Device> pickedDevices;

    public Collection(Prealert prealert) {
        this.prealert = prealert;
        this.pickedDevices = new ArrayList<>();
    }

    public boolean recogerEquipo(Device device) {
        if (!prealert.getAssignedDevices().contains(device)) {
            System.out.println("El equipo no pertenece a la prealerta seleccionada.");
            return false;
        }

        if (pickedDevices.contains(device)) {
            System.out.println("El equipo ya ha sido recogido previamente.");
            return false;
        }

        if (!validarLargo(device)) {
            System.out.println("El largo del serial o MAC no coincide con el tipo de equipo.");
            return false;
        }

        pickedDevices.add(device);
        System.out.println("Equipo recogido exitosamente.");
        
        if (pickedDevices.size() == prealert.getAssignedDevices().size()) {
            finalizarRecogida();
        }

        return true;
    }

    private boolean validarLargo(Device device) {
        return device.getSerial().length() == device.getType().getSerialLength() &&
               device.getMac().length() == device.getType().getMacLength();
    }

    public void finalizarRecogida() {
        prealert.endPrealert();
        System.out.println("Recogida finalizada. Todos los equipos han sido recogidos.");
    }

    public int getCantidadRecogida() {
        return pickedDevices.size();
    }

    public int getCantidadAsignada() {
        return prealert.getAssignedDevices().size();
    }

    public Prealert getPrealert() {
        return prealert;
    }

    public void setPrealert(Prealert prealert) {
        this.prealert = prealert;
    }

    public List<Device> getPickedDevices() {
        return pickedDevices;
    }
}
