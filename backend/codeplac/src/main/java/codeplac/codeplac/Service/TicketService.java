package codeplac.codeplac.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import codeplac.codeplac.Exception.Excecao;
import codeplac.codeplac.Model.TicketModel;
import codeplac.codeplac.Repository.TicketRepository;

@Service
public class TicketService {

  @Autowired
  private TicketRepository ticketRepository;

  public TicketModel createTicket(TicketModel ticketModel) {
    return ticketRepository.save(ticketModel);
  }

  public List<TicketModel> getAllTickets() {
    return ticketRepository.findAll();
  }

  public TicketModel getTicketById(int id) throws Excecao {
    Optional<TicketModel> ticket = ticketRepository.findById(id);
    if (ticket.isPresent()) {
      return ticket.get();
    }

    throw new Excecao("Ticket não encontrado com id: " + id);
  }

  public TicketModel updateTicket(int id, TicketModel ticket) throws Excecao {
    if (ticketRepository.existsById(id)) {
      ticket.setEvento(ticket.getEvento());
      ticket.setValidado(ticket.getValidado());
      return ticketRepository.save(ticket);
    } else {
      throw new Excecao("Ticket não encontrado com id: " + id);
    }
  }

  public boolean deleteTicket(int id) throws Excecao {
    if (ticketRepository.existsById(id)) {
      ticketRepository.deleteById(id);
    }

    throw new Excecao("Ticket não encontrado com id: " + id);
  }
}
