package aula2;

public class Notas implements IMedia {

    private Double nota1;
    private Double nota2;
    private Double nota3;

    public Double getNota1() {
        return nota1;
    }

    public void setNota1(Double nota1) {
        this.nota1 = validarNota(nota1);
    }

    public Double getNota2() {
        return nota2;
    }

    public void setNota2(Double nota2) {
        this.nota2 = validarNota(nota2);
    }

    public Double getNota3() {
        return nota3;
    }

    public void setNota3(Double nota3) {
        this.nota3 = validarNota(nota3);
    }

    private Double validarNota(Double nota) {
        if (nota == null) {
            throw new IllegalArgumentException("Nota não pode ser nula");
        }
        if (nota < 0.0 || nota > 10.0) {
            throw new IllegalArgumentException("Nota invalida");
        }
        return nota;
    }

    public Double calcularMedia() {
        return (this.nota1 + this.nota2 + this.nota3) / 3;
    }
}
