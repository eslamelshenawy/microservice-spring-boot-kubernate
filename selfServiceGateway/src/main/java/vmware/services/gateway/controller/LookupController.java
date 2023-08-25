package vmware.services.gateway.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vmware.services.gateway.entity.Language;
import vmware.services.gateway.entity.User;
import vmware.services.gateway.response.Response;
import vmware.services.gateway.service.LanguageService;

import java.util.List;

@RestController
@RequestMapping(value = "/lookup")
public class LookupController {

    private static final Logger LOGGER = LoggerFactory.getLogger(LookupController.class);
    @Autowired
    LanguageService languageService;

    @GetMapping("/languages")
    public ResponseEntity<Response<List<Language>>>  getLanguages() {
        return languageService.AllLanguage();
    }

}
