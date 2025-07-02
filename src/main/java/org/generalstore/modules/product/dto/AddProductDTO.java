package org.generalstore.modules.product.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
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

    @NotBlank(message = "Product name required.")
    @Min(value = 4, message = "Name should be longer than 3 characters.")
    private String name;

    private String description;

    @PositiveOrZero(message = "Quantity should be at least 0.")
    private Integer quantity;

    @PositiveOrZero(message = "Price should be at least 0.")
    private BigDecimal price;
}
