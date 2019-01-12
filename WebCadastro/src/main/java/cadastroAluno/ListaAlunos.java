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
		out.println("<html><body>");
		out.println("<table border=1>");
		out.println("<tr>");
		out.println("<td> Numero De Inscrição </td>");
		out.println("<td> nome </td>");
		out.println("<td> sobrenome </td>");
		out.println("<td> Sexo </td>");
		out.println("<td> Telefone </td>");
		out.println("<td> E-mail </td>");
		out.println("<td> Endereço </td>");
		out.println("<td> CEP </td>");
		out.println("<td> RG </td>");
		out.println("<td> CPF </td>");
		out.println("<td> Titullo de eleitor </td>");
		out.println("</tr>");
		out.println("<tr>");
		for (Aluno aluno : lista) {
			num++;
			out.println("<td><center>"+num+"</center></td>");
			out.println("<td>" + aluno.getNome() + "</td>");
			out.println("<td>" + aluno.getSobrenome() + "</td>");
			out.println("<td>" + aluno.getSexo() + "</td>");
			out.println("<td>" + aluno.getTelefone() + "</td>");
			out.println("<td>" + aluno.getEmail() + "</td>");
			out.println("<td>" + aluno.getEndereco() + "</td>");
			out.println("<td>" + aluno.getCEP() + "</td>");
			out.println("<td>" + aluno.getRG() + "</td>");
			out.println("<td>" + aluno.getCPF() + "</td>");
			out.println("<td>" + aluno.getTituloDeEleitor() + "</td>");
			out.println("</tr>");
		}

		out.println("</table>");

		out.println("</body></html>");

	}

}
