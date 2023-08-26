package vmware.services.gateway.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import vmware.services.gateway.client.DBClient;
import vmware.services.gateway.entity.Language;
import vmware.services.gateway.entity.User;
import vmware.services.gateway.response.Response;

import java.util.List;

@Service
public class LanguageService {

    private static final Logger LOGGER = LoggerFactory.getLogger(LanguageService.class);
    @Autowired
    DBClient dbClient;

    public ResponseEntity<Response<List<Language>>> AllLanguage() {
        LOGGER.info("LanguageService ", ".......getLanguages");
        List<Language> languages = dbClient.getLanguages();
        Response<List<Language>> response = Response.<List<Language>>builder().ResponseMessage("success").data(languages).ResponseCode(200).build();
        return ResponseEntity.ok(response);
    }


}
