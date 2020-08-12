package com.sg.framework.util;//package com.yy.framework.util;
//
//import java.io.ByteArrayInputStream;
//import java.io.ByteArrayOutputStream;
//import java.io.IOException;
//import java.io.ObjectInputStream;
//import java.io.ObjectOutputStream;
//
//import org.apache.shiro.codec.Base64;
//import org.apache.shiro.session.Session;
//
///**
// * 序列化辅助类
// * 
// * @author 
// * @version $Id: SerializableUtil.java, v 0.1 2014-6-10 下午1:30:39  Exp
// *          $
// */
//public final class SerializeUtil {
//	private SerializeUtil() {
//	}
//
//	/**
//	 * 序列化
//	 * 
//	 * @param object
//	 * @return
//	 */
//	public static final byte[] toBytes(Object object) {
//		ByteArrayOutputStream baos = new ByteArrayOutputStream();
//		ObjectOutputStream oos = null;
//		try {
//			oos = new ObjectOutputStream(baos);
//			oos.writeObject(object);
//			return baos.toByteArray();
//		} catch (IOException ex) {
//			throw new RuntimeException(ex.getMessage(), ex);
//		} finally {
//			try {
//				oos.close();
//				baos.close();
//			} catch (Exception e) {
//			}
//		}
//	}
//
//	/**
//	 * 反序列化
//	 * 
//	 * @param bytes
//	 * @return
//	 */
//	public static final Object toObject(byte[] bytes) {
//		ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
//		ObjectInputStream ois = null;
//		try {
//			ois = new ObjectInputStream(bais);
//			return ois.readObject();
//		} catch (IOException ex) {
//			throw new RuntimeException(ex.getMessage(), ex);
//		} catch (ClassNotFoundException ex) {
//			throw new RuntimeException(ex.getMessage(), ex);
//		} finally {
//			try {
//				ois.close();
//				bais.close();
//			} catch (Exception e) {
//			}
//		}
//	}
//	
//	public static String serialize(Session session) {
//        try {
//            ByteArrayOutputStream bos = new ByteArrayOutputStream();
//            ObjectOutputStream oos = new ObjectOutputStream(bos);
//            oos.writeObject(session);
//            return Base64.encodeToString(bos.toByteArray());
//        } catch (Exception e) {
//            throw new RuntimeException("serialize session error", e);
//        }
//    }
//    public static Session deserialize(String sessionStr) {
//        try {
//            ByteArrayInputStream bis = new ByteArrayInputStream(Base64.decode(sessionStr));
//            ObjectInputStream ois = new ObjectInputStream(bis);
//            return (Session)ois.readObject();
//        } catch (Exception e) {
//            throw new RuntimeException("deserialize session error", e);
//        }
//    }
//}
