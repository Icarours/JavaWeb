package bean;

public class HelpDetail {

	private String title;

	private String content;

	public HelpDetail() {
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getTitle() {
		return title;
	}

	@Override
	public String toString() {
		return "HelpDetail [title=" + title + ", content=" + content + "]";
	}

	public void setTitle(String title) {
		this.title = title;
	}

}
