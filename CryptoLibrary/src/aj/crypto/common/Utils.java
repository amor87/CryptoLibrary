package aj.crypto.common;

import java.nio.charset.StandardCharsets;
import java.util.Random;

import javax.xml.bind.DatatypeConverter;

public class Utils {

	public static String getRootPath() {
		java.net.URL rootPath = Utils.class.getProtectionDomain().getCodeSource().getLocation();
		return rootPath.getFile();
	}

	public static class Generate {
		private static final String CHAR_CHAIN = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

		public static String getRandomString(int length) {
			Random rng = new Random();
			char[] text = new char[length];
			for (int i = 0; i < length; i++) {
				text[i] = CHAR_CHAIN.charAt(rng.nextInt(CHAR_CHAIN.length()));
			}
			return new String(text);
		}

		public static int getRandomNumber(int aStart, int aEnd) {
			Random aRandom = new Random();
			if (aStart > aEnd) {
				throw new IllegalArgumentException("Start cannot exceed End.");
			}
			long range = (long) aEnd - (long) aStart + 1;
			long fraction = (long) (range * aRandom.nextDouble());
			int randomNumber = (int) (fraction + aStart);
			return randomNumber;
		}

	}

	public static class Transform {

		public static byte[] FromHexStringToByteArray(String s) {
			int len = s.length();
			byte[] data = new byte[len / 2];
			for (int i = 0; i < len; i += 2) {
				data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4) + Character.digit(s.charAt(i + 1), 16));
			}
			return data;
		}

		public static String FromStringToStringHex(String inputString) {
			return FromByteArrayToHexString(inputString.getBytes());
		}

		public static String FromByteArrayToHexString(byte buf[]) {
			StringBuffer strbuf = new StringBuffer(buf.length * 2);
			int i;
			for (i = 0; i < buf.length; i++) {
				if (((int) buf[i] & 0xff) < 0x10)
					strbuf.append("0");
				strbuf.append(Long.toString((int) buf[i] & 0xff, 16));
			}
			return strbuf.toString();
		}

		public static String FromByteArrayToBase64String(byte[] bytes) {
			return DatatypeConverter.printBase64Binary(bytes);
		}

		public static byte[] FromStringBase64toByteArray(String str) {
			return DatatypeConverter.parseBase64Binary(str);
		}

		public static String FromByteHexToString(byte[] bytes) {
			return DatatypeConverter.printHexBinary(bytes);
		}

		public static byte[] FromStringHexToByteArray(String str) {
			return DatatypeConverter.parseHexBinary(str);
		}

		public static byte[] FromStringToByteArray(String str) {
			return str.getBytes();
		}

		public static String FromByteArrayToString(byte[] bytes) {
			return new String(bytes, StandardCharsets.UTF_8).trim();
		}
	}

	public static class Validate {
		public static boolean isStringEmptyOrNull(String string) {
			if (string == null || string.equals("")) {
				return true;
			} else {
				return false;
			}
		}
	}
}