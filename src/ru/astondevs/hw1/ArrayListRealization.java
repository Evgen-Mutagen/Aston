/**
 * Реализация ArrayList
 */

package ru.astondevs.hw1;


public class ArrayListRealization<E> {
    ArrayListRealization<E> arrayList;
    private int capacity = 20;
    private int size;

    private Object[] array = new Object[capacity];

    /**
     * Добавление элемента
     *
     * @param element новый элемент
     */
    public void add(E element) {
        if (size == array.length - 1) {
            increase();
        }
        array[size++] = element;
    }

    /**
     * Получение элемента
     *
     * @param index индекс элемента
     */
    public E get(int index) {
        return (E) array[index];
    }

    /**
     * Удаление элемента
     *
     * @param element элемент
     */
    public void delete(E element) {
        for (int i = 0; i < array.length; i++) {
            Object ob = array[i];
            if (ob.equals(element)) {
                int shiftedIndex = size - i - 1;
                if (shiftedIndex > 0) {
                    System.arraycopy(array, i + 1, array, i, shiftedIndex);
                    size--;
                    array[size] = null;
                }
            }
        }
    }

    /**
     * Очистка листа
     */
    public void deleteAll() {
        for (int i = 0; i < size; i++) {
            array[i] = null;
        }
        size = 0;
    }

    /**
     * Увеличение ёмкости
     */
    private void increase() {
        capacity = capacity * 2;
        Object[] newArray = new Object[capacity];
        System.arraycopy(array, 0, newArray, 0, capacity);
        array = newArray;
    }
}
