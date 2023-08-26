package vmware.services.gateway.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vmware.services.gateway.entity.Organization;
import vmware.services.gateway.response.Response;
import vmware.services.gateway.service.OrganizationService;
import static vmware.services.gateway.config.Translator.translate;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
public class OrganizationController {

	@Autowired
	OrganizationService organizationService;

	@GetMapping("/message")
	public String getMessage(){
		return translate("message.greeting");
	}

	@PostMapping("/add")
	public ResponseEntity<Response<Boolean>> addOrganization(@Valid  @RequestBody Organization organization) {
		return organizationService.addOrganization(organization);
	}

	@GetMapping("/all")
	public ResponseEntity<Response<List<Organization>>> findAll() {
		return organizationService.getAllOrganization();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Response<Optional<Organization>>> findById(@PathVariable("id") Long id) {
		return organizationService.getOrganizationById(id);
	}

	@GetMapping("/{id}/with-employees")
	public ResponseEntity<Response<Optional<Organization>>> findByIdWithEmployees(@PathVariable("id") Long id) {
		return organizationService.findByIdWithEmployees(id);
	}
}
