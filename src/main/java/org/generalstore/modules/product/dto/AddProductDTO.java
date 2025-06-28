package org.generalstore.modules.product.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddProductDTO {

    private String name;

    private String description;

    private Integer quantity;

    private BigDecimal price;
}
