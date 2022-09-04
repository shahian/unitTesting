package com.shahian.unittesting.repository;

import com.shahian.unittesting.model.entity.Group;
import com.shahian.unittesting.model.repository.GroupRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class GroupRepositoryTest {
    @Autowired
    GroupRepository groupRepository;
    @Autowired
    TestEntityManager entityManager;

    @Before
    public void contexLoads() {
        Assertions.assertNotNull(entityManager);
    }

    @Test
    public void when_findAll_thenReturnGroups() {
        //when
        List<Group> groups = new ArrayList<>();
        groups.add(new Group("Add new Group1", "desc Group1"));
        groups.add(new Group("Add new Group2", "desc Group2"));
        groups.add(new Group("Add new Group3", "desc Group3"));
        groups.add(new Group("Add new Group4", "desc Group4"));
        //given
        groupRepository.saveAll(groups);
        //then
        Assert.assertEquals(4, groups.size());
    }

    @Test
    public void when_findByTitle_thenReturnGroup_Repository() {
        //when
        String title = "test";
        Group group = Group.builder()
                .title("test")
                .description("this is a test")
                .build();
        groupRepository.save(group);
        //given
        Optional<Group> group1 = groupRepository.findByTitle(title);
        //then
        Assert.assertTrue(group1.isPresent());
        Assert.assertEquals(group1.get().getTitle(), title);

    }

    @Test
    public void verifyRepositoryByPersistingGroup() {
        //when
        Group group = Group.builder()
                .title("test")
                .description("this is a test")
                .build();
        //given
        Assertions.assertNull(group.getId());
        groupRepository.save(group);
        //then
        Assertions.assertNotNull(group.getId());
    }

    @Test
    public void when_findByTitle_thenReturnGroup_Bootstrapping() {
        //Given
        String title = "test";
        Group group = Group.builder()
                .title("test")
                .description("this is a test")
                .build();
        entityManager.persist(group);
        entityManager.flush();
        //When
        Optional<Group> group1 = groupRepository.findByTitle(title);
        //then
        Assertions.assertTrue(group1.isPresent());
        Assertions.assertEquals(group1.get().getTitle(),title);

    }

    @Test
    public void verifyBootstrappingByPersistingGroup() {
        Group group = Group.builder()
                .title("test")
                .description("this is a test")
                .build();
        //given
        Assertions.assertNull(group.getId());
        entityManager.persist(group);
        //then
        Assertions.assertNotNull(group.getId());
    }
}
