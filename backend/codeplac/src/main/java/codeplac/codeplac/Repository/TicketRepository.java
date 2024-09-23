package codeplac.codeplac.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import codeplac.codeplac.Model.TicketModel;

public interface TicketRepository extends JpaRepository<TicketModel, Integer> {

}