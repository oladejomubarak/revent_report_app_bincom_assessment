package bincom.assessement.reportapp.service;


import bincom.assessement.reportapp.data.dtos.request.CreateUserRequest;
import bincom.assessement.reportapp.data.dtos.request.LoginRequest;
import bincom.assessement.reportapp.data.dtos.request.UserUpdateRequest;
import bincom.assessement.reportapp.data.dtos.response.CreateUserResponse;
import bincom.assessement.reportapp.data.dtos.response.GetResponse;
import bincom.assessement.reportapp.data.dtos.response.LoginResponse;
import bincom.assessement.reportapp.data.model.User;
import bincom.assessement.reportapp.data.repository.UserRepository;
import bincom.assessement.reportapp.validators.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepository userRepository;

    private final User user = new User();
    @Override
    public CreateUserResponse createUser(CreateUserRequest createUserRequest) {
        validateRegistrationInput(createUserRequest);
        return registerUser(createUserRequest);
    }


    private CreateUserResponse registerUser(CreateUserRequest createUserRequest) {
        if(userRepository.findUserByEmail(createUserRequest.getEmail()).isPresent())
            throw new RuntimeException("The email already exists, try another email");
        else
            user.setEmail(createUserRequest.getEmail());
        if(userRepository.findUserByPhoneNumber(createUserRequest.getPhoneNumber()).isPresent())
            throw new RuntimeException("Phone number already exists, choose another phone number");
        else
            user.setPhoneNumber(createUserRequest.getPhoneNumber());
        user.setFirstName(createUserRequest.getFirstName());
        user.setLastName(createUserRequest.getLastName());
        user.setPassword(createUserRequest.getPassword());

        User savedUser = userRepository.save(user);

        CreateUserResponse createUserResponse = new CreateUserResponse();
        createUserResponse.setMessage("User created successfully");
        createUserResponse.setId(savedUser.getId());
        createUserResponse.setStatusCode(201);
        return createUserResponse;
    }

    private void validateRegistrationInput(CreateUserRequest createUserRequest) {
        if (!UserValidator.isValidEmail(createUserRequest.getEmail())) throw new RuntimeException(
                String.format("The email %s is invalid", createUserRequest.getEmail()));
        if (!UserValidator.isValidPassword(createUserRequest.getPassword())) throw new RuntimeException(
                String.format("The password %s is invalid", createUserRequest.getPassword()));
        if (!UserValidator.isValidPhoneNumber(createUserRequest.getPhoneNumber())) throw new RuntimeException(
                String.format("The phone number %s is invalid", createUserRequest.getPhoneNumber()));
    }

    @Override
    public LoginResponse userLogin(LoginRequest loginRequest) {
        User findUser = userRepository.findUserByEmail(loginRequest.getEmail()).orElseThrow(()->
                new RuntimeException("Email not found"));
        //findUser.setEmail(loginRequest.getEmail());
        if(loginRequest.getPassword().equals(findUser.getPassword()))
            findUser.setPassword(loginRequest.getPassword());
        else
            throw new RuntimeException("Incorrect password");
        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setMessage("Login is successful");
        return loginResponse;
    }

    @Override
    public GetResponse updateUser(UserUpdateRequest userUpdateRequest) {
        User foundUser = userRepository.findById(userUpdateRequest.getId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        validateUpdateInput(userUpdateRequest);

        foundUser.setEmail(userUpdateRequest.getEmail() != null
                && !userUpdateRequest.getEmail().equals("") ? userUpdateRequest.getEmail() : foundUser.getEmail());
        foundUser.setPassword(userUpdateRequest.getPassword() != null
                && !userUpdateRequest.getPassword().equals("") ? userUpdateRequest.getPassword() : foundUser.getPassword());
        foundUser.setFirstName(userUpdateRequest.getFirstName() != null
                && !userUpdateRequest.getFirstName().equals("") ? userUpdateRequest.getFirstName() : foundUser.getFirstName());
        foundUser.setLastName(userUpdateRequest.getLastName() != null
                && !userUpdateRequest.getLastName().equals("") ? userUpdateRequest.getLastName() : foundUser.getLastName());
        foundUser.setPhoneNumber(userUpdateRequest.getPhoneNumber() != null
                && !userUpdateRequest.getPhoneNumber().equals("") ? userUpdateRequest.getPhoneNumber() : foundUser.getPhoneNumber());
        userRepository.save(foundUser);
        return new GetResponse("User detail updated successfully");
    }

    private void validateUpdateInput(UserUpdateRequest userUpdateRequest) {
        if(userUpdateRequest.getEmail() != null && !UserValidator.isValidEmail(userUpdateRequest.getEmail()))
            throw new RuntimeException(
                    String.format("The email %s is invalid", userUpdateRequest.getEmail()));
        if(userUpdateRequest.getPassword() != null && !UserValidator.isValidPassword(userUpdateRequest.getPassword()))
            throw new RuntimeException(
                    String.format("Password %s is weak, choose a strong one", userUpdateRequest.getPassword()));
        if(userUpdateRequest.getPhoneNumber() != null && !UserValidator.isValidPhoneNumber(userUpdateRequest.getPhoneNumber()))
            throw new RuntimeException(
                    String.format("The phone number %s is invalid", userUpdateRequest.getPhoneNumber()));
    }

    @Override
    public GetResponse deleteUser(int id) {
        userRepository.deleteById(id);
        return new GetResponse("User deleted successfully");
    }
}
