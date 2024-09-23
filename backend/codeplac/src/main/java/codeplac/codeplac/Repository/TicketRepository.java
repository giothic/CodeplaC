package codeplac.codeplac.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import codeplac.codeplac.Model.ticketModel;

public interface ticketRepository extends JpaRepository<ticketModel, Integer> {

}