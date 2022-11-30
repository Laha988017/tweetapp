package com.tweetapp.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;

import java.util.Objects;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Users {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false)
	private Long id;
	private String email;
	@Column(unique = true)
	private String username;
	private String firstName;
	private String lastName;
	private String password;
	private String contactNumber;

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
		Users users = (Users) o;
		return id != null && Objects.equals(id, users.id);
	}

	@Override
	public int hashCode() {
		return getClass().hashCode();
	}
}
