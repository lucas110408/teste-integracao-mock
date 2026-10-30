package aula2;

public class Aluno {

    private Integer frequencia;

    public Aluno() {
    }

    public Integer getFrequencia() {
        return frequencia;
    }

    public void setFrequencia(Integer frequencia) {
        if (frequencia == null || frequencia < 0 || frequencia > 100) {
            throw new IllegalArgumentException("Frequencia invalida");
        }
        this.frequencia = frequencia;
    }

    public String verificarSituacao(IMedia notas) {
        if (this.frequencia < 75) {
            return "Reprovado por falta";
        }
        Double media = notas.calcularMedia();
        if (media >= 6.0) {
            return "Aprovado";
        } else if (media >= 4.0) {
            return "Recuperacao";
        } else {
            return "Reprovado por nota";
        }
    }
}
