package cadastroAluno;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.caelum.stella.ValidationMessage;
import br.com.caelum.stella.validation.CPFValidator;
import br.com.caelum.stella.validation.InvalidStateException;
import br.com.caelum.stella.validation.TituloEleitoralValidator;

@WebServlet(urlPatterns = "/novoAluno")
public class NovoAluno extends HttpServlet {

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		PrintWriter out = resp.getWriter();
		String nome = req.getParameter("nome");
		String sobrenome = req.getParameter("sobrenome");
		String sexo = req.getParameter("Sexo");
		String endereco = req.getParameter("endereco");
		String telefone = req.getParameter("Telefone");
		String email = req.getParameter("Email");
		String CEP = req.getParameter("CEP");
		String RG = req.getParameter("RG");
		String CPF = req.getParameter("CPF");
		String TituloDeEleitor = req.getParameter("TituloDeEleitor");

		out.print("<html>");
		out.println("<head>");
		out.println("<title>Primeira Servlet</title>");
		out.println("</style>");
		out.println("</head>");
		out.println("<body>");
		Aluno e1 = new Aluno();
		e1.setNome(nome);
		if (nome.isEmpty()) {
			out.println(
					"<h1>O Campo de nome foi deixado em branco, por favor preencha todos os campus obrigadorios </h1>");
			out.print("<a href=http://localhost:8080/website/Formulario1.html><br />voltar</a>");
			out.println("</body>");
			out.println("</html>");
		}
		e1.setSobrenome(sobrenome);
		if (sobrenome.isEmpty()) {
			out.println(
					"<h1>O Campo Sobrenome foi deixado em branco, por favor preencha todos os campus obrigadorios </h1>");
			out.print("<a href=http://localhost:8080/website/Formulario1.html><br />voltar</a>");
			out.println("</body>");
			out.println("</html>");
		}
		e1.setSexo(sexo);
		if (sexo.isEmpty()) {
			out.println(
					"<h1>O Campo Sexo deixado em branco, por favor preencha todos os campus obrigadorios </h1>");
			out.print("<a href=http://localhost:8080/website/Formulario1.html><br />voltar</a>");
			out.println("</body>");
			out.println("</html>");
		}	
		e1.setTelefone(telefone);
		e1.setEmail(email);
		e1.setEndereco(endereco);
		if (endereco.isEmpty()) {
			out.println(
					"<h1>O Campo de endereço foi deixado em branco, por favor preencha todos os campus obrigadorios </h1>");
			out.print("<a href=http://localhost:8080/website/Formulario1.html><br />voltar</a>");
			out.println("</body>");
			out.println("</html>");
		}
		e1.setCEP(CEP);
		if (CEP.isEmpty()) {

			out.println(
					"<h1>O Campo de CEP foi deixado em branco, por favor preencha todos os campus obrigadorios </h1>");
			out.print("<a href=http://localhost:8080/website/Formulario1.html><br />voltar</a>");
			out.println("</body>");
			out.println("</html>");

		}
		e1.setRG(RG);
		if(RG.isEmpty()) {
			
			out.println("<h1>O Campo do RG foi deixado em branco, por favor preencha todos os campus obrigadorios </h1>");
			out.print("<a href=http://localhost:8080/website/Formulario1.html><br />voltar</a>");
			out.println("</body>");
			out.println("</html>");
			
		}
		e1.setCPF(CPF);
		if(CPF.isEmpty()) {
			
			out.println("<h1>O Campo do CPF foi deixado em branco, por favor preencha todos os campus obrigadorios </h1>");
			out.print("<a href=http://localhost:8080/website/Formulario1.html><br />voltar</a>");
			out.println("</body>");
			out.println("</html>");
		}	
		CPFValidator cpfvalidator = new CPFValidator();
		try {
			cpfvalidator.assertValid(CPF);
		} catch (InvalidStateException e) {
			List<ValidationMessage> cpfValidationMessages = cpfvalidator.invalidMessagesFor(CPF);

			if (!cpfValidationMessages.isEmpty()) {
				out.print("cpf invalido");
				out.print("<a href = http://localhost:8080/website/Formulario1.html>voltar</a>");
				out.println("</body></html>");
				return;
			}
		}

		e1.setTituloDeEleitor(TituloDeEleitor);
		if(TituloDeEleitor.isEmpty()) {
			
			out.println("<h1>O Campo do Titulo de eleitor foi deixado em branco, por favor preencha todos os campus obrigadorios </h1>");
			out.print("<a href=http://localhost:8080/website/Formulario1.html><br />voltar</a>");
			out.println("</body>");
			out.println("</html>");
		}
		TituloEleitoralValidator tituloValidator = new TituloEleitoralValidator();
		try {
			tituloValidator.assertValid(TituloDeEleitor);
		} catch (InvalidStateException e) {
			List<ValidationMessage> tituloValidationMessages = tituloValidator.invalidMessagesFor(CPF);

			if (!tituloValidationMessages.isEmpty()) {
				out.print("Titulo de eleitor invalido");
				out.print("<a href = http://localhost:8080/website/Formulario1.html>voltar</a>");
				out.println("</body></html>");
				return;
			}

		}

		

			Banco b1 = new Banco();
			b1.adiciona(e1);
			out.println("<h1>Oi " + nome + " " + sobrenome + " voçe foi cadastrado com sucesso!</h1>");
			out.println("</body>");
			out.println("</html>");
		}

	
}
