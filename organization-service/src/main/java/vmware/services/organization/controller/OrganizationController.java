package vmware.services.organization.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vmware.services.organization.entity.Organization;
import vmware.services.organization.response.Response;
import vmware.services.organization.service.OrganizationService;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
public class OrganizationController {

	@Autowired
	OrganizationService organizationService;
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
}
