package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.Perfil;

public class PerfilDAO extends DAO {

	public boolean inserirPerfil(Perfil Perfil) {
		boolean status = false;
		try {
			PreparedStatement ps = conexao.prepareStatement(
					"INSERT INTO Perfil (username, email, senha, tipo) VALUES (?, ?, ?, ?);");
			ps.setString(1, Perfil.getUsername());
			ps.setString(2, Perfil.getEmail());
			ps.setBytes(3, Perfil.getSenha());
			ps.setInt(4, Perfil.getTipo());
			ps.executeUpdate();
			ps.close();
			status = true;
		} catch (SQLException u) {
			throw new RuntimeException(u);
		}
		return status;
	}

	public boolean atualizarPerfil(Perfil Perfil) {
		boolean status = false;
		try {
			PreparedStatement ps = conexao.prepareStatement(
					"UPDATE Perfil SET username = ?, email = ?, senha = ?, tipo = ? WHERE idPerfil = ?");
			ps.setString(1, Perfil.getUsername());
			ps.setString(2, Perfil.getEmail());
			ps.setBytes(3, Perfil.getSenha());
			ps.setInt(4, Perfil.getTipo());
			ps.setLong(5, Perfil.getIdPerfil());
			ps.executeUpdate();
			ps.close();
			status = true;
		} catch (SQLException u) {
			throw new RuntimeException(u);
		}
		return status;
	}

	public boolean excluirPerfil(long idPerfil) {
		boolean status = false;
		try {
			Statement st = conexao.createStatement();
			st.executeUpdate("DELETE FROM Perfil WHERE idPerfil = " + idPerfil);
			st.close();
			status = true;
		} catch (SQLException u) {
			throw new RuntimeException(u);
		}
		return status;
	}

	private Perfil newPerfilFromRS(ResultSet rs) throws SQLException {
		return new Perfil(
				rs.getLong("idPerfil"),
				rs.getString("username"),
				rs.getString("email"),
				rs.getBytes("senha"),
				rs.getInt("tipo"));
	}

	public Perfil[] listarPerfils() {
		Perfil[] Perfils = null;
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = st.executeQuery("SELECT * FROM Perfil");
			if (rs.next()) {
				rs.last();
				Perfils = new Perfil[rs.getRow()];
				rs.beforeFirst();
				for (int i = 0; rs.next(); i++)
					Perfils[i] = newPerfilFromRS(rs);
			}
			st.close();
		} catch (SQLException u) {
			throw new RuntimeException(u);
		}
		return Perfils;
	}

	public Perfil lerPerfil(long idPerfil) {
		Perfil Perfil = null;
		try {
			Statement st = conexao.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM Perfil WHERE idPerfil = " + idPerfil);
			if (rs.next())
				Perfil = newPerfilFromRS(rs);
			st.close();
		} catch (SQLException u) {
			throw new RuntimeException(u);
		}
		return Perfil;
	}
}