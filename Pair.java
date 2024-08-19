import java.util.Objects;

public class Pair<K, V, D> {
    K key;
    V value;
    D destiny;

    Pair(K key, V value, D destiny) {
        this.key = key;
        this.value = value;
        this.destiny = destiny;
    }

    K getOrigin() {
        return key;
    }
    D getDestiny() {
        return destiny;
    }
    V getValue() {
        return value;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Pair)) return false;
        Pair<?, ?, ?> pair = (Pair<?, ?, ?>) obj;
        return Objects.equals(key, pair.key) && Objects.equals(value, pair.value) && Objects.equals(destiny, pair.destiny);
    }

    @Override
    public int hashCode() {
        return Objects.hash(key, value, destiny);
    }
}