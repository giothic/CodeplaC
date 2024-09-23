package codeplac.codeplac.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import codeplac.codeplac.Model.registrationModel;

public interface registrationRepository extends JpaRepository<registrationModel, Integer> {

}