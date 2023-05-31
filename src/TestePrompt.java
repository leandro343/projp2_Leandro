public class TestePrompt {
  public static void main(String[] args) throws Exception {
      String openaiApiKey = "";
      ChatGPTClient chatGPTClient = new ChatGPTClient(openaiApiKey);
      chatGPTClient.obterTraducoes("oi como vai você?", 1).get(0);
      chatGPTClient.criarPergunta(
              null,
              "Java",
              "alternativa",
              "ultra hard core",
              "Por que o céu é azul?"
      );
  }
}
