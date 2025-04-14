import javax.swing.UIManager;

public class Main {{
	try {
	    UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
	} catch (Exception e) {
	    e.printStackTrace();
	}}
	

	public static Fenster LF;
	
	public static void main(String[] args) {
		LF = new Fenster("Pedram der Mulmer");
	}
}
//Mulm