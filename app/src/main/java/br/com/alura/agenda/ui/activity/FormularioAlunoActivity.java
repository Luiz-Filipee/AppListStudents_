package br.com.alura.agenda.ui.activity;

import static br.com.alura.agenda.ui.activity.ConstantesActivities.CHAVE_ALUNO;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import br.com.alura.agenda.R;
import br.com.alura.agenda.dao.AlunoDAO;
import br.com.alura.agenda.model.Aluno;

public class FormularioAlunoActivity extends AppCompatActivity {

    private EditText campoNome;
    private EditText campoTelefone;
    private EditText campoEmail;
    private Aluno aluno;
    private final AlunoDAO dao = new AlunoDAO();
    private static final String TITULO_APPBAR_NOVO_ALUNO = "Novo Aluno";
    private static final String TITULO_APPBAR_Edita_ALUNO = "Edita Aluno";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_alunos);
        inicializacaoDosCampos();
        configuraBotaoSalvar();
        configuraBotaoVoltar();
        carregaAluno();
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

    private void configuraBotaoSalvar() {
        Button btnSalvar = findViewById(R.id.activity_formulario_aluno_botao_salvar);
        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finalizaFormulario();
            }
        });
    }

    private void finalizaFormulario() {
        preencheAluno();
        if (aluno.temIdValido()) {
            dao.edita(aluno);
            finish();
        } else {
           if (aluno.isEmailValid(aluno.getEmail()) == false){
               Toast.makeText(this,"Email invalido. Tente novamente", Toast.LENGTH_SHORT).show();
           }else{
               dao.salva(aluno);
               finish();
           }
        }
    }

    private void configuraBotaoVoltar() {
        ImageView setaVoltar = findViewById(R.id.iv_close_activity);
        setaVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void inicializacaoDosCampos() {
        campoNome = findViewById(R.id.activity_formulario_aluno_nome);
        campoTelefone = findViewById(R.id.activity_formulario_aluno_telefone);
        campoEmail = findViewById(R.id.activity_formulario_aluno_email);
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