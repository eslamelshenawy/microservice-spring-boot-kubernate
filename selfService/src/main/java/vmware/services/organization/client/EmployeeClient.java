package vmware.services.organization.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import vmware.services.organization.entity.Employee;

import java.util.Set;

@FeignClient(name = "db")
public interface EmployeeClient {

	@GetMapping("/organization/{organizationId}")
	Set<Employee> findByOrganization(@PathVariable("organizationId") Long organizationId);
	
}
