import javax.swing.JOptionPane;

public class ExplicacaoParaCriancasOpcao {
    private final ChatGPTClient chatGPTClient;

    public ExplicacaoParaCriancasOpcao(ChatGPTClient chatGPTClient) {
        this.chatGPTClient = chatGPTClient;
    }

    public void executar() {
        try {
            String pergunta = JOptionPane.showInputDialog("digite uma pergunta :");
            String explicacao = chatGPTClient.obterTraducoes(pergunta, 1).get(0);
            exibirExplicacao(explicacao);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void exibirExplicacao(String explicacao) {
        JOptionPane.showMessageDialog(null, "explicação para crianças:\n" + explicacao);
    }
}
