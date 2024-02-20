package br.com.alura.agenda.dao;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import br.com.alura.agenda.model.Aluno;

public class AlunoDAO {

    private final static List<Aluno> alunos = new ArrayList<>();
    private static int contadorDeId = 1;

    public void salva(Aluno aluno) {
        aluno.setId(contadorDeId);
        alunos.add(aluno);
        atualizaIds();
    }

    public void atualizaIds(){
        contadorDeId++;
    }

    public void edita(Aluno aluno) {
        Aluno alunoEncontrado = findViewById(aluno);
        if (alunoEncontrado != null) {
            int posicaoAluno = alunos.indexOf(alunoEncontrado);
            alunos.set(posicaoAluno, aluno);
        }
    }

    @Nullable
    private static Aluno findViewById(Aluno aluno) {
        for (Aluno a : alunos) {
            if (a.getId() == aluno.getId()) {
                return a;
            }
        }
        return null;
    }


    public List<Aluno> todos() {
        return new ArrayList<>(alunos);
    }

    public void remove(Aluno alunoEscolhido) {
        Aluno alunoDevolvido = findViewById(alunoEscolhido);
        if(alunoDevolvido != null){
            alunos.remove(alunoDevolvido);
        }
    }
}