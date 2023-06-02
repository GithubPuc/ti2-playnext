package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.Aventura;

public class AventuraDAO extends DAO {

	public boolean inserirAventura(Aventura aventura) {
		boolean status = false;
		try {
			PreparedStatement ps = conexao.prepareStatement(
					"INSERT INTO Aventura (idUsuario, idJogo, confianca, tipo) VALUES (?, ?, ?, ?, ?);");
			ps.setLong(1, aventura.getIdUsuario());
			ps.setLong(2, aventura.getIdJogo());
			ps.setInt(3, aventura.getConfianca());
			ps.setInt(4, aventura.getTipo());
			ps.executeUpdate();
			ps.close();
			status = true;
		} catch (SQLException u) {
			throw new RuntimeException(u);
		}
		return status;
	}

	public boolean atualizarAventura(Aventura aventura) {
		boolean status = false;
		try {
			PreparedStatement ps = conexao.prepareStatement(
					"UPDATE Aventura SET idUsuario = ?, idJogo = ?, confianca = ?, tipo = ? WHERE idAventura = ?");
			ps.setLong(1, aventura.getIdUsuario());
			ps.setLong(2, aventura.getIdJogo());
			ps.setInt(3, aventura.getConfianca());
			ps.setInt(4, aventura.getTipo());
			ps.setLong(5, aventura.getIdAventura());
			ps.executeUpdate();
			ps.close();
			status = true;
		} catch (SQLException u) {
			throw new RuntimeException(u);
		}
		return status;
	}

	public boolean excluirAventura(long idAventura) {
		boolean status = false;
		try {
			Statement st = conexao.createStatement();
			st.executeUpdate("DELETE FROM Aventura WHERE idAventura = " + idAventura);
			st.close();
			status = true;
		} catch (SQLException u) {
			throw new RuntimeException(u);
		}
		return status;
	}

	private Aventura newAventuraFromRS(ResultSet rs) throws SQLException {
		return new Aventura(
				rs.getLong("idAventura"),
				rs.getLong("idUsuario"),
				rs.getLong("idJogo"),
				rs.getInt("confianca"),
				rs.getInt("tipo"));
	}

	public Aventura[] listarAventuras() {
		Aventura[] aventuras = null;
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = st.executeQuery("SELECT * FROM Aventura");
			if (rs.next()) {
				rs.last();
				aventuras = new Aventura[rs.getRow()];
				rs.beforeFirst();
				for (int i = 0; rs.next(); i++)
					aventuras[i] = newAventuraFromRS(rs);
			}
			st.close();
		} catch (SQLException u) {
			throw new RuntimeException(u);
		}
		return aventuras;
	}

	public Aventura lerAventura(long idAventura) {
		Aventura aventura = null;
		try {
			Statement st = conexao.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM Aventura WHERE idAventura = " + idAventura);
			if (rs.next())
				aventura = newAventuraFromRS(rs);
			st.close();
		} catch (SQLException u) {
			throw new RuntimeException(u);
		}
		return aventura;
	}

	public Aventura[] listarAventurasParaUsuario(Long idUsuario) {
		Aventura[] aventuras = null;
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = st.executeQuery("SELECT * FROM Aventura WHERE idUsuario = " + idUsuario);
			if (rs.next()) {
				rs.last();
				aventuras = new Aventura[rs.getRow()];
				rs.beforeFirst();
				for (int i = 0; rs.next(); i++)
					aventuras[i] = newAventuraFromRS(rs);
			}
			st.close();
		} catch (SQLException u) {
			throw new RuntimeException(u);
		}
		return aventuras;
	}
}
