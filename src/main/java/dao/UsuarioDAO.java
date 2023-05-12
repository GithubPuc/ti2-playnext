package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.Usuario;

public class UsuarioDAO {
	private Connection conexao;

	public UsuarioDAO() {
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

	public boolean inserirUsuario(Usuario usuario) {
		boolean status = false;
		try {
			Statement st = conexao.createStatement();
			st.executeUpdate(
					"INSERT INTO \"Usuario\" (username, email, senha, grupo)"
							+ " VALUES ('"
							+ usuario.getUsername() + "', '"
							+ usuario.getEmail() + "', '"
							+ usuario.getSenha() + "',"
							+ usuario.getGrupo() + ");");
			st.close();
			status = true;
		} catch (SQLException u) {
			throw new RuntimeException(u);
		}
		return status;
	}

	public boolean atualizarUsuario(Usuario usuario) {
		boolean status = false;
		try {
			Statement st = conexao.createStatement();
			String sql = "UPDATE \"Usuario\" SET username = '"
					+ usuario.getUsername()
					+ "', email = '"
					+ usuario.getEmail()
					+ "', senha = '"
					+ usuario.getSenha()
					+ "', grupo = "
					+ usuario.getGrupo()
					+ " WHERE \"idUsuario\" = " + usuario.getIdUsuario();
			st.executeUpdate(sql);
			st.close();
			status = true;
		} catch (SQLException u) {
			throw new RuntimeException(u);
		}
		return status;
	}

	public boolean excluirUsuario(long idUsuario) {
		boolean status = false;
		try {
			Statement st = conexao.createStatement();
			st.executeUpdate("DELETE FROM \"Usuario\" WHERE \"idUsuario\" = " + idUsuario);
			st.close();
			status = true;
		} catch (SQLException u) {
			throw new RuntimeException(u);
		}
		return status;
	}

	private Usuario newUsuarioFromRS(ResultSet rs) throws SQLException {
		return new Usuario(rs.getLong("idUsuario"), rs.getString("username"), rs.getString("email"),
				rs.getString("senha"), rs.getInt("grupo"));
	}

	public Usuario[] listarUsuarios() {
		Usuario[] usuarios = null;

		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = st.executeQuery("SELECT * FROM \"Usuario\"");
			if (rs.next()) {
				rs.last();
				usuarios = new Usuario[rs.getRow()];
				rs.beforeFirst();

				for (int i = 0; rs.next(); i++) {
					usuarios[i] = newUsuarioFromRS(rs);
				}
			}
			st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return usuarios;
	}

	public Usuario lerUsuario(long idUsuario) {
		Usuario usuario = null;
		try {
			Statement st = conexao.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM \"Usuario\" WHERE \"idUsuario\" = " + idUsuario);
			if (rs.next()) {
				usuario = newUsuarioFromRS(rs);
			}
			st.close();
		} catch (SQLException u) {
			throw new RuntimeException(u);
		}
		return usuario;
	}
}