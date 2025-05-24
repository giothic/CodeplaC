package codeplac.codeplac.Repository;

import codeplac.codeplac.Model.JuizCodigoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JuizCodigoRepository extends JpaRepository<JuizCodigoModel, Long> {
}