package br.com.fiap.app;

import br.com.fiap.dao.MensagemDAO;
import br.com.fiap.model.Mensagem;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Scanner;

public class AplicacaoAltera {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            MensagemDAO mensagemDAO = new MensagemDAO();

            System.out.print("Digite o ID da mensagem que deseja alterar: ");
            long id = scanner.nextLong();
            scanner.nextLine();

            Mensagem mensagem = mensagemDAO.recuperaPorId(id);
            if (mensagem == null) {
                System.out.println("Mensagem não encontrada.");
                return;
            }

            System.out.println("Alterando mensagem com ID: " + mensagem.getId());
            System.out.print("Novo assunto (deixe em branco para manter): ");
            String assunto = scanner.nextLine();
            if (!assunto.isEmpty()) {
                mensagem.setAssunto(assunto);
            }

            System.out.print("Novo destinatário (deixe em branco para manter): ");
            String destinatario = scanner.nextLine();
            if (!destinatario.isEmpty()) {
                mensagem.setDestinatario(destinatario);
            }

            System.out.print("Novo remetente (deixe em branco para manter): ");
            String remetente = scanner.nextLine();
            if (!remetente.isEmpty()) {
                mensagem.setRemetente(remetente);
            }

            System.out.print("Nova data (ou pressione Enter para manter): ");
            String dataInput = scanner.nextLine();
            if (!dataInput.isEmpty()) {
                mensagem.setData(LocalDate.parse(dataInput));
            }

            System.out.print("Novo conteúdo (deixe em branco para manter): ");
            String conteudo = scanner.nextLine();
            if (!conteudo.isEmpty()) {
                mensagem.setConteudo(conteudo);
            }

            System.out.print("Novo status (0: não lida, 1: lida, 2: armazenada, deixe em branco para manter): ");
            String statusInput = scanner.nextLine();
            if (!statusInput.isEmpty()) {
                mensagem.setStatus(Integer.parseInt(statusInput));
            }

            mensagemDAO.altera(mensagem);
            System.out.println("Mensagem alterada com sucesso!");
        } catch (SQLException e) {
            System.err.println("Erro ao alterar a mensagem: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Erro: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}
