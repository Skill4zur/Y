package menu;

public class TextInput extends DisplayElement{

	private String placeholder;
	private String text;
	
	public TextInput(int relativeX, int relativeY, Menu menu) {
		// TODO Auto-generated constructor stub
		this.setAbsoluteX(relativeX + menu.getAbsoluteX());
		this.setAbsoluteY(relativeY + menu.getAbsoluteY());
		this.placeholder = "";
		
	}
	
	public TextInput(int relativeX, int relativeY, Menu menu, String placeholder) {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void render() {
		// TODO Auto-generated method stub
		
	}
	
	public String getPlaceholder() { return placeholder; }

	public String getText() { return text; }

	public void setText(String text) { this.text = text; }

}
