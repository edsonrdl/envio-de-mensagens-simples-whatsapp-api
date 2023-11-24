import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class MessageWhatsappApi {
    public static void main(String[] args) {
        String baseUrl = "https://graph.facebook.com/v17.0/";
        // Adicione seu token
        String temporaryAccessToken = "cole aqui o token ";

        // Adicione o número de identificação
        String telephoneNumberIdentification = "número de identificação ";

        // colque aqui seu número de telefone  que você adicionou para receber mensagens
        String phoneNumber = "Número de telefone ";

        //Exemplo da mensagem que você vai receber ,pode ser alterado para fazer testes
        String messageEx = "Minha primeira mensagem";

        try {

            HttpClient httpClient = HttpClient.newHttpClient();

            String requestBody = "{\"messaging_product\":\"whatsapp\",\"to\":\"" + phoneNumber
                    + "\",\"type\":\"text\",\"text\":{\"body\":\"" + messageEx + "\"}}";

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(baseUrl + telephoneNumberIdentification + "/messages"))
                    .header("Authorization", "Bearer " + temporaryAccessToken)
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                    .build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            int statusCode = response.statusCode();

            if (statusCode == 200) {

                String responseBody = response.body();
                System.out.println("Resposta da Requisição HTTP:\n" + responseBody);
            } else {
                System.err.println("Falha na requisição HTTP, código de status: " + statusCode);
            }
        } catch (IOException | InterruptedException | URISyntaxException e) {
            e.printStackTrace();
        }
    }
}
