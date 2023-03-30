package JDBC;

public class DAOTeste {
	public static void main(String[] args) {
		DAO dao = new DAO();
		
		String sql = "INSERT INTO pessoas (nome)"
				+ "VALUES (?)";
		
		System.out.println(dao.incluir(sql, "Betinho"));
		System.out.println(dao.incluir(sql, "Ze ninguem"));
		System.out.println(dao.incluir(sql, "Marcolino"));
	}
}
