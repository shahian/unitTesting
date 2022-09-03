package com.shahian.unittesting.model.service.intrface;

import com.shahian.unittesting.model.entity.Group;
import com.sun.org.apache.xpath.internal.operations.Bool;

import java.util.List;

public interface GroupService {
    List<Group> getAllGroups();
    Group getGroupById(Long id);
    Group getGroupByTitle(String title);
    Boolean addGroup(Group group);
    void updateGroup(Group group,Long id);
    void deleteGroup(Long id);
}
