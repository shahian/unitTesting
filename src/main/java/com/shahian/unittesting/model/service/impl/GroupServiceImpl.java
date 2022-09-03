package com.shahian.unittesting.model.service.impl;

import com.shahian.unittesting.model.entity.Group;
import com.shahian.unittesting.model.repository.GroupRepository;
import com.shahian.unittesting.model.service.intrface.GroupService;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class GroupServiceImpl implements GroupService {
    final GroupRepository groupRepository;

    public GroupServiceImpl(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    @Override
    public List<Group> getAllGroups() {
        List<Group>groupList=groupRepository.findAll();

        return  groupList;
    }

    @Override
    public Group getGroupById(Long id) {
        return groupRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Not Found"));
    }

    @Override
    public Group getGroupByTitle(String title) {
        return groupRepository.findByTitle(title)
                .orElseThrow(() -> new RuntimeException("Not Found"));
    }

    @Override
    public Boolean addGroup(Group  groupDTO) {
        Group group=Group.builder()
                .title(groupDTO.getTitle())
                .description(groupDTO.getDescription())
                .build();
        groupRepository.save(group);
        return true;
    }

    @Override
    public void updateGroup(Group groupDTO, Long id) {
        Group  group=groupRepository.findById(id)
                .orElseThrow(()->new  RuntimeException("Not Found"));
        group.setTitle(groupDTO.getTitle());
        group.setDescription(groupDTO.getDescription());
        groupRepository.save(group);

    }

    @Override
    public void deleteGroup(Long id) {
        Group  group=groupRepository.findById(id)
                .orElseThrow(()->new  RuntimeException("Not Found"));
        groupRepository.delete(group);
    }
}
