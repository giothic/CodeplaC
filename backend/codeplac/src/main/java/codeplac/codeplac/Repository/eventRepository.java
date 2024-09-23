package codeplac.codeplac.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import codeplac.codeplac.Model.eventModel;

public interface eventRepository extends JpaRepository<eventModel, Integer> {

}