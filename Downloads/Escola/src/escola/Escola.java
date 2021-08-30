package escola;

import java.util.List;
import javax.swing.JOptionPane;

public class Escola {
    public static void main(String[] args) {
        boolean fimprog = false;
        
        do {            
            menuPrincipal();
            Aluno alu = new Aluno();
            String opcao = JOptionPane.showInputDialog("Informe a opcao");
            
            switch(opcao) {        
                case "1":   alu.setNomaluno(JOptionPane.showInputDialog("Nome do Aluno"));
                            alu.setMedaluno(Float.parseFloat(JOptionPane.showInputDialog("Média do Aluno")));
                            if (alu.IncluirAluno()) {
                                JOptionPane.showMessageDialog(null, "Aluno Cadastrado com Sucesso");
                            } else {
                                JOptionPane.showMessageDialog(null,"Problema ao cadastrar aluno");
                            }
                            break;

                case "2":   alu.setCodaluno(Integer.parseInt(JOptionPane.showInputDialog("Informe o Código do Aluno")));
                            alu.setNomaluno(JOptionPane.showInputDialog("Nome do Aluno"));
                            alu.setMedaluno(Float.parseFloat(JOptionPane.showInputDialog("Média do Aluno")));
                            if (alu.AlterarAluno()) {
                                JOptionPane.showMessageDialog(null, "Aluno Alterado com Sucesso");
                            } else {
                                JOptionPane.showMessageDialog(null,"Problema ao alterar o Aluno","ATENÇÃO",JOptionPane.WARNING_MESSAGE);
                            }
                            break;                        
                
                case "3":   alu.setCodaluno(Integer.parseInt(JOptionPane.showInputDialog("Informe o Código do Aluno")));
                            if (alu.ExcluirAluno()) {
                                JOptionPane.showMessageDialog(null, "Aluno Removido com Sucesso");
                            } else {
                                JOptionPane.showMessageDialog(null,"Problema ao remover o Aluno","ATENÇÃO",JOptionPane.WARNING_MESSAGE);
                            }
                            break;                               
                            
                case "4" :  Aluno alu1 = new Aluno();
                            List<Aluno> alunos = alu1.consultaAlunos();
                            for (Aluno a : alunos) {
                                System.out.println("Aluno :" + a.getCodaluno());
                                System.out.println("Nome :" + a.getNomaluno());
                                System.out.println("Média :" + a.getMedaluno());
                            }
                            break;
               
                case "5" :  int codAluno;
                            codAluno = (Integer.parseInt(JOptionPane.showInputDialog("Informe o aluno para \n inserir as notas")));
                            if  (alu.alunoExiste(codAluno)) {
                                int qtdeNotas;
                                qtdeNotas = Integer.parseInt(JOptionPane.showInputDialog("Informe a Qtde de Notas"));
                                boolean atualizaOk = true;
                                // INSERÇÃO DE NOTAS
                                for (int nrNotaDigitada = 1; nrNotaDigitada <= qtdeNotas; nrNotaDigitada++) {
                                    Nota n = new Nota();
                                    n.setCodaluno(codAluno);
                                    n.setNravaliacao(nrNotaDigitada);
                                    n.setVlrnota(Float.parseFloat(JOptionPane.showInputDialog("Informe o valor da Nota nrº: " + nrNotaDigitada)));
                                    if (n.IncluirNotas()) {
                                    } else {
                                        atualizaOk = false;
                                        nrNotaDigitada = qtdeNotas + 1;
                                    }
                                }
                                if (atualizaOk) {
                                    JOptionPane.showMessageDialog(null,"Notas cadastradas com sucesso.","SHOW !!",JOptionPane.INFORMATION_MESSAGE);
                                } else {
                                    JOptionPane.showMessageDialog(null,"Problemas no Cadastramento das Notas.","ERRO !!",JOptionPane.ERROR_MESSAGE);    
                                }
                            } else {
                                    JOptionPane.showMessageDialog(null,"Aluno não cadastrado.","ATENÇÃO",JOptionPane.WARNING_MESSAGE);
                            }
                            break;
                            
                case "8" :  codAluno = (Integer.parseInt(JOptionPane.showInputDialog("Informe o aluno para \n inserir as notas")));
                            Aluno relaluno = new Aluno();
                            List<Aluno> listaaluno = relaluno.consultarDadosAluno(codAluno);
                            for (Aluno a : listaaluno ) {
                                System.out.println(" Aluno nrº: " + codAluno + " - " + a.getNomaluno() + " Média : " + a.getMedaluno());
                            }
                            // listando as notas
                            Nota notas = new Nota();
                            List<Nota> notasaluno = notas.consultarNotasAluno(codAluno);
                            for (Nota n : notasaluno) {
                                System.out.println(" Nota nrº:" + n.getNravaliacao() + " " + n.getVlrnota());
                            }
                            break;

                case "9" :  Aluno alu2 = new Aluno();
                            List<Aluno> alunos2 = alu2.consultarAlunoGeral();
                            for (Aluno a : alunos2 ) {
                                System.out.println(" Aluno nrº: " + a.getCodaluno() + " - " + a.getNomaluno() + " Média : " + a.getMedaluno());
                                // listando as notas
                                Nota notas2 = new Nota();
                                List<Nota> notasaluno2 = notas2.consultarNotasAluno(a.getCodaluno());
                                for (Nota n : notasaluno2) {
                                    System.out.println(" Nota nrº:" + n.getNravaliacao() + " " + n.getVlrnota());
                                }
                            }
                            break;

                case "10" : Aluno med = new Aluno();
                            List<Aluno> medalunos = med.calcularMedia();
                            for (Aluno a : medalunos ) {
                                a.ajustarMedia(a.getCodaluno(),a.getMedaluno());
                            }
                    
                            Aluno alu3 = new Aluno();
                            List<Aluno> alunos3 = alu3.consultarAlunoGeral();
                            for (Aluno a : alunos3 ) {
                                System.out.println(" Aluno nrº: " + a.getCodaluno() + " - " + a.getNomaluno() + " Média : " + a.getMedaluno());
                                // listando as notas
                                Nota notas3 = new Nota();
                                List<Nota> notasaluno3 = notas3.consultarNotasAluno(a.getCodaluno());
                                for (Nota n : notasaluno3) {
                                    System.out.println(" Nota nrº:" + n.getNravaliacao() + " " + n.getVlrnota());
                                }
                            }
                            break;
                            
                case "99" :  fimprog = true;
                            JOptionPane.showMessageDialog(null,"Programa Finalizado \n com Sucesso !!!","Atenção",JOptionPane.WARNING_MESSAGE);
                            break;
                            
                default: JOptionPane.showMessageDialog(null,"Opção Inválida. Redigite !","ERRO",JOptionPane.ERROR_MESSAGE);
            }
        } while (!fimprog);        
    }
    
    public static void menuPrincipal(){
        System.out.println("+---------------------------------+");
        System.out.println("|       MENU PRINCIPAL            |");
        System.out.println("+---------------------------------+");
        System.out.println("| ALUNO          NOTAS            |");
        System.out.println("| 1. Incluir    5. Incluir        |");
        System.out.println("| 2. Alterar    6. Alterar        |");
        System.out.println("| 3. Excluir    7. Excluir        |");
        System.out.println("| 4. Consultar  8. Consultar      |");
        System.out.println("|               9. Consulta Todos |");
        System.out.println("|              10. Calcula Média  |");
        System.out.println("|         99 = FIM                |");
        System.out.println("+---------------------------------+");
    }
}
