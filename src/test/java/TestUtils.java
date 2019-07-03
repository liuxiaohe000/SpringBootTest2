import com.lxh.config.TestConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestComponent;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @author 刘晓禾
 * @date 2019/7/2 17:24
 * @company www.tydic.com
 * @description
 */

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = TestUtils.class)
public class TestUtils {

    @Resource
    TestConfig testConfig;

    @Test
    public void test1(){
        System.out.println("1");
    }
}
