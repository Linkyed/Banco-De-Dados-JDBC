package JDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class AtualizarPessoa {
	public static void main(String[] args) throws SQLException {
		Scanner entrada = new Scanner(System.in);
		System.out.print("Informe o codigo da pessoa que deseja alterar o nome: ");
		int codigo =  Integer.parseInt(entrada.nextLine().trim());
		
		String update = "UPDATE pessoas SET nome = ? WHERE codigo = ?";
		
		String select = "SELECT * FROM pessoas WHERE codigo = ?";
		
		Connection conexao = FabricaConexao.getConexao();
		
		PreparedStatement stmt = conexao.prepareStatement(select);
		stmt.setInt(1, codigo);
		ResultSet consulta = stmt.executeQuery();
		
		if (consulta.next()) {
			System.out.println("A pessoa selecionada foi: " + consulta.getString("nome"));
			System.out.print("Agora Informe novo nome da pessoa: ");
			String novoNome = entrada.nextLine().trim();
			
			stmt.close();
			stmt = conexao.prepareStatement(update);
			stmt.setString(1, novoNome);
			stmt.setInt(2, codigo);
			
			stmt.execute();
			
			System.out.println("Nome trocado com sucesso!");
		} else {
			System.out.println("Pessoa não encontrada!");
		}
		stmt.close();
		conexao.close();
		entrada.close();	
	}
}
