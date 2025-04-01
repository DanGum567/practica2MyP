package BinaryTree;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Comparator;

public class BinarySearchTreeTest {


    //Funcion Insert
        //Añadimos elemento null a arbol devuelve excepcion.
        //Añadimos elemento a arbol vacio.
        //Añadimos elemento de menor valor se incluye a la izquierda del arbol.
        //Añadimos elemento de mayor valor se incluye a la derecha del arbol.
        //Añadimos elemento que ya existe en el arbol devuelve excepcion.

    @DisplayName("Anyadimos un elemento null a un arbol vacio devuelve excepción.")
    @Test
    public void insert_nullArbolVacio_DevuelveException() {
        //Arrange
        Comparator<Integer> comparator = Comparator.comparingInt(a -> a);
        BinarySearchTree<Integer> bt = new BinarySearchTree<Integer>(comparator);
        Integer value = null;
        //Act & Assert
        assertThrows(BinarySearchTreeException.class, () -> bt.insert(value));
    }

    @DisplayName("Anyadimos un elemento a un arbol vacio y comprobamos que se inserta en la cabeza.")
    @Test
    public void insert_elementoEnArbolVacio_ValorInsertado(){
        //Arrange
        Comparator<Integer> comparator = Comparator.comparingInt(a -> a);
        BinarySearchTree<Integer> bt = new BinarySearchTree<Integer>(comparator);
        int expected = 10;
        //Act
        bt.insert(expected);
        //Assert
        assertEquals(expected, bt.getValue());
    }

    @DisplayName("Anyadimos un elemento menor en un arbol, este debe insertarse a la izquierda.")
    @Test
    public void insert_elementoMenorEnArbol_InsertaALaIzquierda(){
        //Arrange
        Comparator<Integer> comparator = Comparator.comparingInt(a -> a);
        BinarySearchTree<Integer> bt = new BinarySearchTree<Integer>(comparator);
        int initial = 10;
        bt.insert(initial);
        int expected = 3;
        //Act
        bt.insert(expected);
        //Assert
        assertEquals(expected, bt.getLeft().getValue());
    }

    @DisplayName("Anyadimos un elemento mayor en un arbol, este debe insertarse a la derecha.")
    @Test
    public void insert_elementoMayorEnArbol_InsertaALaDerecha(){
        //Arrange
        Comparator<Integer> comparator = Comparator.comparingInt(a -> a);
        BinarySearchTree<Integer> bt = new BinarySearchTree<Integer>(comparator);
        int initial = 10;
        bt.insert(initial);
        int expected = 16;
        //Act
        bt.insert(expected);
        //Assert
        assertEquals(expected, bt.getRight().getValue());
    }

    @DisplayName("Anyadimos un elemento que ya existe en el arbol, devuelve excepción.")
    @Test
    public void insert_elementoExistenteEnArbol_DevuelveException() {
        //Arrange
        Comparator<Integer> comparator = Comparator.comparingInt(a -> a);
        BinarySearchTree<Integer> bt = new BinarySearchTree<Integer>(comparator);
        int initial = 10;
        bt.insert(initial);
        int newElem = 10;
        //Act & Assert
        assertThrows(BinarySearchTreeException.class, () -> bt.insert(newElem));
    }

    //Funcion isLeaf
        //Por compilacion si el arbol es null no puede llamar a funciones.

        //En un arbol vacio devuelve excepcion
        //Si es una hoja devuelve que true.
        //Si no es una hoja devuelve false.

    @DisplayName("Comprobamos si es hoja en un arbol vacio, devuelve exepción")
    @Test
    public void isLeaf_arbolVacio_DevuelveException() {
        //Arange
        Comparator<Integer> comparator = Comparator.comparingInt(a -> a);
        BinarySearchTree<Integer> bt = new BinarySearchTree<Integer>(comparator);
        //Act & Assert
        assertThrows(BinarySearchTreeException.class, () -> bt.isLeaf());
    }

    @DisplayName("Comprobamos que cuando es hoja devuelve true.")
    @Test
    public void isLeaf_arbolEsHoja_DevuelveTrue() {
        //Arange
        Comparator<Integer> comparator = Comparator.comparingInt(a -> a);
        BinarySearchTree<Integer> bt = new BinarySearchTree<Integer>(comparator);
        int elem = 10;
        bt.insert(elem);
        //Act
        boolean actual = bt.isLeaf();
        //Assert
        assertEquals(true, actual);
    }

    @DisplayName("Comprobamos que cuando no es hoja devuelve false.")
    @Test
    public void isLeaf_arbolNoEsHoja_DevuelveFalse() {
        //Arange
        Comparator<Integer> comparator = Comparator.comparingInt(a -> a);
        BinarySearchTree<Integer> bt = new BinarySearchTree<Integer>(comparator);
        int elem = 10;
        int elem2 = 5;
        bt.insert(elem);
        bt.insert(elem2);
        //Act
        boolean actual = bt.isLeaf();
        //Assert
        assertEquals(false, actual);
    }

    //Funcion contains
        //Por compilacion detecta arboles null llamandose a funciones internas y

        //El arbol es vacio devuelve excepción.
        //El elemento no esta en el arbol, devuelve false.
        //El elemento esta en el arbol, devuelve true.

    @DisplayName("Cuando el arbol es vacio devuelve exepción.")
    @Test
    public void contains_arbolVacio_DevuelveException() {
        //Arange
        Comparator<Integer> comparator = Comparator.comparingInt(a -> a);
        BinarySearchTree<Integer> bt = new BinarySearchTree<Integer>(comparator);

        //Act & Assert
        int elem = 5;
        assertThrows(BinarySearchTreeException.class, () -> bt.contains(elem));
    }

    @DisplayName("Cuando el elemento esta en el arbol devuelve true.")
    @Test
    public void contains_elementoEstaEnArbol_DevuelveTrue() {
        //Arange
        Comparator<Integer> comparator = Comparator.comparingInt(a -> a);
        BinarySearchTree<Integer> bt = new BinarySearchTree<Integer>(comparator);
        int elem = 10;
        bt.insert(elem);
        int elem2 = 10;
        //Act
        boolean actual = bt.contains(elem2);
        //Assert
        assertEquals(true, actual);
    }

    @DisplayName("Cuando el elemento no esta en el arbol devuelve false.")
    @Test
    public void contains_elementoNoEstaEnArbol_DevuelveFalse() {
        //Arange
        Comparator<Integer> comparator = Comparator.comparingInt(a -> a);
        BinarySearchTree<Integer> bt = new BinarySearchTree<Integer>(comparator);
        int elem = 10;
        bt.insert(elem);
        int elem2 = 5;
        //Act
        boolean actual = bt.contains(elem2);
        //Assert
        assertEquals(false, actual);
    }
}