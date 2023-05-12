package util;

import java.io.IOException;
import java.util.Properties;

public class Config {
	private static Properties propBD;

	public static String bd(String prop) throws IOException {
		if (propBD == null) {
			propBD = new Properties();
			try {
				propBD.load(Config.class.getResourceAsStream("/config/DB.properties"));
			} catch (IOException e) {
				throw new IOException("Erro ao buscar arquivo de configuração da conexão com BD");
			}
		}
		return propBD.getProperty(prop);
	}
}
