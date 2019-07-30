package com.lxh.utils;


import ch.qos.logback.core.net.SyslogOutputStream;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.boot.system.SystemProperties;

import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 刘晓禾
 * @date 2019/7/30 14:46
 * @company www.tydic.com
 * @description
 */
public class RSAUtils {

    //生成公钥、私钥
    public static Map<String,Object> initKey(){
        Map<String, Object> keyPairMap = new HashMap<>();
        try {
            KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
            KeyPair keyPair = generator.genKeyPair();
            PublicKey publicKey = keyPair.getPublic();
            PrivateKey privateKey = keyPair.getPrivate();
            keyPairMap.put("publicKey", Base64.encodeBase64String(publicKey.getEncoded()));
            System.out.println("publicKey ="+publicKey.toString());
            keyPairMap.put("privateKey", Base64.encodeBase64String(privateKey.getEncoded()));
        }catch (Exception e){
            e.printStackTrace();
        }
        return keyPairMap;
    }


    /**
     *
     * 功能描述: 使用私钥加密
     *
     *
     * @param data 加密数据
     * @param privateKey 私钥对象
     * @return
     * @throws
     * @auther 刘晓禾
     * @date  2019/7/30
     */
    public static byte[] signByPrivateKey(byte[] data, PrivateKey privateKey)
            throws Exception {
        Signature sig = Signature.getInstance("SHA256withRSA");
        sig.initSign(privateKey);
        sig.update(data);
        byte[] ret = sig.sign();
        return ret;
    }




    /**
     *
     * 功能描述: 使用公钥进行验签
     *
     * @param  data 原数据
     * @param  sign 签名数据
     * @return
     * @throws
     * @auther 刘晓禾
     * @date  2019/7/30
     */
    public  static boolean verfity(byte[] data,byte[] sign,PublicKey publicKey)
            throws Exception{
        Signature signet = Signature.getInstance("SHA256withRSA");
        signet.initVerify(publicKey);
        signet.update(data);
        boolean verify = signet.verify(sign);
        return verify;
    }



    /**
     *
     * 功能描述: 还原私钥对象
     *
     * @param base64String base64加密后的私钥数据
     * @return
     * @throws
     * @auther 刘晓禾
     * @date  2019/7/30
     */
    public static PrivateKey getPrivateKeyFromString(String base64String)
            throws InvalidKeySpecException, NoSuchAlgorithmException {
        byte[] bt = Base64.decodeBase64(base64String.getBytes());
        PKCS8EncodedKeySpec privateKeySpec = new PKCS8EncodedKeySpec(bt);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PrivateKey privateKey = keyFactory.generatePrivate(privateKeySpec);
        return privateKey;
    }



    /**
     *
     * 功能描述: 还原公钥对象
     *
     * @param base64String base64加密后的公钥数据
     * @return
     * @throws
     * @auther 刘晓禾
     * @date  2019/7/30
     */
    public static PublicKey getPulbicKeyFromString(String base64String)
            throws InvalidKeySpecException, NoSuchAlgorithmException {
        byte[] bt = Base64.decodeBase64(base64String.getBytes());
        X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(bt);
        KeyFactory factory = KeyFactory.getInstance("RSA");
        PublicKey publicKey = factory.generatePublic(x509EncodedKeySpec);
        return publicKey;
    }




    public static void main(String[] args) throws  Exception{
        
        //加密数据
        String requestDate = "EWEFSOVMSDOM34230FFVJO3MF94F02";

        //1、获取公钥、私钥（这里的公钥、私钥都通过Base64再次加密了，也就是证书）
        Map<String, Object> map = initKey();
        
        //2、使用私钥加密，通过工具类还原私钥
        PrivateKey privateKey = getPrivateKeyFromString(map.get("privateKey").toString());

        byte[] bytes = signByPrivateKey(requestDate.getBytes(), privateKey);
        System.out.println("加密后的签名："+new String(bytes));

        //3、使用公钥验签
        PublicKey publicKey = getPulbicKeyFromString(map.get("publicKey").toString());
        boolean verfity = verfity(requestDate.getBytes(), bytes,publicKey);
        System.out.println("验签结果"+verfity);

    }
}
