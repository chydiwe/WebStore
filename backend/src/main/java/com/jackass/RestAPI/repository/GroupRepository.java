package com.jackass.RestAPI.repository;

import com.jackass.RestAPI.entity.Group;
import org.springframework.data.repository.RepositoryDefinition;

import java.util.Set;

@RepositoryDefinition(domainClass = Group.class, idClass = Integer.class)
public interface GroupRepository {

    Group getGroupById(int id);

    Set<Group> findAll();

    Group save(Group group);

    void delete(Group group);

}
