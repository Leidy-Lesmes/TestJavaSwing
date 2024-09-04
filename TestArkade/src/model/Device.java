package model;

public class Device {

    private String name;
    private DeviceType type;
    private String serial;
    private String mac;
    private String observations;
    
    
    public Device(String name, DeviceType type, String serial, String mac, String observations) {
        this.name = name;
        this.type = type;
        this.serial = serial;
        this.mac = mac;
        this.observations = observations;
    }
    
    public String getName() {
        return name;
    }

    public DeviceType getType() {
        return type;
    }

    public String getSerial() {
        return serial;
    }

    public String getMac() {
        return mac;
    }

    public String getObservaciones() {
        return observations;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }
   
	// NOTA  el serial y Mac debe validar el largo 
    //de caracteres según Tipo de equipo
}
