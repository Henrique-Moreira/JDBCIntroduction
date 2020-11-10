import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class contaDAO {
	public void inserir(Conta c) throws SQLException {
		Connection conexao = FabricaDeConexao.getConnection();
		String sql = "insert into conta" + "(titular,numero,agencia,limite,saldo)" + " values (?,?,?,?,?)";
		PreparedStatement stmt = conexao.prepareStatement(sql);
		stmt.setString(1, c.getTitular());
		stmt.setInt(2, c.getConta());
		stmt.setInt(3, c.getAgencia());
		stmt.setDouble(4, c.getLimite());
		stmt.setDouble(5, c.getSaldo());
		stmt.execute();
		stmt.close();
		conexao.close();
	}
	
	
	public void listagem() throws SQLException {
		  Connection conexao = FabricaDeConexao.getConnection();
		  String sql = "select * from conta";
		  PreparedStatement stmt = conexao.prepareStatement(sql);
		  ResultSet resultado = stmt.executeQuery();
		  while (resultado.next()) {
		    System.out.println(resultado.getString("titular") + " " + resultado.getDouble("saldo"));
		  }
		  resultado.close();
		  stmt.close();
		  conexao.close();
		}

	public void atualiza(Conta c, int id) throws SQLException {
		Connection conexao = FabricaDeConexao.getConnection();		
		String sql = "UPDATE conta SET titular = ?, numero = ?, agencia = ?, limite = ?, saldo = ? WHERE id = ?";
		PreparedStatement stmt = conexao.prepareStatement(sql);
		stmt.setString(1, c.getTitular());
		stmt.setInt(2, c.getConta());
		stmt.setInt(3, c.getAgencia());
		stmt.setDouble(4, c.getLimite());
		stmt.setDouble(5, c.getSaldo());
		stmt.setInt(6, id);
		stmt.execute();
		stmt.close();
		conexao.close();
	}
	
	
	public void exclui(int id) throws SQLException {
		Connection conexao = FabricaDeConexao.getConnection();
		String sql = "DELETE FROM conta WHERE id = ?";
		PreparedStatement stmt = conexao.prepareStatement(sql);
		stmt.setInt(1, id);
		stmt.execute();
		stmt.close();
		conexao.close();
	}
	
	public static void main(String[] args) {
		Conta c1 = new Conta("Rodolfo Rodrigues", 1, 2, 100.0,99.5);
		Conta c2 = new Conta("Hellen Alameda", 1, 2, 10.0,1.5);
		Conta c3 = new Conta("Roney Barbosa", 1, 2, 50.5,25.23);
		Conta c4 = new Conta("Barbara Naves", 1, 2, 55,10.9);
		Conta c5 = new Conta("Eduardo Jose", 1, 2, 85,71.25);
		contaDAO dao = new contaDAO();
		try {
			dao.inserir(c1);
			dao.inserir(c2);
			dao.inserir(c3);
			dao.inserir(c4);
			dao.inserir(c5);
			dao.exclui(5);
			dao.listagem();
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
}
