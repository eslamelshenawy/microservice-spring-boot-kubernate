package vmware.services.organization.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vmware.services.organization.util.Constant;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "organization")
@Entity
public class Organization {
	@Id
	@Column(name = "id", nullable = false, columnDefinition = "BIGINT AUTO_INCREMENT")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@NotBlank(message = Constant.NOT_BLANK + " .. name")
	private String name;
	@NotBlank(message = Constant.NOT_BLANK + "  .. address")
	private String address;
	@OneToMany(mappedBy="organization")
	private Set<Employee> employees;

}
