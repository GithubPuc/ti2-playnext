package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.TagRel;

public class TagRelDAO extends DAO {

	public boolean inserirTagRel(TagRel tagRel) {
		boolean status = false;
		try {
			PreparedStatement ps = conexao.prepareStatement(
					"INSERT INTO TagRel (idJogo, idTag) VALUES (?, ?);");
			ps.setLong(1, tagRel.getIdJogo());
			ps.setLong(2, tagRel.getIdTag());
			ps.executeUpdate();
			ps.close();
			status = true;
		} catch (SQLException u) {
			throw new RuntimeException(u);
		}
		return status;
	}

	public boolean atualizarTagRel(TagRel tagRel) {
		boolean status = false;
		try {
			PreparedStatement ps = conexao.prepareStatement(
					"UPDATE TagRel SET idTag = ? WHERE idJogo = ? AND idTag = ?");
			ps.setLong(1, tagRel.getIdTag());
			ps.setLong(2, tagRel.getIdJogo());
			ps.setLong(3, tagRel.getIdTag());
			ps.executeUpdate();
			ps.close();
			status = true;
		} catch (SQLException u) {
			throw new RuntimeException(u);
		}
		return status;
	}

	public boolean excluirTagRel(long idTagRel) {
		boolean status = false;
		try {
			Statement st = conexao.createStatement();
			st.executeUpdate("DELETE FROM TagRel WHERE idTagRel = " + idTagRel);
			st.close();
			status = true;
		} catch (SQLException u) {
			throw new RuntimeException(u);
		}
		return status;
	}

	private TagRel newTagRelFromRS(ResultSet rs) throws SQLException {
		return new TagRel(
				rs.getLong("idJogo"),
				rs.getLong("idTag"));
	}

	public TagRel[] listarTagRels() {
		TagRel[] tagRels = null;
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = st.executeQuery("SELECT * FROM TagRel");
			if (rs.next()) {
				rs.last();
				tagRels = new TagRel[rs.getRow()];
				rs.beforeFirst();
				for (int i = 0; rs.next(); i++)
					tagRels[i] = newTagRelFromRS(rs);
			}
			st.close();
		} catch (SQLException u) {
			throw new RuntimeException(u);
		}
		return tagRels;
	}

	public TagRel lerTagRel(long idTagRel) {
		TagRel tagRel = null;
		try {
			Statement st = conexao.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM TagRel WHERE idTagRel = " + idTagRel);
			if (rs.next())
				tagRel = newTagRelFromRS(rs);
			st.close();
		} catch (SQLException u) {
			throw new RuntimeException(u);
		}
		return tagRel;
	}
}
