package com.thiagodev.app.categoryservice.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Category implements Serializable {

	private static final long serialVersionUID = -448760385229721893L;

	@Id
	@SequenceGenerator(name = "sequence_category", sequenceName = "seq_category")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "sequence_category")
	@Column(name = "id_category", precision = 12, scale = 0)
	private Long id;

	@Column(nullable = false, length = 200)
	private String description;
}
