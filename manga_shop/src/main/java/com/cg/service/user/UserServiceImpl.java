package com.cg.service.user;

import com.cg.model.User;
import com.cg.model.UserPrinciple;
import com.cg.model.dto.UserDTO;
import com.cg.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public List<UserDTO> findAllUserDTO() {
        return userRepository.findAllUserDTO();
    }

    @Override
    public User getByUsername(String username) {
        return userRepository.getByUsername(username);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public Optional<UserDTO> findUserDTOByUsername(String username) {
        return userRepository.findUserDTOByUsername(username);
    }

    @Override
    public Optional<UserDTO> findUserDTOByPhone(String phone) {
        return userRepository.findUserDTOByPhone(phone);
    }

    @Override
    public User save(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public void remove(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userOptional = userRepository.findByUsername(username);
        if (!userOptional.isPresent()) {
            throw new UsernameNotFoundException(username);
        }
        return UserPrinciple.build(userOptional.get());
//        return (UserDetails) userOptional.get();
    }

    @Override
    public void blockUserById(Long id) {
        userRepository.blockUserById(id);
    }

    @Override
    public void unLockUserById(Long id) {
        userRepository.unLockUserById(id);
    }

    @Override
    public Optional<UserDTO> findUserDTOById(Long id) {
        return userRepository.findUserDTOById(id);
    }

    @Override
    public Optional<UserDTO> findUserDTOByUserName(String username) {
        return userRepository.findUserDTOByUserName(username);
    }
}
