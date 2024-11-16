package codeplac.codeplac.Repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

import codeplac.codeplac.Model.UsersModel;

public interface UsersRepository extends JpaRepository<UsersModel, String> {

    Optional<UsersModel> findByMatricula(String matricula);

}