package bincom.assessement.reportapp.data.repository;

import bincom.assessement.reportapp.data.model.Report;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReportRepository extends JpaRepository<Report, Long> {
}
