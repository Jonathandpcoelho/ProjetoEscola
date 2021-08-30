package escola;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import util.Conexao;

public class Nota {
    private int   codaluno;
    private int   nravaliacao;
    private float vlrnota;

    public boolean IncluirNotas() {
        String sql  = "insert into nota  ";
               sql += "(codaluno, nravaliacao,vlrnota) ";
               sql += " values(?,?,?);";
        Connection con = Conexao.conectar();
        try {
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setInt(1, this.codaluno);
            stm.setInt(2,this.nravaliacao);
            stm.setFloat(3, this.vlrnota);
            stm.execute();
        } catch (SQLException ex) {
            System.out.println("Erro: " + ex.getMessage() +sql);
            return false;
        }
        return true;
    }    
    
    
    
    public List<Nota> consultarNotasAluno(int pCodAluno){
        List<Nota> lista = new ArrayList();
        Connection con = Conexao.conectar();
        String sql  = "select n.codaluno, n.nravaliacao, n.vlrnota ";
               sql += "from nota  n ";
               sql += "where n.codaluno = ? ";
               sql += "order by n.nravaliacao;";
        try {
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setInt(1, pCodAluno);
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                Nota notas = new Nota();                                
                notas.setCodaluno((rs.getInt("codaluno")));
                notas.setNravaliacao(rs.getInt("nravaliacao"));
                notas.setVlrnota(rs.getFloat("vlrnota"));
                lista.add(notas);
            }
        } 
            catch (SQLException ex) {
            System.out.println("Erro: " + ex.getMessage());
        }      
        return lista;
    }

    // area de getters e setters
    public int getCodaluno() {
        return codaluno;
    }

    public void setCodaluno(int codaluno) {
        this.codaluno = codaluno;
    }

    public int getNravaliacao() {
        return nravaliacao;
    }

    public void setNravaliacao(int nravaliacao) {
        this.nravaliacao = nravaliacao;
    }

    public float getVlrnota() {
        return vlrnota;
    }

    public void setVlrnota(float vlrnota) {
        this.vlrnota = vlrnota;
    }
    
}
