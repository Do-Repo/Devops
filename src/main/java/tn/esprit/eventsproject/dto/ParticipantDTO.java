package tn.esprit.eventsproject.dto;

import java.io.Serializable;
import java.util.Set;

public class ParticipantDTO implements Serializable{
    private int idPart;
    private String nom;
    private String prenom;
    private String tache;
    private Set<Integer> eventIds;
    public int getIdPart() {
        return idPart;
    }
    public void setIdPart(int idPart) {
        this.idPart = idPart;
    }
    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
    public String getPrenom() {
        return prenom;
    }
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
    public String getTache() {
        return tache;
    }
    public void setTache(String tache) {
        this.tache = tache;
    }
    public Set<Integer> getEventIds() {
        return eventIds;
    }
    public void setEventIds(Set<Integer> eventIds) {
        this.eventIds = eventIds;
    }
}
