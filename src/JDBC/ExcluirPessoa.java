package JDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class ExcluirPessoa {
	public static void main(String[] args) throws SQLException {
		Scanner entrada = new Scanner(System.in);
		System.out.print("Informe o codigo da pessoa que deseja excluir: ");
		int codigo =  Integer.parseInt(entrada.nextLine().trim());
		
		String select = "SELECT * FROM pessoas WHERE codigo = ?";
		String delete = "DELETE FROM pessoas WHERE codigo = ?";
		
		Connection conexao = FabricaConexao.getConexao();
		
		PreparedStatement stmt = conexao.prepareStatement(select);
		stmt.setInt(1, codigo);
		ResultSet consulta = stmt.executeQuery();
		
		if (consulta.next()) {
			System.out.println("A pessoa encontrada foi: " + consulta.getString("nome"));
			System.out.print("Realmente deseja excluir? (S/n): ");
			String resposta = entrada.nextLine();
			if ("n".equalsIgnoreCase(resposta)) {
				System.out.println("Pessoa não foi excluida");
			} else {
				stmt.close();
				stmt = conexao.prepareStatement(delete);
				stmt.setInt(1, codigo);
				stmt.execute();
				System.out.println("Pessoa foi excluida!");
			}
			
			stmt.close();
			conexao.close();
			entrada.close();
		}
	}
}
