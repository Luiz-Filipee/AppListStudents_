package br.com.alura.agenda.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import br.com.alura.agenda.R;
import br.com.alura.agenda.model.Aluno;

public class LoginAlunosActivity extends Activity {
    private Aluno aluno = new Aluno();
    private EditText fieldEmail;
    private EditText fieldPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_alunos);
        startFields();
        configureButtonSave();
    }

    public void startFields(){
        fieldEmail = findViewById(R.id.acitivity_login_campo_email);
        fieldPassword = findViewById(R.id.acitivity_login_campo_password);
    }

    public void configureButtonSave(){
        Button buttonSave = findViewById(R.id.activity_login_button);

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = fieldEmail.getText().toString();
                String password = fieldPassword.getText().toString();

                if(!email.isEmpty() || !password.isEmpty()){
                    openListActivity();
                }
            }
        });
    }

    public void openListActivity(){
        startActivity(new Intent(LoginAlunosActivity.this, ListaAlunosActivity.class));
    }
}