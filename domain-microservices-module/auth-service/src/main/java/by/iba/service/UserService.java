package by.iba.service;

import by.iba.dto.PasswordDTO;
import by.iba.dto.UserDTO;

public interface UserService {

    UserDTO save(UserDTO userDTO);

    void confirmUserAccount(final String confirmationToken);

    UserDTO passwordUpdate(String confirmationToken, PasswordDTO passwordDTO);

    void sendRecoverMessage(String email);
}
