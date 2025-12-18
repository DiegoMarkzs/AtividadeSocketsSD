import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.Socket;

class Connection extends Thread {
    DataInputStream in;
    DataOutputStream out;
    Socket clientSocket;
    GerenciadorMensagem gerenciador = new GerenciadorMensagem();

    public Connection(Socket aClientSocket) {
        try {
            clientSocket = aClientSocket;
            in = new DataInputStream(clientSocket.getInputStream());
            out = new DataOutputStream(clientSocket.getOutputStream());
            this.start();
        } catch (IOException e) {
            System.out.println("Connection:" + e.getMessage());
        }
    }

    @Override
    public void run() {
        String data;
        try { // an echo server
            data = in.readUTF();
            data = gerenciador.buscarMensagemEspecifica(Integer.parseInt(data));
            out.writeUTF(data);
            out.flush(); 
            return;
        } catch (EOFException e) {
            data = "Não foi possivel encontrar a frase desejada";
        } catch (IOException e) {
            data = "Não foi possivel encontrar a frase desejada";
        } catch (NumberFormatException e) {
           data = "A mensagem deve ser um numero";
        }

        finally{
            try { in.close(); } catch (IOException ignored) {}
        try { out.close(); } catch (IOException ignored) {}
        try { clientSocket.close(); } catch (IOException ignored) {}}
    }
}
