package br.com.fiap.app;

import br.com.fiap.dao.MensagemDAO;
import br.com.fiap.model.Mensagem;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Scanner;

public class AplicacaoInclui {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            MensagemDAO mensagemDAO = new MensagemDAO();
            Mensagem mensagem = new Mensagem();

            System.out.println("Inserir nova mensagem:");
            System.out.print("Assunto: ");
            mensagem.setAssunto(scanner.nextLine());

            System.out.print("Destinatário: ");
            mensagem.setDestinatario(scanner.nextLine());

            System.out.print("Remetente: ");
            mensagem.setRemetente(scanner.nextLine());

            System.out.print("Data (ou pressione Enter para usar a data atual): ");
            String dataInput = scanner.nextLine();
            if (dataInput.isEmpty()) {
                mensagem.setData(LocalDate.now());
            } else {
                mensagem.setData(LocalDate.parse(dataInput)); 
            }

            System.out.print("Conteúdo: ");
            mensagem.setConteudo(scanner.nextLine());

            System.out.print("Status (0: não lida, 1: lida, 2: armazenada): ");
            mensagem.setStatus(scanner.nextInt());

            mensagemDAO.insere(mensagem);
            System.out.println("Mensagem inserida com sucesso! ID: " + mensagem.getId());
        } catch (SQLException e) {
            System.err.println("Erro ao inserir a mensagem: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Erro: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}
