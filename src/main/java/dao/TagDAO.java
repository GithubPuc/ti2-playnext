package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.Tag;

public class TagDAO extends DAO {

	public boolean inserirTag(Tag tag) {
		boolean status = false;
		try {
			PreparedStatement ps = conexao.prepareStatement(
					"INSERT INTO Tag (tagName, tagDesc) VALUES (?, ?);");
			ps.setString(1, tag.getTagName());
			ps.setString(2, tag.getTagDesc());
			ps.executeUpdate();
			ps.close();
			status = true;
		} catch (SQLException u) {
			throw new RuntimeException(u);
		}
		return status;
	}

	public boolean atualizarTag(Tag tag) {
		boolean status = false;
		try {
			PreparedStatement ps = conexao.prepareStatement(
					"UPDATE Tag SET tagName = ?, tagDesc = ? WHERE idTag = ?");
			ps.setString(1, tag.getTagName());
			ps.setString(2, tag.getTagDesc());
			ps.setLong(6, tag.getIdTag());
			ps.executeUpdate();
			ps.close();
			status = true;
		} catch (SQLException u) {
			throw new RuntimeException(u);
		}
		return status;
	}

	public boolean excluirTag(long idTag) {
		boolean status = false;
		try {
			Statement st = conexao.createStatement();
			st.executeUpdate("DELETE FROM Tag WHERE idTag = " + idTag);
			st.close();
			status = true;
		} catch (SQLException u) {
			throw new RuntimeException(u);
		}
		return status;
	}

	private Tag newTagFromRS(ResultSet rs) throws SQLException {
		return new Tag(
				rs.getLong("idTag"),
				rs.getString("tagName"),
				rs.getString("tagDesc"));
	}

	public Tag[] listarTags() {
		Tag[] tags = null;
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = st.executeQuery("SELECT * FROM Tag");
			if (rs.next()) {
				rs.last();
				tags = new Tag[rs.getRow()];
				rs.beforeFirst();
				for (int i = 0; rs.next(); i++)
					tags[i] = newTagFromRS(rs);
			}
			st.close();
		} catch (SQLException u) {
			throw new RuntimeException(u);
		}
		return tags;
	}

	public Tag lerTag(long idTag) {
		Tag tag = null;
		try {
			Statement st = conexao.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM Tag WHERE idTag = " + idTag);
			if (rs.next())
				tag = newTagFromRS(rs);
			st.close();
		} catch (SQLException u) {
			throw new RuntimeException(u);
		}
		return tag;
	}
}
