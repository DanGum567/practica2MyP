package gestor;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class GestorSensoresTest {

    @Test
    @DisplayName("Initially the number of sensors is 0")
    public void getNumeroSensores_init_returns0(){
        //Arrange
        GestorSensores gestor = new GestorSensores();
        
        // Act
        int num_sensores = gestor.getNumeroSensores();

        //Assert
        assertEquals(num_sensores, 0);
    }

    @Test
    public void borrarSensor_SensorNoExistente_DevuelveExcepcion() {
        //Arrange
        GestorSensores gestor = new GestorSensores();
        
        //Act, Assert
        assertThrows(IllegalArgumentException.class, () -> {
                gestor.borrarSensor("NombreInexistente");
        });
    }

    
}
