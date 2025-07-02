package org.generalstore.modules.user.adapter;

import lombok.RequiredArgsConstructor;
import org.generalstore.modules.user.service.application.UserApplicationService;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
class UserAdapter implements UserPort {

    private final UserApplicationService userApplicationService;

    @Override
    public Long getUserId(String username) {
        return userApplicationService.getUserByUsername(username).getId();
    }
}
