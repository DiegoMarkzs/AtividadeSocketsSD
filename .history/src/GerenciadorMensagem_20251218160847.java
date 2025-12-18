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
        try {
            arquivo = new File("./mensagens.txt");
            BufferedReader reader = new BufferedReader(new FileReader(arquivo));
            String linha;
            int numeroLinhaAtual = 0;
            
            int numMax = Integer.parseInt((reader.readLine()));
            
            if(numeroLinhaMensagem == 0){
                numeroLinhaMensagem = (int) (Math.random() * numMax) + 1;
            }

            if(numeroLinhaMensagem < 0 || numeroLinhaMensagem > numMax){
                reader.close();
                return "Número inválido! Escolha um número entre 1 e " + numMax;
            }
            

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
