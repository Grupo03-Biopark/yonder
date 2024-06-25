package projeto.Grupo3.YonderBase;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class YonderBaseApplicationTests {

	@Test
	void contextLoads() {
	}





	@Test
	void tdd(){
		int id, res;

		res=sefez(id);

		
		assertEquals(1, res);


	}




	private int sefez(int id) {
		return 1;
	}









}
