package br.com.alura.agenda.dao;

import java.util.ArrayList;
import java.util.List;

import br.com.alura.agenda.model.Aluno;

public class AlunoDAO {
    private final static List<Aluno> alunos = new ArrayList<>();
    private static int contadorDeIds=1;

    public void salva(Aluno aluno) {
        aluno.setId(contadorDeIds);
        alunos.add(aluno);
        atualizaIds();

    }

    private void atualizaIds() {
        contadorDeIds++;
    }

    public void edita(Aluno aluno){
        Aluno alunoEncotrado = buscaAlunoPeloId(aluno);
        if (alunoEncotrado!=null){
            int posicaoDoAluno=alunos.indexOf(alunoEncotrado);
            alunos.set(posicaoDoAluno,aluno);
        }
    }


    private Aluno buscaAlunoPeloId(Aluno aluno) {

        for (Aluno a:alunos){
            if (a.getId()== aluno.getId()){
                return a;
            }
        }
        return null;
    }
    public void remove(Aluno aluno) {
        Aluno alunoDevolvido = buscaAlunoPeloId(aluno);
        if(alunoDevolvido != null){
            alunos.remove(alunoDevolvido);
        }
    }
    public List<Aluno> todos() {
        return new ArrayList<>(alunos);
    }
}
