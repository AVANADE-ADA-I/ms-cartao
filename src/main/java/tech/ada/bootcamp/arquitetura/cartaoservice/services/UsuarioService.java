package tech.ada.bootcamp.arquitetura.cartaoservice.services;


import org.springframework.http.*;
import org.springframework.stereotype.Service;
import tech.ada.bootcamp.arquitetura.cartaoservice.entities.Dependente;
import tech.ada.bootcamp.arquitetura.cartaoservice.payloads.request.CadastroDependenteRequest;
import tech.ada.bootcamp.arquitetura.cartaoservice.payloads.request.CadastroPrincipalRequest;
import org.springframework.web.client.RestTemplate;


import java.util.List;

@Service
public class UsuarioService {
    private ApiService apiService;

    public UsuarioService(ApiService apiService) {
        this.apiService = apiService;
    }

    public  ResponseEntity<String> criarDependente(CadastroDependenteRequest dto) {
        String adicionarDependenteUrl = apiService.getAdicionarDependente().getUrl();
        String adicionarCartaoCompraUrl = apiService.getAdicionarCartaoCompra().getUrl();
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        try {
            HttpEntity<CadastroDependenteRequest> dtoToJson = new HttpEntity<>(dto, headers);

            ResponseEntity<String> dependente = restTemplate.exchange(
                    adicionarDependenteUrl,
                    HttpMethod.POST,
                    dtoToJson,
                    String.class
            );

            if (dependente.getStatusCode().is2xxSuccessful()) {
                String usuarioBody = dependente.getBody();
                restTemplate.getForEntity(adicionarCartaoCompraUrl, String.class);
                return ResponseEntity.ok(usuarioBody);
            }
            return ResponseEntity.status(500).body("Ocorreu um erro interno do servidor: Não foi adicionado o dependente");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Ocorreu um erro interno do servidor: " + e.getMessage());
        }
    }

    public ResponseEntity<String> criarPrincipal(CadastroPrincipalRequest  dto) {
        String criarUsuarioUrl = apiService.getCriarUsuario().getUrl();
        String adicionarCartaoCompraUrl = apiService.getAdicionarCartaoCompra().getUrl();
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        try {
            HttpEntity<CadastroPrincipalRequest> dtoToJson = new HttpEntity<>(dto, headers);

            ResponseEntity<String> usuario = restTemplate.exchange(
                    criarUsuarioUrl,
                    HttpMethod.POST,
                    dtoToJson,
                    String.class
            );

            if (usuario.getStatusCode().is2xxSuccessful()) {
                String usuarioBody = usuario.getBody();
                restTemplate.getForEntity(adicionarCartaoCompraUrl, String.class);
                return ResponseEntity.ok(usuarioBody);
            }
            return ResponseEntity.status(500).body("Ocorreu um erro interno do servidor: Não foi criado o usario");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Ocorreu um erro interno do servidor: " + e.getMessage());
        }
    }
}
