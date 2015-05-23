package Datatypes;

public class IsDescribed<T> {
	private String tag;
	private T label;
	public String getTag() {
		return tag;
	}
	public void setTag(String name) {
		this.tag = name;
	}
	public T getLabel() {
		return label;
	}
	public void setLabel(T label) {
		this.label = label;
	}
}
