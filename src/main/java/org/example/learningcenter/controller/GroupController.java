package org.example.learningcenter.controller;

import lombok.RequiredArgsConstructor;
import org.example.learningcenter.entity.dto.group.GroupCreateDto;
import org.example.learningcenter.entity.dto.group.GroupDto;
import org.example.learningcenter.entity.dto.group.GroupUpdateDto;
import org.example.learningcenter.service.GroupService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/group")
@RequiredArgsConstructor
public class GroupController {
    final GroupService groupService;

    @GetMapping
    public ResponseEntity<Page<GroupDto>> getAllGroups(@RequestParam(required = false) String search,
                                                       @RequestParam(defaultValue = "0") int page,
                                                       @RequestParam(defaultValue = "20") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(groupService.getAll(pageable,search));
    }

    @GetMapping("/{id}")
    public ResponseEntity<GroupDto> getGroupById(@PathVariable String id) {
        return ResponseEntity.ok(groupService.get(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<GroupDto> updateGroup(@PathVariable String id, @RequestBody GroupUpdateDto updateDto) {
        return ResponseEntity.ok(groupService.update(updateDto, id));
    }

    @PostMapping
    public ResponseEntity<GroupDto> createGroup(@RequestBody GroupCreateDto groupCreateDto) {
        return ResponseEntity.ok(groupService.create(groupCreateDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteGroup(@PathVariable String id) {
        groupService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
