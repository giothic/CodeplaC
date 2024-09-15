package codeplac.codeplac.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import codeplac.codeplac.Model.usersModel;

public interface usersRepository extends JpaRepository<usersModel, Integer> {

}
