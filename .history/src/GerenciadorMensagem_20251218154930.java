import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class GerenciadorMensagem {
    public static void main(String[] args) {
        GerenciadorMensagem gerenciador = new GerenciadorMensagem();
        gerenciador.buscarMensagemEspecifica(0);

    }

    File arquivo;

    public String buscarMensagemEspecifica(int numeroLinhaMensagem){
        if(numeroLinhaMensagem == 0){
            numeroLinhaMensagem = (int) (Math.random() * 10) + 1;
        }
        try {
            arquivo = new File("./mensagens.txt");
            BufferedReader reader = new BufferedReader(new FileReader(arquivo));
            String linha;
            int numeroLinhaAtual = 0;
            while ((linha = reader.readLine()) != null) {
                if (numeroLinhaAtual == numeroLinhaMensagem) {
                    reader.close();
                    return linha;
                    
                }
                numeroLinhaAtual++;
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo: " + e.getMessage());
        }
        return null;
        
    }

}
