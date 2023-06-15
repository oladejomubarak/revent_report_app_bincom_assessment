package bincom.assessement.reportapp.data.repository;



import bincom.assessement.reportapp.data.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional <User> findUserByEmail(String email);
    Optional<User> findUserByPhoneNumber(String phoneNumber);
}

