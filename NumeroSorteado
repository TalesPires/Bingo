public class NumeroSorteado {
    private int numero;

    public NumeroSorteado(int numero) {
        this.numero = numero;
    }

    public int getNumero() {
        return numero;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        NumeroSorteado that = (NumeroSorteado) obj;
        return numero == that.numero;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(numero);
    }
}
