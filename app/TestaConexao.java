package br.com.fiap.app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TestaConexao {
    public static void main(String[] args) {
        String url = "jdbc:oracle:thin:@oracle.fiap.com.br:1521:orcl";
        String user = "rm555160"; // substitua pelo seu usuário
        String password = "130905"; // substitua pela sua senha

        try {
            Connection con = DriverManager.getConnection(url, user, password);
            System.out.println("Conexão realizada com sucesso!");
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
