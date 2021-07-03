package modulos.produto;

import dataFactory.ProdutoDataFactory;
import dataFactory.UsuarioDataFactory;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;

@DisplayName("Testes de API Rest do Modulo de Produto")
public class ProdutoTest {
    private String token;

    @BeforeEach
    public void beforeEach() {

        //configurar os dados da API Rest da Lojinha
        baseURI = "http://165.227.93.41";
        basePath = "/lojinha";

        // Obter o token da lojinha admin

        this.token = given()
                .contentType(ContentType.JSON)
                .body(UsuarioDataFactory.criarUsuarioAdministrador())
                .when()
                .post("v2/login")
                .then()
                .extract()
                .path("data.token");
        // System.out.println(token);

    }

    @Test
    @DisplayName(" Validar que o valor do produto igual a 0.00 não é permitido")
    public void testValidarLimitesIqualAZerorProibidoDoValorProduto() {
        // tentar inserir um produto com valor 0.00 e validar que a menssagem de erro apresentda e o
        // Status code foi 422

        given()
                .contentType(ContentType.JSON)
                .header("token", this.token)
                .body(ProdutoDataFactory.criarProdutoComumComOValorIgualA(0.00))
                .when()
                .post("/v2/produtos")
                .then()
                .assertThat()
                .body("error", equalTo("O valor do produto deve estar entre R$ 0,01 e R$ 7.000,00"))
                .statusCode(422);
    }


    @Test
    @DisplayName(" Validar que o        valor do produto maior que 7000.00 não é permitido")
    public void testValidarLimitesMaiorQueSeteMilProibidoDoValorProduto() {
        // tentar inserir um produto com valor 7000.0 1e validar que a menssagem de erro apresentda e o
        // Status code foi 422

        given()
                .contentType(ContentType.JSON)
                .header("token", this.token)
                .body(ProdutoDataFactory.criarProdutoComumComOValorIgualA(7000.01))
                .when()
                .post("/v2/produtos")
                .then()
                .assertThat()
                .body("error", equalTo("O valor do produto deve estar entre R$ 0,01 e R$ 7.000,00"))
                .statusCode(422);


    }
}
