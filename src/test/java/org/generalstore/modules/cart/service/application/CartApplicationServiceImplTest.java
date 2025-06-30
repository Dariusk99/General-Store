package org.generalstore.modules.cart.service.application;

import org.generalstore.modules.cart.service.domain.CartDomainService;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class CartApplicationServiceImplTest {

    @Mock
    private CartDomainService cartDomainService;
}
