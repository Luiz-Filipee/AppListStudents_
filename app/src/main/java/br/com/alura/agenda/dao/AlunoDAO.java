package br.com.alura.agenda.dao;

import java.util.ArrayList;
import java.util.List;

import br.com.alura.agenda.model.Aluno;

public class AlunoDAO {

    private final static List<Aluno> alunos = new ArrayList<>();
    private static int contadorDeId = 1;

    public void salva(Aluno aluno) {
        aluno.setId(contadorDeId);
        alunos.add(aluno);
        contadorDeId++;
    }

    public void edita(Aluno aluno){
        Aluno alunoEncontrado = null;
        for (Aluno a : alunos){
            if(a.getId() == aluno.getId()){
                alunoEncontrado = a;
            }
            if (alunoEncontrado != null){
                int posicaoAluno = alunos.indexOf(alunoEncontrado);
                alunos.set(posicaoAluno, aluno);
            }
        }
    }


    public List<Aluno> todos() {
        return new ArrayList<>(alunos);
    }
}