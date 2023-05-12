package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.Recomendacao;

public class RecomendacaoDAO extends DAO {

	public boolean inserirRecomendacao(Recomendacao recomendacao) {
		boolean status = false;
		try {
			PreparedStatement ps = conexao.prepareStatement(
					"INSERT INTO Recomendacao (idUsuario, idJogo, confianca) VALUES (?, ?, ?, ?);");
			ps.setLong(1, recomendacao.getIdUsuario());
			ps.setLong(2, recomendacao.getIdJogo());
			ps.setInt(3, recomendacao.getConfianca());
			ps.executeUpdate();
			ps.close();
			status = true;
		} catch (SQLException u) {
			throw new RuntimeException(u);
		}
		return status;
	}

	public boolean atualizarRecomendacao(Recomendacao recomendacao) {
		boolean status = false;
		try {
			PreparedStatement ps = conexao.prepareStatement(
					"UPDATE Recomendacao SET idUsuario = ?, idJogo = ?, confianca = ? WHERE idRecomendacao = ?");
			ps.setLong(1, recomendacao.getIdUsuario());
			ps.setLong(2, recomendacao.getIdJogo());
			ps.setInt(3, recomendacao.getConfianca());
			ps.setLong(5, recomendacao.getIdRecomendacao());
			ps.executeUpdate();
			ps.close();
			status = true;
		} catch (SQLException u) {
			throw new RuntimeException(u);
		}
		return status;
	}

	public boolean excluirRecomendacao(long idRecomendacao) {
		boolean status = false;
		try {
			Statement st = conexao.createStatement();
			st.executeUpdate("DELETE FROM Recomendacao WHERE idRecomendacao = " + idRecomendacao);
			st.close();
			status = true;
		} catch (SQLException u) {
			throw new RuntimeException(u);
		}
		return status;
	}

	private Recomendacao newRecomendacaoFromRS(ResultSet rs) throws SQLException {
		return new Recomendacao(
				rs.getLong("idRecomendacao"),
				rs.getLong("idUsuario"),
				rs.getLong("idJogo"),
				rs.getInt("confianca"));
	}

	public Recomendacao[] listarRecomendacoes() {
		Recomendacao[] recomendacoes = null;
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = st.executeQuery("SELECT * FROM Recomendacao");
			if (rs.next()) {
				rs.last();
				recomendacoes = new Recomendacao[rs.getRow()];
				rs.beforeFirst();
				for (int i = 0; rs.next(); i++)
					recomendacoes[i] = newRecomendacaoFromRS(rs);
			}
			st.close();
		} catch (SQLException u) {
			throw new RuntimeException(u);
		}
		return recomendacoes;
	}

	public Recomendacao lerRecomendacao(long idRecomendacao) {
		Recomendacao recomendacao = null;
		try {
			Statement st = conexao.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM Recomendacao WHERE idRecomendacao = " + idRecomendacao);
			if (rs.next())
				recomendacao = newRecomendacaoFromRS(rs);
			st.close();
		} catch (SQLException u) {
			throw new RuntimeException(u);
		}
		return recomendacao;
	}
}
