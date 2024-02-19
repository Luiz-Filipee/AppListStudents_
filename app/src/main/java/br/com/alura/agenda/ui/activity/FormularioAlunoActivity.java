package br.com.alura.agenda.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_alunos);
        inicializacaoDosCampos();
        configuraBotaoSalvar();
        configuraBotaoVoltar();

        Intent dados = getIntent(); // Pegando o intent da outra classe
        if(dados.hasExtra("aluno")){ // verificando se o extra "aluno" existe
            aluno = (Aluno) dados.getSerializableExtra("aluno");
            campoNome.setText(aluno.getNome());
            campoTelefone.setText(aluno.getTelefone());
            campoEmail.setText(aluno.getEmail());
        }else{
           aluno = new Aluno();
        }
    }

    private void configuraBotaoSalvar() {
       Button btnSalvar = findViewById(R.id.activity_formulario_aluno_botao_salvar);
       btnSalvar.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               preencheAluno();
               if(aluno.temIdValido()){
                   dao.edita(aluno);
               }else{
                   dao.salva(aluno);
               }
               finish();
           }
       });
    }

    private void configuraBotaoVoltar(){
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