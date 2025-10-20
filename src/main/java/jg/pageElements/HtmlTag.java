package jg.pageElements;

public enum HtmlTag {
	DIV("div"),
	UL("ul"),
	LI("li");
	
	
	private String tag;

	private HtmlTag(String tag) {
		this.tag = tag;
	}

	public String getTag() {
		return tag;
	}

	
	
	
	

}
