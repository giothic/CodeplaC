package codeplac.codeplac.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import codeplac.codeplac.Exception.Excecao;
import codeplac.codeplac.Model.GroupModel;
import codeplac.codeplac.Repository.GroupRepository;

@Service
public class GroupService {

    @Autowired
    private GroupRepository groupRepository;

    public GroupModel createGroup(GroupModel groupModel) {
        return groupRepository.save(groupModel);
    }

    public List<GroupModel> getAllGroups() {
        return groupRepository.findAll();
    }

    public GroupModel getGroupById(int id) throws Excecao {
        Optional<GroupModel> group = groupRepository.findById(id);
        if (group.isPresent()) {
            return group.get();
        }

        throw new Excecao("Grupo não encontrado com id: " + id);
    }

    public GroupModel updateGroup(int id, GroupModel groupModel) throws Excecao {
        Optional<GroupModel> optionalGroup = groupRepository.findById(id);

        if (optionalGroup.isPresent()) {
            GroupModel existingGroup = optionalGroup.get();

            if (groupModel.getDataInscricao() != null)
                existingGroup.setDataInscricao(groupModel.getDataInscricao());
            if (groupModel.getMembros() != null)
                existingGroup.setMembros(groupModel.getMembros());
            if (groupModel.getNomeEquipe() != null)
                existingGroup.setNomeEquipe(groupModel.getNomeEquipe());
            if (groupModel.getNomeLider() != null)
                existingGroup.setNomeLider(groupModel.getNomeLider());

            return groupRepository.save(existingGroup);
        } else {
            throw new Excecao("Grupo não encontrado com id: " + id);
        }
    }

    public boolean deleteGroup(int id) throws Excecao {
        if (groupRepository.existsById(id)) {
            groupRepository.deleteById(id);
            return true;
        }

        throw new Excecao("Grupo não encontrado com id: " + id);
    }
}