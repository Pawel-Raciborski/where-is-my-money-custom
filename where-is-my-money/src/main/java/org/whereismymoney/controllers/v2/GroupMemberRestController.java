package org.whereismymoney.controllers.v2;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.whereismymoney.controllers.v2.dto.Members;
import org.whereismymoney.dto.CreateMemberRequest;
import org.whereismymoney.dto.UserDto;
import org.whereismymoney.model.User;
import org.whereismymoney.service.MemberService;
import org.whereismymoney.util.UserUtil;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@RestController
@RequestMapping(GroupMemberRestController.API_V_2_GROUPS_GROUP_ID_MEMBERS)
@RequiredArgsConstructor
public class GroupMemberRestController {
    public static final String API_V_2_GROUPS_GROUP_ID_MEMBERS = "api/v2/groups/{groupId}/members";
    private final MemberService memberService;

    @PostMapping("/add")
    public ResponseEntity<?> addMember(
            @CookieValue(name = "GROUP_TOKEN", required = false) String cookieToken,
            @RequestParam(name = "token", required = false) String requestParamToken,
            @PathVariable UUID groupId,
            @RequestBody @Valid CreateMemberRequest createMemberRequest
    ) {
        String token = "";
        if (Objects.nonNull(cookieToken)) {
            token = cookieToken;
        }
        if (Objects.nonNull(requestParamToken)) {
            token = requestParamToken;
        }

        if (token.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        UserDto user = UserUtil.mapToDto(memberService.addMemberToGroup(groupId, createMemberRequest, token));
        return ResponseEntity.ok(user);
    }

    @GetMapping
    public ResponseEntity<Members> getMembers(
            @PathVariable UUID groupId
    ){
        List<UserDto> list = memberService.getGroupMembers(groupId).stream().map(UserUtil::mapToDto).toList();

        return ResponseEntity.ok(new Members(list));
    }
}
