package com.wgcisotto.buddy.google.sheets.service;

import java.time.Month;
import java.util.Map;

public interface ReportService {

    Map<Month, Map<String, String>> movementsReportInPercentage ();

    Map<Month, Map<String, Double>> movementsReport ();

    Map<Month, Map<String, Double>> movementsReportCompact();
}
