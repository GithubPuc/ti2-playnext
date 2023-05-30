package util;

import org.bouncycastle.crypto.generators.Argon2BytesGenerator;
import org.bouncycastle.crypto.params.Argon2Parameters;

import java.security.SecureRandom;

public class Seguranca {
	public static byte[] hash(byte[] password) {
		int iterations = 3;
		int memory = 262144;
		int length = 32;
		int parallelism = 1;
		Argon2Parameters.Builder builder = new Argon2Parameters.Builder(Argon2Parameters.ARGON2_id)
				.withVersion(Argon2Parameters.ARGON2_VERSION_13).withIterations(iterations).withMemoryAsKB(memory)
				.withParallelism(parallelism).withSalt(salt16());
		Argon2BytesGenerator gen = new Argon2BytesGenerator();
		gen.init(builder.build());
		byte[] result = new byte[length];
		gen.generateBytes(password, result, 0, result.length);
		return result;
	}

	private static byte[] salt16() {
		SecureRandom secureRandom = new SecureRandom();
		byte[] salt = new byte[16];
		secureRandom.nextBytes(salt);
		return salt;
	}
}