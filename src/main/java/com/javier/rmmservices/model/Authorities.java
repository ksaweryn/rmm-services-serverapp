package com.javier.rmmservices.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Authorities implements Serializable {

	private static final long serialVersionUID = 6162325674463366796L;
	
	@Id
	private String username;
	@Id
	private String authority;
	@OneToOne
	private Users user;

}
