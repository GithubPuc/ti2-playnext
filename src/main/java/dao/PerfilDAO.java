package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.Perfil;

public class PerfilDAO extends DAO {

	public boolean inserirPerfil(Perfil perfil) {
		boolean status = false;
		try {
			PreparedStatement ps = conexao.prepareStatement(
					"INSERT INTO Perfil (username, steamId) VALUES (?, ?);");
			ps.setString(1, perfil.getUsername());
			ps.setString(2, perfil.getSteamId());
			ps.executeUpdate();
			ps.close();
			status = true;
		} catch (SQLException u) {
			throw new RuntimeException(u);
		}
		return status;
	}

	public boolean atualizarPerfil(Perfil perfil) {
		boolean status = false;
		try {
			PreparedStatement ps = conexao.prepareStatement(
					"UPDATE Perfil SET username = ?, steamId = ? WHERE idUsuario = ?");
			ps.setString(1, perfil.getUsername());
			ps.setString(2, perfil.getSteamId());
			ps.setLong(3, perfil.getIdUsuario());
			ps.executeUpdate();
			ps.close();
			status = true;
		} catch (SQLException u) {
			throw new RuntimeException(u);
		}
		return status;
	}

	public boolean excluirPerfil(long idUsuario) {
		boolean status = false;
		try {
			Statement st = conexao.createStatement();
			st.executeUpdate("DELETE FROM Perfil WHERE idUsuario = " + idUsuario);
			st.close();
			status = true;
		} catch (SQLException u) {
			throw new RuntimeException(u);
		}
		return status;
	}

	private Perfil newPerfilFromRS(ResultSet rs) throws SQLException {
		return new Perfil(
				rs.getLong("idUsuario"),
				rs.getString("username"),
				rs.getString("steamId"));
	}

	public Perfil[] listarPerfis() {
		Perfil[] perfis = null;
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = st.executeQuery("SELECT * FROM Perfil");
			if (rs.next()) {
				rs.last();
				perfis = new Perfil[rs.getRow()];
				rs.beforeFirst();
				for (int i = 0; rs.next(); i++)
					perfis[i] = newPerfilFromRS(rs);
			}
			st.close();
		} catch (SQLException u) {
			throw new RuntimeException(u);
		}
		return perfis;
	}

	public Perfil lerPerfil(long idUsuario) {
		Perfil perfil = null;
		try {
			Statement st = conexao.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM Perfil WHERE idUsuario = " + idUsuario);
			if (rs.next())
				perfil = newPerfilFromRS(rs);
			st.close();
		} catch (SQLException u) {
			throw new RuntimeException(u);
		}
		return perfil;
	}

	public Perfil lerPerfilPorSteamId(String steamId) {
		Perfil perfil = null;
		try {
			PreparedStatement ps = conexao.prepareStatement("SELECT * FROM Perfil WHERE steamId = ?");
			ps.setString(1, steamId);
			var rs = ps.executeQuery();
			if (rs.next())
				perfil = newPerfilFromRS(rs);
			ps.close();
		} catch (SQLException u) {
			throw new RuntimeException(u);
		}
		return perfil;
	}
}