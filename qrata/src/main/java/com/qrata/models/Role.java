package com.qrata.models;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "roles", schema = "public")
public class Role extends CommonEntity implements Serializable{
	
	private static final long serialVersionUID = 326571666707801514L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="roles_id_seq")
	@SequenceGenerator(name="roles_id_seq", initialValue=1, sequenceName="roles_id_seq", allocationSize=1)
	@Column(name = "id", unique = true, nullable = false)
	private short id;
	
	@Column(name = "name", length = 50)
	private String name;
	
	@ManyToMany(fetch =  FetchType.LAZY, mappedBy = "roles")
    private Set<User> users = new HashSet<User>(0);

	public Role() {
	}

	public Role(short id) {
		this.id = id;
	}
	
	public short getId() {
		return id;
	}

	public void setId(short id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

	@Override
	public void setSortName(String sortName){
		super.setSortName(sortName);
	}

    @Override
    public String toString() {
        return name;
    }

}
