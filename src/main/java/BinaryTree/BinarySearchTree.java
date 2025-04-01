package BinaryTree;

import java.util.Comparator;
import java.util.List;

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

    public Comparator<T> getComparator() {
        return comparator;
    }

    public T getValue() {
        return value;
    }

    public BinarySearchTree<T> getLeft() {
        return left;
    }

    public BinarySearchTree<T> getRight() {
        return right;
    }

    @Override
    public void insert(T value) {
        // TODO Review
        if (value == null){
            throw new BinarySearchTreeException("No se puede insertar un elemento null");
        }
        else if(this.value == null) { //Arbol sin elementos
            this.value = value;
        }else if (comparator.compare(value, this.value) < 0 ) { //value se deberá poner a la izq.
            if(left == null){
                BinarySearchTree<T> newTree = new BinarySearchTree(this.comparator);
                newTree.insert(value);
                left = newTree;
            }else{
                left.insert(value);
            }
        }else if (comparator.compare(value, this.value) > 0 ) { //value se coloca a la drch.
            if(left == null){
                BinarySearchTree<T> newTree = new BinarySearchTree(this.comparator);
                newTree.insert(value);
                right = newTree;
            }else{
                right.insert(value);
            }
        }
        else {
            throw new BinarySearchTreeException("El elemento ya esta en el árbol");
        }
    }

    @Override
    public boolean isLeaf() {
        // TODO Review
        if(getValue() == null){
            throw new BinarySearchTreeException("El arbol no tiene elementos");
        }else{
            return (left == null && right == null) ? true : false;
        }
    }

    @Override
    public boolean contains(T value) {
        // TODO Review
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
    public T maximum(){
        if(this.right == null){
            return this.value;
        }
        return this.right.maximum();
    }

    @Override
    public void removeBranch(T value){
        // TODO
        if(!this.contains(value)){
            throw new BinarySearchTreeException("El valor no existe");
        } 
        
    }

    @Override
    public int size() {
        int size = 0;
        if(this.value == null){
            return 0;
        }
        else{
          ++size;
          if(this.right != null){
            size  += this.right.size();     
           }
           if(this.left != null){
           // size += this.element;
           }
       }
       return 0;
        
    }

    @Override
    public int depth() {
        // TODO
        return 0;
    }

    @Override
    public void removeValue(T value) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'removeValue'");
    }

    @Override
    public List<T> inOrder() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'inOrder'");
    }

    @Override
    public void balance() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'balance'");
    }

    // Complex operations
    // (Estas operaciones se incluirán más adelante para ser realizadas en la segunda
    // sesión de laboratorio de esta práctica)
}
