import static org.junit.Assert.*;

import org.junit.Test;


public class AlgTest {

	@Test
	public void test() {
		Main main = new Main();
		Line[] lines = main.genLines(200000);
		main.bruteForce(lines);
	}

}
