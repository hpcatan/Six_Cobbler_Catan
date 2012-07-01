package com.games.catan.framework.flow;

import java.util.HashSet;
import java.util.Set;

public class Operate extends BaseFlow {
	private String name;
	private String processClassName;
	private Set<NextStatus> nextStatuses;
	private String id;
	
	public Operate(){
		nextStatuses = new HashSet<NextStatus>();
	}
	
	public Set<NextStatus> getNextStatuses() {
		return nextStatuses;
	}

	public void setNextStatuses(Set<NextStatus> nextStatuses) {
		this.nextStatuses = nextStatuses;
	}
	
	public void addNextStatus(NextStatus nextStatus){
		this.nextStatuses.add(nextStatus);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getProcessClassName() {
		return processClassName;
	}
	public void setProcessClassName(String processClassName) {
		this.processClassName = processClassName;
	}
	
	@Override
	public String toString() {
		return "Operate [name=" + name + ", processClassName="
				+ processClassName + ", nextStatuses=" + nextStatuses + ", id="
				+ id + "]";
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
		Operate other = (Operate) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
