package bincom.assessement.reportapp.data.dtos.request;
import lombok.Data;

@Data
public class LoginRequest {
    private String email;
    private String password;
}
