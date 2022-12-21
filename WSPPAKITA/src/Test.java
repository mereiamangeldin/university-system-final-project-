import java.io.IOException;

public class Test {
	public static void main(String[] args) {
		try {
			(new ManagerDemo()).run();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
