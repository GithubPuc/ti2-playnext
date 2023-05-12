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
					"INSERT INTO \"Jogo\" (titulo, descricao, url, display, pontuacao) VALUES (?, ?, ?, ?, ?);");
			ps.setString(1, jogo.getTitulo());
			ps.setString(2, jogo.getDescricao());
			ps.setString(3, jogo.getUrl());
			ps.setString(4, jogo.getDisplay());
			ps.setInt(5, jogo.getPontuacao());
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
					"UPDATE \"Jogo\" SET titulo = ?, descricao = ?, url = ?, display = ?, pontuacao = ? WHERE \"idJogo\" = ?");
			ps.setString(1, jogo.getTitulo());
			ps.setString(2, jogo.getDescricao());
			ps.setString(3, jogo.getUrl());
			ps.setString(4, jogo.getDisplay());
			ps.setInt(5, jogo.getPontuacao());
			ps.setLong(6, jogo.getIdJogo());
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
			ResultSet rs = st.executeQuery("SELECT * FROM \"Jogo\" WHERE \"idJogo\" = " + idJogo);
			if (rs.next())
				jogo = newJogoFromRS(rs);
			st.close();
		} catch (SQLException u) {
			throw new RuntimeException(u);
		}
		return jogo;
	}
}