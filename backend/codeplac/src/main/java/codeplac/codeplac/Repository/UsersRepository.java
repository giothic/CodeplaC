package codeplac.codeplac.Repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import codeplac.codeplac.Model.UsersModel;
import jakarta.transaction.Transactional;

public interface UsersRepository extends JpaRepository<UsersModel, Integer> {

    
    @Modifying
    @Transactional
    @Query("UPDATE UsersModel u SET u.refreshToken = :refreshToken WHERE u.matricula = :matricula")
    void updateRefreshToken(String matricula, String refreshToken);

    Optional<UsersModel> findByMatricula(int matricula);

    @Modifying
    @Transactional
    @Query("UPDATE UsersModel u SET u.accessToken = :accessToken WHERE u.matricula = :matricula")
    void updateAccessToken(String matricula, String accessToken);
}