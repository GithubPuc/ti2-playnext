package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.Interesse;

public class InteresseDAO extends DAO {

	public boolean inserirInteresse(Interesse interesse) {
		boolean status = false;
		try {
			PreparedStatement ps = conexao.prepareStatement(
					"INSERT INTO Interesse (idUsuario, idJogo, tipo, peso) VALUES (?, ?, ?, ?);");
			ps.setLong(1, interesse.getIdUsuario());
			ps.setLong(2, interesse.getIdJogo());
			ps.setInt(3, interesse.getTipo());
			ps.setInt(4, interesse.getPeso());
			ps.executeUpdate();
			ps.close();
			status = true;
		} catch (SQLException u) {
			throw new RuntimeException(u);
		}
		return status;
	}

	public boolean atualizarInteresse(Interesse interesse) {
		boolean status = false;
		try {
			PreparedStatement ps = conexao.prepareStatement(
					"UPDATE Interesse SET idUsuario = ?, idJogo = ?, tipo = ?, peso = ? WHERE idInteresse = ?");
			ps.setLong(1, interesse.getIdUsuario());
			ps.setLong(2, interesse.getIdJogo());
			ps.setInt(3, interesse.getTipo());
			ps.setInt(4, interesse.getPeso());
			ps.setLong(5, interesse.getIdInteresse());
			ps.executeUpdate();
			ps.close();
			status = true;
		} catch (SQLException u) {
			throw new RuntimeException(u);
		}
		return status;
	}

	public boolean excluirInteresse(long idInteresse) {
		boolean status = false;
		try {
			Statement st = conexao.createStatement();
			st.executeUpdate("DELETE FROM Interesse WHERE idInteresse = " + idInteresse);
			st.close();
			status = true;
		} catch (SQLException u) {
			throw new RuntimeException(u);
		}
		return status;
	}

	private Interesse newInteresseFromRS(ResultSet rs) throws SQLException {
		return new Interesse(
				rs.getLong("idInteresse"),
				rs.getLong("idUsuario"),
				rs.getLong("idJogo"),
				rs.getInt("tipo"),
				rs.getInt("peso"));
	}

	public Interesse[] listarInteresses() {
		Interesse[] interesses = null;
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = st.executeQuery("SELECT * FROM Interesse");
			if (rs.next()) {
				rs.last();
				interesses = new Interesse[rs.getRow()];
				rs.beforeFirst();
				for (int i = 0; rs.next(); i++)
					interesses[i] = newInteresseFromRS(rs);
			}
			st.close();
		} catch (SQLException u) {
			throw new RuntimeException(u);
		}
		return interesses;
	}

	public Interesse lerInteresse(long idInteresse) {
		Interesse interesse = null;
		try {
			Statement st = conexao.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM Interesse WHERE idInteresse = " + idInteresse);
			if (rs.next())
				interesse = newInteresseFromRS(rs);
			st.close();
		} catch (SQLException u) {
			throw new RuntimeException(u);
		}
		return interesse;
	}
}