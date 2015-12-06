import static org.junit.Assert.*;

import org.junit.Test;


public class AlgTest {

	@Test
	public void test() {
		System.out.println("running");
		Main main = new Main();
		Line[] lines = main.genLines(10);
		main.bruteForce(lines);
	}

}
