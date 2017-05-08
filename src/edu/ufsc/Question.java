package edu.ufsc;

public class Question {

	private String property;
	private String clazz;
	private Integer cardinality;
	private String value;

	public Question(String property, String clazz, Integer cardinality) {
		super();
		this.property = property;
		this.clazz = clazz;
		this.cardinality = cardinality;
		this.value = buildValue();
	}

	@Override
	public String toString() {
		return value;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((value == null) ? 0 : value.hashCode());
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
		Question other = (Question) obj;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		return true;
	}

	private String buildValue() {
		return cardinality == null ? String.format("%s %s", property, clazz) : String.format("%s %s %s", property, cardinality, clazz);
	}

}
