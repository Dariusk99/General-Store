package org.generalstore.modules.user.service.application;

import lombok.RequiredArgsConstructor;
import org.generalstore.modules.cart.core.adapter.CartPort;
import org.generalstore.modules.cart.core.entity.Cart;
import org.generalstore.modules.user.dto.RegisterUserDTO;
import org.generalstore.modules.user.dto.UserDTO;
import org.generalstore.modules.user.entity.User;
import org.generalstore.modules.user.factory.UserFactory;
import org.generalstore.modules.user.mapper.UserMapper;
import org.generalstore.modules.user.service.domain.UserDomainService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
class UserApplicationServiceImpl implements UserApplicationService {

    private final UserDomainService userDomainService;
    private final UserMapper userMapper;
    private final CartPort cartPort;
    private final UserFactory userFactory;

    @Override
    public UserDTO registerUserWithCart(RegisterUserDTO registerUserDTO) {
        // Create and save User
        User user = userFactory.createUserFromDTO(registerUserDTO);
        User savedUser = userDomainService.register(user);

        // Create Cart for User
        Cart cart = new Cart(null, savedUser, new ArrayList<>());
        cartPort.saveCartForUser(cart);

        // Return User DTO
        return userMapper.toDTO(savedUser);
    }

    @Override
    public UserDTO getUserByUsername(String username) {
        return userMapper.toDTO(userDomainService.findUserByUsername(username));
    }
}