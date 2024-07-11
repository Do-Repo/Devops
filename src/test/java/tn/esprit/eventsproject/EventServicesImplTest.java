package tn.esprit.eventsproject;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import com.mysql.cj.x.protobuf.MysqlxDatatypes.Any;

import tn.esprit.eventsproject.entities.Event;
import tn.esprit.eventsproject.entities.Logistics;
import tn.esprit.eventsproject.entities.Participant;
import tn.esprit.eventsproject.entities.Tache;
import tn.esprit.eventsproject.repositories.EventRepository;
import tn.esprit.eventsproject.repositories.LogisticsRepository;
import tn.esprit.eventsproject.repositories.ParticipantRepository;
import tn.esprit.eventsproject.services.EventServicesImpl;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class EventServicesImplTest {

    @Mock
    EventRepository eventRepository;

    @Mock
    ParticipantRepository participantRepository;

    @Mock
    LogisticsRepository logisticsRepository;

    @InjectMocks
    EventServicesImpl eventServices;

    Participant participant = new Participant(1, "Romdhane", "Yasine", Tache.ORGANISATEUR, new HashSet<>());
    Event mockEvent = new Event(1, "Sample Event", LocalDate.of(2024, 7, 5), LocalDate.of(2024, 7, 6), 100.0f, new HashSet<>(), new HashSet<>());
    Logistics mockLogistic = new Logistics(1, "Sample Logistics", true, 10, 10);
    Set<Logistics> mockLogistics = new HashSet<Logistics>() {
        {
            add(new Logistics(2, "Sample 2 Logistics", true, 15, 15));
            add(new Logistics(3, "Sample 3 Logistics", true, 20, 20));

        }
    };

    @BeforeEach
    void setup() {
        eventServices = new EventServicesImpl(eventRepository, participantRepository, logisticsRepository);
    }

    @Test
    public void addParticipant() {

        Mockito.when(participantRepository.save(Mockito.any(Participant.class))).thenReturn(participant);

        Participant result = eventServices.addParticipant(participant);

        assertNotNull(result);
        assertEquals(participant.getIdPart(), result.getIdPart());
        verify(participantRepository, times(1)).save(participant);
    }

    @Test
    public void addAffectEvenParticipant() {
        
        when(participantRepository.findById(anyInt())).thenReturn(Optional.of(participant));
        when(eventRepository.save(mockEvent)).thenReturn(mockEvent);

        Event result = eventServices.addAffectEvenParticipant(mockEvent, 1);
        
        verify(participantRepository).findById(1);
        verify(eventRepository).save(mockEvent);
        assertEquals(result, mockEvent);
    }

    @Test
    public void addAffectLog() {

        when(eventRepository.findByDescription("Sample Event")).thenReturn(mockEvent);
        when(logisticsRepository.save(Mockito.any(Logistics.class))).thenReturn(mockLogistic);

        Logistics result = eventServices.addAffectLog(mockLogistic, "Sample Event");

        assertEquals(result, mockLogistic);
        assertTrue(mockEvent.getLogistics().contains(mockLogistic));
    }

    @Test
    public void getLogisticsDates() {
        LocalDate startDate=  LocalDate.of(2024, 7, 5);
        LocalDate endDate = startDate.plusDays(1);

        Event mockEventWithLogistics = mockEvent;
        mockEventWithLogistics.setLogistics(Set.of(mockLogistic));
        List<Event> events = Collections.singletonList(mockEvent);

        when(eventRepository.findByDateDebutBetween(any(LocalDate.class), any(LocalDate.class))).thenReturn(events);

        List<Logistics> result = eventServices.getLogisticsDates(startDate, endDate);
        
        assertEquals(1, result.size());
        assertEquals(mockLogistic, result.get(0));
        verify(eventRepository, times(1)).findByDateDebutBetween(startDate, endDate);
        verify(logisticsRepository, never()).save(any());
    }

  

}