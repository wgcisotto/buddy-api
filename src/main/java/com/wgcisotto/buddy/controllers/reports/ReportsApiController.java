package com.wgcisotto.buddy.controllers.reports;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wgcisotto.buddy.controllers.ReportsApi;
import com.wgcisotto.buddy.service.ReportService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/v1")
public class ReportsApiController implements ReportsApi {

    private ReportService reportService;

    private ReportsApiController(ReportService reportService){
        this.reportService = reportService;
    }

    @GetMapping(value = "/report", produces = {"application/json" })
    public ResponseEntity<String> reports(@Valid @RequestParam(value = "category", required = false) List<String> category,
                                          @Valid @RequestParam(value = "month", required = false) List<String> month,
                                          @Valid @RequestParam(value = "result",  required = false) String result) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();

        if("percentage".equals(result)){
            return new ResponseEntity<>(objectMapper.writeValueAsString(reportService.movementsReportInPercentage()), HttpStatus.OK);
        }

        if("compact".equals(result)){
            return new ResponseEntity<>(objectMapper.writeValueAsString(reportService.movementsReportCompact()), HttpStatus.OK);
        }

        return new ResponseEntity<>(objectMapper.writeValueAsString(reportService.movementsReport()), HttpStatus.OK);
    }

}
