package com.example.dbservice.controller;

import com.example.dbservice.dto.LanguageDTO;
import com.example.dbservice.model.Language;
import com.example.dbservice.repository.LanguageRepository;
import com.example.dbservice.service.LanguageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/lookup")
public class LookupController {

    private static final Logger LOGGER = LoggerFactory.getLogger(LookupController.class);
    @Autowired
    LanguageService languageService;

    @GetMapping("/languages")
    public List<Language> getLanguages() {
        return languageService.AllLanguage();
    }

}
