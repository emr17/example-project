package com.example.mvcexample.service;

import com.example.mvcexample.dto.ExampleDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExampleService {

    public ExampleDto getExample(){
        ExampleDto exampleDto= ExampleDto.builder().name("Response").build();
        return  exampleDto;

    }

    public ExampleDto getExampleById(Long id) {
        ExampleDto exampleDto= ExampleDto.builder().name("Response").build();
        return  exampleDto;
    }

    public List<ExampleDto> searchExamples(String keyword) {
        ExampleDto exampleDto= ExampleDto.builder().name("Response").build();
        return  List.of(exampleDto);
    }

    public ExampleDto createExample(ExampleDto exampleDto) {
        return  exampleDto;
    }

    public ExampleDto updateExample(Long id, ExampleDto exampleDto) {
        return  exampleDto;
    }
}
