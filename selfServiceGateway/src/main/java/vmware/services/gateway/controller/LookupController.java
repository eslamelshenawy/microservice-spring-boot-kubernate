package vmware.services.gateway.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vmware.services.gateway.entity.Employee;
import vmware.services.gateway.entity.Language;
import vmware.services.gateway.response.Response;
import vmware.services.gateway.service.LookupService;

import java.util.List;

@RestController
@RequestMapping(value = "/lookup")
public class LookupController {

    private static final Logger LOGGER = LoggerFactory.getLogger(LookupController.class);
    @Autowired
    LookupService lookupService;

    @GetMapping("/languages")
    public ResponseEntity<Response<List<Language>>>  getLanguages() {
        return lookupService.AllLanguage();
    }
    @GetMapping("/info/{pUserId}")
    public ResponseEntity<Response<Employee>>  getInfo(@RequestHeader("Accept-Language") String lang , @PathVariable Integer pUserId) {
        return lookupService.getInfo(lang,pUserId);
    }

}
