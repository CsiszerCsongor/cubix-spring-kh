package hu.webuni.airport.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Address {

	@Id
	@GeneratedValue
	@EqualsAndHashCode.Include()
	private Long id;

	private String country;
	private String city;
	private String street;
	private String zip;
	private LocalDateTime birthday;

	@Override
	public boolean equals(final Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) {
			return false;
		}
		final Address address = (Address) o;
		return id != null && Objects.equals(id, address.id);
	}

	@Override
	public int hashCode() {
		return getClass().hashCode();
	}

}
