package org.spring_security.whereismymoney.controllers;

import lombok.RequiredArgsConstructor;
import org.spring_security.whereismymoney.dto.CreateGroupRequest;
import org.spring_security.whereismymoney.service.GroupService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Random;
import java.util.UUID;

@Controller
@RequestMapping("/group")
@RequiredArgsConstructor
public class CreateGroupController {
    private final GroupService groupService;

    @GetMapping("/new")
    public String createGroupView(Model model) {
        model.addAttribute("groupCreated", false);
        return "create_group";
    }

    @PostMapping("/new")
    public String creteGroup(@ModelAttribute CreateGroupRequest createGroupRequest, Model model) {
        UUID id = UUID.randomUUID();

        model.addAttribute("groupCreated", true);
        model.addAttribute("groupId", id);
        System.out.println("Registering user: " + createGroupRequest);

        return "create_group";
    }
}
