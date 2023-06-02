package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.Editar;

public class EditarDAO extends DAO {

	public boolean inserirEditar(Editar Editar) {
		boolean status = false;
		try {
			PreparedStatement ps = conexao.prepareStatement(
					"INSERT INTO Editar (username, email, senha, tipo) VALUES (?, ?, ?, ?);");
			ps.setString(1, Editar.getUsername());
			ps.setString(2, Editar.getEmail());
			ps.setBytes(3, Editar.getSenha());
			ps.setInt(4, Editar.getTipo());
			ps.executeUpdate();
			ps.close();
			status = true;
		} catch (SQLException u) {
			throw new RuntimeException(u);
		}
		return status;
	}

	public boolean atualizarEditar(Editar Editar) {
		boolean status = false;
		try {
			PreparedStatement ps = conexao.prepareStatement(
					"UPDATE Editar SET username = ?, email = ?, senha = ?, tipo = ? WHERE idEditar = ?");
			ps.setString(1, Editar.getUsername());
			ps.setString(2, Editar.getEmail());
			ps.setBytes(3, Editar.getSenha());
			ps.setInt(4, Editar.getTipo());
			ps.setLong(5, Editar.getIdEditar());
			ps.executeUpdate();
			ps.close();
			status = true;
		} catch (SQLException u) {
			throw new RuntimeException(u);
		}
		return status;
	}

	public boolean excluirEditar(long idEditar) {
		boolean status = false;
		try {
			Statement st = conexao.createStatement();
			st.executeUpdate("DELETE FROM Editar WHERE idEditar = " + idEditar);
			st.close();
			status = true;
		} catch (SQLException u) {
			throw new RuntimeException(u);
		}
		return status;
	}

	private Editar newEditarFromRS(ResultSet rs) throws SQLException {
		return new Editar(
				rs.getLong("idEditar"),
				rs.getString("username"),
				rs.getString("email"),
				rs.getBytes("senha"),
				rs.getInt("tipo"));
	}

	public Editar[] listarEditars() {
		Editar[] Editars = null;
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = st.executeQuery("SELECT * FROM Editar");
			if (rs.next()) {
				rs.last();
				Editars = new Editar[rs.getRow()];
				rs.beforeFirst();
				for (int i = 0; rs.next(); i++)
					Editars[i] = newEditarFromRS(rs);
			}
			st.close();
		} catch (SQLException u) {
			throw new RuntimeException(u);
		}
		return Editars;
	}

	public Editar lerEditar(long idEditar) {
		Editar Editar = null;
		try {
			Statement st = conexao.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM Editar WHERE idEditar = " + idEditar);
			if (rs.next())
				Editar = newEditarFromRS(rs);
			st.close();
		} catch (SQLException u) {
			throw new RuntimeException(u);
		}
		return Editar;
	}
}