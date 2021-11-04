import com.alibaba.fastjson.JSON;
import org.apache.shiro.crypto.hash.Md5Hash;

import java.util.Map;

public class Password {

    public static void main(String[] args) {
        //salt是workNumber
        //String password = new Md5Hash("123456", "123456", 3).toString();
        //System.out.println(password);

        String str="{ot=1, vInitial=36, e=2.059e11}";
        Map map=(Map) JSON.parse(str);
        System.out.println(map);

    }
}
