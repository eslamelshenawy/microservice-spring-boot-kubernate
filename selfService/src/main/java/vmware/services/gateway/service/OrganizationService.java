package vmware.services.gateway.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vmware.services.gateway.client.EmployeeClient;
import vmware.services.gateway.controller.OrganizationController;
import vmware.services.gateway.exceptions.RuntimeBusinessException;
import vmware.services.gateway.entity.Organization;
import vmware.services.gateway.repository.OrganizationRepository;
import vmware.services.gateway.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static vmware.services.gateway.exceptions.ErrorCodes.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.NOT_ACCEPTABLE;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = {RuntimeBusinessException.class, Exception.class})
public class OrganizationService {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrganizationController.class);

    @Autowired
    private OrganizationRepository organizationRepository;
    @Autowired
    EmployeeClient employeeClient;

    public ResponseEntity<Response<Boolean>> addOrganization(Organization input) {
        organizationRepository.save(input);
        Response<Boolean> response = Response.<Boolean>builder().ResponseMessage("success add ").data(true).ResponseCode(200).build();
        return ResponseEntity.ok(response);
    }

    public ResponseEntity<Response<List<Organization>>> getAllOrganization() {
        List<Organization> organizations = organizationRepository.findAll();
        Response<List<Organization>> response = Response.<List<Organization>>builder().data(organizations).ResponseMessage("all organization").ResponseCode(200).build();
        return ResponseEntity.ok(response);
    }

    public ResponseEntity<Response<Optional<Organization>>> getOrganizationById(Long orgId) {

        Optional<Organization> organization = Optional.ofNullable(organizationRepository.findById(orgId)
                .orElseThrow(() -> new RuntimeBusinessException(NOT_ACCEPTABLE, org$0001, orgId)));
        Response<Optional<Organization>> response = Response.<Optional<Organization>>builder().ResponseCode(200).ResponseMessage("organization ").data(organization).build();
        return ResponseEntity.ok(response);
    }

    public ResponseEntity<Response<Optional<Organization>>> findByIdWithEmployees(Long id) {
        LOGGER.info("Organization find: id={}", id);
        Optional<Organization> organization = organizationRepository.findById(id);
        if (organization.isPresent()) {
            Organization o = organization.get();
            o.setEmployees(employeeClient.findByOrganization(o.getId()));
        } else {
            return null;
        }
        Response<Optional<Organization>> response = Response.<Optional<Organization>>builder().ResponseCode(200).ResponseMessage("organization ").data(organization).build();
        return ResponseEntity.ok(response);
    }



}
