package tn.esprit.eventsproject.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import tn.esprit.eventsproject.dto.EventDTO;
import tn.esprit.eventsproject.dto.LogisticsDTO;
import tn.esprit.eventsproject.dto.ParticipantDTO;
import tn.esprit.eventsproject.entities.Event;
import tn.esprit.eventsproject.entities.Logistics;
import tn.esprit.eventsproject.entities.Participant;
import tn.esprit.eventsproject.entities.Tache;
import tn.esprit.eventsproject.repositories.EventRepository;
import tn.esprit.eventsproject.repositories.LogisticsRepository;
import tn.esprit.eventsproject.repositories.ParticipantRepository;
import tn.esprit.eventsproject.services.IEventServices;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RequestMapping("event")
@RestController
public class EventRestController {
    private final IEventServices eventServices;

    private final EventRepository eventRepository;
    private final ParticipantRepository participantRepository;
    private final LogisticsRepository logisticsRepository;

    @PostMapping("/addPart")
    public Participant addParticipant(@RequestBody ParticipantDTO participantDTO){
        Participant participant = new Participant();
        participant.setIdPart(participantDTO.getIdPart());
        participant.setNom(participantDTO.getNom());
        participant.setPrenom(participantDTO.getPrenom());
        participant.setTache(Tache.valueOf(participantDTO.getTache()));

        Set<Event> events = new HashSet<>();
        for (Integer eventId : participantDTO.getEventIds()) {
            eventRepository.findById(eventId).ifPresent(events::add);
        }
        participant.setEvents(events.isEmpty() ? null : events);

        return eventServices.addParticipant(participant);
    }
    @PostMapping("/addEvent/{id}")
    public Event addEventPart(@RequestBody EventDTO eventDTO, @PathVariable("id") int idPart){
        Event event = new Event();
        event.setIdEvent(eventDTO.getIdEvent());
        event.setDescription(eventDTO.getDescription());
        event.setDateDebut(eventDTO.getDateDebut());
        event.setDateFin(eventDTO.getDateFin());
        event.setCout(eventDTO.getCout());

        Set<Participant> participants = new HashSet<>();
        for (Integer participantId : eventDTO.getParticipantIds()) {
            participantRepository.findById(participantId).ifPresent(participants::add);
        }
        event.setParticipants(participants);

        Set<Logistics> logistics = new HashSet<>();
        for (Integer logisticId : eventDTO.getLogistics()) {
            logisticsRepository.findById(logisticId).ifPresent(logistics::add);
        }
        event.setLogistics(logistics);

        return eventServices.addAffectEvenParticipant(event, idPart);
    }
    @PostMapping("/addEvent")
    public Event addEvent(@RequestBody EventDTO eventDTO){
        Event event = new Event();
        event.setIdEvent(eventDTO.getIdEvent());
        event.setDescription(eventDTO.getDescription());
        event.setDateDebut(eventDTO.getDateDebut());
        event.setDateFin(eventDTO.getDateFin());
        event.setCout(eventDTO.getCout());

        Set<Participant> participants = new HashSet<>();
        for (Integer participantId : eventDTO.getParticipantIds()) {
            participantRepository.findById(participantId).ifPresent(participants::add);
        }
        event.setParticipants(participants);

        Set<Logistics> logistics = new HashSet<>();
        for (Integer logisticId : eventDTO.getLogistics()) {
            logisticsRepository.findById(logisticId).ifPresent(logistics::add);
        }
        event.setLogistics(logistics);

        return eventServices.addAffectEvenParticipant(event);
    }
    @PutMapping("/addAffectLog/{description}")
    public Logistics addAffectLog(@RequestBody LogisticsDTO logisticsDTO,@PathVariable("description") String descriptionEvent){
        Logistics logistics = new Logistics();
        logistics.setIdLog(logisticsDTO.getIdLog());
        logistics.setDescription(logisticsDTO.getDescription());
        logistics.setReserve(logisticsDTO.isReserve());
        logistics.setPrixUnit(logisticsDTO.getPrixUnit());
        logistics.setQuantite(logisticsDTO.getQuantite());
        return eventServices.addAffectLog(logistics,descriptionEvent);
    }
    @GetMapping("/getLogs/{d1}/{d2}")
    public List<Logistics> getLogistiquesDates (@PathVariable("d1") LocalDate date_debut, @PathVariable("d2") LocalDate date_fin){
        return eventServices.getLogisticsDates(date_debut,date_fin);
    }
}
