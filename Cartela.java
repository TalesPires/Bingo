import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.Collections;

public class Cartela {
    private String jogador;
    private List<Integer> numeros;

    public Cartela(String jogador) {
        this.jogador = jogador;
        this.numeros = gerarNumerosAleatorios();
    }

    public String getJogador() {
        return jogador;
    }

    public void setJogador(String n){
        jogador = n;
    }
    public List<Integer> getNumeros() {
        return numeros;
    }

    public void setNumeros (List<Integer> n){
        numeros = n;
    }

    public boolean contemNumero(int numero) {
        return numeros.contains(numero);
    }

    private List<Integer> gerarNumerosAleatorios() {
        Set<Integer> numerosSet = new HashSet<>();
        Random random = new Random();

        while (numerosSet.size() < 24) {
            int numero = random.nextInt(99) + 1;
            numerosSet.add(numero);
        }

        return new ArrayList<>(numerosSet);
    }

    @Override
    public String toString() {
        Collections.sort(numeros);
        return jogador + ": " + numeros.toString();
    }
}
