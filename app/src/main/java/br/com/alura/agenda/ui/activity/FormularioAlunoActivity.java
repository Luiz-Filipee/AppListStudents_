package br.com.alura.agenda.ui.activity;

import static br.com.alura.agenda.ui.activity.ConstantesActivities.CHAVE_ALUNO;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import br.com.alura.agenda.R;
import br.com.alura.agenda.dao.AlunoDAO;
import br.com.alura.agenda.database.AgendaDataBase;
import br.com.alura.agenda.database.dao.RoomAlunoDAO;
import br.com.alura.agenda.model.Aluno;

public class FormularioAlunoActivity extends AppCompatActivity {

    private EditText campoNome;
    private EditText campoTelefone;
    private EditText campoEmail;
    private Aluno aluno;
    private RoomAlunoDAO dao;
    private static final String TITULO_APPBAR_NOVO_ALUNO = "Novo Aluno";
    private static final String TITULO_APPBAR_Edita_ALUNO = "Edita Aluno";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_alunos);
        AgendaDataBase dataBase = AgendaDataBase.getInstance(this);
        dao = dataBase.getRoomAlunoDAO();
        inicializacaoDosCampos();
        carregaAluno();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_formulario_aluno_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.activity_formulario_aluno_menu_salvar) {
            finalizaFormulario();
        } else if (itemId == R.id.menu_voltar) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }


    private void carregaAluno() {
        Intent dados = getIntent(); // Pegando o intent da outra classe
        if (dados.hasExtra(CHAVE_ALUNO)) { // verificando se o extra "aluno" existe
            setTitle(TITULO_APPBAR_Edita_ALUNO);
            aluno = (Aluno) dados.getSerializableExtra(CHAVE_ALUNO);
            preencheCampos();
        } else {
            setTitle(TITULO_APPBAR_NOVO_ALUNO);
            aluno = new Aluno();
        }
    }

    private void preencheCampos() {
        campoNome.setText(aluno.getNome());
        campoTelefone.setText(aluno.getTelefone());
        campoEmail.setText(aluno.getEmail());
    }

    private void finalizaFormulario() {
        preencheAluno();
        if (aluno.temIdValido()) {
            dao.edita(aluno);
            finish();
        } else {
            dao.salva(aluno);
            finish();
        }
    }


    private void inicializacaoDosCampos() {
        campoNome = findViewById(R.id.activity_formulario_aluno_campo_nome);
        campoTelefone = findViewById(R.id.activity_formulario_aluno_campo_telefone);
        campoEmail = findViewById(R.id.activity_formulario_aluno_campo_email);
    }

    private void preencheAluno() {
        String nome = campoNome.getText().toString();
        String telefone = campoTelefone.getText().toString();
        String email = campoEmail.getText().toString();

        aluno.setNome(nome);
        aluno.setTelefone(telefone);
        aluno.setEmail(email);
    }
}