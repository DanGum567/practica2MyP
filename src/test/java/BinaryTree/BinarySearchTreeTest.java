package BinaryTree;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Comparator;

public class BinarySearchTreeTest {


    @DisplayName("Anyadimos un nuevo valor a un arbol vacio sin valores comprobamos datos correctos.")
    @Test
    public void insert_nuevoValorArbolSinValores_DevuelveValor(){
        //Arrange
        Comparator<Integer> comparador = Comparator.comparingInt(a -> a);
        BinarySearchTree<Integer> bt = new BinarySearchTree<Integer>(comparador);
        int expected = 10;
        //Act
        bt.insert(expected);
        //Assert
        assertEquals(expected, bt.getValue());
    }

    @DisplayName("Anyadimos un nuevo valor menor y se anyade a la izquierda.")
    @Test
    public void insert_nuevoValorIzquierda_InsertaCorrectamente(){
        //Arrange
        Comparator<Integer> comparador = Comparator.comparingInt(a -> a);
        BinarySearchTree<Integer> bt = new BinarySearchTree<Integer>(comparador);
        int inicial = 10;
        bt.insert(inicial);
        int expected = 3;
        //Act
        bt.insert(expected);
        //Assert
        assertEquals(expected, bt.getLeft().getValue());
    }

    @DisplayName("Anyadimos un nuevo valor mayor y se anyade a la derecha.")
    @Test
    public void insert_nuevoValorDerecha_InsertaCorrectamente(){
        //Arrange
        Comparator<Integer> comparador = Comparator.comparingInt(a -> a);
        BinarySearchTree<Integer> bt = new BinarySearchTree<Integer>(comparador);
        int inicial = 10;
        bt.insert(inicial);
        int expected = 16;
        //Act
        bt.insert(expected);
        //Assert
        assertEquals(expected, bt.getRight().getValue());
    }
}
