package bincom.assessement.reportapp.service;

import bincom.assessement.reportapp.data.dtos.request.ReportDto;
import bincom.assessement.reportapp.data.model.EventCategory;
import bincom.assessement.reportapp.data.model.Report;
import bincom.assessement.reportapp.data.model.User;
import bincom.assessement.reportapp.data.repository.ReportRepository;
import bincom.assessement.reportapp.data.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class ReportServiceImpl implements ReportService{
    @Autowired
    private ReportRepository reportRepository;
    @Autowired
    private UserRepository userRepository;

    private DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");


    @Override
    public Report createReport(ReportDto reportDto) {
        User foundUser = userRepository.findUserByEmail(reportDto.getReporterEmail()).orElseThrow(()-> new
                IllegalStateException("no user found with this email"));
         var formattedDate = LocalDate.parse(reportDto.getEventDate(), dateFormatter);
        Report report = new Report();
        report.setCategory(EventCategory.valueOf(reportDto.getCategory()));
        report.setReportDescription(reportDto.getDescription());
        report.setImageUrl(reportDto.getImageUrl());
        report.setReporterEmail(foundUser.getFirstName());
        report.setReportDateAndTime(LocalDateTime.now().toString());
        report.setEventDate(formattedDate);
        reportRepository.save(report);

        foundUser.getReportList().add(report);
        userRepository.save(foundUser);

        return report;
    }

    @Override
    public Report viewReport(Long reportId) {

        return reportRepository.findById(reportId).orElseThrow(()-> new RuntimeException(
                "report not found"
        ));
    }

    @Override
    public List<Report> viewReportByCategory(String category) {
        List<Report> foundLists = new ArrayList<>();
        for (Report report: reportRepository.findAll()) {
            if(report.getCategory().toString().equals(category)) foundLists.add(report);
        }
        return foundLists;
    }

    @Override
    public String deleteReport(Long reportId) {
        Report foundReport = viewReport(reportId);
        reportRepository.delete(foundReport);
        return "report has been successfully deleted";
    }
}
