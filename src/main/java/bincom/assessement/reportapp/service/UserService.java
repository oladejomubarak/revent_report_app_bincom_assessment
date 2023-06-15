package bincom.assessement.reportapp.service;

import bincom.assessement.reportapp.data.dtos.request.CreateUserRequest;
import bincom.assessement.reportapp.data.dtos.request.LoginRequest;
import bincom.assessement.reportapp.data.dtos.request.UserUpdateRequest;
import bincom.assessement.reportapp.data.dtos.response.CreateUserResponse;
import bincom.assessement.reportapp.data.dtos.response.GetResponse;
import bincom.assessement.reportapp.data.dtos.response.LoginResponse;
import bincom.assessement.reportapp.data.model.User;

public interface UserService {
    CreateUserResponse createUser(CreateUserRequest createUserRequest);
    LoginResponse userLogin(LoginRequest loginRequest);
    GetResponse updateUser(UserUpdateRequest userUpdateRequest);
    GetResponse deleteUser(int id);

}

