package com.shahian.unittesting.controller;

import com.shahian.unittesting.model.controller.GroupController;
import com.shahian.unittesting.model.entity.Group;
import com.shahian.unittesting.model.service.impl.GroupServiceImpl;
import com.shahian.unittesting.model.service.intrface.GroupService;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@RunWith(SpringRunner.class)
@WebMvcTest(GroupController.class)
public class GroupControllerTest {
    @Autowired
    private MockMvc mvc;
    @MockBean
    private GroupService groupService;

    @Test
    public void whenGetGroupByTitle_shouldReturnMockedObject() throws Exception {
        String title = "Group1";
        Group group = new Group();
        group.setTitle("Group1");
        group.setDescription("desc Group1");
        given(groupService.getGroupByTitle(title)).willReturn(group);
        mvc.perform(get("/api/v1/groupTitle")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                 ;
//                .andExpect(jsonPath("$.Title").value(title));

    }



}
