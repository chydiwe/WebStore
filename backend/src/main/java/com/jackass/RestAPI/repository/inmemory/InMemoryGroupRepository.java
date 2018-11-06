package com.jackass.RestAPI.repository.inmemory;

import com.jackass.RestAPI.entity.Group;
import com.jackass.RestAPI.repository.GroupRepository;

import java.util.Set;

public class InMemoryGroupRepository extends InMemoryRepository<Group> implements GroupRepository {
    @Override
    public Group getGroupById(int id) {
        return table.stream().filter(group -> group.getId() == id).findFirst().orElse(null);
    }

    @Override
    public Set<Group> findAll() {
        return table;
    }

    @Override
    public Group save(Group group) {
        group.setId(id++);
        table.add(group);
        return group;
    }

    @Override
    public void delete(Group group) {
        table.remove(group);
    }
}
