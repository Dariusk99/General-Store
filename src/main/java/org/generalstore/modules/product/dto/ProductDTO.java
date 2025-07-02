package org.generalstore.modules.product.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProductDTO {

    private Long id;

    private String name;

    private String description;

    private Integer quantity;

    private BigDecimal price;
}
