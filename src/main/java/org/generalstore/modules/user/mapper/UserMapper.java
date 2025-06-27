package org.generalstore.modules.user.mapper;

import org.generalstore.modules.user.dto.RegisterUserDTO;
import org.generalstore.modules.user.dto.UserDTO;
import org.generalstore.modules.user.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface UserMapper {

    @Mapping(target = "roles", ignore = true)
    @Mapping(target = "active", ignore = true)
    User toRegisterEntity(RegisterUserDTO dto);

    UserDTO toDTO(User user);
}
