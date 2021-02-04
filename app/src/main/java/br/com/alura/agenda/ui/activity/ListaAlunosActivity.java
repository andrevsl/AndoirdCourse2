package br.com.alura.agenda.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.Toast;
import androidx.annotation.Nullable;
import br.com.alura.agenda.model.Aluno;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import br.com.alura.agenda.R;
import br.com.alura.agenda.dao.AlunoDAO;

import java.util.List;

import static br.com.alura.agenda.ui.activity.ConstantesActivities.CHAVE_ALUNO;

public class ListaAlunosActivity extends AppCompatActivity {

    public static final String TITULO_APPBAR = "Lista de alunos";

    private final AlunoDAO dao = new AlunoDAO();
    private ArrayAdapter<Aluno> adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_alunos);
        configuraFabNovoAluno();
        configuraLista();
        for (int i = 0; i <10 ; i++) {
            dao.salva(new Aluno("Aluno1","1223131","aluano1@aluno.com"));
            dao.salva(new Aluno("Aluno2","1223131","aluano2@aluno.com"));
        }


    }

    private void configuraFabNovoAluno() {
        FloatingActionButton botaoNovoAluno = findViewById(R.id.activity_lista_alunos_fab_novo_aluno);
        botaoNovoAluno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                abreFormularioModoInsereAluno();
            }
        });
    }

    private void abreFormularioModoInsereAluno() {

        startActivity(new Intent(this, FormularioAlunoActivity.class));
    }

    @Override
    protected void onResume() {
        super.onResume();
        atualizaAlunos();
    }

    private void atualizaAlunos() {
        adapter.clear();
        adapter.addAll(dao.todos());
    }

    private void configuraLista() {
        ListView listaDeAlunos = findViewById(R.id.activity_lista_alunos_listview);
        final List<Aluno> alunos = dao.todos();
        configuraAdapter(listaDeAlunos, alunos);
        configuraListernerDeCliquePorItem(listaDeAlunos);
        registerForContextMenu(listaDeAlunos);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.activity_lista_alunos_menu, menu);


    }
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        if(itemId == R.id.activity_lista_alunos_menu_remover){
            AdapterView.AdapterContextMenuInfo menuInfo =
                    (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
            Aluno alunoEscolhido = adapter.getItem(menuInfo.position);
            removeAluno(alunoEscolhido);
        }

        return super.onContextItemSelected(item);
    }
    private void configureListenerDeCliqueLongoPorItem(ListView listaDeAlunos) {
        listaDeAlunos.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int posicao, long id) {
                Aluno alunoEscolhido = (Aluno) adapterView.getItemAtPosition(posicao);
                removeAluno(alunoEscolhido);
                return true;
            }
        });
    }

    private void removeAluno(final Aluno aluno) {
        dao.remove(aluno);
        adapter.remove(aluno);
    }

    private void configuraListernerDeCliquePorItem(ListView listaDeAlunos) {
        listaDeAlunos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int posicao, long id) {
                Aluno alunoEscolhido = (Aluno)adapterView.getItemAtPosition(posicao);
                abreFormularioModoEditaAluno(alunoEscolhido);
            }
        });
    }

    private void abreFormularioModoEditaAluno(Aluno aluno) {
        Intent vaiParaFormularioActivity = new Intent(ListaAlunosActivity.this, FormularioAlunoActivity.class);
        vaiParaFormularioActivity.putExtra(CHAVE_ALUNO, aluno);
        startActivity(vaiParaFormularioActivity);
    }

    private void configuraAdapter(ListView listaDeAlunos, List<Aluno> alunos) {
        adapter=new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                alunos) ;
        listaDeAlunos.setAdapter(adapter);
    }

}
