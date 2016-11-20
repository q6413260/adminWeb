import com.xiaoming.dto.MenuDTO;
import com.xiaoming.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * Created by xiaoming on 17/11/2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:testApplicationContext.xml")
public class UserServiceTest extends AbstractJUnit4SpringContextTests{

    @Autowired
    private UserService userService;
    @Test
    public void testGetAccessMenus() {
        List<MenuDTO> list = userService.getAccessMenus(0l);
    }
}
