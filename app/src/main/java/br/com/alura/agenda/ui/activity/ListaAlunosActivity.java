package br.com.alura.agenda.ui.activity;

import static br.com.alura.agenda.ui.activity.ConstantesActivities.CHAVE_ALUNO;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.zip.Inflater;

import br.com.alura.agenda.R;
import br.com.alura.agenda.dao.AlunoDAO;
import br.com.alura.agenda.model.Aluno;

public class ListaAlunosActivity extends AppCompatActivity {
    private final AlunoDAO dao = new AlunoDAO();
    private static final String TITULO_APPBAR = "Lista de Alunos";
    private ArrayAdapter<Aluno> adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_alunos);
        setTitle(TITULO_APPBAR);
        configuraFabNovoAluno();
        configuraLista();

        dao.salva(new Aluno("luiz filipe", "2312312312", "luizkato8@gmail.com"));
        dao.salva(new Aluno("2", "2312312312", "luizkato8@gmail.com"));
        dao.salva(new Aluno("3", "2312312312", "luizkato8@gmail.com"));
        dao.salva(new Aluno("luiz 4", "2312312312", "luizkato8@gmail.com"));
        dao.salva(new Aluno("luiz 5", "2312312312", "luizkato8@gmail.com"));
        dao.salva(new Aluno("luiz 6", "2312312312", "luizkato8@gmail.com"));
        dao.salva(new Aluno("luiz 7", "2312312312", "luizkato8@gmail.com"));
        dao.salva(new Aluno("luiz 8", "2312312312", "luizkato8@gmail.com"));
        dao.salva(new Aluno("luiz 9", "2312312312", "luizkato8@gmail.com"));
        dao.salva(new Aluno("luiz 9", "2312312312", "luizkato8@gmail.com"));
        dao.salva(new Aluno("luiz e", "2312312312", "luizkato8@gmail.com"));
        dao.salva(new Aluno("luiz 1", "2312312312", "luizkato8@gmail.com"));
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater()
                .inflate(R.menu.activity_lista_alunos_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        int itemRemover = item.getItemId();
        if (itemRemover == R.id.item_remover_alunos) {
            AdapterView.AdapterContextMenuInfo menuInfo =
                    (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
            Aluno alunoEscolhido = adapter.getItem(menuInfo.position);
            removeAluno(alunoEscolhido);
        }
        return super.onContextItemSelected(item);
    }

    private void configuraFabNovoAluno() {
        FloatingActionButton floatingActionButton = findViewById(R.id.fab);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                abreFormularioModoSalvaAluno();
            }
        });
    }


    private void abreFormularioModoSalvaAluno() {
        startActivity(new Intent(ListaAlunosActivity.this, FormularioAlunoActivity.class));
    }

    @Override
    protected void onResume() {
        super.onResume();
        atualizaAluno();
    }

    private void atualizaAluno() {
        adapter.clear();
        adapter.addAll(dao.todos());
    }

    private void configuraLista() {
        ListView listaDeAlunos = findViewById(R.id.list_view);
        configuraAdapter(listaDeAlunos);
        configuraListenerDeCliquePorItem(listaDeAlunos);
        registerForContextMenu(listaDeAlunos);
    }

    private void removeAluno(Aluno aluno) {
        dao.remove(aluno);
        adapter.remove(aluno);
    }

    private void configuraListenerDeCliquePorItem(ListView listaDeAlunos) {
        listaDeAlunos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Aluno alunoEscolhido = (Aluno) adapterView.getItemAtPosition(position);
                abreFormularioModoEditaAluno(alunoEscolhido);
            }
        });
    }

    private void abreFormularioModoEditaAluno(Aluno aluno) {
        Intent abreFormularioAcitivity = new Intent(ListaAlunosActivity.this, FormularioAlunoActivity.class);
        abreFormularioAcitivity.putExtra(CHAVE_ALUNO, aluno);
        startActivity(abreFormularioAcitivity);
    }

    private void configuraAdapter(ListView listaDeAlunos) {
        adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1);
        listaDeAlunos.setAdapter(adapter);
    }


}