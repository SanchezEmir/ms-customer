package com.nttdata.customer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class CustomerApplication {

  public static void main(String[] args) {
    SpringApplication.run(CustomerApplication.class, args);
  }

  // Define todo los detalles que tendra la API
  /*
   * private ApiInfo apiInfo() { return new
   * ApiInfoBuilder().title("API de clientes bancario") .description(
   * "La API servira para listar, eliminar, actualizar clientes")
   * .termsOfServiceUrl("http://emirsanchez.com") .contact(new
   * Contact("Emir Sanchez", "", "emir2104@gmail.com"))
   * .license("Emir Sanchez").licenseUrl("http://emirsanchez.com")
   * .version("1.0.0.").build(); }
   */

  // Define la configuración para swagger
  /*
   * @Bean public Docket petApi() { return new
   * Docket(DocumentationType.SWAGGER_2)
   * .groupName("Bootcamp").apiInfo(apiInfo()).select()
   * .apis(RequestHandlerSelectors .basePackage("com.nttdata.customer"))
   * .paths(PathSelectors.any()).build() .tags(new Tag("Customer API",
   * "Mostrar todas las APIS"));
   * 
   * }
   */

}
