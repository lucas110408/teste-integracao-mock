package aula2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.easymock.EasyMock.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class AlunoTest {

    Aluno aluno;
    Notas notas;

    @BeforeEach
    public void setUp() {
        aluno = new Aluno();
        notas = new Notas();
    }

    // validações

    @Test
    void deveTratarFrequenciaNegativa() {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> aluno.setFrequencia(-1));
        assertEquals("Frequencia invalida", e.getMessage());
    }

    @Test
    void deveTratarFrequenciaMaiorQueCem() {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> aluno.setFrequencia(101));
        assertEquals("Frequencia invalida", e.getMessage());
    }

    @Test
    void deveTestarFrequenciaValida() {
        aluno.setFrequencia(80);
        assertEquals(80, aluno.getFrequencia());
    }

    @Test
    void deveTratarNotaNula() {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> notas.setNota1(null));
        assertEquals("Nota não pode ser nula", e.getMessage());
    }

    @Test
    void deveTratarNotaNegativa() {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> notas.setNota2(-0.1));
        assertEquals("Nota invalida", e.getMessage());
    }

    @Test
    void deveTratarNotaMaiorQueDez() {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> notas.setNota3(10.1));
        assertEquals("Nota invalida", e.getMessage());
    }

    @Test
    void deveCalcularMedia() {
        notas.setNota1(5.0);
        notas.setNota2(7.0);
        notas.setNota3(9.0);
        assertEquals(7.0, notas.calcularMedia());
    }

    // testes com a classe Notas

    @Test
    void aprovado() {
        aluno.setFrequencia(75);
        notas.setNota1(6.0);
        notas.setNota2(6.0);
        notas.setNota3(6.0);
        assertEquals("Aprovado", aluno.verificarSituacao(notas));
    }

    @Test
    void recuperacao() {
        aluno.setFrequencia(90);
        notas.setNota1(4.0);
        notas.setNota2(5.0);
        notas.setNota3(3.0);
        assertEquals("Recuperacao", aluno.verificarSituacao(notas));
    }

    @Test
    void reprovadoPorNota() {
        aluno.setFrequencia(100);
        notas.setNota1(3.0);
        notas.setNota2(4.0);
        notas.setNota3(4.0);
        assertEquals("Reprovado por nota", aluno.verificarSituacao(notas));
    }

    @Test
    void reprovadoPorFalta() {
        aluno.setFrequencia(74);
        notas.setNota1(10.0);
        notas.setNota2(10.0);
        notas.setNota3(10.0);
        assertEquals("Reprovado por falta", aluno.verificarSituacao(notas));
    }

    // testes com mock

    @Test
    void mockAprovado() {
        IMedia notasMock = createMock(IMedia.class);
        expect(notasMock.calcularMedia()).andReturn(6.0);
        replay(notasMock);
        aluno.setFrequencia(75);
        assertEquals("Aprovado", aluno.verificarSituacao(notasMock));
        verify(notasMock);
    }

    @Test
    void mockRecuperacao() {
        IMedia notasMock = createMock(IMedia.class);
        expect(notasMock.calcularMedia()).andReturn(5.9);
        replay(notasMock);
        aluno.setFrequencia(80);
        assertEquals("Recuperacao", aluno.verificarSituacao(notasMock));
        verify(notasMock);
    }

    @Test
    void mockRecuperacaoLimite() {
        IMedia notasMock = createMock(IMedia.class);
        expect(notasMock.calcularMedia()).andReturn(4.0);
        replay(notasMock);
        aluno.setFrequencia(80);
        assertEquals("Recuperacao", aluno.verificarSituacao(notasMock));
        verify(notasMock);
    }

    @Test
    void mockReprovadoPorNota() {
        IMedia notasMock = createMock(IMedia.class);
        expect(notasMock.calcularMedia()).andReturn(3.9);
        replay(notasMock);
        aluno.setFrequencia(100);
        assertEquals("Reprovado por nota", aluno.verificarSituacao(notasMock));
        verify(notasMock);
    }

    @Test
    void mockReprovadoPorFalta() {
        // sem expect, se chamar calcularMedia() da erro
        IMedia notasMock = createMock(IMedia.class);
        replay(notasMock);
        aluno.setFrequencia(74);
        assertEquals("Reprovado por falta", aluno.verificarSituacao(notasMock));
        verify(notasMock);
    }
}
