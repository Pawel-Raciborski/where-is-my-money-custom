package org.whereismymoney.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.whereismymoney.dto.GroupDetails;
import org.whereismymoney.model.Group;
import org.whereismymoney.model.User;
import org.whereismymoney.model.VerificationCode;
import org.whereismymoney.service.GroupDetailsService;
import org.whereismymoney.service.GroupService;
import org.whereismymoney.service.VerificationCodeService;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Controller
@RequestMapping("/group")
@RequiredArgsConstructor
public class GroupController {
    private final GroupDetailsService groupDetailsService;
    private final VerificationCodeService verificationCodeService;
    private final GroupService groupService;

    @GetMapping("/{groupId}")
    public String viewGroup(
            @PathVariable UUID groupId,
            @RequestParam(required = false) String token,
            Model model
    ) {
        boolean isTokenPresent = Objects.nonNull(token);

        if(!isTokenPresent) {
            // TODO dodać logikę dla gościa
            groupDetailsService.getGroupDetails(groupId);
            return "group";
        }

        VerificationCode verificationCode = verificationCodeService.checkIfExistForGroupWithToken(groupId, token)
                .orElseGet(() -> verificationCodeService.createVerificationCodeForGroupWithToken(groupId, token));
        // 7047

        if(!verificationCode.isVerified()) {
            // TODO redirect /verify
        }

        if(verificationCode.getExpireAt().isBefore(LocalDateTime.now())){
            verificationCodeService.deletePreviousVerificationCodeForGroupWithToken(groupId,token);
            verificationCode = verificationCodeService.createVerificationCodeForGroupWithToken(groupId, token);
        }
        System.out.println(verificationCode);

        //TODO wysłanie maila z kodem weryfikacyjnym do użytkownika

        GroupDetails groupDetails = groupDetailsService.getGroupDetails(groupId);
        System.out.println("groupId: " + groupId + ", token: " + token);
        return "group";
    }

    @GetMapping("/{groupId}/access")
    public String accessGroup(
            @PathVariable UUID groupId,
            @RequestParam(required = true) String token,
            Model model
    ){
        Group groupWithToken = groupService.findGroupWithToken(groupId, token);
        User owner = groupWithToken.getOwner();

        model.addAttribute("ownerEmail", owner.getEmail());
        return "access";
    }
}
