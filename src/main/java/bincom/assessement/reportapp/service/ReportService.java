package bincom.assessement.reportapp.service;

import bincom.assessement.reportapp.data.dtos.request.ReportDto;
import bincom.assessement.reportapp.data.model.Report;

import java.util.List;

public interface ReportService {
    Report createReport(ReportDto reportDto);
    Report viewReport(Long reportId);
    List<Report> viewReportByCategory(String category);
    String deleteReport(Long reportId);
}
