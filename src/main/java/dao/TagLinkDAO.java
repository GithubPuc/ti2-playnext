package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.TagLink;

public class TagLinkDAO extends DAO {

	public boolean inserirTagLink(TagLink ttagLink) {
		boolean status = false;
		try {
			PreparedStatement ps = conexao.prepareStatement("INSERT INTO TagLink (idJogo, idTag) VALUES (?, ?);");
			ps.setLong(1, ttagLink.getIdJogo());
			ps.setLong(2, ttagLink.getIdTag());
			ps.executeUpdate();
			ps.close();
			status = true;
		} catch (SQLException u) {
			throw new RuntimeException(u);
		}
		return status;
	}

	public boolean atualizarTagLink(TagLink atual, long novoIdTag) {
		boolean status = false;
		try {
			PreparedStatement ps = conexao.prepareStatement(
					"UPDATE TagLink SET idTag = ? WHERE idJogo = ? AND idTag = ?");
			ps.setLong(1, novoIdTag);
			ps.setLong(2, atual.getIdJogo());
			ps.setLong(3, atual.getIdTag());
			ps.executeUpdate();
			ps.close();
			status = true;
		} catch (SQLException u) {
			throw new RuntimeException(u);
		}
		return status;
	}

	public boolean excluirTagLink(long idJogo, long idTag) {
		boolean status = false;
		try {
			Statement st = conexao.createStatement();
			st.executeUpdate("DELETE FROM TagLink WHERE idJogo = " + idJogo + " AND idTag = " + idTag);
			st.close();
			status = true;
		} catch (SQLException u) {
			throw new RuntimeException(u);
		}
		return status;
	}

	private TagLink newTagLinkFromRS(ResultSet rs) throws SQLException {
		return new TagLink(
				rs.getLong("idJogo"),
				rs.getLong("idTag"));
	}

	public TagLink[] listarTagLinks() {
		TagLink[] ttagLinks = null;
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = st.executeQuery("SELECT * FROM TagLink");
			if (rs.next()) {
				rs.last();
				ttagLinks = new TagLink[rs.getRow()];
				rs.beforeFirst();
				for (int i = 0; rs.next(); i++)
					ttagLinks[i] = newTagLinkFromRS(rs);
			}
			st.close();
		} catch (SQLException u) {
			throw new RuntimeException(u);
		}
		return ttagLinks;
	}

	public TagLink lerTagLink(long idJogo, long idTag) {
		TagLink ttagLink = null;
		try {
			Statement st = conexao.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM TagLink WHERE idJogo = " + idJogo + " AND idTag = " + idTag);
			if (rs.next())
				ttagLink = newTagLinkFromRS(rs);
			st.close();
		} catch (SQLException u) {
			throw new RuntimeException(u);
		}
		return ttagLink;
	}
}
