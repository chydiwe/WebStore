package com.jackass.RestAPI.controller;

import com.jackass.RestAPI.entity.Group;
import com.jackass.RestAPI.exception.AlreadyExistsException;
import com.jackass.RestAPI.exception.NotFoundException;
import com.jackass.RestAPI.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/users/groups")
@CrossOrigin(origins = "http://localhost:3000")
public class GroupController {

    @Autowired
    private GroupRepository groupRepository;

    //
    //  GET
    //
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Set<Group>> getGroups() {
        Set<Group> groups = groupRepository.findAll();
        return ResponseEntity.ok().body(groups);
    }

    //
    //  POST
    //
    @RequestMapping(method = RequestMethod.POST)
    public void addGroup(@RequestParam String name) {
        Set<Group> groups = groupRepository.findAll();
        for (Group g : groups) {
            if (g.getName().equals(name)) {
                throw new AlreadyExistsException("Such group name already exists.");
            }
        }
        Group group = new Group();
        group.setName(name);
        groupRepository.save(group);
    }

    //
    //  DELETE
    //
    @RequestMapping(method = RequestMethod.DELETE)
    public void deleteGroup(@RequestParam int id) {
        Group group = groupRepository.getGroupById(id);
        if (group == null) {
            throw new NotFoundException("Wrong group ID.");
        }
        groupRepository.delete(group);
    }

}
