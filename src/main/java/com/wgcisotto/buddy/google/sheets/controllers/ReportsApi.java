package com.wgcisotto.buddy.google.sheets.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.wgcisotto.buddy.google.sheets.model.Report;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;

@Api(value = "reports", description = "the reports API")
public interface ReportsApi {

    @ApiOperation(value = "Buddy reports by category", nickname = "reports", notes = "Multiple categories and months values can be provided with comma separated strings", response = Report.class, responseContainer = "List", tags={ "reports", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "successful operation", response = Report.class, responseContainer = "List"),
        @ApiResponse(code = 400, message = "Invalid status value") })
    ResponseEntity<String> reports(@ApiParam(value = "Category values that need to be considered for filter, if not informed all categories will be considered", allowableValues = "available, pending, sold")
                                   @Valid @RequestParam(value = "category", required = false) List<String> category,
                                   @ApiParam(value = "Months that need to be considered for filter, if not informed all months will be considered", allowableValues = "available, pending, sold")
                                   @Valid @RequestParam(value = "month", required = false) List<String> month,
                                   @ApiParam(value = "Type of result", allowableValues = "sum, percentage")
                                   @Valid @RequestParam(value = "result", required = false) String result) throws JsonProcessingException;

}
