package io.tribehouse.motomo.user;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    @Autowired
    public PasswordEncoder passwordEncoder;

    /**
     * Every created account gets User role by default.
     */
    public UserDto createUser(UserDto userDto) {

        boolean userAlreadyExist = checkIfUserExist(userDto.getEmail());

        if (!userAlreadyExist) {

            userDto.setRoles(Set.of(Role.ROLE_USER));
            userDto.setEnabled(true);
            userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));

            UserEntity userEntityFromDto = UserMapper.INSTANCE.userDtoToUserEntity(userDto);

            UserEntity newUser = userRepository.save(userEntityFromDto);
            UserDto newUserDto = UserMapper.INSTANCE.userEntityToUserDto(newUser);
            return newUserDto;
        } else {
            // TODO obsłużyć
            return null;
        }
    }

    public boolean checkIfUserExist(String email) {
        Optional<UserDto> user = userRepository.findByEmail(email);
        return user.isPresent();
    }

    public UserDto findByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User" + email + "not found"));
    }
}
