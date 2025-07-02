package org.generalstore.modules.cart.cartitem.mapper;

import org.generalstore.modules.cart.cartitem.dto.CartItemDTO;
import org.generalstore.modules.cart.cartitem.entity.CartItem;
import org.generalstore.modules.product.mapper.ProductMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR, uses = ProductMapper.class)
public interface CartItemMapper {

    CartItemDTO toDTO(CartItem cartItem);

    @Mapping(target = "cart", ignore = true)
    CartItem toEntity(CartItemDTO cartItemDTO);
}
