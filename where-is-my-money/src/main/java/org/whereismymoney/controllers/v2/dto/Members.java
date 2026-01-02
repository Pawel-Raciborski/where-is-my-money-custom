package org.whereismymoney.controllers.v2.dto;

import org.whereismymoney.dto.UserDto;

import java.util.List;

public record Members(
        List<UserDto> members
) {

}
