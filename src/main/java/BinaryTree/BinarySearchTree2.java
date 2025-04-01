package BinaryTree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Stack;
import java.util.Collections;

public class BinarySearchTree2<T> implements BinarySearchTreeStructure<T> {
    private Comparator<T> comparator;
    private T value;
    private BinarySearchTree2<T> left;
    private BinarySearchTree2<T> right;

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
    public BinarySearchTree2(Comparator<T> comparator) {
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
        }else if (comparator.compare(value, this.value) < 0 ) { //value se deberá insertar a la izq.
            if(this.left == null){
                left = new BinarySearchTree2<>(comparator);
            }
            left.insert(value);
        }else if (comparator.compare(value, this.value) > 0 ) { //value se coloca a la drch.
            if(this.right == null){
                right = new BinarySearchTree2<>(comparator);
            }
            right.insert(value);
        }
    }
    @Override
    public boolean isLeaf() {
        // TODO
        if(this.value == null)
            throw new BinarySearchTreeException("El arbol esta vacio");
        return (left == null && right == null) ? true : false;
    }
    @Override
    public boolean contains(T value) {
        // TODO
        if (this.value == value) {
            return true;
        }else if (!isLeaf()){
            if(comparator.compare(value, this.value) < 0 && this.left != null) {
                return left.contains(value);
            }else if(comparator.compare(value, this.value) > 0 && this.right != null){
                return right.contains(value);
            }
        }
       return false;
    }
    @Override
    public T minimum() {
        // TODO
        if(this.value == null){
            throw new BinarySearchTreeException("El árbol está vacio");
        }
        else if (left == null) {
            return this.value;
        }else{
            return left.minimum();
        }
    }
    @Override
    public T maximum(){
        if(this.value == null){
            throw new BinarySearchTreeException("El árbol está vacio");
        }
        else if(this.right == null){
            return this.value;
        }
        return this.right.maximum();
    }
    @Override
    public void removeBranch(T value){
        // TODO
        if(this.value == null){
            throw new BinarySearchTreeException("El arbol está vacio");
        }
        else if(comparator.compare(this.value, value) == 0){
            this.value = null;
            this.right = null;
            this.left = null;
        }else if (comparator.compare(this.value, value) < 0 && this.right != null){
            this.right.removeBranch(value);
        }else if(comparator.compare(this.value, value) > 0 && this.left != null){
            this.left.removeBranch(value);
        }else{
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
           size += this.left.size();
           }
       }
       return size;        
    }

    @Override
    public int depth(){
        int depthRight = 1;
        int depthLeft = 1;
        if(this.value == null){
            return 0;
        } else if(isLeaf()){
            return 1;
        }else{            
            if(this.right != null){
                depthRight += this.right.depth();
            }
            if(this.left != null){
                depthLeft += this.left.depth();
            }
        }
        return depthRight >= depthLeft ? depthRight : depthLeft;
    }

    // Complex operations
    // (Estas operaciones se incluirán más adelante para ser realizadas en la segunda
    // sesión de laboratorio de esta práctica)
    /*
     *    void removeValue(T value);
     *    List<T> inOrder();
     *    void balance();
     */

    @Override
    public void removeValue(T val){
        if(this.value == null){
            throw new BinarySearchTreeException("El valor no existe");
        } 
        else if(comparator.compare(this.value, val) == 0){
            T temp = null;
            if(this.left != null){
                temp = this.left.maximum(); 
                this.removeValue(temp);
                this.value = temp;
            } else if(this.right != null){
                temp = this.right.minimum();
                this.removeValue(temp);
                this.value = temp;
            }else{
                this.value = null;
            }
        }else if (comparator.compare(this.value, val) < 0 && this.right != null){
            this.right.removeValue(val);
        }else if(comparator.compare(this.value, val) > 0 && this.left != null){
            this.left.removeValue(val);
        }else{
            throw new BinarySearchTreeException("El elemento no está en el arbol");
        }
    }

    public List<T> inOrder(){
        if(this.value == null){
            return new ArrayList<>();
        } 
        Stack<T> inorder = new Stack<>();
        if(this.left != null){
           inorder.addAll(this.left.inOrder());
        }
        inorder.add(this.value);
        if(this.right != null)
        {
            inorder.addAll(this.right.inOrder());
        }
        return new ArrayList<T>(inorder);
    }


    @Override
    public void balance() {
        List<T> sortedElements = inOrder();
        // Si el tien más de un elemento puede ser balanceado
        BinarySearchTree2<T> balanced_bst = buildBalancedTree(sortedElements, 0, sortedElements.size() - 1);
        if(balanced_bst != null){
            this.value = balanced_bst.value;
            this.left = balanced_bst.left;
            this.right = balanced_bst.right;
        }
    }

    private BinarySearchTree2<T> buildBalancedTree(List<T> sorted, int start, int end){
        if(start > end) return null;
        int mid = (start + end)/2;
        BinarySearchTree2<T> newNode = new BinarySearchTree2<>(this.comparator);
        newNode.value = sorted.get(mid);
        newNode.left = buildBalancedTree(sorted, start, mid-1);
        newNode.right = buildBalancedTree(sorted, mid + 1, end);
        return newNode;
    }

}