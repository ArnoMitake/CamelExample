import org.apache.camel.spring.Main;

public class AppMain {
	public static void main(String[] args) throws Exception {
		Main main = new Main();
		main.setApplicationContextUri("System_Camel_Context.xml");
		main.run();

	}	
}
