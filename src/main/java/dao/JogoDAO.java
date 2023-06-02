package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.Jogo;

public class JogoDAO extends DAO {

	public boolean inserirJogo(Jogo jogo) {
		boolean status = false;
		try {
			PreparedStatement ps = conexao.prepareStatement(
					"INSERT INTO Jogo (steamidjogo, titulo, descricao, url, display, pontuacao) VALUES (?, ?, ?, ?, ?, ?);");
			ps.setLong(1, jogo.getSteamIdJogo());
			ps.setString(2, jogo.getTitulo());
			ps.setString(3, jogo.getDescricao());
			ps.setString(4, jogo.getUrl());
			ps.setString(5, jogo.getDisplay());
			ps.setInt(6, jogo.getPontuacao());
			ps.executeUpdate();
			ps.close();
			status = true;
		} catch (SQLException u) {
			throw new RuntimeException(u);
		}
		return status;
	}

	public boolean atualizarJogo(Jogo jogo) {
		boolean status = false;
		try {
			PreparedStatement ps = conexao.prepareStatement(
					"UPDATE Jogo SET steamidjogo= ?, titulo = ?, descricao = ?, url = ?, display = ?, pontuacao = ? WHERE idJogo = ?");
			ps.setLong(1, jogo.getSteamIdJogo());
			ps.setString(2, jogo.getTitulo());
			ps.setString(3, jogo.getDescricao());
			ps.setString(4, jogo.getUrl());
			ps.setString(5, jogo.getDisplay());
			ps.setInt(6, jogo.getPontuacao());
			ps.setLong(7, jogo.getIdJogo());
			ps.executeUpdate();
			ps.close();
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
			st.executeUpdate("DELETE FROM Jogo WHERE idJogo = " + idJogo);
			st.close();
			status = true;
		} catch (SQLException u) {
			throw new RuntimeException(u);
		}
		return status;
	}

	private Jogo newJogoFromRS(ResultSet rs) throws SQLException {
		return new Jogo(
				rs.getLong("idJogo"), rs.getLong("SteamIdJogo"),
				rs.getString("titulo"),
				rs.getString("descricao"),
				rs.getString("url"),
				rs.getString("display"),
				rs.getInt("pontuacao"));
	}

	public Jogo[] listarJogos() {
		Jogo[] jogos = null;
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = st.executeQuery("SELECT * FROM Jogo");
			if (rs.next()) {
				rs.last();
				jogos = new Jogo[rs.getRow()];
				rs.beforeFirst();
				for (int i = 0; rs.next(); i++)
					jogos[i] = newJogoFromRS(rs);
			}
			st.close();
		} catch (SQLException u) {
			throw new RuntimeException(u);
		}
		return jogos;
	}

	public Jogo lerJogo(long idJogo) {
		Jogo jogo = null;
		try {
			Statement st = conexao.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM Jogo WHERE idJogo = " + idJogo);
			if (rs.next())
				jogo = newJogoFromRS(rs);
			st.close();
		} catch (SQLException u) {
			throw new RuntimeException(u);
		}
		return jogo;
	}

	public Jogo lerJogoPorTituloParcial(String titulo) {
		Jogo jogo = null;
		try {
			PreparedStatement ps = conexao.prepareStatement("SELECT * FROM Jogo WHERE titulo LIKE ?");
			ps.setString(1, "%" + titulo + "%");
			ResultSet rs = ps.executeQuery();
			if (rs.next())
				jogo = newJogoFromRS(rs);
			ps.close();
		} catch (SQLException u) {
			throw new RuntimeException(u);
		}
		return jogo;
	}

	public Jogo lerJogoPorTituloExato(String titulo) {
		Jogo jogo = null;
		try {
			PreparedStatement ps = conexao.prepareStatement("SELECT * FROM Jogo WHERE titulo = ?");
			ps.setString(1, titulo);
			ResultSet rs = ps.executeQuery();
			if (rs.next())
				jogo = newJogoFromRS(rs);
			ps.close();
		} catch (SQLException u) {
			throw new RuntimeException(u);
		}
		return jogo;
	}

	public Jogo lerJogoPorSteamId(long steamIdJogo) {
		Jogo jogo = null;
		try {
			Statement st = conexao.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM Jogo WHERE steamIdJogo = " + steamIdJogo);
			if (rs.next())
				jogo = newJogoFromRS(rs);
			st.close();
		} catch (SQLException u) {
			throw new RuntimeException(u);
		}
		return jogo;
	}
}