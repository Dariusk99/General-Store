package org.generalstore.modules.cart.mapper;

import org.generalstore.modules.cart.dto.CartDTO;
import org.generalstore.modules.cart.entity.Cart;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface CartMapper {

    CartDTO toDTO(Cart cart);

    @Mapping(target = "user", ignore = true)
    Cart toEntity(CartDTO cartDTO);
}
