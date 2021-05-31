//Pacote, Libs, Arquivos, Etc
package github.marcelobaldi;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//Anotações - Classe
@SpringBootApplication
@RestController
@EnableWebMvc
public class Main implements WebMvcConfigurer {

    //Requisição Http - Habilitar Para Receber
    @Override
    public void addCorsMappings(CorsRegistry registry){
        registry.addMapping("/**").allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS");
    }

    //Método - Inicial
    public static void main(String[] args) {
        SpringApplication.run(Main.class,args);
    }
}

//Fluxo
//- ApiRest             Recebe Dados do FrontEnd    e   Envia Para o Service
//- Service             Recebe Dados da ApiRest     e   Envia Para o Repository
//- Repository          Executa o CRUD no BD
//* Main                É o Arquivo Inicial;
//* Entity              São os Models/Tabelas BD    (Poderia Separar os Models Numa DTO)
//* Banco Dados         Application.Properties
//* Tratamento Erro     Exception
//* Lib's Projeto       Pom.xml
//* Arquivo Inicial     Main


