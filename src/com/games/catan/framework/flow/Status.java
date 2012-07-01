package com.games.catan.framework.flow;

import java.util.HashSet;
import java.util.Set;

public class Status extends BaseFlow {
	private String id;
	private String name;
	private Set<Operate> operates;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Status() {
		operates = new HashSet<Operate>();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Operate> getOperates() {
		return operates;
	}

	public void addOperate(Operate operate){
		this.operates.add(operate);
	}

	@Override
	public String toString() {
		return "Status [name=" + name + ", operates=" + operates + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Status other = (Status) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
