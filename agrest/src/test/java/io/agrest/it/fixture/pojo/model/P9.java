package io.agrest.it.fixture.pojo.model;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;

import io.agrest.annotation.AgAttribute;

public class P9 {

	private String name;
	
	private OffsetDateTime created;
	
	private LocalDateTime createdLocal;

	@AgAttribute
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@AgAttribute
	public OffsetDateTime getCreated() {
		return created;
	}

	public void setCreated(OffsetDateTime created) {
		this.created = created;
	}

	@AgAttribute
	public LocalDateTime getCreatedLocal() {
		return createdLocal;
	}

	public void setCreatedLocal(LocalDateTime createdLocal) {
		this.createdLocal = createdLocal;
	}
}