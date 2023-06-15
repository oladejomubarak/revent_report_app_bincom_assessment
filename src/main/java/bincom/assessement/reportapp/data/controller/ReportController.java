package bincom.assessement.reportapp.data.controller;

import bincom.assessement.reportapp.data.dtos.request.ReportDto;
import bincom.assessement.reportapp.service.ReportServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1/api")
@CrossOrigin("*")
public class ReportController {
    @Autowired
    private ReportServiceImpl reportService;
    @PostMapping("/report")
    public ResponseEntity<?> createReport(ReportDto reportDto){
        return new ResponseEntity<>(reportService.createReport(reportDto), HttpStatus.OK);
    }
    @GetMapping("/get-report/{reportId}")
    public ResponseEntity<?> viewReport(@PathVariable Long reportId){
        return ResponseEntity.ok(reportService.viewReport(reportId));

    }
    @GetMapping("get-report_by_category/{category}")
    public ResponseEntity<?> getReportByCategory(@PathVariable String category){
        return ResponseEntity.ok(reportService.viewReportByCategory(category));
    }
    @DeleteMapping("delete-report/{reportId}")
    public ResponseEntity<?> deleteReport(@PathVariable Long reportId){
        return ResponseEntity.ok(reportService.deleteReport(reportId));
    }
}
