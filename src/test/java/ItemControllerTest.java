import jpabook.jpashop.domain.Album;
import jpabook.jpashop.domain.Book;
import jpabook.jpashop.domain.Item;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration({
        "file:web/WEB-INF/applicationContext.xml",
        "file:web/WEB-INF/dispatcher-servlet.xml"
})
@Slf4j
public class ItemControllerTest {

    @Autowired
    private WebApplicationContext context;

    //Main entry point for server-side Spring MVC test support.
    // 모의 MVC 테스트
    private MockMvc mockMvc;

    @Before
    public void setup(){
        this.mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    public void testList() throws Exception{
        log.info(
                String.valueOf(mockMvc.perform(MockMvcRequestBuilders.get("/items"))
                         .andReturn()
                         .getModelAndView()
                         .getModelMap())
        );
    }

}
