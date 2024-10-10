package br.com.fiap.dao;

import br.com.fiap.model.Mensagem;
import br.com.fiap.util.Conexao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MensagemDAO {

    public void insere(Mensagem m) throws Exception {
        String sql = "insert into tbl_mensagem(assunto,destinatario , remetente, data,conteudo,status " +
                "values(?, ?, ?, ?, ?, ?)";

        try(Connection con = new Conexao().getConexao();
            PreparedStatement pstmt = con.prepareStatement(sql, new String[]{"id"});) {

            pstmt.setString(1, m.getAssunto());
            pstmt.setString(2, m.getDestinatario());
            pstmt.setString(3, m.getRemetente());
            pstmt.setDate(4, Date.valueOf(m.getData()));
            pstmt.setString(5, m.getConteudo());
            pstmt.setInt(6, m.getStatus());
            pstmt.executeUpdate();

            ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                m.setId(rs.getBigDecimal(1).longValue());
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public void altera(Mensagem m) throws Exception {
        String sql = "update tbl_mensagem set assunto=?, destinatario=?, remetente=?, data=?, " +
                "conteudo=?, status=? where id=?";
        try(Connection con = new Conexao().getConexao();
            PreparedStatement pstmt = con.prepareStatement(sql);)
        {
            pstmt.setString(1, m.getAssunto());
            pstmt.setString(2, m.getDestinatario());
            pstmt.setString(3, m.getRemetente());
            pstmt.setDate(4, Date.valueOf(m.getData()));
            pstmt.setString(5, m.getConteudo());
            pstmt.setInt(6, m.getStatus());
            pstmt.executeUpdate();
        }
        catch(Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
    public Mensagem recuperaPorId(Long id) throws Exception {
        String sql = "select id, assunto, destinario, remetente, data,conteudo,status " +
                "from tbl_mensagem where id=?";

        try(Connection con = new Conexao().getConexao();
            PreparedStatement pstmt = con.prepareStatement(sql);){

            pstmt.setLong(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                Mensagem m = new Mensagem();
                m.setId(rs.getLong("id"));
                m.setAssunto(rs.getString("assunto"));
                m.setDestinatario(rs.getString("destinatario"));
                m.setRemetente(rs.getString("remetente"));
                m.setData(rs.getDate("data").toLocalDate());
                m.setConteudo(rs.getString("conteudo"));
                m.setStatus(rs.getInt("status"));
                return m;
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return null;
    }

    public List<Mensagem> recuperaPorData(LocalDate data) throws Exception {
        String sql = "select id, assunto, destinario, remetente, data,conteudo,status " +
                "from tbl_mensagem where data=?";
        List<Mensagem> m = new ArrayList<>();

        try(Connection con = new Conexao().getConexao();
            PreparedStatement pstmt = con.prepareStatement(sql);){

            pstmt.setDate(1, Date.valueOf(data));
            ResultSet rs = pstmt.executeQuery();
            while (rs.next() ) {
                Mensagem mens = new Mensagem();
                mens.setId(rs.getLong("id"));
                mens.setAssunto(rs.getString("assunto"));
                mens.setDestinatario(rs.getString("destinatario"));
                mens.setRemetente(rs.getString("remetente"));
                mens.setData(rs.getDate("data").toLocalDate());
                mens.setConteudo(rs.getString("conteudo"));
                mens.setStatus(rs.getInt("status"));
                m.add(mens);

            }
        }
        catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return m;

    }
    public List<Mensagem> recuperaPorDestinatario(String destinatario) throws Exception {
        String sql = "select id, assunto, destinatario, remetente, data, conteudo, status " +
                "from tbl_mensagem where destinatario=?";
        List<Mensagem> mensagens = new ArrayList<>();

        try (Connection con = new Conexao().getConexao();
             PreparedStatement pstmt = con.prepareStatement(sql)) {

            pstmt.setString(1, destinatario);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Mensagem mens = new Mensagem();
                mens.setId(rs.getLong("id"));
                mens.setAssunto(rs.getString("assunto"));
                mens.setDestinatario(rs.getString("destinatario"));
                mens.setRemetente(rs.getString("remetente"));
                mens.setData(rs.getDate("data").toLocalDate());
                mens.setConteudo(rs.getString("conteudo"));
                mens.setStatus(rs.getInt("status"));
                mensagens.add(mens);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return mensagens;
    }
}
