package com.jackass.RestAPI.repository;

import com.jackass.RestAPI.entity.Group;

import java.util.Set;

public interface GroupRepository {

    Group getGroupById(int id);

    Set<Group> findAll();

    Group save(Group group);

    void delete(Group group);

}
