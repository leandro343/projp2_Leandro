import java.util.List;
import javax.swing.JOptionPane;

public class TraducaoOpcao {
    private final ChatGPTClient chatGPTClient;

    public TraducaoOpcao(ChatGPTClient chatGPTClient) {
        this.chatGPTClient = chatGPTClient;
    }

    public void executar() {
        try {
            String textoIngles = JOptionPane.showInputDialog("Digite o texto em inglês:");
            int quantidade = 2;
            List<String> traducoes = chatGPTClient.obterTraducoes(textoIngles, quantidade);
            exibirTraducoes(traducoes);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void exibirTraducoes(List<String> traducoes) {
        StringBuilder sb = new StringBuilder();
        sb.append("Traduções:\n");
        for (String traducao : traducoes) {
            sb.append("- ").append(traducao).append("\n");
        }
        JOptionPane.showMessageDialog(null, sb.toString());
    }
}
