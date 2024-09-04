package model;


public class DeviceType {
    private String name;
    private int serialLength;
    private int macLength;

    public DeviceType(String name, int serialLength, int macLength) {
        this.name = name;
        this.serialLength = serialLength;
        this.macLength = macLength;
    }

    public String getName() {
        return name;
    }

    public int getSerialLength() {
        return serialLength;
    }

    public int getMacLength() {
        return macLength;
    }

    public void setSerialLength(int serialLength) {
        this.serialLength = serialLength;
    }

    public void setMacLength(int macLength) {
        this.macLength = macLength;
    }
}
