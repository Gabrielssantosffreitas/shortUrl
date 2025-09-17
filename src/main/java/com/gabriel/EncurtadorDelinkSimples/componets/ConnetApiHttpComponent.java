package com.gabriel.EncurtadorDelinkSimples.componets;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import io.opencensus.trace.Link;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Component
public class ConnetApiHttpComponent {
    private final String LINK = "https://generativelanguage.googleapis.com/v1beta/models/gemini-2.0-flash:generateContent";
    private final String API_GEMINI_KEY = "AIzaSyAbV964qC-RA_IM8Ahop0sXzJr2JxF1z6c";
    private final String PROMPT =  " Você tem à sua disposição uma lista de registros com informações sobre URLs curtas e seus respectivos dados. Cada registro contém:\n" +
            "\n" +
            "URL Original: O endereço completo da página de destino.\n" +
            "\n" +
            "URL Curta: A versão encurtada do link.\n" +
            "\n" +
            "Número de Cliques: O total de cliques que a URL encurtada recebeu.\n" +
            "\n" +
            "Data de Expiração: A data em que a URL encurtada expira.\n" +
            "\n" +
            "Data de Criação: A data em que a URL encurtada foi gerada.\n" +
            "\n" +
            "Sua tarefa é analisar esses dados e gerar um relatório detalhado, destacando pontos relevantes, como:\n" +
            "\n" +
            "Resumo geral:\n" +
            "\n" +
            "Quantas URLs curtas foram criadas.\n" +
            "\n" +
            "O total de cliques acumulados.\n" +
            "\n" +
            "Quantas URLs já expiraram ou estão prestes a expirar.\n" +
            "\n" +
            "URLs mais populares:\n" +
            "\n" +
            "Exibir as 5 URLs curtas com o maior número de cliques.\n" +
            "\n" +
            "Mostrar também o número de cliques e a URL original correspondente.\n" +
            "\n" +
            "URLs expiradas ou próximas da expiração:\n" +
            "\n" +
            "Exibir as URLs que já expiraram ou que estão com a data de expiração próxima (por exemplo, nos próximos 7 dias).\n" +
            "\n" +
            "Para cada URL, mostrar a URL curta, a data de expiração e a quantidade de cliques.\n" +
            "\n" +
            "Análise de criação e cliques:\n" +
            "\n" +
            "Verifique se há alguma correlação entre a data de criação e o número de cliques.\n" +
            "\n" +
            "Identifique se as URLs criadas em um determinado período (por exemplo, no último mês) estão recebendo mais cliques do que outras.\n" +
            "\n" +
            "Sugestões de otimização:\n" +
            "\n" +
            "Com base nos dados, sugira melhorias para aumentar o engajamento ou o número de cliques, como ajustar a frequência de criação de URLs curtas ou modificar a data de expiração para maximizar a interação.\n" +
            "\n" +
            "Exemplo de entrada de dados:\"" +
            "obs: so quero a anlise do texto nada mais e os e so com os dados passados a sequir";

    public HttpResponse<String> ConnetHttp (String dados){

        HttpResponse<String> response = null;
        try {
            JsonObject jsonRequest = new JsonObject();
            JsonArray contentsArray = new JsonArray();
            JsonObject contentObject = new JsonObject();
            JsonArray partsArray = new JsonArray();
            JsonObject partObject = new JsonObject();

            partObject.addProperty("text", PROMPT + dados );
            partsArray.add(partObject);
            contentObject.add("parts", partsArray);
            contentsArray.add(contentObject);
            jsonRequest.add("contents", contentsArray);

            String jsonString = jsonRequest.toString();

            HttpRequest httpRequest = HttpRequest.newBuilder()
                    .uri(URI.create(LINK))
                    .header("Content-Type", "application/json")
                    .header("Accept", "application/json")
                    .header("X-goog-api-key", API_GEMINI_KEY)
                    .POST(HttpRequest.BodyPublishers.ofString(jsonString)) // Corpo da requisição
                    .build();

            HttpClient httpClient = HttpClient.newHttpClient();

            return httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());


        }catch (Exception e){
            e.printStackTrace();
            return null;
        }

    }
}
