import com.jackass.RestAPI.conf.ConditionsConfig;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Conditional;

@SpringBootApplication
@ComponentScan("com.jackass.RestAPI")
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
@Conditional(ConditionsConfig.InMemoryCondition.class)
public class InMemoryWebStoreApplication {
}
