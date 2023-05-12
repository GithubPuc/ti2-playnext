package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.Jogo;

public class JogoDAO {
	private Connection conexao;

	public JogoDAO() {
		conexao = null;
	}

	public boolean conectar() {
		String driverName = "org.postgresql.Driver";
		String serverName = "192.168.18.236";
		String mydatabase = "teste";
		int porta = 5432;
		String url = "jdbc:postgresql://" + serverName + ":" + porta + "/" + mydatabase;
		String username = "ti2cc";
		String password = "ti@cc";
		boolean status = false;

		try {
			Class.forName(driverName);
			conexao = DriverManager.getConnection(url, username, password);
			status = (conexao != null);
			System.out.println("Conexão efetuada com o postgres!");
		} catch (ClassNotFoundException e) {
			System.err.println("Conexão NÃO efetuada com o postgres -- Driver não encontrado -- " + e.getMessage());
		} catch (SQLException e) {
			System.err.println("Conexão NÃO efetuada com o postgres -- " + e.getMessage());
		}
		return status;
	}

	public boolean close() {
		boolean status = false;
		try {
			conexao.close();
			status = true;
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		return status;
	}

	public boolean inserirJogo(Jogo jogo) {
		boolean status = false;
		try {
			Statement st = conexao.createStatement();
			st.executeUpdate(
					"INSERT INTO \"Jogo\" (titulo, descricao, url, display, pontuacao)"
							+ " VALUES ('"
							+ jogo.getTitulo() + "', '"
							+ jogo.getDescricao() + "', '"
							+ jogo.getUrl() + "', '"
							+ jogo.getDisplay() + "', "
							+ jogo.getPontuacao() + ");");
			st.close();
			status = true;
		} catch (SQLException u) {
			throw new RuntimeException(u);
		}
		return status;
	}

	public boolean atualizarJogo(Jogo jogo) {
		boolean status = false;
		try {
			Statement st = conexao.createStatement();
			String sql = "UPDATE \"Jogo\" SET titulo = '"
					+ jogo.getTitulo()
					+ "', descricao = '"
					+ jogo.getDescricao()
					+ "', url = '"
					+ jogo.getUrl()
					+ "', display = '"
					+ jogo.getDisplay()
					+ "', pontuacao = "
					+ jogo.getPontuacao()
					+ " WHERE \"idJogo\" = " + jogo.getIdJogo();
			st.executeUpdate(sql);
			st.close();
			status = true;
		} catch (SQLException u) {
			throw new RuntimeException(u);
		}
		return status;
	}

	public boolean excluirJogo(long idJogo) {
		boolean status = false;
		try {
			Statement st = conexao.createStatement();
			st.executeUpdate("DELETE FROM \"Jogo\" WHERE \"idJogo\" = " + idJogo);
			st.close();
			status = true;
		} catch (SQLException u) {
			throw new RuntimeException(u);
		}
		return status;
	}

	private Jogo newJogoFromRS(ResultSet rs) throws SQLException {
		return new Jogo(rs.getLong("idJogo"), rs.getString("titulo"), rs.getString("descricao"),
				rs.getString("url"), rs.getString("display"), rs.getInt("pontuacao"));
	}

	public Jogo[] listarJogos() {
		Jogo[] jogos = null;

		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = st.executeQuery("SELECT * FROM \"Jogo\"");
			if (rs.next()) {
				rs.last();
				jogos = new Jogo[rs.getRow()];
				rs.beforeFirst();

				for (int i = 0; rs.next(); i++) {
					jogos[i] = newJogoFromRS(rs);
				}
			}
			st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return jogos;
	}

	public Jogo lerJogo(long idJogo) {
		Jogo jogo = null;
		try {
			Statement st = conexao.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM \"Jogo\" WHERE \"idJogo\" = " + idJogo);
			if (rs.next()) {
				jogo = newJogoFromRS(rs);
			}
			st.close();
		} catch (SQLException u) {
			throw new RuntimeException(u);
		}
		return jogo;
	}
}