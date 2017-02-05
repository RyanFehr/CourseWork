package lab;

class Array<E> {
    Object[] data;
    int num_elements;

    Array() {
        data = new Object[10];
        num_elements = 0;
    }
    public void add(E d) {
        if (num_elements >= data.length) {
            resize(data.length * 2);
        }
        data[num_elements] = d;
        ++num_elements;
    }

    void resize(int s) {
        Object[] new_data = new Object[s];
        for (int i = 0; i != num_elements; ++i) {
            new_data[i] = data[i];
        }
        data = new_data;
    }

    public ArrayIter<E> begin() { return new ArrayIter<E>(data, 0); }
    public ArrayIter<E> end() { return new ArrayIter<E>(data, num_elements); }
} // Array
