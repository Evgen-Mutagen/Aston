/**
 * Реализация HashMap
 */

package ru.astondevs.hw1;

import java.util.*;

public class HashMapRealization<K, V> {
    /**
     * Реализация Entity
     *
     * @param <K> ключ
     * @param <V> значение
     */
    private static class MapEntity<K, V> {
        private final K key;
        private V value;

        public MapEntity(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

        public V setValue(V newValue) {
            V oldValue = value;
            value = newValue;
            return oldValue;
        }
    }

    private ArrayList<LinkedList<MapEntity<K, V>>> table;
    private int size;

    public HashMapRealization() {
        table = new ArrayList<>();

        for (int i = 0; i < 20; i++) {
            table.add(new LinkedList<>());
        }

        size = 0;
    }

    /**
     * Получение списка
     *
     * @param key ключ
     * @return полчуение по индексу из коризны
     */
    private LinkedList<MapEntity<K, V>> getList(K key) {
        int index = Math.abs(key.hashCode() % table.size());
        return table.get(index);
    }

    /**
     * Добавление элемента
     *
     * @return
     */
    public V add(K key, V value) {
        LinkedList<MapEntity<K, V>> list = getList(key);
        V oldValue = null;

        if (containsKey(key)) {
            for (MapEntity<K, V> entity : list) {
                if (entity.getKey().equals(key)) {
                    oldValue = entity.getValue();
                    entity.setValue(value);
                }
            }
        } else {
            list.add(new MapEntity<>(key, value));
            size++;
        }

        return oldValue;
    }

    /**
     * Получение значения
     */
    public V get(Object key) {
        LinkedList<MapEntity<K, V>> list = getList((K) key);
        for (MapEntity<K, V> entity : list) {
            if (entity.getKey().equals(key))
                return entity.getValue();
        }

        return null;
    }

    /**
     * Удаление объекта
     *
     * @param obj объект
     */
    public V delete(Object obj) {
        K key = (K) obj;
        V deletedValue = null;

        if (containsKey(key)) {
            LinkedList<MapEntity<K, V>> list = getList(key);
            V value = get(key);

            int index = list.indexOf(new MapEntity<>(key, value));

            deletedValue = list.get(index).getValue();
            list.remove(index);

            size--;
        }
        return deletedValue;
    }

    /**
     * Очистка
     */
    public void deleteAll() {
        for (LinkedList<MapEntity<K, V>> list : table)
            list.clear();

        size = 0;
    }

    /**
     * Существование искомого ключа
     */
    public boolean containsKey(Object key) {
        LinkedList<MapEntity<K, V>> list = getList((K) key);
        for (MapEntity<K, V> entity : list)
            if (entity.getKey().equals(key)) return true;

        return false;
    }

    /**
     * Существование искомого значения
     */
    public boolean containsValue(Object value) {
        for (LinkedList<MapEntity<K, V>> list : table)
            for (MapEntity<K, V> entity : list)
                if (entity.getValue().equals(value)) return true;

        return false;
    }

    /**
     * Возвращение ключей
     *
     * @return
     */
    public Set<K> keySet() {
        Set<K> set = new HashSet<>();
        for (LinkedList<MapEntity<K, V>> list : table) {
            for (MapEntity entity : list) {
                set.add((K) entity.getKey());
            }
        }
        return set;
    }

    /**
     * Сортировка по ключам
     *
     * @return отсортированная мапа
     */
    public static <K extends Comparable, V extends Comparable> HashMapRealization<K, V> sortByKeys(HashMapRealization<K, V> map) {
        List<K> keys = new LinkedList<K>(map.keySet());
        Collections.sort(keys);
        HashMapRealization<K, V> sortedMap;
        sortedMap = new HashMapRealization<K, V>();
        for (K key : keys) {
            sortedMap.add(key, map.get(key));
        }
        return sortedMap;
    }
}
