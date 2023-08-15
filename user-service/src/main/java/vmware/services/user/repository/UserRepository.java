package vmware.services.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vmware.services.user.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
}
