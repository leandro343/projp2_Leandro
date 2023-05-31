import com.google.gson.Gson;
import okhttp3.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ChatGPTClient {
    private final String OPENAI_API_KEY;
    private final OkHttpClient httpClient;
    private final Gson gson;

    public ChatGPTClient(String openaiApiKey) {
        this.OPENAI_API_KEY = openaiApiKey;
        this.httpClient = new OkHttpClient();
        this.gson = new Gson();
    }

    public List<String> obterTraducoes(String textoIngles, int quantidade) throws IOException {
        String prompt = "Traduza o texto para o português:\n" + textoIngles;

        ChatGPTRequest request = new ChatGPTRequest("text-davinci-003", prompt, quantidade);
        String requestBody = gson.toJson(request);

        Request httpRequest = new Request.Builder()
                .url("https://api.openai.com/v1/completions")
                .addHeader("Content-Type", "application/json")
                .addHeader("Authorization", "Bearer " + OPENAI_API_KEY)
                .post(RequestBody.create(requestBody, MediaType.parse("application/json")))
                .build();

        try (Response response = httpClient.newCall(httpRequest).execute()) {
            if (response.isSuccessful()) {
                ChatGPTResponse chatGPTResponse = gson.fromJson(response.body().string(), ChatGPTResponse.class);
                List<String> traducoes = new ArrayList<>();
                for (Choice choice : chatGPTResponse.getChoices()) {
                    traducoes.add(choice.getText());
                }
                return traducoes;
            } else {
                throw new IOException("Erro na solicitação: " + response);
            }
        }
    }

    public String criarPergunta(String contexto, String disciplina, String nivel, String tipo, String pergunta) throws IOException {
        String prompt = "Contexto: " + contexto + "\n"
                + "Disciplina: " + disciplina + "\n"
                + "Nível: " + nivel + "\n"
                + "Tipo: " + tipo + "\n"
                + "Pergunta: " + pergunta;

        ChatGPTRequest request = new ChatGPTRequest("text-davinci-003", prompt, 1);
        String requestBody = gson.toJson(request);

        Request httpRequest = new Request.Builder()
                .url("https://api.openai.com/v1/completions")
                .addHeader("Content-Type", "application/json")
                .addHeader("Authorization", "Bearer " + OPENAI_API_KEY)
                .post(RequestBody.create(requestBody, MediaType.parse("application/json")))
                .build();

        try (Response response = httpClient.newCall(httpRequest).execute()) {
            if (response.isSuccessful()) {
                ChatGPTResponse chatGPTResponse = gson.fromJson(response.body().string(), ChatGPTResponse.class);
                String resposta = chatGPTResponse.getChoices().get(0).getText();
                return resposta;
            } else {
                throw new IOException("Erro na solicitação: " + response);
            }
        }
    }

    public List<String> obterEmojisFilme(String filme, int quantidade) throws IOException {
        String prompt = "Obtenha emojis relacionados ao filme:\n" + filme;

        ChatGPTRequest request = new ChatGPTRequest("text-davinci-003", prompt, quantidade);
        String requestBody = gson.toJson(request);

        Request httpRequest = new Request.Builder()
                .url("https://api.openai.com/v1/completions")
                .addHeader("Content-Type", "application/json")
                .addHeader("Authorization", "Bearer " + OPENAI_API_KEY)
                .post(RequestBody.create(requestBody, MediaType.parse("application/json")))
                .build();

        try (Response response = httpClient.newCall(httpRequest).execute()) {
            if (response.isSuccessful()) {
                ChatGPTResponse chatGPTResponse = gson.fromJson(response.body().string(), ChatGPTResponse.class);
                List<String> emojis = new ArrayList<>();
                for (Choice choice : chatGPTResponse.getChoices()) {
                    emojis.add(choice.getText());
                }
                return emojis;
            } else {
                throw new IOException("Erro na solicitação: " + response);
            }
        }
    }


    public static void main(String[] args) {
        String openaiApiKey = "";
        ChatGPTClient client = new ChatGPTClient(openaiApiKey);

        try {
            String textoIngles = "Hello, how are you?";
            List<String> traducoes = client.obterTraducoes(textoIngles, 2);
            System.out.println("Traduções: " + traducoes);

            String pergunta = "Por que o céu é azul?";
            String resposta = client.criarPergunta(null, "Java", "alternativa", "ultra hard core", pergunta);
            System.out.println("Resposta: " + resposta);

            String filme = "Titanic";
            List<String> emojisFilme = client.obterEmojisFilme(filme, 3);
            System.out.println("Emojis do filme: " + emojisFilme);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
