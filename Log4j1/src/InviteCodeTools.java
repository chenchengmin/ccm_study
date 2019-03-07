


import java.io.UnsupportedEncodingException;

/**
 * @Title: InviteCodeTools
 * @Description: 邀请码生成工具
 * @Author: zdf
 * @Since: 2018年3月13日
 * @Version: 1.1.0
 */
public final class InviteCodeTools {

	private InviteCodeTools() {
	}

	/**
	 * @Description: 根据指定的数据生成邀请码
	 * @Author: zdf
	 * @Since: 2018年3月13日
	 * @param data
	 * @param key
	 *            生成邀请码的Key
	 * @return
	 */
	public static String createInviteCode(String data, String key) {
		if (data == null || key == null) {
			return null;
		}
		return toHexString(byteToString(createByte(data, key)));
	}

	/**
	 * 
	 * @Description: 生成字节数组Byte
	 * @Author: zdf
	 * @Since: 2018年3月13日
	 * @param data
	 * @param key
	 * @return
	 */
	private static byte[] createByte(String data, String key) {
		if (data == null || key == null) {
			return null;
		}
		byte[] b_data = null;
		try {
			b_data = data.getBytes("UTF-8");
		} catch (UnsupportedEncodingException e) {
			b_data = new byte[1024];
		}
		return encrypt(b_data, key);
	}

	/**
	 * @Description: 加密
	 * @Author: zdf
	 * @Since: 2018年3月13日
	 * @param paramByte
	 * @param keyStr
	 * @return
	 */
	private static byte[] encrypt(byte[] paramByte, String keyStr) {
		int x = 0;
		int y = 0;
		byte bytekey[] = initKey(keyStr);
		int xorIndex;
		byte[] result = new byte[paramByte.length];
		for (int i = 0; i < paramByte.length; i++) {
			x = (x + 1) & 0xff;
			y = ((bytekey[x] & 0xff) + y) & 0xff;
			byte tmp = bytekey[x];
			bytekey[x] = bytekey[y];
			bytekey[y] = tmp;
			xorIndex = ((bytekey[x] & 0xff) + (bytekey[y] & 0xff)) & 0xff;
			result[i] = (byte) (paramByte[i] ^ bytekey[xorIndex]);
		}
		return result;
	}

	/**
	 * @Description: 对key重新排序
	 * @Author: zdf
	 * @Since: 2018年3月13日
	 * @param key
	 *            指定的参数Key
	 * @return
	 */
	private static byte[] initKey(String key) {
//		byte[] b_key = key.getBytes();
		byte[] b_key = null;
		try {
			b_key = key.getBytes("UTF-8");
		} catch (UnsupportedEncodingException e) {
			b_key = null;
		}
		byte state[] = new byte[256];
		for (int i = 0; i < 256; i++) {
			state[i] = (byte) i;
		}
		int index1 = 0;
		int index2 = 0;
		if (b_key == null || b_key.length == 0) {
			return null;
		}
		for (int i = 0; i < 256; i++) {
			index2 = ((b_key[index1] & 0xff) + (state[i] & 0xff) + index2) & 0xff;
			byte tmp = state[i];
			state[i] = state[index2];
			state[index2] = tmp;
			index1 = (index1 + 1) % b_key.length;
		}
		return state;
	}

	/**
	 * @Description: 将String转成16进制String
	 * @Author: zdf
	 * @Since: 2018年3月13日
	 * @param param
	 *            指定String
	 * @return
	 */
	private static String toHexString(String param) {
		String str = "";
		StringBuffer sbf = new StringBuffer();
		for (int i = 0; i < param.length(); i++) {
			int ch = (int) param.charAt(i);
			String tempStr = Integer.toHexString(ch & 0xFF);
			StringBuffer sb = new StringBuffer();
			if (tempStr.length() == 1) {
				sb.append('0').append(tempStr);
			}
			tempStr = sb.length() > 0 ? sb.toString() : tempStr;
			sbf.append(str).append(tempStr);
		}
		return sbf.toString();
	}

	/**
	 * @Description: 将Byte转成String
	 * @Author: zdf
	 * @Since: 2018年3月13日
	 * @param buf
	 * @return
	 */
	private static String byteToString(byte[] buf) {
		StringBuffer sb = new StringBuffer(buf.length);
		for (int i = 0; i < buf.length; i++) {
			sb.append((char) buf[i]);
		}
		return sb.toString();
	}

}
