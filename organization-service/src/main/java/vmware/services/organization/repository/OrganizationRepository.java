package vmware.services.organization.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vmware.services.organization.entity.Organization;
@Repository
public interface OrganizationRepository extends JpaRepository<Organization, Long> {

}
