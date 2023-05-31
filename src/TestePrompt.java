public class TestePrompt {
  public static void main(String[] args) throws Exception {
      String openaiApiKey = "sua_chave_de_api_aqui";
      ChatGPTClient chatGPTClient = new ChatGPTClient(openaiApiKey);
      chatGPTClient.obterTraducoes("olá, como vai você?", 1).get(0);
      chatGPTClient.criarPergunta(
              null,
              "Java",
              "alternativa",
              "ultra hard core",
              "Por que o céu é azul?"
      );
  }
}
