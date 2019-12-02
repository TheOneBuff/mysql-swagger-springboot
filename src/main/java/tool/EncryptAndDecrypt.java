package tool;

import globalvariables.GlobalVariables;
import org.apache.xmlbeans.impl.util.Base64;
import org.springframework.stereotype.Component;


/**
*@Author by wanghaopeng on 2019/11/26 17:07
*@Comment 加密解密用
**/
@Component
public class EncryptAndDecrypt {

    /**
     * @Comment 对密码进行加密
     * @param [password] 密码
     * @return java.lang.String
     **/
    public String encrypt(String password) throws Exception {
        //先编码再加密 根据密匙来对密码进行加密
        byte[] encryptResult = encode(password, GlobalVariables.password_encrypt_decrypt);
        return new String(encryptResult);
    }

    /**
     * @Comment 解密
     * @param [encryptResult] 加密后的数据
     * @return java.lang.String
     **/
    public String decrypt(byte[] encryptResult) throws Exception {
        byte[] decryptResult = decode(encryptResult, GlobalVariables.password_encrypt_decrypt);
        return new String(decryptResult);
    }
    //编码函数
    public static byte[] encode(String content, String key) throws Exception {
        byte[] encrypt = Encrypt.encrypt(content, key);
        return Base64.encode(encrypt);
    }
    //解码函数
    public static byte[] decode(byte[] encode, String key) throws Exception {
        byte[] encrypt = Base64.decode(encode);
        byte[] content = Decrypt.decrypt(encrypt, key);
        return content;
    }

}
