package org.whereismymoney.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.whereismymoney.dto.GroupDetails;
import org.whereismymoney.service.GroupDetailsService;

import java.util.UUID;

@Controller
@RequestMapping("/group")
@RequiredArgsConstructor
public class GroupController {
    private final GroupDetailsService groupDetailsService;

    @GetMapping("/{groupId}")
    public String viewGroup(
            @PathVariable UUID groupId,
            @RequestParam(required = false) String token,
            Model model
    ) {
        GroupDetails groupDetails = groupDetailsService.getGroupDetails(groupId, token);
        System.out.println("groupId: " + groupId + ", token: " + token);
        return "group";
    }
}
