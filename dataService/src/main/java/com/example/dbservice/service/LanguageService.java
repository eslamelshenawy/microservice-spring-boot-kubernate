package com.example.dbservice.service;

import com.example.dbservice.dto.LanguageDTO;
import com.example.dbservice.model.Language;
import com.example.dbservice.repository.LanguageRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LanguageService {

    private static final Logger LOGGER = LoggerFactory.getLogger(LanguageService.class);
    @Autowired
    private LanguageRepository repository;

    public List<Language> AllLanguage() {
        LOGGER.info("LanguageService ", ".......getLanguages");
        List<Language> languages = repository.getLanguages();
        return languages;
    }


}
