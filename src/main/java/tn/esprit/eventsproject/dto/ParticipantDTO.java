package tn.esprit.eventsproject.dto;

import java.io.Serializable;
import java.util.Set;

public class ParticipantDTO implements Serializable{
    public int idPart;
    public String nom;
    public String prenom;
    public String tache;
    public Set<Integer> eventIds;
}
