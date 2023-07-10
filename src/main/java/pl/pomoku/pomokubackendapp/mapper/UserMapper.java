package pl.pomoku.pomokubackendapp.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pl.pomoku.pomokubackendapp.entity.User;
import pl.pomoku.pomokubackendapp.request.RegisterRequest;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target = "password", ignore = true)
    User registerRequestToUser(RegisterRequest request);
}
