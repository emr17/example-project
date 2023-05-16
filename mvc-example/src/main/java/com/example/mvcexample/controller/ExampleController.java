package com.example.mvcexample.controller;

import com.example.mvcexample.dto.ExampleDto;
import com.example.mvcexample.service.ExampleService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/example")
@RequiredArgsConstructor
@Validated
public class ExampleController {

    private final ExampleService exampleService;

    @GetMapping
    public ExampleDto getExample(){
        return exampleService.getExample();
    }

    @GetMapping("/{id}")
    public ExampleDto getExampleById(@PathVariable Long id){
        return exampleService.getExampleById(id);
    }

    @GetMapping("/search")
    public List<ExampleDto> searchExamples(@RequestParam String keyword){
        return exampleService.searchExamples(keyword);
    }

    @PostMapping
    public ExampleDto createExample(@Valid @RequestBody ExampleDto exampleDto){
        return exampleService.createExample(exampleDto);
    }

    @PutMapping("/{id}")
    public ExampleDto updateExample(@PathVariable Long id, @Valid @RequestBody ExampleDto exampleDto){
        return exampleService.updateExample(id, exampleDto);
    }
}