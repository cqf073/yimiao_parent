import org.junit.Test;

import java.util.ArrayList;

/**
 * 功能描述：
 *
 * @Author：cqf
 * @ 2023/2/20 9:25
 * @description： TODO
 * @modifiedBy：
 * @version: 1.0
 **/
public class fanxingTest {
    @Test
        public  void  test(){
        Class a = new ArrayList<Integer>().getClass();
        Class b = new ArrayList<String>().getClass();

        System.out.println("a="+a);
        System.out.println("b="+b);
    }
}
