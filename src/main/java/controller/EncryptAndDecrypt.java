package controller;

import globalvariables.GlobalVariables;
import io.swagger.annotations.ApiOperation;
import org.apache.xmlbeans.impl.util.Base64;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tool.Decrypt;
import tool.Encrypt;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.NetworkInterface;

/**
*@Author by wanghaopeng on 2019/11/22 8:26
*@Comment 对密码进行加密
**/
@RestController
@RequestMapping("/login")
public class EncryptAndDecrypt {

    /**
     * @Comment 对密码进行加密
     * @param [name, password]
     * @return java.lang.String
     **/
    @ApiOperation(value = "对字符串加密",notes = "对字符串加密")
    @RequestMapping(value = "/encrypt",method = RequestMethod.GET)
    public String encrypt(@RequestParam(value="password") String password) throws Exception {

        //先编码再加密 根据密匙来对密码进行加密
        byte[] encryptResult2 = encode(password, GlobalVariables.password_encrypt_decrypt);
        //先解码再解密
        byte[] decryptResult2 = decode(encryptResult2, GlobalVariables.password_encrypt_decrypt);
        System.out.println("解密后2：" + decryptResult2);
        //获得IP
        InetAddress ia = InetAddress.getLocalHost();
        System.out.print(ia);
        //获取MAC
        byte[] mac = NetworkInterface.getByInetAddress(ia).getHardwareAddress();
        System.out.print(mac);
        System.out.print(new String(mac));

        String str = getWindowsMACAddress();
        return new String(decryptResult2);
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


    /**
     * 获取widnows网卡的mac地址.
     *
     * @return mac地址
     */
    public static String getWindowsMACAddress() {
        String mac = null;
        BufferedReader bufferedReader = null;
        Process process = null;
        try {
            // windows下的命令，显示信息中包含有mac地址信息
            process = Runtime.getRuntime().exec("ipconfig /all");
            //GBK编码专门用来解决中文编码的，是双字节的。不论中英文都是双字节的
            bufferedReader = new BufferedReader(new InputStreamReader(
                    process.getInputStream(), "GBK"));
            String encder = new InputStreamReader(process.getInputStream()).getEncoding();
            System.out.print(encder);
            String line = null;
            int index = -1;
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
                // 寻找标示字符串[physical
                index = line.toLowerCase().indexOf("physical address");

                if (index >= 0) {// 找到了
                    index = line.indexOf(":");// 寻找":"的位置
                    if (index >= 0) {
                        System.out.println(mac);
                        // 取出mac地址并去除2边空格
                        mac = line.substring(index + 1).trim();
                    }
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            bufferedReader = null;
            process = null;
        }

        return mac;
    }
}
