package entidade;

import controle.AgendaEvento;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

public class ManterAgenda extends DAO {

    public void inserir(AgendaEvento a) throws Exception {
        try {
            abrirBanco();
            String query = "INSERT INTO agenda(id,nome,email,horario,data) "
                    + "values(?,?,?,?,?)";
            pst = (PreparedStatement) con.prepareStatement(query);
            pst.setInt(1, a.getId());
            pst.setString(2, a.getNome());
            pst.setString(3, a.getEmail());
            pst.setString(4, a.getHora());
            pst.setString(5, a.getData());
            pst.execute();
            fecharBanco();
        } catch (Exception e) {
            System.out.println("Erro " + e.getMessage());
        }
    }

    public ArrayList<AgendaEvento> PesquisarTudo() throws Exception {
        ArrayList<AgendaEvento> agenda = new ArrayList<AgendaEvento>();
        try {
            abrirBanco();
            String query = "select id,nome,email,horario,data FROM agenda";
            pst = (PreparedStatement) con.prepareStatement(query);
            ResultSet tr = pst.executeQuery();
            AgendaEvento a;
            while (tr.next()) {
                a = new AgendaEvento();
                a.setId(tr.getInt("id"));
                a.setNome(tr.getString("nome"));
                a.setEmail(tr.getString("email"));
                a.setHora(tr.getString("horario"));
                a.setData(tr.getString("data"));
                agenda.add(a);
            }
            fecharBanco();
        } catch (Exception e) {
            System.out.println("Erro " + e.getMessage());
        }
        return agenda;
    }

    public void PesquisarRegistro(AgendaEvento a) throws Exception {
        try {
            abrirBanco();
            String query = "select * FROM agenda where id=?";
            pst = (PreparedStatement) con.prepareStatement(query);
            pst.setInt(1, a.getId());
            ResultSet tr = pst.executeQuery();
            if (tr.next()) {
                a.setId(tr.getInt("id"));
                a.setNome(tr.getString("nome"));
                a.setEmail(tr.getString("email"));
                a.setHora(tr.getString("horario"));
                a.setData(tr.getString("data"));
            } else {
                //  JOptionPane.showMessageDialog(null, "Nenhum resultado encontrado! ");
            }
            fecharBanco();
        } catch (Exception e) {
            System.out.println("Erro " + e.getMessage());
        }
    }

    public void editarAgenda(AgendaEvento a) throws Exception {
        abrirBanco();
        //JOptionPane.showMessageDialog(null, a.getNome()+ a.getEmail() + a.getIdade());
        String query = "UPDATE agenda set nome = ?,email = ?, horario = ?,data = ? where id=?";
        pst = (PreparedStatement) con.prepareStatement(query);
        pst.setString(1, a.getNome());
        pst.setString(2, a.getEmail());
        pst.setString(3, a.getHora());
        pst.setString(4, a.getData());
        pst.setInt(5, a.getId());
        pst.executeUpdate();
        fecharBanco();
    }

    public void deletarAgenda(AgendaEvento a) throws Exception {
        abrirBanco();
        String query = "delete from agenda where id=?";
        pst = (PreparedStatement) con.prepareStatement(query);
        pst.setInt(1, a.getId());
        pst.execute();
        JOptionPane.showMessageDialog(null, "Deletado com sucesso!");
        fecharBanco();
    }
}
