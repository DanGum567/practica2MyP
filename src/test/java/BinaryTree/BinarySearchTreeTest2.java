
package BinaryTree;

//Daniil Gumeniuk, 3º Software A
/*
 * - Probar balance con profundidad o render
 * - DisplayName tiene que ser explicativo, pero no tiene un formato
*/

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
public class BinarySearchTreeTest2 {
    private BinarySearchTree2<Integer> bst;
    private Comparator<Integer> comparator;

    // ---------------------------------------
    // Tests para un árbol vacio
    // ---------------------------------------
    @BeforeEach
    public void setUp() {
        comparator = Comparator.naturalOrder();
        bst = new BinarySearchTree2<>(comparator);
    } 
        @Test
        @DisplayName("Anyadimos un nuevo valor a un arbol vacio sin valores comprobamos datos correctos.")
        public void insert_insertarUnElemento_ArbolContieneElemento() {
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
            int val2 = 8;
            int val3 = 2;
            int val4 = 12;
            // Act & Assert
            assertThrows(BinarySearchTreeException.class, () ->{bst.contains(val1);});
            assertThrows(BinarySearchTreeException.class, () ->{bst.contains(val2);});
            assertThrows(BinarySearchTreeException.class, () ->{bst.contains(val3);});
            assertThrows(BinarySearchTreeException.class, () ->{bst.contains(val4);});
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
            int result = 0;
            // Act
            result = bst.size();
            // Assert
            assertEquals(0, result);
        }
        @Test
        @DisplayName("La profundidad de un arbol vacio es 0")
        public void depth_arbolVacio_Cero() {
            // Arrange
            int result = 0;
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
            public void insertar_ElementoMayorQueRaiz_InsercionDerecha(){
                // Arrange
                int val = 15;
                // Act
                bst.insert(val);
                // Assert
                assertEquals("10(,15)", bst.render());
            }
            @Test
            @DisplayName("Insertamos un elemento menor que la raiz en el arbol hoja")
            public void insertar_ElementoMenorQueRaiz_InsercionIzquierda(){
                // Arrange
                int val = 9;
                // Act
                bst.insert(val);
                // Assert
                assertEquals("10(9,)", bst.render());
            }

            @Test
            @DisplayName("Un arbol con un elemento es hoja")
            public void isLeaf_arbolConUnElemento_DevuelveVerdadero() {
                //Act & Assert
                assertTrue(bst.isLeaf());
            }

            @Test
            @DisplayName("Un arbol que contiene solamente el elemento que buscamos")
            public void contains_arbolConUnSoloElementoQueEsCorrecto_DevuelveVerdadero() {
                //Arrange
                int val = 10;
                //Act & Assert
                assertTrue(bst.contains(val));
            }

            @Test
            @DisplayName("Un arbol que contiene un solo elemento que no coincide con el que buscamos")
            public void contains_arbolConUnElementoQueNoCorrespondeConElQueBuscamos_DevuelveFalse() {
                //Arrange
                int val = 9;
                //Act & Assert
                assertFalse(bst.contains(val));
            }

            @Test
            @DisplayName("Un arbol que contiene un solo elemento que no coincide con el que buscamos")
            public void minimuma_ArbolUnitario_DevuelveRaiz() {
                //Arrange
                int expected = 10;
                //Act & Assert
                assertEquals(expected, bst.minimum());
            }
            @Test
            @DisplayName("Un arbol que contiene un solo elemento que no coincide con el que buscamos")
            public void maximum_ArbolUnitario_DevuelveRaiz() {
                //Arrange
                int expected = 10;
                //Act & Assert
                assertEquals(expected, bst.maximum());
            }
            @Test
            @DisplayName("Un arbol que contiene un solo elemento que coincide con el que se desea eliminar")
            public void removeBranch_ArbolUnitarioConElementoCorrecto_ArbolVacio() {
                //Arrange
                int val = 10;
                //Act 
                bst.removeBranch(val);
                //Assert
                assertEquals("", bst.render());
            }
            @Test
            @DisplayName("Un arbol que contiene un solo elemento que no coincide con el que se desea eliminar")
            public void removeBranch_ArbolUnitarioConElementoIncorrecto_LanzaExcepcion() {
                //Arrange
                int val = 11;
                //Act && Assert
                assertThrows(BinarySearchTreeException.class, () -> {
                    bst.removeBranch(val);
                });
            }
            @Test
            @DisplayName("Un arbol unitario tiene tamanyo 1")
            public void size_ArbolUnitario_TamanyoUno() {
                //Arrange
                int res;
                //Act
                res = bst.size();                
                //Assert
                assertTrue(res == 1);
            }
            @Test
            @DisplayName("Un arbol unitario tiene profundidad 1")
            public void depth_ArbolUnitario_ProfundidadUno() {
                //Arrange
                int res;
                //Act
                res = bst.depth();                
                //Assert
                assertTrue(res == 1);
            }
            @Test
            @DisplayName("Si arbol hoja contiene elemento que deseamos eliminar, se elimina")
            public void removeValue_arbolHojaContieneElemento_arbolVacio() {
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
                public void insert_Duplicados_NoSeAnyade() {
                // Arrange
                int val = 10;
                // Act
                bst.insert(val);
                // Assert
                assertEquals("10(3,15)", bst.render());
                }

                @Test
                @DisplayName("Anyadimos un nuevo valor menor que la raiz y el hijo izquierdo se anyade ")
                public void insert_menorQueLaRaizMenorQueElHijoIzquierdo_InsertadoALaIzquierda(){
                //Arrange
                int val = 2;
                //Act
                bst.insert(val);
                //Assert
                assertEquals("10(3(2,),15)", bst.render());
                }

                @Test
                @DisplayName("Anyadimos un nuevo valor menor que la raiz, pero mayor que el hijo izquierdo se anyade ")
                public void insert_menorQueLaRaizMayorQueElHijoIzquierdo_InsertadoALaIzquierda(){
                //Arrange
                int val = 4;
                //Act
                bst.insert(val);
                //Assert
                assertEquals("10(3(,4),15)", bst.render());
                }

                @Test
                @DisplayName("Anyadimos un nuevo valor mayor que la raiz y mayor que el hijo ")
                public void insert_nuevoValorMayorQueLaRaizMayorQueElHijoDerecho_InsertarDerecha(){
                //Arrange
                int val = 16;
                //Act
                bst.insert(val);
                //Assert
                assertEquals("10(3,15(,16))", bst.render());
                }

                @Test
                @DisplayName("Anyadimos un nuevo valor mayor que la raiz, pero menor que el hijo ")
                public void insert_nuevoValorMayorQueLaRaizMenorQueElHijoDerecho_InsertarDerechaRaizIzquierdaHijo(){
                //Arrange
                int val = 14;
                //Act
                bst.insert(val);
                //Assert
                assertEquals("10(3,15(14,))", bst.render());
                }

                @Test
                @DisplayName("Arbol con mas de un elemento no es hoja")
                public void isLeaf_ArbolConMasDeUnElemento_NoEsHoja(){
                //Arrange
                boolean res;
                //Act 
                res = bst.isLeaf();
                // Assert
                assertFalse(res);
                }
                
                @Test
                @DisplayName("Arbol con mas de un elemento contiene el que buscamos")
                public void contains_ArbolConElementoDeseado_SeEncuentraElemento(){
                //Arrange
                int val = 10;
                //Act && Assert
                assertTrue(bst.contains(val));
                }

                @Test
                @DisplayName("Arbol con mas de un elemento no contiene el que buscamos")
                public void contains_ArbolSinElementoDeseado_NoSeEncuentraElemento(){
                //Arrange
                int val = 9;
                //Act && Assert
                assertFalse(bst.contains(val));
                }

                @Test
                @DisplayName("Arbol con mas de un elemento tiene maximo")
                public void maximum_ArbolConMasDeUnElemento_DevuelveMaximo(){
                //Arrange
                int expected = 15;
                //Act && Assert
                assertEquals(expected, bst.maximum());
                }

                @Test
                @DisplayName("Arbol con mas de un elemento tiene maximo")
                public void minimum_ArbolConMasDeUnElemento_DevuelveMinimo(){
                //Arrange
                int expected = 3;
                //Act && Assert
                assertEquals(expected, bst.minimum());
                }

                @Test
                @DisplayName("Se elimina la raiz, se elimina el arbol")
                public void removeBranch_EliminamosRaiz_ArbolVacio(){
                //Arrange
                int val = 10;
                //Act
                bst.removeBranch(val); 
                // Assert
                assertEquals("", bst.render());
                }

                @Test
                @DisplayName("Eliminar la rama derecha")
                public void removeBranch_EliminamosRamaDerecha_ArbolSinRamaDerecha(){
                //Arrange
                int val = 15;
                //Act
                bst.removeBranch(val); 
                // Assert
                assertEquals("10(3,)", bst.render());
                }

                @Test
                @DisplayName("Eliminar la rama izquierda")
                public void removeBranch_EliminamosRamaIzquierda_ArbolSinRamaIzquierda(){
                //Arrange
                int val = 3;
                //Act
                bst.removeBranch(val); 
                // Assert
                assertEquals("10(,15)", bst.render());
                }

                @Test
                @DisplayName("Eliminar elemento que no esta en el arbol")
                public void removeBranch_EliminamosElementoQueNoEsta_LanzaExcepcion(){
                //Arrange
                int val = 2;
                //Act && Assert
                assertThrows(BinarySearchTreeException.class, ()->{
                    bst.removeBranch(val);
                });
                }

                @Test
                @DisplayName("Tamanyo de un arbol con mas de un elemento")
                public void size_ArbolConMasDeUnElemento_DevuelveTamanyoDelArbol(){
                //Arrange
                int expected = 3;
                //Act && Assert
                assertEquals(expected, bst.size());
                }
                @Test
                @DisplayName("Eliminar elemento que no esta en el arbol")
                public void depth_ArbolConMasDeUnElemento_DevuelveProfundidadDelArbol(){
                // Arrange
                int expected = 2;
                //Act && Assert
                assertEquals(expected, bst.depth());
                }

            // TESTS PARA LOS MÉTODOS COMPLEJOS:
            @Test
            @DisplayName("Eliminamos la raiz del arbol normal")
            public void removeValue_eliminamosRaizDelArbolNormal_RaizSeElimina() {
                // Act 
                bst.removeValue(10);
                //Assert
                assertEquals("3(,15)", bst.render());        
            }
            @Test
            @DisplayName("Eliminamos el hijo derecho del arbol normal")
            public void removeValue_eliminamosHijoDerechoDelArbolNormal_HijoSeElimina() {
                // Act 
                bst.removeValue(15);
                //Assert
                assertEquals("10(3,)", bst.render());        
            }
            @Test
            @DisplayName("Eliminamos el hijo izquierdo del arbol normal")
            public void removeValue_eliminamosHijoIzquierdoDelArbolNormal_HijoSeElimina() {
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
            @DisplayName("Arbol desbalanceado por la derecha")
            class DesbalanceadoPorLaDerecha{
               @BeforeEach
               public void setUp(){
                  bst.insert(16);
                  bst.insert(18);
                }
                @Test
                @DisplayName("Arbol contiene 5 elementos y esta besbalanceado")
                void arbolDesbalanceado() {
                    assertEquals("10(3,15(,16(,18)))", bst.render());
                }
                @Test
                @DisplayName("Arbol contiene 5 elementos y esta besbalanceado")
                void balance_ArbolDesbalanceadoPorLaDerecha_SeBalanceaCorrectamente() {
                    //Act
                    bst.balance();
                    //Assert
                    assertEquals("15(3(,10),16(,18))", bst.render());
                }
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
                void arbolDesbalanceado() {
                    assertEquals("10(3(2(1,),),15)", bst.render());
                }
                @Test
                @DisplayName("Arbol contiene 5 elementos y esta besbalanceado")
                void balance_ArbolDesbalanceadoPorLaIzquierda_SeBalanceaCorrectamente() {
                    //Act
                    bst.balance();
                    //Assert
                    assertEquals("3(1(,2),10(,15))", bst.render());
                }
            }
        }
    }
}


        