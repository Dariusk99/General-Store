package org.generalstore.modules.cart.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.generalstore.modules.product.dto.ProductDTO;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CartDTO {

    private UUID id;

    private List<ProductDTO> products;
}