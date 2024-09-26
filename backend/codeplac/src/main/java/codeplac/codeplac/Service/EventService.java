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
        if (eventRepository.existsById(id)) {
            eventModel.setData(eventModel.getData());
            eventModel.setNome(eventModel.getNome());
            eventModel.setDescription(eventModel.getDescription());
            eventModel.setLocal(eventModel.getLocal());
            eventModel.setTipo(eventModel.getTipo());
            eventModel.setStatus(eventModel.getStatus());

            return eventRepository.save(eventModel);
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