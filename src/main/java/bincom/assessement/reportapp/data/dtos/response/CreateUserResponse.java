package bincom.assessement.reportapp.data.dtos.response;


import lombok.Data;

@Data
public class CreateUserResponse {
    private int id;
    private int statusCode;
    private String message;
}
