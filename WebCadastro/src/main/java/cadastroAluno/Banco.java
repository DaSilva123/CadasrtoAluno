package cadastroAluno;

import java.util.ArrayList;
import java.util.List;

public class Banco {

	private static List<Aluno> listaDeAlunos = new ArrayList<>();
	

	public void adiciona(Aluno e1) {
		
		listaDeAlunos.add(e1);
	}
	
	List<Aluno>getAlunos(){
		return Banco.listaDeAlunos;
	}

}
