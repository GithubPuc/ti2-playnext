package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.Home;

public class HomeDAO extends DAO {

	public boolean inserirHome(Home home) {
		boolean status = false;
		try {
			PreparedStatement ps = conexao.prepareStatement(
					"INSERT INTO Home (titulo, descricao, url, display, pontuacao) VALUES (?, ?, ?, ?, ?);");
			ps.setString(1, home.getTitulo());
			ps.setString(2, home.getDescricao());
			ps.setString(3, home.getUrl());
			ps.setString(4, home.getDisplay());
			ps.setInt(5, home.getPontuacao());
			ps.executeUpdate();
			ps.close();
			status = true;
		} catch (SQLException u) {
			throw new RuntimeException(u);
		}
		return status;
	}

	public boolean atualizarHome(Home home) {
		boolean status = false;
		try {
			PreparedStatement ps = conexao.prepareStatement(
					"UPDATE Home SET titulo = ?, descricao = ?, url = ?, display = ?, pontuacao = ? WHERE idHome = ?");
			ps.setString(1, home.getTitulo());
			ps.setString(2, home.getDescricao());
			ps.setString(3, home.getUrl());
			ps.setString(4, home.getDisplay());
			ps.setInt(5, home.getPontuacao());
			ps.setLong(6, home.getIdHome());
			ps.executeUpdate();
			ps.close();
			status = true;
		} catch (SQLException u) {
			throw new RuntimeException(u);
		}
		return status;
	}

	public boolean excluirHome(long idHome) {
		boolean status = false;
		try {
			Statement st = conexao.createStatement();
			st.executeUpdate("DELETE FROM Home WHERE idHome = " + idHome);
			st.close();
			status = true;
		} catch (SQLException u) {
			throw new RuntimeException(u);
		}
		return status;
	}

	private Home newHomeFromRS(ResultSet rs) throws SQLException {
		return new Home(
				rs.getLong("idHome"),
				rs.getString("titulo"),
				rs.getString("descricao"),
				rs.getString("url"),
				rs.getString("display"),
				rs.getInt("pontuacao"));
	}

	public Home[] listarHomes() {
		Home[] homes = null;
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = st.executeQuery("SELECT * FROM Home");
			if (rs.next()) {
				rs.last();
				homes = new Home[rs.getRow()];
				rs.beforeFirst();
				for (int i = 0; rs.next(); i++)
					homes[i] = newHomeFromRS(rs);
			}
			st.close();
		} catch (SQLException u) {
			throw new RuntimeException(u);
		}
		return homes;
	}

	public Home lerHome(long idHome) {
		Home home = null;
		try {
			Statement st = conexao.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM Home WHERE idHome = " + idHome);
			if (rs.next())
				home = newHomeFromRS(rs);
			st.close();
		} catch (SQLException u) {
			throw new RuntimeException(u);
		}
		return home;
	}
}