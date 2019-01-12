package cadastroAluno;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns="/busca")
public class Busca extends HttpServlet {
	int num = 0;
		
			
		 private String cep;
		    private String logradouro;
		    private String complemento;
		    private String bairro;
		    private String localidade;
		    private String uf;
		 
		    //getters 
		 
		    @Override
		    public String toString() {
		        return "Endereco{" +
		                "cep='" + cep + '\'' +
		                ", logradouro='" + logradouro + '\'' +
		                ", complemento='" + complemento + '\'' +
		                ", bairro='" + bairro + '\'' +
		                ", localidade='" + localidade + '\'' +
		                ", uf='" + uf + '\'' +
		                '}';
		    }
		
		
	

}
