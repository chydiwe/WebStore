import com.jackass.RestAPI.controller.ProductController;
import com.jackass.RestAPI.controller.UserController;
import com.jackass.RestAPI.entity.ConfirmationToken;
import com.jackass.RestAPI.entity.User;
import com.jackass.RestAPI.repository.ConfirmationTokenRepository;
import com.jackass.RestAPI.repository.UserRepository;
import com.jackass.RestAPI.conf.ConditionsConfig;
import junit.framework.TestCase;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class RestSteps {

    private UserController userController;
    private ProductController productController;

    private UserRepository userRepository;
    private ConfirmationTokenRepository confirmationTokenRepository;

    @Given("running server")
    public void init(){
        // activate in memory flag
        ConditionsConfig.IN_MEMORY = true;

        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.scan("com.jackass.RestAPI");
        applicationContext.refresh();

        this.userController = applicationContext.getBean(UserController.class);
        this.productController = applicationContext.getBean(ProductController.class);

        this.userRepository = applicationContext.getBean(UserRepository.class);
        this.confirmationTokenRepository = applicationContext.getBean(ConfirmationTokenRepository.class);
    }

    @When("POST /user? email=$email & password=$password & name=$name & surname=$surname & patronymic=$patronymic")
    public void addUser(String email,
                        String password,
                        String name,
                        String surname,
                        String patronymic){
        userController.register(email, password, name, surname, patronymic);
    }

    @Then("user with email $email has token")
    public void checkThatTokenExists(String email){
        User user = userRepository.getUserByEmail(email);
        ConfirmationToken confirmationToken = confirmationTokenRepository.getTokenById(user.getId());
        TestCase.assertEquals(user.getId(), confirmationToken.getId());
    }

}
