package org.generalstore.modules.cart.core.dto;

import lombok.*;
import org.generalstore.modules.cart.cartitem.dto.CartItemDTO;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CartDTO {

    private UUID id;
    private List<CartItemDTO> items;
}