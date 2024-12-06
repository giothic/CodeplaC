package codeplac.codeplac.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import codeplac.codeplac.DTO.RequestsDTO.Ticket.TicketRequest;
import codeplac.codeplac.DTO.ResponsesDTO.Ticket.TicketEventDTO;
import codeplac.codeplac.DTO.ResponsesDTO.Ticket.TicketResponse;
import codeplac.codeplac.DTO.ResponsesDTO.Ticket.TicketUserDTO;
import codeplac.codeplac.Exception.Excecao;
import codeplac.codeplac.Model.EventModel;
import codeplac.codeplac.Model.TicketModel;
import codeplac.codeplac.Model.UsersModel;
import codeplac.codeplac.Repository.EventRepository;
import codeplac.codeplac.Repository.TicketRepository;
import codeplac.codeplac.Repository.UsersRepository;

@Service
public class TicketService {

  @Autowired
  private TicketRepository ticketRepository;

  @Autowired
  private EventRepository eventRepository;

  @Autowired
  private UsersRepository usersRepository;

  public TicketResponse createTicket(TicketRequest ticket) {
    EventModel event = eventRepository.findById(ticket.getEvento())
        .orElseThrow(() -> new RuntimeException("Evento não encontrado"));

    UsersModel user = usersRepository.findByMatricula(ticket.getUsuario())
        .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

    TicketModel newTicket = new TicketModel();

    newTicket.setQrCode(ticket.getQrCode());
    newTicket.setValidacao(ticket.getValidacao());
    newTicket.setEvento(event);
    newTicket.setUsuario(user);

    ticketRepository.save(newTicket);

    return createTicketResponse(newTicket, event, user);
  }

  public List<TicketResponse> getAllTickets() {
    List<TicketModel> ticketModelsList = ticketRepository.findAll();
    List<TicketResponse> ticketResponsesList = new ArrayList<>();

    for (TicketModel ticketModel : ticketModelsList) {
      ticketResponsesList.add(createTicketResponse(ticketModel, ticketModel.getEvento(), ticketModel.getUsuario()));
    }

    return ticketResponsesList;
  }

  public TicketResponse getTicketById(int id) throws Excecao {
    Optional<TicketModel> optionalTicket = ticketRepository.findById(id);
    if (optionalTicket.isPresent()) {
      TicketModel existingTicket = optionalTicket.get();

      return createTicketResponse(existingTicket, existingTicket.getEvento(), existingTicket.getUsuario());
    }

    throw new Excecao("Ticket não encontrado com id: " + id);
  }

  public TicketResponse updateTicket(int id, TicketModel ticket) throws Excecao {
    Optional<TicketModel> optionalTicket = ticketRepository.findById(id);

    if (optionalTicket.isPresent()) {
      TicketModel existingTicket = optionalTicket.get();

      if (ticket.getQrCode() != null)
        existingTicket.setQrCode(ticket.getQrCode());
      if (ticket.getValidacao() != null)
        existingTicket.setValidacao(ticket.getValidacao());

      ticketRepository.save(existingTicket);

      return createTicketResponse(existingTicket, existingTicket.getEvento(), existingTicket.getUsuario());
    } else {
      throw new Excecao("Ticket não encontrado com id: " + id);
    }
  }

  public boolean deleteTicket(int id) throws Excecao {
    if (ticketRepository.existsById(id)) {
      ticketRepository.deleteById(id);
      return true;
    }

    throw new Excecao("Ticket não encontrado com id: " + id);
  }

  private TicketResponse createTicketResponse(TicketModel ticket, EventModel event, UsersModel user) {
    TicketEventDTO ticketEventDTO = new TicketEventDTO(event.getIdEvento(), event.getNome(), event.getDescricao(),
        event.getDataEvento());

    TicketUserDTO ticketUserDTO = new TicketUserDTO(user.getMatricula(), user.getNome(), user.getSobrenome(),
        user.getCpf());

    TicketResponse ticketResponse = new TicketResponse(ticket.getIdIngresso(), ticket.getQrCode(),
        ticket.getValidacao(), ticketEventDTO,
        ticketUserDTO);

    return ticketResponse;
  }
}
