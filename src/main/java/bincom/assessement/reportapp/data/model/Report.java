package bincom.assessement.reportapp.data.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Generated;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Enumerated(EnumType.STRING)
    private EventCategory category;
    private String reportDescription;
    private LocalDate eventDate;
    private String reportDateAndTime;
    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> imageUrl = new ArrayList<>();
    private String reporterEmail;
    private int views;
}
