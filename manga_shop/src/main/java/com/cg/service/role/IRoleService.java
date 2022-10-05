package com.cg.service.role;

import com.cg.model.Role;
import com.cg.model.dto.CategoryDTO;
import com.cg.model.dto.RoleDTO;
import com.cg.model.dto.UserDTO;
import com.cg.service.IGeneralService;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface IRoleService extends IGeneralService<Role> {
    Role findByName(String name);

    List<RoleDTO> getAllRoleDTO();

    Optional<RoleDTO> findRoleDTOById(@Param("id") Long id);


}
