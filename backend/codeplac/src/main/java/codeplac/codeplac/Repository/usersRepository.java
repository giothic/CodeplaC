package codeplac.codeplac.Repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import codeplac.codeplac.Model.usersModel;
import jakarta.transaction.Transactional;

public interface usersRepository extends JpaRepository<usersModel, Integer> {

    @Modifying
    @Transactional
    @Query("UPDATE UsersModel u SET u.refreshToken = :refreshToken WHERE u.matricula = :matricula")
    void updateRefreshToken(String matricula, String refreshToken);

    Optional<usersModel> findByMatricula(int matricula);
}