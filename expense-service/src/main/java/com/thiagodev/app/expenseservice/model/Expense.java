package com.thiagodev.app.expenseservice.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Version;

import lombok.Data;

@Entity
@Data
public class Expense implements Serializable {

	private static final long serialVersionUID = -8507622473380945770L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "sequence_expense")
	@SequenceGenerator(name = "sequence_expense", sequenceName = "seq_expense")
	@Column(name = "id_expense", precision = 12, scale = 0)
	private Long id;

	@Column(nullable = false)
	private double value;

	@Column(name = "user_id", nullable = false)
	private Long userId;

	@Column(name = "expense_date", nullable = false)
	private LocalDateTime expenseDate;

	@Version
	@Column(name = "version", nullable = false)
	private Integer version;

	@ManyToOne(optional = true)
	@JoinColumn(name = "id_category", referencedColumnName = "id_category", nullable = true)
	private Category category;
}
