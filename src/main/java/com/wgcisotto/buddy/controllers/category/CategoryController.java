package com.wgcisotto.buddy.controllers.category;

import com.wgcisotto.buddy.entity.Category;
import com.wgcisotto.buddy.google.sheets.model.enums.MovementType;
import com.wgcisotto.buddy.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.util.Objects;

@RestController
@RequestMapping("/v1")
public class CategoryController {

    @Autowired
    private CategoryService service;

    @PostMapping("/category")
    public ResponseEntity<Mono<Category>> save(@RequestBody Category category){
        return ResponseEntity.ok(service.save(category));
    }

    @GetMapping("/category")
    public ResponseEntity<Flux<Category>> getAll(@Valid @RequestParam(value = "type",  required = false) MovementType type){
        if(Objects.nonNull(type)){
            return ResponseEntity.ok(service.findAllByType(type));
        }
        return ResponseEntity.ok(service.findAll());
    }


}
