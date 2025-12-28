package org.whereismymoney.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.whereismymoney.dto.CreateMemberRequest;
import org.whereismymoney.service.MemberService;

import java.util.Objects;
import java.util.UUID;

@Controller
@RequestMapping("/group/{groupId}/members")
@RequiredArgsConstructor
public class MembersController {
    private final MemberService memberService;

    @PostMapping("/add")
    public String addMember(
            @CookieValue(name = "GROUP_TOKEN", required = false) String cookieToken,
            @RequestParam(name = "token", required = false) String requestParamToken,
            @PathVariable UUID groupId,
            CreateMemberRequest createMemberRequest,
            Model model
    ) {
        String token = "";

        if (Objects.nonNull(cookieToken)) {
            token = cookieToken;
        }
        if (Objects.nonNull(requestParamToken)) {
            token = requestParamToken;
        }
        String url = "redirect:/group/".concat(groupId.toString());
        if (token.isEmpty()) {
            return url;
        }

        memberService.addMemberToGroup(groupId, createMemberRequest, token);

        return url.concat("?token=").concat(token);
    }
}
