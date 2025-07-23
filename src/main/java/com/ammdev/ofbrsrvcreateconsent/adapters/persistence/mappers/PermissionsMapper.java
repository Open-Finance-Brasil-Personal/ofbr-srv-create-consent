package com.ammdev.ofbrsrvcreateconsent.adapters.persistence.mappers;

import com.ammdev.ofbrsrvcreateconsent.adapters.persistence.repositories.SpringDataPermissionRepository;
import com.ammdev.ofbrsrvcreateconsent.application.domain.enums.PermissionsEnum;
import com.ammdev.ofbrsrvcreateconsent.adapters.persistence.entities.PermissionEntity;
import lombok.RequiredArgsConstructor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class PermissionMapper {

    private final SpringDataPermissionRepository permissionRepository;

    public PermissionMapper(SpringDataPermissionRepository permissionRepository) {
        this.permissionRepository = permissionRepository;
    }

    public Set<PermissionEntity> toEntities(Set<PermissionsEnum> enums) {
        return enums.stream()
                .map(PermissionsEnum::getId)
                .map(perm -> this.permissionRepository.getReferenceById(perm))
                .collect(Collectors.toSet());
    }
}
