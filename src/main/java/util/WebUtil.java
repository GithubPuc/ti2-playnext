package util;

import java.util.Scanner;

import com.fasterxml.jackson.databind.ObjectMapper;

public class WebUtil {
	public static final ObjectMapper objectMapper = new ObjectMapper();

	public static String lerHTML(String filename) {
		Scanner sc = new Scanner(WebUtil.class.getResourceAsStream("/internal/" + filename), "UTF-8");
		String html = "";
		while (sc.hasNext())
			html += sc.nextLine() + '\n';
		sc.close();
		return html;
	}

	public static String jsonPadrao(int tipo, String valor) {
		return String.format("{\"tipo\":%d,\"valor\":%s}", tipo, valor);
	}

	public static String jsonLista(Object[] o) {
		try {
			return jsonPadrao(1, objectMapper.writeValueAsString(o));
		} catch (Exception e) {
			return jsonPadrao(0, "Erro interno");
		}
	}

	public static String jsonPadrao(Object o) {
		try {
			return jsonPadrao(0, objectMapper.writeValueAsString(o));
		} catch (Exception e) {
			return jsonPadrao(0, "Erro interno");
		}
	}
}
