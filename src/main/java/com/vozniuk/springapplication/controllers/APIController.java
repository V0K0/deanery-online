package com.vozniuk.springapplication.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.vozniuk.springapplication.json.SubjectSerializer;
import com.vozniuk.springapplication.domain.data.university.Subject;
import com.vozniuk.springapplication.service.impl.SubjectServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


@Controller
public class APIController {

    @Autowired
    private SubjectServiceImpl subjectServiceImpl;

    @ResponseBody
    @GetMapping(value = "api/subject", produces = MediaType.APPLICATION_JSON_VALUE)
    public String getSubjects(
            @RequestParam(name = "page", defaultValue = "1") String page, Model model) {
        return getSubjectsJSONAsString(page);
    }

    private String getSubjectsJSONAsString(String page) {
        int pageNumIndex = Integer.parseInt(page) - 1;
        Pageable pageable = PageRequest.of(pageNumIndex, 10);
        Page<Subject> subjects = subjectServiceImpl.findAllLimit(pageable);
        List<Subject> subjectList = subjects.toList();
        final ObjectMapper mapper = new ObjectMapper();
        SimpleModule simpleModule = new SimpleModule();
        simpleModule.addSerializer(Subject.class, new SubjectSerializer());
        mapper.registerModule(simpleModule);
        try {
            return mapper.writeValueAsString(subjectList);
        } catch (JsonProcessingException e) {
            return null;
        }

    }



}
