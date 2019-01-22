package cadastroAluno;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.util.List;
import java.io.BufferedReader;
import java.io.FileOutputStream;

import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import br.com.caelum.stella.ValidationMessage;
import br.com.caelum.stella.boleto.Beneficiario;
import br.com.caelum.stella.boleto.Boleto;
import br.com.caelum.stella.boleto.Datas;
import br.com.caelum.stella.boleto.Endereco;
import br.com.caelum.stella.boleto.Pagador;
import br.com.caelum.stella.boleto.bancos.BancoDoBrasil;
import br.com.caelum.stella.boleto.transformer.GeradorDeBoleto;
import br.com.caelum.stella.boleto.transformer.GeradorDeBoletoHTML;
import br.com.caelum.stella.validation.CPFValidator;
import br.com.caelum.stella.validation.InvalidStateException;
import br.com.caelum.stella.validation.TituloEleitoralValidator;

@WebServlet(urlPatterns = "/novoAluno")
public class NovoAluno extends HttpServlet {

	// usando o json
	// ***************************************************************************************
	public static JSONObject readJsonFromUrl(String link) throws IOException, JSONException {
		URL url = new URL(link);
		URLConnection urlConn = url.openConnection();
		urlConn.addRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1)");
		InputStream is = urlConn.getInputStream();

		try {
			BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
			String jsonText = readAll(rd);
			JSONObject json = new JSONObject(jsonText);
			return json;
		} finally {
			is.close();
		}
	}

	private static String readAll(BufferedReader rd) throws IOException {
		StringBuilder sb = new StringBuilder();
		int cep;
		while ((cep = rd.read()) != -1) {
			sb.append((char) cep);
		}
		return sb.toString();
	}

	// ***************************************************************************************
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		PrintWriter out = resp.getWriter();

		// passando os dados do formulario para a servlet
//******************************************************************************			
		String nome = req.getParameter("nome");
		String sobrenome = req.getParameter("sobrenome");
		String sexo = req.getParameter("Sexo");
		String endereco = req.getParameter("Endereco");
		String bairro = req.getParameter("Bairro");
		String cidade = req.getParameter("Cidade");
		String estado = req.getParameter("Estado");
		String telefone = req.getParameter("Telefone");
		String email = req.getParameter("Email");
		String CEP = req.getParameter("CEP");
		String RG = req.getParameter("RG");
		String CPF = req.getParameter("CPF");
		String TituloDeEleitor = req.getParameter("TituloDeEleitor");
// **************************************************************************

		CPF = CPF.replace(".", "").replace(" ", "").replace("-", "");

		// começando o html do servlet
// *************************************************************
		out.println("<html><head>");
// *************************************************************

		// Ajeitando o cabeçalho atraves do css
// *************************************************************
		out.println("<style type=text/css>");
		out.print("#cabecalho"); // arrumando o cabeça~ho da pagina
		out.print("{");
		out.print("background-color: #21610B;");
		out.print("font-family: Arial;");
		out.print("padding: 0.5%;");
		out.print("color: white;");
		out.print("}");
		out.print("#InstFed"); // ajeitando o nome do intitudo federal no cabeçalho
		out.print("{");
		out.print("margin-top: 0.90%; ");
		out.print("position: absolute;");
		out.print("margin-left: 9%;");
		out.print("}");
		out.print("#IFCE"); // ajeitando o nome do ifce no cabeçalho
		out.print("{");
		out.print("margin-left: 9%; ");
		out.print("font-size: 150%; ");
		out.print("margin-top: 1%");
		out.print("}");
		out.print("#MinEdu"); // ajeitando o nome do Ministerio da educação no cabeçalho
		out.print("{");
		out.print("margin-top: -3%;");
		out.print("position: absolute;");
		out.print("margin-left: 9%;");
		out.print("}");
		out.print("</style>");
		out.print("</head>");
//******************************************************************************		

		out.print("<body>");
		out.print("<div id = cabecalho>"); // criando a div do cabeçalho
		out.print("<div id= InstFed >"); // criando a div do nome Isntitudo federal no cabeçalho
		out.print("<h1 style=\"font-size: 70%\">Instituto Federal de Educação, Ciência e Tecnologia do Ceará</h1>");
		out.print("</div>");
		out.print("<div id= IFCE >"); // criando a div do nome ifce do cabeçalho
		out.print("<h1 >IFCE</h1>");
		out.print("</div>");
		out.print("<div id= MinEdu>"); // criando a div do nome ministerio da educação do cabeçalho
		out.print("<h1 style=\"font-size: 80%\">MINISTÉRIO DA EDUCAÇÃO</h1>");
		out.print("</div>");
		out.print("</div>");

		Aluno e1 = new Aluno();

		// Verificando se algum campo ficou em branco
//***********************************************************************************
		if (nome.isEmpty()) {

			out.println("<center>");
			out.println(
					"<h1>O Campo de nome foi deixado em branco, por favor preencha todos os campus obrigadorios </h1>");
			out.println(
					"<button><a href=http://localhost:8080/website/Formulario.html>Formulario de inscrição</a></button>");
			out.println("</center>");
			out.println("</body></html>");
			return;
		}
		if (sobrenome.isEmpty()) {

			out.println("<center>");
			out.println(
					"<h1>O Campo sobrenome de eleitor foi deixado em branco, por favor preencha todos os campus obrigadorios </h1>");
			out.println(
					"<button><a href=http://localhost:8080/website/Formulario.html>Formulario de inscrição</a></button>");
			out.println("</center>");
			out.println("</body></html>");

			return;
		}
		if (sexo.isEmpty()) {
			out.println("<center>");
			out.println(
					"<h1>O Campo Sexo foi deixado em branco, por favor preencha todos os campus obrigadorios </h1>");
			out.println(
					"<button><a href=http://localhost:8080/website/Formulario.html>Formulario de inscrição</a></button>");
			out.println("</center>");
			out.println("</body></html>");

			return;
		}
		if (endereco.isEmpty()) {

			out.println("<center>");
			out.println(
					"<h1>O Campo endereço foi deixado em branco, por favor preencha todos os campus obrigadorios </h1>");
			out.println(
					"<button><a href=http://localhost:8080/website/Formulario.html>Formulario de inscrição</a></button>");
			out.println("</center>");

			out.println("</body></html>");
			return;
		}
		if (bairro.isEmpty()) {

			out.println("<center>");
			out.println(
					"<h1>O Campo Bairro foi deixado em branco, por favor preencha todos os campus obrigadorios </h1>");
			out.println(
					"<button><a href=http://localhost:8080/website/Formulario.html>Formulario de inscrição</a></button>");
			out.println("</center>");

			out.println("</body></html>");

			return;
		}

		if (CEP.isEmpty()) {

			out.println("<center>");
			out.println("<h1>O Campo CEP foi deixado em branco, por favor preencha todos os campus obrigadorios </h1>");
			out.println(
					"<button><a href=http://localhost:8080/website/Formulario.html>Formulario de inscrição</a></button>");
			out.println("</center>");
			out.println("</body></html>");

			return;
		}
		JSONObject json = null;
		String cj = CEP.replace(".", "").replace("-", "");

		try {
			json = readJsonFromUrl("https://viacep.com.br/ws/" + cj + "/json/");
		} catch (JSONException e) {
			out.println("<center>");
			out.println("<h1>CEP invalido </h1>");
			out.println(
					"<button><a href=http://localhost:8080/website/Formulario.html>Formulario de inscrição</a></button>");
			out.println("</center>");
			out.println("</body></html>");

			return;
		} catch (IOException e) {
			out.println("<center>");
			out.println("<h1>CEP invalido</h1>");
			out.println(
					"<button><a href=http://localhost:8080/website/Formulario.html>Formulario de inscrição</a></button>");
			out.println("</center>");
			out.println("</body></html>");

			return;
		}
		if (RG.isEmpty()) {
			out.println("<center>");
			out.println("<h1>O Campo RG foi deixado em branco, por favor preencha todos os campus obrigadorios </h1>");
			out.println(
					"<button><a href=http://localhost:8080/website/Formulario.html>Formulario de inscrição</a></button>");
			out.println("</center>");
			out.println("</body></html>");

			return;
		}
		if (CPF.isEmpty()) {

			out.println("<center>");
			out.println("<h1>O Campo CPF foi deixado em branco, por favor preencha todos os campus obrigadorios </h1>");
			out.println(
					"<button><a href=http://localhost:8080/website/Formulario.html>Formulario de inscrição</a></button>");
			out.println("</center>");
			out.println("</body></html>");

			return;
		}
		if (TituloDeEleitor.isEmpty()) {
			out.println("<center>");
			out.println(
					"<h1>O Campo Titulo de eleitor foi deixado em branco, por favor preencha todos os campus obrigadorios </h1>");
			out.println(
					"<button><a href=http://localhost:8080/website/Formulario.html>Formulario de inscrição</a></button>");
			out.println("</center>");
			out.println("</body></html>");

			return;
		}
//******************************************************************************

		// verificando se o CPF e o Titulo sâo validos
//******************************************************************************
		CPFValidator cpfvalidator = new CPFValidator();
		try {
			cpfvalidator.assertValid(CPF);
		} catch (InvalidStateException e) {
			List<ValidationMessage> cpfValidationMessages = cpfvalidator.invalidMessagesFor(CPF);

			if (!cpfValidationMessages.isEmpty()) {
				out.println("<center>");
				out.println("<h1>O Campo CPF esta invalido, por favor preencha novamente </h1>");
				out.println(
						"<button><a href=http://localhost:8080/website/Formulario.html>Formulario de inscrição</a></button>");
				out.println("</center>");
				out.println("</body></html>");
				return;
			}
		}

		TituloEleitoralValidator tituloValidator = new TituloEleitoralValidator();
		try {
			tituloValidator.assertValid(TituloDeEleitor);
		} catch (InvalidStateException e) {
			List<ValidationMessage> tituloValidationMessages = tituloValidator.invalidMessagesFor(CPF);

			if (!tituloValidationMessages.isEmpty()) {

				out.println("<center>");
				out.print("Titulo de eleitor invalido");
				out.println(
						"<button><a href=http://localhost:8080/website/Formulario.html>Formulario de inscrição</a></button>");
				out.println("</center>");

				out.println("</body></html>");
				return;
			}

		}
//******************************************************************************

		Banco b = new Banco();
		List<Aluno> lista = b.getAlunos();
		// verificando se ja o CPF ja foi cadastrado anteriomente
//******************************************************************************
		for (Aluno aluno : lista) {
			if (aluno.getCPF().contains(CPF)) {
				out.println("</center>");
				out.println("<h1>Esse CPF ja foi cadastrado</h1>");
				out.print("<button><a href=http://localhost:8080/website/lista?>Lista de cadastredos</a></button>");
				out.println(
						"<button><a href=http://localhost:8080/website/Formulario.html>Formulario de inscrição</a></button>");
				out.println("</center>");
				out.println("</body>");
				out.println("</html>");
				return;
			}
		}

		// adicionando o os campos do formulario no objeto aluno
//***********************************************************************************
		e1.setNome(nome);
		e1.setSobrenome(sobrenome);
		e1.setSexo(sexo);
		e1.setTelefone(telefone);
		e1.setEmail(email);
		e1.setEndereco(endereco);
		e1.setBairro(bairro);
		e1.setCidade(json.get("localidade").toString());
		e1.setEstado(json.get("uf").toString());
		e1.setCEP(CEP);
		e1.setRG(RG);
		e1.setCPF(CPF);
		e1.setTituloDeEleitor(TituloDeEleitor);
//***********************************************************************************

		b.adiciona(e1); // adicionando os dados do aluno no banco de dados
		out.println("<center>");

		// mensagem mostrada caso o aluno tenha sido cadastrado com sucesso
//******************************************************************************
		out.println("<h1>Oi " + nome + " " + sobrenome + " voçê foi cadastrado com sucesso!<br /></h1>");
		out.print("<button><a href=http://localhost:8080/website/lista?>Lista de cadastredos</a></button>");
		out.println(
				"<button><a href=http://localhost:8080/website/Formulario.html>Formulario de inscrição</a></button>");
		out.println("</center>");
		out.println("</body>");
		out.println("</html>");
		return;
	}
//******************************************************************************

}
