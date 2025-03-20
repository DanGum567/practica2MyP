package BinaryTree;

import java.util.Comparator;

public class BinarySearchTree<T> implements BinarySearchTreeStructure<T> {
    private Comparator<T> comparator;
    private T value;
    private BinarySearchTree<T> left;
    private BinarySearchTree<T> right;

    public String render(){
        String render = "";

        if (value != null) {
            render += value.toString();
        }

        if (left != null || right != null) {
            render += "(";
            if (left != null) {
                render += left.render();
            }
            render += ",";
            if (right != null) {
                render += right.render();
            }
            render += ")";
        }

        return render;
    }

    public BinarySearchTree(Comparator<T> comparator) {
        // TODO
        this.comparator = comparator;
        this.left = null;
        this.right = null;
        this.value = null;
    }

    @Override
    public void insert(T value) {
        // TODO
        if(this.value == null) { //Arbol sin elementos
            this.value = value;
        }else if (comparator.compare(value, this.value) < 0 ) { //value se deberá poner a la izq.
            left.insert(value);
        }else if (comparator.compare(value, this.value) > 0 ) { //value se coloca a la drch.
            right.insert(value);
        }
        else {
            throw new BinarySearchTreeException("El elemento ya esta en el árbol");
        }
    }

    @Override
    public boolean isLeaf() {
        // TODO
        return (left == null && right == null) ? true : false;
    }

    @Override
    public boolean contains(T value) {
        // TODO
        if (this.value == value) {
            return true;
        }else if (!isLeaf()){
            if(comparator.compare(value, this.value) < 0) {
                return left.contains(value);
            }else{
                return right.contains(value);
            }
        }else{
            return false;
        }
    }

    @Override
    public T minimum() {
        // TODO
        if (left == null) {
            return this.value;
        }else{
            return left.minimum();
        }
    }

    @Override
    public T maximum() {
        // TODO
        return null;
    }

    @Override
    public void removeBranch(T value){
        // TODO
    }

    @Override
    public int size() {
        //TODO
        return 0;
    }

    @Override
    public int depth() {
        // TODO
        return 0;
    }

    // Complex operations
    // (Estas operaciones se incluirán más adelante para ser realizadas en la segunda
    // sesión de laboratorio de esta práctica)
}
