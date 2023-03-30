package JDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class ConsultarPessoas2 {
	public static void main(String[] args) throws SQLException {
		Scanner entrada = new Scanner(System.in);
		System.out.print("Informe uma parte do nome da pessoa que deseja consultar: ");
		String parteNome =  entrada.nextLine().trim();
		
		String sql = "SELECT * FROM pessoas WHERE nome like ?";
		
		Connection conexao = FabricaConexao.getConexao();
		
		PreparedStatement stmt = conexao.prepareStatement(sql);
		stmt.setString(1, "%" + parteNome + "%");
		
		ResultSet resultado = stmt.executeQuery();
		
		while (resultado.next()) {
			System.out.println(resultado.getInt("codigo") + "==>" + resultado.getString("nome"));
		}
		
		stmt.close();
		conexao.close();
		entrada.close();
	}
}
