package com.cg.controller.rest;


 import com.cg.model.Product;
 import com.cg.model.User;
 import com.cg.model.dto.ProductDTO;
 import com.cg.model.dto.RoleDTO;
 import com.cg.model.dto.UserDTO;
 import com.cg.service.role.IRoleService;
 import com.cg.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
 import org.springframework.web.bind.annotation.*;

 import java.util.List;
 import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserControllerRest {
    @Autowired
    private IUserService userService;

    @Autowired
    private IRoleService roleService;

    @GetMapping
    public ResponseEntity<?> listUser() {

         List<UserDTO> userDTOList = userService.findAllUserDTO();

        return new ResponseEntity<>(userDTOList, HttpStatus.OK);
    }


    @GetMapping("/role")
    public ResponseEntity<?> getAllRole() {
        List<RoleDTO> roleDTOList = roleService.getAllRoleDTO();

        return new ResponseEntity<>(roleDTOList, HttpStatus.OK);
    }

    @GetMapping("/role/{id}")
    public ResponseEntity<?> getRoleDTOById(@PathVariable Long id ){

        Optional<RoleDTO> userOptional = roleService.findRoleDTOById(id);

        return new ResponseEntity<>(userOptional.get(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getAllUserDTOById(@PathVariable Long id ){

        Optional<UserDTO> userOptional = userService.findUserDTOById(id);

        return new ResponseEntity<>(userOptional.get(), HttpStatus.OK);
    }

    @GetMapping("/get/{username}")
    public ResponseEntity<?> getAllUserDTOByUsername(@PathVariable String username ){

        Optional<UserDTO> userOptional = userService.findUserDTOByUserName(username);

        return new ResponseEntity<>(userOptional.get(), HttpStatus.OK);
    }

    @DeleteMapping("/block/{id}")
    public ResponseEntity<?> blockUser(@PathVariable Long id ){

            userService.blockUserById(id);

            Optional<UserDTO> userOptional = userService.findUserDTOById(id);

        return new ResponseEntity<>(userOptional.get(), HttpStatus.OK);
    }

    @DeleteMapping("/unLock/{id}")
    public ResponseEntity<?> unlockUser(@PathVariable Long id){

        userService.unLockUserById(id);

        Optional<UserDTO> userOptional = userService.findUserDTOById(id);

        return new ResponseEntity<>(userOptional.get(), HttpStatus.OK);
    }

}
