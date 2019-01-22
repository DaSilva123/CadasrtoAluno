package cadastroAluno;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/lista")
public class ListaAlunos extends HttpServlet {
	int num = 0;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Banco banco = new Banco();
		List<Aluno> lista = banco.getAlunos();
		PrintWriter out = response.getWriter();
		out.println("<html><head>");
		// Ajeitando o cabeçalho atraves do css
		// *************************************************************
		out.println("<style type=text/css>");
		out.print("#cabecalho");
		out.print("{");
		out.print("background-color: #21610B;");
		out.print("font-family: Arial;");
		out.print("padding: 0.5%;");
		out.print("color: white;");
		out.print("}");
		out.print("#InstFed");
		out.print("{");
		out.print("margin-top: 0.90%; ");
		out.print("position: absolute;");
		out.print("margin-left: 9%;");
		out.print("}");
		out.print("#IFCE");
		out.print("{");
		out.print("margin-left: 9%; ");
		out.print("font-size: 150%; ");
		out.print("margin-top: 1%");
		out.print("}");
		out.print("#MinEdu");
		out.print("{");
		out.print("margin-top: -3%;");
		out.print("position: absolute;");
		out.print("margin-left: 9%;");
		out.print("}");
		out.print("</style>");
		// *************************************************************

		out.print("</head>");
		out.print("<body>");
		out.print("<div id = cabecalho>");
		out.print("<div id= InstFed >");
		out.print("<h1 style=\"font-size: 70%\">Instituto Federal de Educação, Ciência e Tecnologia do Ceará</h1>");
		out.print("</div>");
		out.print("<div id= IFCE >");
		out.print("<h1 >IFCE</h1>");
		out.print("</div>");
		out.print("<div id= MinEdu>");
		out.print("<h1 style=\"font-size: 80%\">MINISTÉRIO DA EDUCAÇÃO</h1>");
		out.print("</div>");
		out.print("</div>");
		out.print("<center>");
		out.println("<table border=1>");
		out.println("<tr>");
		// *************************************************************
		// colocando os nomes das tabelas
		out.println("<td> nome </td>");
		out.println("<td> sobrenome </td>");
		out.println("<td> Sexo </td>");
		out.println("<td> Telefone </td>");
		out.println("<td> E-mail </td>");
		out.println("<td> Endereço </td>");
		out.print("<td>Bairro</td>");
		out.print("<td>Cidade</td>");
		out.print("<td>Estado</td>");
		out.println("<td> CEP </td>");
		out.println("<td> RG </td>");
		out.println("<td> CPF </td>");
		out.println("<td> Titullo de eleitor </td>");
		out.println("</tr>");
		out.println("<tr>");
		// *************************************************************

		// preenchendo as tabelas
		// *************************************************************
		for (Aluno aluno : lista) {

			out.println("<td>" + aluno.getNome() + "</td>");
			out.println("<td>" + aluno.getSobrenome() + "</td>");
			out.println("<td>" + aluno.getSexo() + "</td>");
			out.println("<td>" + aluno.getTelefone() + "</td>");
			out.println("<td>" + aluno.getEmail() + "</td>");
			out.println("<td>" + aluno.getEndereco() + "</td>");
			out.println("<td>" + aluno.getBairro() + "</td>");
			out.println("<td>" + aluno.getCidade() + "</td>");
			out.println("<td>" + aluno.getEstado() + "</td>");
			out.println("<td>" + aluno.getCEP() + "</td>");
			out.println("<td>" + aluno.getRG() + "</td>");
			out.println("<td>" + aluno.getCPF() + "</td>");
			out.println("<td>" + aluno.getTituloDeEleitor() + "</td>");
			out.println("</tr>");
		}
		// *************************************************************
		out.println("</table>");
		out.print("<button><a href=http://localhost:8080/website/Formulario1.html>Formulario de incrição</a></button>");
		out.print("</center>");
		out.println("</body></html>");

	}

}
