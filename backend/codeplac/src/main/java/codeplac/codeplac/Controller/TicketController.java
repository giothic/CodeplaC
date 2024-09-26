package codeplac.codeplac.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import codeplac.codeplac.Exception.Excecao;
import codeplac.codeplac.Model.TicketModel;
import codeplac.codeplac.Service.TicketService;

@RestController
@RequestMapping("/ticket")
public class TicketController {

  @Autowired
  TicketService ticketService;

  @PostMapping("/generate")
  public ResponseEntity<TicketModel> criarTicket(@RequestBody TicketModel ticket) {
    return new ResponseEntity<>(ticketService.createTicket(ticket), HttpStatus.CREATED);
  }

  @GetMapping("/list")
  public ResponseEntity<List<TicketModel>> listarTickets() {
    return new ResponseEntity<>(ticketService.getAllTickets(), HttpStatus.OK);
  }

  @GetMapping("/{id}")
  public ResponseEntity<TicketModel> obterTicket(@PathVariable int id) {
    try {
      return new ResponseEntity<>(ticketService.getTicketById(id), HttpStatus.OK);
    } catch (Excecao e) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
    }
  }

  @PutMapping("/modify/{id}")
  public ResponseEntity<TicketModel> modificarTicket(@PathVariable int id, @RequestBody TicketModel ticket) {
    try {
      return new ResponseEntity<>(ticketService.updateTicket(id, ticket), HttpStatus.OK);
    } catch (Excecao e) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
    }
  }

  @DeleteMapping("/destroy/{id}")
  public ResponseEntity<Void> apagarTicket(@PathVariable int id) {
    try {
      boolean deleted = ticketService.deleteTicket(id);
      if (deleted) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      } else {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      }
    } catch (Excecao e) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
    }
  }
}
