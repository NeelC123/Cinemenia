package com.cinemania.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class CinemaniaUser {
	@Id
	@SequenceGenerator(name = "CinemaniaUser_sequence", sequenceName = "CinemaniaUser_sequence", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CinemaniaUser_sequence")
	private Long userId;
	private String userName;
	private String userEmail;
	private String password;

}
