package org.generalstore.modules.cart.cartitem.dto;

import lombok.*;
import org.generalstore.modules.product.dto.ProductDTO;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CartItemDTO {

    private Long id;

    private String name;

    private Integer quantity;

    private ProductDTO product;
}
