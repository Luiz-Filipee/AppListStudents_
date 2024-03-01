package br.com.alura.agenda;

import android.app.Application;

import br.com.alura.agenda.dao.AlunoDAO;
import br.com.alura.agenda.model.Aluno;

public class AgendaApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        criaAlunosTeste();
    }

    private static void criaAlunosTeste() {
        AlunoDAO dao = new AlunoDAO();
        dao.salva(new Aluno("Luiz","1231231","luizkato8@Gmail.com"));
        dao.salva(new Aluno("Luiz filipe","1231231","luizkato8@Gmail.com"));
    }
}
