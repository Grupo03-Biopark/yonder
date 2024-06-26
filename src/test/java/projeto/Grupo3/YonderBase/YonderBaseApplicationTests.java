package projeto.Grupo3.YonderBase;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import main.java.projeto.yonder.controller.NivelamentoUsuarioController;



@SpringBootTest
class YonderBaseApplicationTests {

	@Test
	void contextLoads() {
	}





	@Test
	void tddd(){
		int id = 1, res;

		res=sefez(id);
		
		assertEquals(1, res,"fez");

	}


	private int sefez(int id) {
		
		verSeUsuarioFoiAvaliado()

		
		return id;
	}









}
