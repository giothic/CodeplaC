package codeplac.codeplac.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import codeplac.codeplac.Exception.Excecao;
import codeplac.codeplac.Model.EventModel;
import codeplac.codeplac.Repository.EventRepository;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    public EventModel createEvent(EventModel eventModel) {
        return eventRepository.save(eventModel);
    }

    public List<EventModel> getAllEvents() {
        return eventRepository.findAll();
    }

    public EventModel getEventById(int id) throws Excecao {
        Optional<EventModel> event = eventRepository.findById(id);
        if (event.isPresent()) {
            return event.get();
        }

        throw new Excecao("Evento não encontrado com id: " + id);
    }

    public EventModel updateEvent(int id, EventModel eventModel) throws Excecao {
        Optional<EventModel> optionalEvent = eventRepository.findById(id);

        if (optionalEvent.isPresent()) {
            EventModel existingEvent = optionalEvent.get();

            if (eventModel.getAno() != null)
                existingEvent.setAno(eventModel.getAno());
            if (eventModel.getBimestre() != null)
                existingEvent.setBimestre(eventModel.getBimestre());
            if (eventModel.getDataEvento() != null)
                existingEvent.setDataEvento(eventModel.getDataEvento());
            if (eventModel.getLugar() != null)
                existingEvent.setLugar(eventModel.getLugar());
            if (eventModel.getPeriodo() != null)
                existingEvent.setPeriodo(eventModel.getPeriodo());
            if (eventModel.getTipoEvento() != null)
                existingEvent.setTipoEvento(eventModel.getTipoEvento());

            return eventRepository.save(existingEvent);
        } else {
            throw new Excecao("Evento não encontrado com id: " + id);
        }
    }

    public boolean deleteEvent(int id) throws Excecao {
        if (eventRepository.existsById(id)) {
            eventRepository.deleteById(id);
            return true;
        }

        throw new Excecao("Evento não encontrado com id: " + id);
    }
}