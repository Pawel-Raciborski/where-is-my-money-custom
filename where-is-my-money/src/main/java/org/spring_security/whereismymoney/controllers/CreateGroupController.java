package org.spring_security.whereismymoney.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.spring_security.whereismymoney.config.ApplicationProperties;
import org.spring_security.whereismymoney.dto.CreateGroupRequest;
import org.spring_security.whereismymoney.model.Member;
import org.spring_security.whereismymoney.model.Owner;
import org.spring_security.whereismymoney.service.GroupService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.UUID;

@Controller
@RequestMapping("/group")
@RequiredArgsConstructor
public class CreateGroupController {
    private final GroupService groupService;
    private final ApplicationProperties applicationProperties;

    @GetMapping("/new")
    public String createGroupView(Model model) {
        model.addAttribute("groupCreated", false);
        return "create_group";
    }

    @PostMapping("/new")
    public String creteGroup(@Valid @ModelAttribute CreateGroupRequest createGroupRequest, Model model) {
        groupService.create(createGroupRequest);
        UUID id = UUID.randomUUID();
        String url = applicationProperties.getBaseUrl().concat("group/").concat(id.toString());

        model.addAttribute("groupCreated", true);
        model.addAttribute("groupId", url);
        System.out.println("Registering user: " + createGroupRequest);

        return "create_group";
    }
}
