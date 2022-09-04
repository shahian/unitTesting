package com.shahian.unittesting.controller;

import com.shahian.unittesting.model.controller.GroupController;
import com.shahian.unittesting.model.entity.Group;
import com.shahian.unittesting.model.service.impl.GroupServiceImpl;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.OngoingStubbing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@RunWith(SpringRunner.class)
public class GroupControllerTest {
    @InjectMocks
    GroupController groupController;
    @Mock
    GroupServiceImpl groupService;
    @Mock
    private RestTemplate restTemplate;

    @Test
    public void findAllTest() {
        // given
        List<Group> groups = new ArrayList<>();
        groups.add(new Group("Add new Group1", "desc Group1"));
        groups.add(new Group("Add new Group2", "desc Group2"));
        groups.add(new Group("Add new Group3", "desc Group3"));
        groups.add(new Group("Add new Group4", "desc Group4"));
        when(groupService.getAllGroups()).thenReturn(groups);
        //when
        List<Group> groupList = groupController.getAllGroups();
        //then
        Assertions.assertEquals(4, groupList.size());
    }

    @Test
    public void getGroupByTitleTest() {
        //given
        String title = "Group1";
        Group group = new Group("Group1", "desc Group1");
        HttpHeaders header = new HttpHeaders();
        header.setContentType(MediaType.APPLICATION_JSON);

        ResponseEntity<?> responseEntity = new ResponseEntity<>(
                group,
                header,
                HttpStatus.OK
        );
        //when(groupController.getGroupByTitle(title)).thenReturn(responseEntity);
        doReturn(responseEntity).when(groupController.getGroupByTitle(title)).getStatusCode();
        Assertions.assertEquals(title, group.getTitle());

    }

    @Test
    public void whenGetGroupByTitle_shouldReturnMockedObject() {
        String title = "Group1";
        Group group = new Group("Group1", "desc Group1");
        Mockito
                .when(restTemplate.getForEntity(
                        "http://localhost:8086/api/v1/groupTitle/Group1”", Group.class))
                .thenReturn(new ResponseEntity(group, HttpStatus.OK));

        Group groupByTitle = groupService.getGroupByTitle(title);
        Assertions.assertEquals(group, groupByTitle);

    }

    @Test
    public void addGroupTest() {
        //when
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        ;
        when(groupService.addGroup(any(Group.class))).thenReturn(true);
//        when(groupController.addGroup(any(Group.class))).thenReturn(true);

        //given
        Group group = new Group("test", "desc ..");
        ResponseEntity<Group> responseEntity = (ResponseEntity<Group>) groupController.addGroup(group);
        //then
        Assertions.assertEquals(200, responseEntity.getStatusCodeValue());
    }


}
