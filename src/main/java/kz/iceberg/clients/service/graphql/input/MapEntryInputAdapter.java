package kz.iceberg.clients.service.graphql.input;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
public class MapEntryInputAdapter<K, V> {
    private K key;
    private V value;

    public static <K, V> Map.Entry<K, V> toEntry(K key, V value) {
        return new Map.Entry<>() {
            final K k = key;
            V val = value;

            @Override
            public K getKey() {
                return k;
            }

            @Override
            public V getValue() {
                return val;
            }

            @Override
            public V setValue(V value) {
                this.val = value;
                return this.val;
            }
        };
    }

    @Override
    public String toString() {
        return "MapEntryInputAdapter{" +
                "key=" + key +
                ", value=" + value +
                '}';
    }
}
