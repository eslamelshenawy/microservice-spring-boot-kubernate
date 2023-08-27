package vmware.services.gateway.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import vmware.services.gateway.client.DBClient;
import vmware.services.gateway.entity.Employee;
import vmware.services.gateway.entity.Language;
import vmware.services.gateway.response.Response;

import java.util.List;

@Service
public class LookupService {

    private static final Logger LOGGER = LoggerFactory.getLogger(LookupService.class);
    @Autowired
    DBClient dbClient;

    public ResponseEntity<Response<List<Language>>> AllLanguage() {
        LOGGER.info("LookupService ", ".......getLanguages");
        List<Language> languages = dbClient.getLanguages();
        Response<List<Language>> response = Response.<List<Language>>builder().ResponseMessage("success").data(languages).ResponseCode(200).build();
        return ResponseEntity.ok(response);
    }
    public ResponseEntity<Response<Employee>> getInfo(String lang ,Integer pUserId) {
        LOGGER.info("LookupService ", ".......getInfo");
        Employee languages = dbClient.getInfo(lang,pUserId);
        Response<Employee> response = Response.<Employee>builder().ResponseMessage("success").data(languages).ResponseCode(200).build();
        return ResponseEntity.ok(response);
    }


}
