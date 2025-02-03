package org.demo.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * @author Boniface Chacha
 */
@Entity
@Table(name = "technology")
public class Technology implements Serializable {

	@Id
	@GeneratedValue
	private Long id;

	@Version
	private Integer version;

	@NotBlank
	@NotNull
	@Column(unique = true)
	private String name;

	@Lob
	private String description;

	@ManyToOne
	private Technology parent;

	public Technology() {
	}

	public Technology(String name, String description, Technology parent) {
		this.name = name;
		this.description = description;
		this.parent = parent;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Technology getParent() {
		return parent;
	}

	public void setParent(Technology parent) {
		this.parent = parent;
	}

	public String toString() {
		return name;
	}

	public boolean hasParent() {
		return parent != null;
	}

	public boolean isRoot() {
		return !hasParent();
	}
}
