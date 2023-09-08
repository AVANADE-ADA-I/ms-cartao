package tech.ada.bootcamp.arquitetura.cartaoservice.services;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("api")
public class ApiService {
    private ApiEndpoint criarUsuario;
    private ApiEndpoint adicionarDependente;
    private ApiEndpoint adicionarCartaoCompra;

    public ApiEndpoint getCriarUsuario() {
        return criarUsuario;
    }

    public void setCriarUsuario(ApiEndpoint criarUsuario) {
        this.criarUsuario = criarUsuario;
    }

    public ApiEndpoint getAdicionarDependente() {
        return adicionarDependente;
    }

    public void setAdicionarDependente(ApiEndpoint adicionarCartaoCompra) {
        this.adicionarDependente = adicionarDependente;
    }

    public ApiEndpoint getAdicionarCartaoCompra() {
        return adicionarCartaoCompra;
    }

    public void setAdicionarCartaoCompra(ApiEndpoint adicionarCartaoCompra) {
        this.adicionarCartaoCompra = adicionarCartaoCompra;
    }


        public static class ApiEndpoint {
        private String url;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
