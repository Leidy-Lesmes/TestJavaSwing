package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Prealert {
	
	private int id;
	private String name;
	private String status;
	private String guide;
	private Date creationDate;
	private String collected;
	private String origin;
	private ArrayList<Device> assignedDevices;

	public Prealert(int id, String name, String status2, String guide, Date creationDate, String collected, String origin) {
        this.id = id;
        this.name = name;
        this.status = status2;
        this.guide = guide;
        this.creationDate = creationDate;
        this.collected = collected;
        this.origin = origin;
        this.assignedDevices = new ArrayList<>();
    }
	
	// asignacion de equipos 
	// carga excel 
	// uno a uno en form 
	

	public Prealert() {
		// TODO Auto-generated constructor stub
	}

	public void assignDevice(Device device) {
        if (device != null && validateLong(device)) {
            assignedDevices.add(device);
            System.out.println("Equipo asignado correctamente a la prealerta.");
        } else {
            System.out.println("Error en la asignación: Serial o MAC no válido.");
        }
    }
	
	
	 private boolean validateLong(Device device) {
        return device.getSerial().length() == device.getType().getSerialLength() && 
               device.getMac().length() == device.getType().getMacLength();
    }
	 
	 public int getId() {
	        return id;
	    }

	    public String getName() {
	        return name;
	    }

	    public String getStatus() {
	        return status;
	    }

	    public String getGuide() {
	        return guide;
	    }

	    public Date getCreationDate() {
	        return creationDate;
	    }

	    public List<Device> getAssignedDevices() {
	        return assignedDevices;
	    }

	    public void setStatus(String status) {
	        this.status = status;
	    }

	    public void endPrealert() {
	        this.status = "CARGADO";
	        System.out.println("Prealerta finalizada.");
	    }

		public String getCollected() {
			return collected;
		}

		public String getOrigin() {
			return origin;
		}
}

