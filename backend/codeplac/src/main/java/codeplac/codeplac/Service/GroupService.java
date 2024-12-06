package codeplac.codeplac.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import codeplac.codeplac.DTO.ResponsesDTO.Group.GroupResponse;
import codeplac.codeplac.Exception.Excecao;
import codeplac.codeplac.Model.GroupModel;
import codeplac.codeplac.Repository.GroupRepository;

@Service
public class GroupService {

    @Autowired
    private GroupRepository groupRepository;

    public GroupResponse createGroup(GroupModel groupModel) {
        groupRepository.save(groupModel);

        return createGroupResponse(groupModel);
    }

    public List<GroupResponse> getAllGroups() {
        List<GroupModel> groupsModelsList = groupRepository.findAll();
        List<GroupResponse> groupsResponsesList = new ArrayList<>();

        for (GroupModel group : groupsModelsList) {
            groupsResponsesList.add(createGroupResponse(group));
        }

        return groupsResponsesList;
    }

    public GroupResponse getGroupById(int id) throws Excecao {
        Optional<GroupModel> optionalGroup = groupRepository.findById(id);
        if (optionalGroup.isPresent()) {
            return createGroupResponse(optionalGroup.get());
        }

        throw new Excecao("Grupo não encontrado com id: " + id);
    }

    public GroupResponse updateGroup(int id, GroupModel groupModel) throws Excecao {
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

            groupRepository.save(existingGroup);

            return createGroupResponse(existingGroup);
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

    private GroupResponse createGroupResponse(GroupModel group) {
        GroupResponse groupResponse = new GroupResponse(group.getIdEquipe(), group.getNomeEquipe(),
                group.getNomeLider(), group.getDataInscricao(), group.getMembros());

        return groupResponse;
    }
}