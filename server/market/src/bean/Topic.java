package bean;

import constant.URL;

public class Topic {
	private int id;
	private String name;
	private String pic;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	@Override
	public String toString() {
		return "Topic [id=" + id + ", name=" + name + ", pic=" + pic + "]";
	}

}
