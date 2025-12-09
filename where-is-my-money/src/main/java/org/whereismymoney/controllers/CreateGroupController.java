package org.whereismymoney.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.whereismymoney.config.ApplicationProperties;
import org.whereismymoney.dto.CreateGroupRequest;
import org.whereismymoney.model.Group;
import org.whereismymoney.service.GroupService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

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

    @GetMapping("/{groupId}")
    public String viewGroup(
            @PathVariable UUID groupId,
            @RequestParam(required = false) String token,
            Model model
    ) {
        System.out.println("groupId: " + groupId + ", token: " + token);
        return "group";
    }
}
