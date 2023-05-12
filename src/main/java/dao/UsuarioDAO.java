package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.Usuario;

public class UsuarioDAO extends DAO {

	public boolean inserirUsuario(Usuario usuario) {
		boolean status = false;
		try {
			PreparedStatement ps = conexao.prepareStatement(
					"INSERT INTO Usuario (username, email, senha, grupo) VALUES (?, ?, ?, ?);");
			ps.setString(1, usuario.getUsername());
			ps.setString(2, usuario.getEmail());
			ps.setString(3, usuario.getSenha());
			ps.setInt(4, usuario.getGrupo());
			ps.executeUpdate();
			ps.close();
			status = true;
		} catch (SQLException u) {
			throw new RuntimeException(u);
		}
		return status;
	}

	public boolean atualizarUsuario(Usuario usuario) {
		boolean status = false;
		try {
			PreparedStatement ps = conexao.prepareStatement(
					"UPDATE Usuario SET username = ?, email = ?, senha = ?, grupo = ? WHERE idUsuario = ?");
			ps.setString(1, usuario.getUsername());
			ps.setString(2, usuario.getEmail());
			ps.setString(3, usuario.getSenha());
			ps.setInt(4, usuario.getGrupo());
			ps.setLong(5, usuario.getIdUsuario());
			ps.executeUpdate();
			ps.close();
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
			st.executeUpdate("DELETE FROM Usuario WHERE idUsuario = " + idUsuario);
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
			ResultSet rs = st.executeQuery("SELECT * FROM Usuario");
			if (rs.next()) {
				rs.last();
				usuarios = new Usuario[rs.getRow()];
				rs.beforeFirst();
				for (int i = 0; rs.next(); i++)
					usuarios[i] = newUsuarioFromRS(rs);
			}
			st.close();
		} catch (SQLException u) {
			throw new RuntimeException(u);
		}
		return usuarios;
	}

	public Usuario lerUsuario(long idUsuario) {
		Usuario usuario = null;
		try {
			Statement st = conexao.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM Usuario WHERE idUsuario = " + idUsuario);
			if (rs.next())
				usuario = newUsuarioFromRS(rs);
			st.close();
		} catch (SQLException u) {
			throw new RuntimeException(u);
		}
		return usuario;
	}
}