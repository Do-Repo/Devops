package tn.esprit.eventsproject.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

public class EventDTO implements Serializable {
    private int idEvent;
    private String description;
    private LocalDate dateDebut;
    private LocalDate dateFin;
    private float cout;
    private Set<Integer> participantIds;
    private Set<Integer> logistics;
    public int getIdEvent() {
        return idEvent;
    }
    public void setIdEvent(int idEvent) {
        this.idEvent = idEvent;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public LocalDate getDateDebut() {
        return dateDebut;
    }
    public void setDateDebut(LocalDate dateDebut) {
        this.dateDebut = dateDebut;
    }
    public LocalDate getDateFin() {
        return dateFin;
    }
    public void setDateFin(LocalDate dateFin) {
        this.dateFin = dateFin;
    }
    public float getCout() {
        return cout;
    }
    public void setCout(float cout) {
        this.cout = cout;
    }
    public Set<Integer> getParticipantIds() {
        return participantIds;
    }
    public void setParticipantIds(Set<Integer> participantIds) {
        this.participantIds = participantIds;
    }
    public Set<Integer> getLogistics() {
        return logistics;
    }
    public void setLogistics(Set<Integer> logistics) {
        this.logistics = logistics;
    }

}
