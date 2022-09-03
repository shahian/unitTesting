package com.shahian.unittesting.service;

import com.shahian.unittesting.model.entity.Group;
import com.shahian.unittesting.model.repository.GroupRepository;
import com.shahian.unittesting.model.service.impl.GroupServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class GroupServiceTest {
    @InjectMocks
    GroupServiceImpl groupService;
    @Mock
    GroupRepository groupRepository;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void when_findAll_thenReturnGroups() {
        //when
        List<Group> groups = new ArrayList<>();
        groups.add(new Group("Add new Group1", "desc Group1"));
        groups.add(new Group("Add new Group2", "desc Group2"));
        groups.add(new Group("Add new Group3", "desc Group3"));
        groups.add(new Group("Add new Group4", "desc Group4"));
        when(groupRepository.findAll()).thenReturn(groups);
        //given
        List<Group> groupList = groupService.getAllGroups();
        //then
        Assert.assertEquals(4, groupList.size());
        verify(groupRepository, times(1)).findAll();
    }

    @Test
    public void when_findByTitle_thenReturnGroup() {
        //when
        String title = "test";
        when(groupRepository.findByTitle(title)).thenReturn(Optional.of(new Group("test", "desc group...")));
        //given
        Group group = groupService.getGroupByTitle(title);
        //then
        Assertions.assertEquals(title,group.getTitle());
    }
    @Test
    public void createGroupTest(){
        //when
        Group group=new Group("test","desc ...");
        //given
        groupService.addGroup(group);
        //then
        verify(groupRepository,times(1)).save(group);
    }
}
