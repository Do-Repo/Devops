package tn.esprit.eventsproject.dto;

import java.io.Serializable;

public class LogisticsDTO implements Serializable {
    public int idLog;
    public String description;
    public boolean reserve;
    public float prixUnit;
    public int quantite;
}