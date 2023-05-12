package dao;

import java.io.Closeable;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import util.Config;

public abstract class DAO implements Closeable {
	protected Connection conexao;
	protected boolean conectado = false;

	public boolean isConectado() {
		return conectado;
	}

	public void conectar() {
		try {
			String driver = Config.bd("Driver");
			Class.forName(driver);
			String server = Config.bd("Server");
			String sqlServer = Config.bd("SQLServer");
			String nome = Config.bd("Name");
			String porta = Config.bd("Port");
			String url = "jdbc:" + sqlServer + "://" + server + ":" + porta + "/" + nome;
			String usuario = Config.bd("Username");
			String senha = Config.bd("Password");
			conexao = DriverManager.getConnection(url, usuario, senha);
			conectado = (conexao != null);
			System.out.println("Conexão efetuada com o postgres!");
		} catch (ClassNotFoundException e) {
			System.err.println("Conexão NÃO efetuada com o postgres -- Driver não encontrado -- " + e.getMessage());
		} catch (SQLException e) {
			System.err.println("Conexão NÃO efetuada com o postgres -- " + e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void close() throws IOException {
		try {
			conexao.close();
			conectado = false;
		} catch (SQLException e) {
			throw new IOException(e);
		}
	}
}
