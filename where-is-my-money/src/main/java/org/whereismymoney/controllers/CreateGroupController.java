package org.whereismymoney.controllers;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.whereismymoney.config.properties.ApplicationProperties;
import org.whereismymoney.dto.CreateGroupRequest;
import org.whereismymoney.model.Group;
import org.whereismymoney.service.GroupService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import java.util.Objects;

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
            @CookieValue(name = "GROUP_TOKEN", required = false) String token,
            HttpServletResponse response
    ) {
        Group createdGroup = groupService.create(createGroupRequest, token);

        if (Objects.isNull(token)) {
            Cookie cookie = new Cookie("GROUP_TOKEN", createdGroup.getToken().getToken());
            cookie.setPath("/");
            cookie.setHttpOnly(true);
            cookie.setMaxAge(60 * 60 * 24 * 30); //
            response.addCookie(cookie);
        }

        String url = applicationProperties.getBaseUrl()
                .concat("group/")
                .concat(createdGroup.getId().toString());

        model.addAttribute("groupCreated", true);
        model.addAttribute("groupUrl", url);
        model.addAttribute("groupId", createdGroup.getId());
        System.out.println("Registering user: " + createGroupRequest);

        return "create_group";
    }
}
