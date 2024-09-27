package codeplac.codeplac.Repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import codeplac.codeplac.Model.UsersModel;
import jakarta.transaction.Transactional;

public interface UsersRepository extends JpaRepository<UsersModel, Integer> {

    @Modifying
    @Transactional
    @Query("UPDATE UsersModel u SET u.refreshToken = :refreshToken WHERE u.matricula = :matricula")
    void updateRefreshToken(String matricula, String refreshToken);

    Optional<UsersModel> findByMatricula(int matricula);
<<<<<<< Updated upstream:backend/codeplac/src/main/java/codeplac/codeplac/Repository/usersRepository.java
}
=======

    @Modifying
    @Query("UPDATE UsersModel u SET u.refreshToken = :refreshToken, u.accessToken = :accessToken WHERE u.matricula = :matricula")
    void updateTokens(@Param("matricula") String matricula, @Param("refreshToken") String refreshToken,
            @Param("accessToken") String accessToken);


@Modifying
@Query("UPDATE UsersModel u SET u.accessToken = :accessToken WHERE u.matricula = :matricula")
void updateAccessToken(@Param("matricula") String matricula, @Param("accessToken") String accessToken);


}
>>>>>>> Stashed changes:backend/codeplac/src/main/java/codeplac/codeplac/Repository/UsersRepository.java
