package org.whereismymoney.controllers.v2;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.whereismymoney.config.properties.ApplicationProperties;
import org.whereismymoney.controllers.v2.dto.GroupDto;
import org.whereismymoney.controllers.v2.dto.GroupSummary;
import org.whereismymoney.dto.CreateGroupRequest;
import org.whereismymoney.dto.GroupDetails;
import org.whereismymoney.model.Group;
import org.whereismymoney.model.VerificationCode;
import org.whereismymoney.service.GroupDetailsService;
import org.whereismymoney.service.GroupService;
import org.whereismymoney.service.GroupSummaryService;
import org.whereismymoney.service.VerificationCodeService;
import org.whereismymoney.util.GroupUtil;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@RestController
@RequestMapping(GroupRestController.V_2_GROUPS)
@RequiredArgsConstructor
public class GroupRestController {
    public static final String V_2_GROUPS = "api/v2/groups";
    private final GroupService groupService;
    private final ApplicationProperties applicationProperties;
    private final GroupDetailsService groupDetailsService;
    private final VerificationCodeService verificationCodeService;
    private final GroupSummaryService getGroupSummaryService;

    @PostMapping("/new")
    public ResponseEntity<Void> createGroup(
            @RequestBody @Valid CreateGroupRequest request,
            HttpServletResponse response
    ) {
        Group group = groupService.create(request, null); // TODO zmodyfikować metodę

        String tokenValue = group.getToken().getValue();
        Cookie cookie = new Cookie("GROUP_TOKEN", tokenValue);
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        cookie.setMaxAge(60 * 60 * 24 * 30);
        response.addCookie(cookie);

        // redirect na /verify
        String url = applicationProperties.getBaseUrl()
                .concat(V_2_GROUPS)
                .concat("/")
                .concat(group.getId().toString())
                .concat("?token=")
                .concat(tokenValue);

        return ResponseEntity.created(URI.create(url)).build();
    }

    @GetMapping("/{groupId}")
    public ResponseEntity<GroupDto> getGroup(
            @PathVariable UUID groupId,
            @RequestParam(required = false) String token
    ) {
        boolean isTokenPresent = Objects.nonNull(token);

        if (!isTokenPresent) {
            // TODO dodać logikę dla gościa
            groupDetailsService.getGroupDetails(groupId);
            return ResponseEntity.ok().build();
        }

        VerificationCode verificationCode = verificationCodeService.findCodeForGroupWithToken(groupId, token)
                .orElseGet(() -> verificationCodeService.createVerificationCodeForGroupWithToken(groupId, token));
        // 7047

        if (!verificationCode.isVerified()) {
            // TODO redirect /verify
        }

        if (verificationCode.getExpireAt().isBefore(LocalDateTime.now())) {
            verificationCodeService.deletePreviousVerificationCodeForGroupWithToken(groupId, token);
            verificationCode = verificationCodeService.createVerificationCodeForGroupWithToken(groupId, token);
        }
        System.out.println(verificationCode);

        //TODO wysłanie maila z kodem weryfikacyjnym do użytkownika

        GroupDto groupDetails = GroupUtil.mapToDto(groupService.findById(groupId));
        System.out.println("groupId: " + groupId + ", token: " + token);

        return ResponseEntity.ok(groupDetails);
    }

    @PostMapping("/{groupId}/verify")
    public ResponseEntity<Void> accessGroup(
            @PathVariable UUID groupId,
            @RequestParam(required = true) String token,
            @RequestParam(required = true) String code
    ) {
        boolean verify = verificationCodeService.verify(groupId, token, code);

        if (!verify) {
            return ResponseEntity.status(403).build();
        }

        String url = applicationProperties.getBaseUrl()
                .concat(V_2_GROUPS)
                .concat("/")
                .concat(groupId.toString())
                .concat("?token=")
                .concat(token);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(HttpHeaders.LOCATION, url);

        return ResponseEntity.ok().headers(httpHeaders).build();
    }

    @GetMapping("/{groupId}/summary")
    public ResponseEntity<?> getGroupDetails(
            @PathVariable UUID groupId,
            @RequestParam(required = false) String token
    ) {
        // TODO jeżeli jest token -> sprawdzić weryfikację -> zwrócić szczegóły grupy oraz dane o właścicielu
        HttpHeaders headers = new HttpHeaders();
        if (Objects.nonNull(token)) {
            boolean isVerified = verificationCodeService.checkIsVerified(groupId, token);

            if (!isVerified) {
                String url = applicationProperties.getBaseUrl()
                        .concat(V_2_GROUPS)
                        .concat("/")
                        .concat(groupId.toString())
                        .concat("/verify")
                        .concat("?token=")
                        .concat(token);

                headers.add(HttpHeaders.LOCATION, url);

                return ResponseEntity.status(403).headers(headers).build();
            }

            headers.add("X-Is-Owner", "true");
        }
        GroupSummary groupSummary = getGroupSummaryService.getGroupSummary(groupId);
        return ResponseEntity.ok(groupSummary);
    }
}
