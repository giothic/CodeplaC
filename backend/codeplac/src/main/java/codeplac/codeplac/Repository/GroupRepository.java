package codeplac.codeplac.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import codeplac.codeplac.Model.GroupModel;

public interface GroupRepository extends JpaRepository<GroupModel, Integer> {

}