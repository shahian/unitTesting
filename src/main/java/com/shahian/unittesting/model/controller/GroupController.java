package com.shahian.unittesting.model.controller;

import com.shahian.unittesting.model.entity.Group;
import com.shahian.unittesting.model.service.impl.GroupServiceImpl;
import com.shahian.unittesting.model.service.intrface.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class GroupController {

    @Autowired
    protected GroupService groupService;

    @GetMapping(value = "/v1/groups", name = "${service.group.getAll}")
    public List<Group> getAllGroups() {
        List<Group> groups = groupService.getAllGroups();
        return groups;
    }

    @GetMapping(value = "/v1/group", name = "${service.group.get}")
    public ResponseEntity<?> getGroup(@RequestParam Long id) {
        Group group= groupService.getGroupById(id);
        return new ResponseEntity<>(group, HttpStatus.OK);
    }
    @GetMapping(value = "/v1/groupTitle", name = "${service.group.get}")
    public ResponseEntity<?> getGroupByTitle() {
        Group group= groupService.getGroupByTitle("title");
        return new ResponseEntity<>(group, HttpStatus.OK);
    }

    @PostMapping(value = "/v1/group", name = "${service.group.addGroup}")
    public ResponseEntity<?> addGroup(@RequestBody Group groupDTO) {
       Boolean aBoolean=  groupService.addGroup(groupDTO);
        return new ResponseEntity<>("ok", HttpStatus.OK);
    }

    @PutMapping(value = "/v1/group", name = "${service.group.updateGroup}")
    public ResponseEntity<?> updateGroup(@RequestBody Group groupDTO, @RequestParam Long id) {
        groupService.updateGroup(groupDTO, id);
        return new ResponseEntity<>("ResponseEntity.ok()", HttpStatus.OK);
    }

    @DeleteMapping(value = "/v1/group", name = "${service.group.deleteGroup}")
    public ResponseEntity<?> deleteGroup(@RequestParam Long id) {
         groupService.deleteGroup(id);
        return new ResponseEntity<>("ResponseEntity.ok()", HttpStatus.OK);
    }
}
