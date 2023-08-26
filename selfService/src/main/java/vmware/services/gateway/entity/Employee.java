package vmware.services.gateway.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "employee")
@Entity
public class Employee {

	@Id
	private Long id;
	private String name;
	private int age;
	private String position;

	@ManyToOne
	@JoinColumn(name="organizationId", nullable=false)
	private Organization organization;

}
