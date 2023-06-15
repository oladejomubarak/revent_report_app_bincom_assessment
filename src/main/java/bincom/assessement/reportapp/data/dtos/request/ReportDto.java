package bincom.assessement.reportapp.data.dtos.request;

import bincom.assessement.reportapp.data.model.EventCategory;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.FetchType;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class ReportDto {
    private String description;
    private String category;
    private String eventDate;
    private List<String> imageUrl = new ArrayList<>();
    private String reporterEmail;
}
