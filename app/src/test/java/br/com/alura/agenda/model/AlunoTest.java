package br.com.alura.agenda.model;

import junit.framework.TestCase;

import org.junit.Test;

public class AlunoTest extends TestCase {

    final Aluno aluno = new Aluno();
    @Test
    public void testIsEmailValid() {
        aluno.setEmail("luizkato@gmail.com");
        String email = aluno.getEmail();
        assertTrue(aluno.isEmailValid(email));
    }
}