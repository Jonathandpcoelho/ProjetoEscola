package escola;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import util.Conexao;

public class Aluno {
    private int    codaluno;
    private String nomaluno;
    private float  medaluno;
    
    public boolean IncluirAluno() {
        String sql  = "insert into aluno ";
               sql += "(nomealuno, medaluno) ";
               sql += " values(?,?);";
        Connection con = Conexao.conectar();
        try {
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setString(1, this.nomaluno);
            stm.setFloat(2, this.medaluno);
            stm.execute();
        } catch (SQLException ex) {
            System.out.println("Erro: " + ex.getMessage() +sql);
            return false;
        }
        return true;
    }

    public boolean AlterarAluno() {
        String sql  = "update aluno ";
               sql += "set nomealuno= ?, medaluno= ? ";
               sql += "where codaluno = ?";
        Connection con = Conexao.conectar();
        try {
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setString(1, this.nomaluno);
            stm.setFloat(2, this.medaluno);
            stm.setInt(3, this.codaluno);
            stm.execute();
        } catch (SQLException ex) {
            System.out.println("Erro: " + ex.getMessage() +sql);
            return false;
        }
        return true;
    }
    
     public boolean ExcluirAluno() {
        String sql  = "delete from aluno ";
               sql += "where codaluno = ?";
        Connection con = Conexao.conectar();
        try {
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setInt(1, this.codaluno);
            stm.execute();
        } catch (SQLException ex) {
            System.out.println("Erro: " + ex.getMessage() +sql);
            return false;
        }
        return true;
    }
   
      public List<Aluno> consultaAlunos() {
        List<Aluno> lista = new ArrayList<>();
        Connection con = Conexao.conectar();
        String sql  = "select codaluno, nomealuno, medaluno ";
               sql += " from aluno";
               sql += " order by 1";
        try {
            PreparedStatement stm = con.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Aluno alu = new Aluno();
                alu.setCodaluno(rs.getInt("codaluno"));
                alu.setNomaluno(rs.getString("nomealuno"));
                alu.setMedaluno(rs.getFloat("medaluno"));
                lista.add(alu);
            }
        } catch (SQLException ex) {
            System.out.println("Erro: " + ex.getMessage());
        }
        return lista;
    }

     public boolean alunoExiste(int pAluno){
        Connection con = Conexao.conectar();
        String sql = "select * from aluno where codaluno = ?";
        try {
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setInt(1, pAluno);
            ResultSet rs = stm.executeQuery();
            return rs.next();            
        } 
            catch (SQLException ex) {
            System.out.println("Erro: " + ex.getMessage());
            }
        return true;
    }  

     public List<Aluno> consultarDadosAluno(int pCodAluno){
        this.codaluno = pCodAluno;
        List<Aluno> lista = new ArrayList();         
        Connection con = Conexao.conectar();
        String sql  = "select codaluno, nomealuno,medaluno ";
               sql += " from aluno ";
               sql += "where codaluno = ?";
        try {
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setInt(1, pCodAluno);
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                Aluno alunos = new Aluno();
                alunos.setCodaluno((rs.getInt("codaluno")));
                alunos.setNomaluno(rs.getString("nomealuno"));
                alunos.setMedaluno(rs.getFloat("medaluno"));
                lista.add(alunos);
            }
        } 
            catch (SQLException ex) {
            System.out.println("Erro: " + ex.getMessage());
        }      
        return lista;
    }
     
     public List<Aluno> consultarAlunoGeral(){
        List<Aluno> lista = new ArrayList();         
        Connection con = Conexao.conectar();
        String sql  = "select codaluno, nomealuno,medaluno ";
               sql += "from aluno ";
               sql += "order by codaluno;";
        try {
            PreparedStatement stm = con.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                Aluno alunos = new Aluno();
                alunos.setCodaluno((rs.getInt("codaluno")));
                alunos.setNomaluno(rs.getString("nomealuno"));
                alunos.setMedaluno(rs.getFloat("medaluno"));
                lista.add(alunos);
            }
        } 
            catch (SQLException ex) {
            System.out.println("Erro: " + ex.getMessage());
        }      
        return lista;
    }

     public List<Aluno> calcularMedia(){
        List<Aluno> lista = new ArrayList();         
        Connection con = Conexao.conectar();
        String sql  = "select n.codaluno , round(avg(n.vlrnota),2) medaluno ";
               sql += "from nota n ";
               sql += "group by n.codaluno ";
               sql += "order by n.codaluno ";
        try {
            PreparedStatement stm = con.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                Aluno alunos = new Aluno();
                alunos.setCodaluno((rs.getInt("codaluno")));
                alunos.setMedaluno(rs.getFloat("medaluno"));
                lista.add(alunos);
            }
        } 
            catch (SQLException ex) {
            System.out.println("Erro: " + ex.getMessage());
        }      
        return lista;
    }
     
    public boolean ajustarMedia(int pCodAluno, float pMedAluno) {
        this.codaluno = pCodAluno;
        this.medaluno = pMedAluno;
                
        String sql  = "update aluno ";
               sql += "set medaluno = ? ";
               sql += "where codaluno = ?";
        Connection con = Conexao.conectar();
        try {
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setFloat(1, this.medaluno);            
            stm.setInt(2, this.codaluno);
            stm.execute();
        } catch (SQLException ex) {
            System.out.println("Erro: " + ex.getMessage() +sql);
            return false;
        }
        return true;
    }
         
    // area de getters e setters
    public int getCodaluno() {
        return codaluno;
    }

    public void setCodaluno(int codaluno) {
        this.codaluno = codaluno;
    }

    public String getNomaluno() {
        return nomaluno;
    }

    public void setNomaluno(String nomaluno) {
        this.nomaluno = nomaluno;
    }

    public float getMedaluno() {
        return medaluno;
    }

    public void setMedaluno(float medaluno) {
        this.medaluno = medaluno;
    }   
}