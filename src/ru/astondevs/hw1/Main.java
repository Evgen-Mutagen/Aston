/**
 * @Autor Evgenii
 * @version 1.0
 * @see ru.astondevs.hw1.ArrayListRealization
 * @see ru.astondevs.hw1.HashMapRealization
 */
package ru.astondevs.hw1;

public class Main {
    public static void main(String[] args) {
        ArrayListRealization<String> arrayList = new ArrayListRealization<>();
        HashMapRealization<Integer, String> hashMap = new HashMapRealization<>();

        arrayList.add("cat");
        arrayList.add("dog");
        arrayList.get(2);

        hashMap.add(1, "pikachu");
        hashMap.add(2, "cheburashka");
    }
}