package org.whereismymoney.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.whereismymoney.config.properties.ApplicationProperties;
import org.whereismymoney.dto.CreateGroupRequest;
import org.whereismymoney.model.Group;
import org.whereismymoney.service.GroupService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

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
    public String creteGroup(
            @Valid @ModelAttribute CreateGroupRequest createGroupRequest,
            Model model,
            @CookieValue(name = "TOKEN_ID", required = false) String token
    ) {
        Group createdGroup = groupService.create(createGroupRequest, token);

        String url = applicationProperties.getBaseUrl()
                .concat("group/")
                .concat(createdGroup.getId().toString());

        model.addAttribute("groupCreated", true);
        model.addAttribute("groupUrl", url);
        model.addAttribute("groupId", createdGroup.getId());
        model.addAttribute("tokenId", createdGroup.getToken().getToken());
        System.out.println("Registering user: " + createGroupRequest);

        return "create_group";
    }
}
