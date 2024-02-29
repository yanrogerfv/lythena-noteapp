package lythena.noteapp.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Lythena Noteapp",
                version = "Alpha 0.0.1",
                description = "Aplicativo de notas Lythena.",
                contact = @Contact(
                        name = "Yan Roger Fogaça",
                        email = "yanrogerfv@gmail.com"
                )
        )
)
public class SwaggerConfig {
}
