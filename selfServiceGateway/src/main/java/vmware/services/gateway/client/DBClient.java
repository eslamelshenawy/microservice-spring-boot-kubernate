package vmware.services.gateway.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import vmware.services.gateway.dto.LoginRequest;
import vmware.services.gateway.entity.Employee;
import vmware.services.gateway.entity.Language;

import java.util.List;
import java.util.Map;

@FeignClient(name = "db")
public interface DBClient {

    @GetMapping("/Login")
    Map<String, Object> login(@RequestBody LoginRequest loginRequest, @RequestHeader("Accept-Language") String lang);

    @GetMapping("/lookup/languages")
    List<Language> getLanguages();

    @GetMapping("/info/{pUserId}")
    Employee getInfo(@RequestHeader("Accept-Language") String lang ,@PathVariable Integer pUserId);

}
