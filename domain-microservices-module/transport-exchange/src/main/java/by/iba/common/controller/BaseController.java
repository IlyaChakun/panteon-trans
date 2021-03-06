package by.iba.common.controller;

import by.iba.common.dto.core.BaseResp;
import by.iba.common.validation.annotation.PositiveLong;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface BaseController<T extends BaseResp> {

    @GetMapping("/{id}")
    ResponseEntity<T> findById(@PathVariable("id") @PositiveLong String id);

    @GetMapping
    ResponseEntity<List<T>> findAll();

    @GetMapping("/is-exist/{id}")
    ResponseEntity<Boolean> existById(@PathVariable("id") @PositiveLong String id);

}
