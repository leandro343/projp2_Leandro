import java.util.List;
import javax.swing.JOptionPane;

public class GeracaoEmojisOpcao {
    private final ChatGPTClient chatGPTClient;

    public GeracaoEmojisOpcao(ChatGPTClient chatGPTClient) {
        this.chatGPTClient = chatGPTClient;
    }

    public void executar() {
        try {
            String nomeFilme = JOptionPane.showInputDialog("Digite o nome do filme:");
            int quantidade = 3;
            List<String> emojis = chatGPTClient.obterEmojisFilme(nomeFilme, quantidade);
            exibirEmojis(emojis);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void exibirEmojis(List<String> emojis) {
        StringBuilder sb = new StringBuilder();
        sb.append("Emojis para o filme:\n");
        for (String emoji : emojis) {
            sb.append("- ").append(emoji).append("\n");
        }
        JOptionPane.showMessageDialog(null, sb.toString());
    }
}
