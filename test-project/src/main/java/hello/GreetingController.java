package hello;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.thymeleaf.spring.support.Layout;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.function.Function;

@Controller
public class GreetingController {

    private Log log = LogFactory.getLog(GreetingController.class);
    private static final Logger logger = LoggerFactory.getLogger(GreetingController.class);
    
    @Autowired
    private LoggingService loggingService;

    List<String> persons = Arrays.asList("Walter White", "Heisenberg", "Jesse Pinkman", "Saul Goodman", "Gustavo Fringe");

    Function<List<String>, String> random = (List<String> list) -> list.get(new Random().nextInt(list.size() - 1));

    @Layout("layouts/logged_in")
    @RequestMapping("/greeting")
    public String greeting(@RequestParam(value = "name", required = false, defaultValue = "World") String name, Model model) {
        log.debug("In greeting()");
        logger.debug("Greeting request received for name: {}", name);
        
        // 使用 LoggingService 记录用户行为
        loggingService.logUserAction(name, "greeting_page_access");
        
        model.addAttribute("name", name);
        logger.info("Greeting page rendered for user: {}", name);
        return "greeting";
    }

    @Layout("layouts/logged_in")
    @RequestMapping("/randomgreeting")
    public String whoAmI(Model model) {
        logger.debug("Random greeting request received");
        final String randomName = random.apply(persons);
        logger.trace("Selected random name: {}", randomName);
        
        // 记录性能信息
        long startTime = System.currentTimeMillis();
        
        model.addAttribute("name",
                persons
                        .stream()
                        .filter(person -> person.equals(randomName))
                        .findFirst().get());
        
        long duration = System.currentTimeMillis() - startTime;
        loggingService.logPerformance("random_greeting_generation", duration);
        
        logger.info("Random greeting generated for character: {}", randomName);
        return "greeting";
    }

}
