package com.wgcisotto.buddy.vo;

import com.wgcisotto.buddy.enums.AverageBy;
import com.wgcisotto.buddy.google.sheets.model.enums.MovementType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AverageFilterVO {

    private AverageBy averageBy;
    private int limit;
    private MovementType type;

}
