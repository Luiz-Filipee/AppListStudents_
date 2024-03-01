package br.com.alura.agenda.dao;

import junit.framework.TestCase;

import br.com.alura.agenda.model.Aluno;

public class AlunoDAOTest extends TestCase {

    public void setUp() throws Exception {
        super.setUp();
    }

    public void testSave() {
        Aluno aluno = new Aluno("luizfilipe","luizkato@gmail.com","123124123");
        AlunoDAO dao = new AlunoDAO();
        dao.salva(aluno);
        assertTrue(dao.todos().contains(aluno));
    }

    public void testGetAllAlunos() {
        Aluno aluno = new Aluno("luizfilipe","luizkato@gmail.com","123124123");
        AlunoDAO da = new AlunoDAO();
        da.salva(aluno);
        assertFalse(da.todos().isEmpty());
    }
}