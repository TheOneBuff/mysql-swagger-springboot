package tool;

import bean.Customer;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Iterator;

/**
*@Author by wanghaopeng on 2019/11/26 17:09
*@Comment 获取IP和mac
**/
@Component
public class GetIpAndMac {
    /**
     * @Comment 获取ip和mac
     * @param []
     * @return bean.Customer
     **/
    public Customer getipmac(Customer customer) throws SocketException {
//        //返回数据类型为Enumeration
//        Enumeration<NetworkInterface> enu1 = NetworkInterface.getNetworkInterfaces();
//        while(enu1.hasMoreElements()){
//            NetworkInterface inet = enu1.nextElement();
//            System.out.println(inet.getName());
//        }
        try {
            //获取所有接口，并放进枚举集合中，然后使用Collections.list()将枚举集合转换为ArrayList集合
            Enumeration<NetworkInterface> enu = NetworkInterface.getNetworkInterfaces();
            ArrayList<NetworkInterface> arr = Collections.list(enu);
            for(Iterator<NetworkInterface> it = arr.iterator(); it.hasNext();) {
                NetworkInterface ni = it.next();
                String intName = ni.getName(); //获取接口名
                //获取每个接口中的所有ip网络接口集合，因为可能有子接口
                ArrayList<InetAddress> inets = Collections.list(ni.getInetAddresses());
                for(Iterator<InetAddress> it1 = inets.iterator();it1.hasNext();) {
                    InetAddress inet = it1.next();
                    //只筛选ipv4地址，否则会同时得到Ipv6地址
                    //loopback地址即本机地址，IPv4的loopback范围是127.0.0.0 ~ 127.255.255.255
                    if(inet != null && inet instanceof Inet4Address && !inet.isLoopbackAddress() &&
                            inet.getHostAddress().startsWith("192.168")) {
                        String ip = inet.getHostAddress();
                        customer.setIp(ip);
                        byte[] mac = NetworkInterface.getByInetAddress(inet).getHardwareAddress();
                        //Integer.toHexString(mac[3]&0xff);
                        if (mac !=null && mac.length > 0)
                        {
                            StringBuffer strbuf = new StringBuffer();
                            for (int i = 0; i < mac.length; i++)
                            {
                                strbuf.append(Integer.toHexString(mac[i]&0xff).toUpperCase());
                                if (i < mac.length -1)
                                {
                                    strbuf.append("-");
                                }
                            }
                            customer.setMac(strbuf.toString());
                        }
                    }
                }
            }
        } catch (SocketException s) {
            s.printStackTrace();
        }
        return customer;
    }
//    /**
//     * 用命令行来，获取widnows网卡的mac地址.
//     *
//     * @return mac地址
//     */
//    public String getWindowsMACAddress() {
//        String ipmac = null;
//        BufferedReader bufferedReader = null;
//        Process process = null;
//        try {
//            //windows下的命令，显示信息中包含有mac地址信息
//            process = Runtime.getRuntime().exec("ipconfig /all");
//            //GBK编码专门用来解决中文编码的，是双字节的。不论中英文都是双字节的
//            bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream(), "GBK"));
//            //获取当前的编码格式
//            //String encder = new InputStreamReader(process.getInputStream()).getEncoding();
//            String line = null;
//            int index = -1;
//            int flag = -1;
//            while ((line = bufferedReader.readLine()) != null) {
//                System.out.println(line);
//                //寻找标示字符串[以太网适配器 以太网:]
//                index = line.toLowerCase().indexOf("以太网适配器 以太网:");
//
//                if (index >= 0 || flag == 1) {//找到了
//                    flag = 1;
//                        if (line.indexOf("物理地址") >= 0)
//                        {
//                            ipmac = line.substring(line.indexOf(":"));
//                        }
//                        else if (line.indexOf("IPv4 地址") >= 0){
//                            ipmac = ipmac + line.substring(line.indexOf(":"));
//                            flag = 0;
//                        }
//                    }
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                if (bufferedReader != null) {
//                    bufferedReader.close();
//                }
//            } catch (IOException e1) {
//                e1.printStackTrace();
//            }
//            bufferedReader = null;
//            process = null;
//        }
//        return ipmac;
//    }
}
