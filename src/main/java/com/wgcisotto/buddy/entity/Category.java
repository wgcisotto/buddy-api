package com.wgcisotto.buddy.entity;

import com.wgcisotto.buddy.google.sheets.model.enums.MovementType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.TextIndexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "category")
public class Category {

    @Id
    private String id;

    @TextIndexed
    private String name;

    @TextIndexed
    private MovementType type;

    private String icon;

    private boolean delete = false;
}
