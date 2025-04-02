package BinaryTree;

//Daniil Gumeniuk, Ángel Bayón Pazos 3º Software A

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Comparator;

@DisplayName("BST")
public class BinarySearchTreeTest {
    private BinarySearchTree<Integer> bst;
    private Comparator<Integer> comparator;

    // ---------------------------------------
    // Tests para un árbol vacio
    // ---------------------------------------
    @BeforeEach
    public void setUp() {
        comparator = Comparator.naturalOrder();
        bst = new BinarySearchTree<>(comparator);
    }
        @Test
        @DisplayName("Anyadimos un nuevo valor null, devuelve excepcion.")
        public void insert_elementoNullEnArbolVacio_LanzaExcepcion() {
            Integer value = null;
            //Act & Assert
            assertThrows(BinarySearchTreeException.class, ()->{
                bst.insert(value);
            });
        }

        @Test
        @DisplayName("Anyadimos un nuevo valor a un arbol vacio, comprobamos datos correctos.")
        public void insert_elementoEnArbolVacio_InsertaNuevoElemento() {
            int expected = 10;
            //Act
            bst.insert(expected);
            //Assert
            assertTrue(bst.contains(expected));
        }

        @Test
        @DisplayName("Un arbol vacio no es hoja")
        public void isLeaf_arbolVacio_LanzaExcepcion() {
            //Act & Assert
            assertThrows(BinarySearchTreeException.class, ()->{
                bst.isLeaf();
            });
        }

        @Test
        @DisplayName("Un arbol vacio no contiene ningun valor")
        public void contains_arbolVacio_NoContieneValor() {
            // Arrange
            int val1 = 12;
            // Act & Assert
            assertThrows(BinarySearchTreeException.class, () ->{bst.contains(val1);});
        }

        @Test
        @DisplayName("Un arbol vacio no tiene elemento minimo")
        public void minimum_arbolVacio_LanzaExcepcion() {
            // Act & Assert
            assertThrows(BinarySearchTreeException.class, ()->{
                bst.minimum();
            });
        }

        @Test
        @DisplayName("Un arbol vacio no tiene elemento maximo")
        public void maximum_arbolVacio_LanzaExcepcion() {
            // Act & Assert
            assertThrows(BinarySearchTreeException.class, ()->{
                bst.maximum();
            });
        }

        @Test
        @DisplayName("Un arbol vacio no tiene ramas por lo que no los podemos eliminar")
        public void removeBranch_arbolVacio_LanzaExcepcion() {
            // Act & Assert
            assertThrows(BinarySearchTreeException.class, ()->{
                bst.removeBranch(10);
            });
        }

        @Test
        @DisplayName("Un arbol vacio no tiene elementos, luego su tamanyo es 0")
        public void size_arbolVacio_Cero() {
            // Arrange
            int result;
            // Act
            result = bst.size();
            // Assert
            assertEquals(0, result);
        }

        @Test
        @DisplayName("La profundidad de un arbol vacio es 0")
        public void depth_arbolVacio_Cero() {
            // Arrange
            int result;
            // Act
            result = bst.depth();
            // Assert
            assertEquals(0, result);
        }

        @Test
        @DisplayName("No podemos eliminar elementos de un árbol vacío")
        public void removeValue_arbolVacio_DevuelveExcepcion() {
            // Act & Assert
            assertThrows(BinarySearchTreeException.class, () -> {
                bst.removeValue(10);
            });
        }

        @Test
        @DisplayName("No podemos recorrer en orden un árbol vacío")
        public void inOrder_arbolVacio_ListaVacia() {
            // Arrange
            List<Integer> res = new ArrayList<>();
            // Act
            res = bst.inOrder();
            assertEquals(0, res.size());
        }

        @Test
        @DisplayName("El arbol vacio no puede ser balanceado")
        public void balance_arbolVacio_NoSeBalancea() {
            // Act 
            bst.balance();
            //Assert
            assertEquals("", bst.render());
        }

        // ---------------------------------------
        // Tests para árbol con un elemento
        // ---------------------------------------
        
        @Nested
        @DisplayName("Despues de insertar un elemento")
        class DespuesDeInsertarUnElemento{
            @BeforeEach
            public void insertarUnElemento(){
                bst.insert(10);
            }

            @Test
            @DisplayName("Arbol no vacio")
            public void arbolNoVacio(){
                assertTrue(bst.contains(10));
            }

            @Test
            @DisplayName("Insertamos un elemento mayor que la raiz en el arbol hoja")
            public void insert_elementoMayorQueRaiz_InsercionDerecha(){
                // Arrange
                int val = 15;
                // Act
                bst.insert(val);
                // Assert
                assertEquals("10(,15)", bst.render());
            }

            @Test
            @DisplayName("Insertamos un elemento menor que la raiz en el arbol hoja")
            public void insert_elementoMenorQueRaiz_InsercionIzquierda(){
                // Arrange
                int val = 9;
                // Act
                bst.insert(val);
                // Assert
                assertEquals("10(9,)", bst.render());
            }

            @Test
            @DisplayName("Un arbol con un elemento es hoja")
            public void isLeaf_arbolUnitario_DevuelveVerdadero() {
                //Act & Assert
                assertTrue(bst.isLeaf());
            }

            @Test
            @DisplayName("Un arbol que contiene solamente el elemento que buscamos")
            public void contains_arbolUnitarioConElemento_DevuelveVerdadero() {
                //Arrange
                int val = 10;
                //Act & Assert
                assertTrue(bst.contains(val));
            }

            @Test
            @DisplayName("Un arbol que contiene un solo elemento y no contine el elemnto que buscamos y que debería estar a a la izquierda")
            public void contains_arbolUnitarioSinElementoBuscadoIzquierda_DevuelveFalse() {
                //Arrange
                int val = 9;
                //Act & Assert
                assertFalse(bst.contains(val));
            }
            
            @Test
            @DisplayName("Un arbol que contiene un solo elemento y no contine el elemnto que buscamos y que debería estar a a la izquierda")
            public void contains_arbolUnitarioSinElementoBuscadoDerecha_DevuelveFalse() {
                //Arrange
                int val = 16;
                //Act & Assert
                assertFalse(bst.contains(val));
            }

            @Test
            @DisplayName("Un arbol que contiene un solo elemento su minimo es la raiz")
            public void minimum_arbolUnitario_DevuelveRaiz() {
                //Arrange
                int expected = 10;
                //Act & Assert
                assertEquals(expected, bst.minimum());
            }

            @Test
            @DisplayName("Un arbol que contiene un solo elemento su maximo es la raiz")
            public void maximum_arbolUnitario_DevuelveRaiz() {
                //Arrange
                int expected = 10;
                //Act & Assert
                assertEquals(expected, bst.maximum());
            }

            @Test
            @DisplayName("Un arbol que contiene un solo elemento y coincide con el elemento que se desea eliminar")
            public void removeBranch_arbolUnitarioConElementoCorrecto_ArbolVacio() {
                //Arrange
                int val = 10;
                //Act 
                bst.removeBranch(val);
                //Assert
                assertEquals("", bst.render());
            }

            @Test
            @DisplayName("Un arbol que contiene un solo elemento y no coincide con el elemento que se desea eliminar")
            public void removeBranch_arbolUnitarioConElementoIncorrecto_LanzaExcepcion() {
                //Arrange
                int val = 11;
                //Act && Assert
                assertThrows(BinarySearchTreeException.class, () -> {
                    bst.removeBranch(val);
                });
            }

            @Test
            @DisplayName("Un arbol unitario tiene tamanyo 1")
            public void size_arbolUnitario_TamanyoUno() {
                //Arrange
                int res;
                //Act
                res = bst.size();                
                //Assert
                assertEquals(1, res);
            }

            @Test
            @DisplayName("Un arbol unitario tiene profundidad 1")
            public void depth_arbolUnitario_ProfundidadUno() {
                //Arrange
                int res;
                //Act
                res = bst.depth();                
                //Assert
                assertEquals(1, res);
            }

            @Test
            @DisplayName("Si arbol hoja contiene elemento que deseamos eliminar, se elimina")
            public void removeValue_arbolHojaContieneElemento_ArbolVacio() {
                // Act 
                bst.removeValue(10);
                //Assert
                assertEquals(0, bst.size());        
            }

            @Test
            @DisplayName("No podemos eliminar del árbol hoja un elemento que no existe")
            public void removeValue_arbolHojaNoContieneElemento_LanzaExcepcion() {
                // Act & Assert
                assertThrows(BinarySearchTreeException.class, ()->{
                    bst.removeValue(9);
                });                    
            }

            @Test
            @DisplayName("Al recorrer el arbol hoja en orden se genera la lista con un solo elemento que contiene este elemento")
            public void inOrder_arbolHoja_ListaConEsteElemento() {
                // Arrange
                List<Integer> res = new ArrayList<>();
                // Act
                res = bst.inOrder();
                // Assert
                assertEquals(1, res.size());
                assertTrue(res.contains(Integer.valueOf(bst.render())));
            }

            @Test
            @DisplayName("Al recorrer el arbol hoja en orden se genera la lista con un solo elemento que contiene este elemento")
            public void balance_arbolHoja_ElArbolOriginalNoCambia() {
                // Arrange
                String arbolInicial = bst.render();
                // Act
                bst.balance();
                // Assert
                assertEquals(arbolInicial, bst.render());
            }

            @Test
            @DisplayName("El arbol vacio no puede ser balanceado")
            public void balance_arbolHoja_SeBalancea() {
                // Act 
                bst.balance();
                //Assert
                assertEquals("10", bst.render());
            }

            // ---------------------------------------
            // Tests para árbol con más de un elemento
            // ---------------------------------------

            @Nested
            @DisplayName("Despues de insertar varios elementos")
            class DespuesInsertarVariosElementos{
                @BeforeEach
                public void insertarElementos(){
                    bst.insert(3);
                    bst.insert(15);
                }

                @Test
                @DisplayName("Arbol contiene 3 elementos")
                void insert_VariosElementos_InsertadosCorrectamente() {
                    assertEquals("10(3,15)", bst.render());
                }

                @Test
                @DisplayName("Intentamos meter elementos duplicados y se inserta solamente 1")
                public void insert_duplicados_NoSeAnyade() {
                    // Arrange
                    int val = 10;
                    // Act
                    bst.insert(val);
                    // Assert
                    assertEquals("10(3,15)", bst.render());
                }

                @Test
                @DisplayName("Anyadimos un nuevo valor menor que la raiz y menor que el hijo izquierdo")
                public void insert_menorQueRaizYMenorQueHijoIzquierdo_InsertarIzquierdaRaizIzquierdaHijo(){
                    //Arrange
                    int val = 2;
                    //Act
                    bst.insert(val);
                    //Assert
                    assertEquals("10(3(2,),15)", bst.render());
                }

                @Test
                @DisplayName("Anyadimos un nuevo valor menor que la raiz, pero mayor que el hijo izquierdo")
                public void insert_menorQueRaizMayorQueHijoIzquierdo_InsertarIzquierdaRaizDerechaHijo(){
                    //Arrange
                    int val = 4;
                    //Act
                    bst.insert(val);
                    //Assert
                    assertEquals("10(3(,4),15)", bst.render());
                }

                @Test
                @DisplayName("Anyadimos un nuevo valor mayor que la raiz y mayor que el hijo derecho ")
                public void insert_mayorQueRaizMayorQueHijoDerecho_InsertarDerechaRaizDerechaHijo(){
                    //Arrange
                    int val = 16;
                    //Act
                    bst.insert(val);
                    //Assert
                    assertEquals("10(3,15(,16))", bst.render());
                }

                @Test
                @DisplayName("Anyadimos un nuevo valor mayor que la raiz y menor que el hijo derecho ")
                public void insert_mayorQueRaizMenorQueHijoDerecho_InsertarDerechaRaizIzquierdaHijo(){
                    //Arrange
                    int val = 14;
                    //Act
                    bst.insert(val);
                    //Assert
                    assertEquals("10(3,15(14,))", bst.render());
                }

                @Test
                @DisplayName("Arbol con mas de un elemento no es hoja")
                public void isLeaf_arbolConMasDeUnElemento_NoEsHoja(){
                    //Arrange
                    boolean res;
                    //Act
                    res = bst.isLeaf();
                    // Assert
                    assertFalse(res);
                }

                @Test
                @DisplayName("Arbol con mas de un elemento contiene el que buscamos")
                public void contains_arbolConElementoDeseado_SeEncuentraElemento(){
                    //Arrange
                    int val = 10;
                    //Act && Assert
                    assertTrue(bst.contains(val));
                }

                @Test
                @DisplayName("Arbol con mas de un elemento contiene el que buscamos y que esta a la izquierda")
                public void contains_arbolConElementoDeseadoALaIzquierda_SeEncuentraElemento(){
                    //Arrange
                    int val = 3;
                    //Act && Assert
                    assertTrue(bst.contains(val));
                }

                @Test
                @DisplayName("Arbol con mas de un elemento contiene el que buscamos y que esta a la derecha")
                public void contains_arbolConElementoDeseadoALaDerecha_SeEncuentraElemento(){
                    //Arrange
                    int val = 15;
                    //Act && Assert
                    assertTrue(bst.contains(val));
                }

                @Test
                @DisplayName("Arbol con mas de un elemento no contiene el que buscamos que deberia estar a la izquierda")
                public void contains_arbolSinElementoDeseadoALaIzquierda_NoSeEncuentraElemento() {
                    //Arrange
                    int val1 = 4;
                    bst.insert(val1);
                    int val2 = 2;
                    //Act & Assert
                    assertFalse(bst.contains(val2));
                }

                @Test
                @DisplayName("Arbol con mas de un elemento no contiene el que buscamos que deberia estar a la derecha")
                public void contains_arbolSinElementoDeseadoALaDerecha_NoSeEncuentraElemento() {
                    //Arrange
                    int val = 14;
                    bst.insert(val);
                    int val2 = 16;
                    //Act & Assert
                    assertFalse(bst.contains(val2));
                }

                @Test
                @DisplayName("Arbol con mas de un elemento tiene minimo")
                public void minimum_arbolConMasDeUnElemento_DevuelveMinimo(){
                    //Arrange
                    int expected = 3;
                    //Act && Assert
                    assertEquals(expected, bst.minimum());
                }

                @Test
                @DisplayName("Arbol con mas de un elemento tiene maximo")
                public void maximum_arbolConMasDeUnElemento_DevuelveMaximo(){
                    //Arrange
                    int expected = 15;
                    //Act && Assert
                    assertEquals(expected, bst.maximum());
                }

                @Test
                @DisplayName("Se elimina la raiz, se elimina el arbol")
                public void removeBranch_eliminamosRaiz_ArbolVacio(){
                    //Arrange
                    int val = 10;
                    //Act
                    bst.removeBranch(val); 
                    // Assert
                    assertEquals("", bst.render());
                }

                @Test
                @DisplayName("Eliminar la rama izquierda")
                public void removeBranch_eliminamosRamaIzquierda_ArbolSinRamaIzquierda(){
                    //Arrange
                    int val = 3;
                    //Act
                    bst.removeBranch(val);
                    // Assert
                    assertEquals("10(,15)", bst.render());
                }

                @Test
                @DisplayName("Eliminar la rama derecha")
                public void removeBranch_eliminamosRamaDerecha_ArbolSinRamaDerecha(){
                    //Arrange
                    int val = 15;
                    //Act
                    bst.removeBranch(val); 
                    // Assert
                    assertEquals("10(3,)", bst.render());
                }

                @Test
                @DisplayName("Eliminar elemento que no esta en el arbol")
                public void removeBranch_eliminamosElementoQueNoEsta_LanzaExcepcion(){
                    //Arrange
                    int val = 2;
                    //Act && Assert
                    assertThrows(BinarySearchTreeException.class, ()->{
                        bst.removeBranch(val);
                    });
                }

                @Test
                @DisplayName("Tamanyo de un arbol con mas de un elemento")
                public void size_arbolConMasDeUnElemento_DevuelveTamanyoDelArbol(){
                    //Arrange
                    int expected = 3;
                    //Act && Assert
                    assertEquals(expected, bst.size());
                }

                @Test
                @DisplayName("Profundidad de un arbol con mas de un elemento ")
                    public void depth_arbolConMasDeUnElemento_DevuelveProfundidadDelArbol(){
                    // Arrange
                    int expected = 2;
                    //Act && Assert
                    assertEquals(expected, bst.depth());
                }

            // TESTS PARA LOS MÉTODOS COMPLEJOS:

            @Test
            @DisplayName("Eliminamos raiz del arbol principal")
            public void removeValue_eliminamosRaizDelArbolPrincipal_RaizSeElimina() {
                // Act 
                bst.removeValue(10);
                //Assert
                assertEquals("3(,15)", bst.render());        
            }

            @Test
            @DisplayName("Eliminamos el hijo izquierdo del arbol principal")
            public void removeValue_eliminamosHijoIzquierdoDelArbolPrincipal_HijoSeElimina() {
                // Act
                bst.removeValue(3);
                //Assert
                assertEquals("10(,15)", bst.render());
            }

            @Test
            @DisplayName("Eliminamos el hijo derecho del arbol principal")
            public void removeValue_eliminamosHijoDerechoDelArbolPrincipal_HijoSeElimina() {
                // Act 
                bst.removeValue(15);
                //Assert
                assertEquals("10(3,)", bst.render());        
            }

            @Test
            @DisplayName("No podemos eliminar del árbol un elemento que no existe")
            public void removeValue_arbolNoContieneElemento_LanzaExcepcion() {
                // Act & Assert
                assertThrows(BinarySearchTreeException.class, ()->{
                    bst.removeValue(9);
                });                    
            }

            @Test
            @DisplayName("Recorremos el arbol en el orden creciente de sus elementos")
            public void inOrder_arbolNormal_ListaConElementosEnOrdenCreciente() {
                // Arrange
                List<Integer> res = new ArrayList<>();
                // Act
                res = bst.inOrder();
                // Assert
                assertEquals(Arrays.asList(3,10,15), res);              
            }

            @Test
            @DisplayName("Si intentamos balancear el arbol que ya está balanceado, este no cambia")
            public void balance_arbolBalanceado_ElArbolOriginalNoCambia() {
                // Arrange
                String arbolInicial = bst.render();
                // Act
                bst.balance();
                // Assert
                assertEquals(arbolInicial, bst.render());                
            }

            @Nested
            @DisplayName("Arbol desbalanceado por la izquierda")
            class DesbalanceadoPorLaIzquierda{
                @BeforeEach
                public void setUp(){
                    bst.insert(2);
                    bst.insert(1);
                }

                @Test
                @DisplayName("Arbol contiene 5 elementos y esta besbalanceado")
                public void arbolDesbalanceado() {
                    assertEquals("10(3(2(1,),),15)", bst.render());
                }

                @Test
                @DisplayName("Arbol contiene 5 elementos, esta besbalanceado asi que se balancea")
                public void balance_ArbolDesbalanceadoPorLaIzquierda_SeBalanceaCorrectamente() {
                    //Act
                    bst.balance();
                    //Assert
                    assertEquals("3(1(,2),10(,15))", bst.render());
                }
            }

            @Nested
            @DisplayName("Arbol desbalanceado por la derecha")
            class DesbalanceadoPorLaDerecha{
                @BeforeEach
                public void setUp(){
                    bst.insert(16);
                    bst.insert(18);
                }

                @Test
                @DisplayName("Arbol contiene 5 elementos y esta besbalanceado")
                public void arbolDesbalanceado() {
                    assertEquals("10(3,15(,16(,18)))", bst.render());
                }

                @Test
                @DisplayName("Arbol contiene 5 elementos esta besbalanceado asi que se balancea")
                public void balance_ArbolDesbalanceadoPorLaDerecha_SeBalanceaCorrectamente() {
                    //Act
                    bst.balance();
                    //Assert
                    assertEquals("15(3(,10),16(,18))", bst.render());
                }
            }
        }

        @Nested
        @DisplayName("Arbol sin hijos por la izquierda")
        class SinHijosPorLaIzquierda{
            @BeforeEach
            public void setUp(){
                bst.insert(15);
                bst.insert(16);
            }

            @Test
            @DisplayName("Si eliminamos la raiz y el arbol no tiene hijos por la izquierda, se elige el mínimo de la parte derecha")
            public void removeValue_eliminamosLaRaiz_SeEligeElMenorElementoPorLaDerecha(){
                //Assert
                String expected = "15(,16(,))";
                //Act
                bst.removeValue(10);
                //Assert
                assertEquals(expected, bst.render());
            }

            @Test
            @DisplayName("La profundidad del arbol se elige por el menor elemento de la derecha")
            public void depth_profundidadDelArbol_SeEligeElMenorElementoPorLaDerecha(){
                //Assert
                int expected = 3;
                int result = 0;
                //Act
                result = bst.depth();
                //Assert
                assertEquals(expected, result);
            }
        }

        @Nested
        @DisplayName("Arbol sin hijos por la derecha")
        class SinHijosPorLaDerecha{
            @BeforeEach
            public void setUp(){
                bst.insert(3);
                bst.insert(2);
            }

            @Test
            @DisplayName("Si eliminamos la raiz y el arbol no tiene hijos por la derecha, se elige el mínimo de la parte izquierda")
            public void removeValue_EliminamosLaRaiz_SeEligeElMayorElementoPorLaIzquierda(){
                //Assert
                String expected = "3(2(,),)";
                //Act
                bst.removeValue(10);
                //Assert
                assertEquals(expected, bst.render());
            }

            @Test
            @DisplayName("La profundidad del arbol se elige por el menor elemento de la derecha")
            public void depth_profundidadDelArbol_SeEligeElMenorElementoPorLaIzquierda(){
                //Assert
                int expected = 3;
                int result = 0;
                //Act
                result = bst.depth();
                //Assert
                assertEquals(expected, result);
            }
        }
    }
}
