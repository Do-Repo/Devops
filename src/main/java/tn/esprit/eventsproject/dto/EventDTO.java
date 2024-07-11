package tn.esprit.eventsproject.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

public class EventDTO implements Serializable {
    public int idEvent;
    public String description;
    public LocalDate dateDebut;
    public LocalDate dateFin;
    public float cout;
    public Set<Integer> participantIds;
    public Set<Integer> logisticIds;
}
