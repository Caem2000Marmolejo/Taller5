package uniandes.dpoo.hamburguesas.tests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import uniandes.dpoo.hamburguesas.excepciones.IngredienteRepetidoException;
import uniandes.dpoo.hamburguesas.excepciones.NoHayPedidoEnCursoException;
import uniandes.dpoo.hamburguesas.excepciones.ProductoFaltanteException;
import uniandes.dpoo.hamburguesas.excepciones.ProductoRepetidoException;
import uniandes.dpoo.hamburguesas.excepciones.YaHayUnPedidoEnCursoException;
import uniandes.dpoo.hamburguesas.mundo.ProductoMenu;
import uniandes.dpoo.hamburguesas.mundo.Restaurante;

public class RestauranteTest {

    private Restaurante restaurante;
    private File archIng;
    private File archMenu;
    private File archCombos;

    @BeforeEach
    void setUp( ) throws Exception
    {
        restaurante = new Restaurante( );
        archIng = new File( "data/ingredientes.txt" );
        archMenu = new File( "data/menu.txt" );
        archCombos = new File( "data/combos.txt" );
    }

    @AfterEach
    void tearDown( ) throws Exception
    {
    }

    @Test
    void testEstadoInicial( )
    {
        assertNull( "El pedido en curso debia ser nulo al inicio", restaurante.getPedidoEnCurso( ) );
        assertEquals( 0, restaurante.getPedidos( ).size( ), "La lista de pedidos debia estar vacia" );
        assertEquals( 0, restaurante.getMenuBase( ).size( ), "El menu base debia estar vacio" );
        assertEquals( 0, restaurante.getMenuCombos( ).size( ), "El menu de combos debia estar vacio" );
        assertEquals( 0, restaurante.getIngredientes( ).size( ), "La lista de ingredientes debia estar vacia" );
    }

    @Test
    void testIniciarYCerrarPedido( )
    {
        try
        {
            restaurante.iniciarPedido( "Juan", "Calle 10 # 20" );
            assertNotNull( "El pedido no deberia ser nulo", restaurante.getPedidoEnCurso( ) );
            assertEquals( "Juan", restaurante.getPedidoEnCurso( ).getNombreCliente( ), "El nombre no coincide" );

            ProductoMenu hamburguesa = new ProductoMenu( "corral", 14000 );
            restaurante.getPedidoEnCurso( ).agregarProducto( hamburguesa );

            restaurante.cerrarYGuardarPedido( );

            assertNull( "El pedido en curso deberia quedar nulo", restaurante.getPedidoEnCurso( ) );
            assertEquals( 1, restaurante.getPedidos( ).size( ), "Deberia haber 1 pedido cerrado" );
        }
        catch( Exception e )
        {
            fail( "No debio lanzar excepcion" );
        }
    }

    @Test
    void testIniciarPedidoYaHayUnoException( )
    {
        try
        {
            restaurante.iniciarPedido( "Pepito", "Calle 1" );
            restaurante.iniciarPedido( "Juanito", "Calle 2" );
            fail( "Debio haber lanzado YaHayUnPedidoEnCursoException" );
        }
        catch( YaHayUnPedidoEnCursoException e )
        {
      
        }
        catch( Exception e )
        {
            fail( "Lanzo una excepcion diferente a YaHayUnPedidoEnCursoException" );
        }
    }

    @Test
    void testCerrarPedidoSinHaberIniciadoException( )
    {
        try
        {
            restaurante.cerrarYGuardarPedido( );
            fail( "Debio haber lanzado NoHayPedidoEnCursoException" );
        }
        catch( NoHayPedidoEnCursoException e )
        {
         
        }
        catch( Exception e )
        {
            fail( "Lanzo una excepcion diferente a NoHayPedidoEnCursoException" );
        }
    }

    @Test
    void testCargarInformacionArchivosValidos( )
    {
        try
        {
            restaurante.cargarInformacionRestaurante( archIng, archMenu, archCombos );
            assertFalse( "Los ingredientes no debian quedar vacios", restaurante.getIngredientes( ).isEmpty( ) );
            assertFalse( "El menu base no debia quedar vacio", restaurante.getMenuBase( ).isEmpty( ) );
            assertFalse( "Los combos no debian quedar vacios", restaurante.getMenuCombos( ).isEmpty( ) );
        }
        catch( Exception e )
        {
            fail( "Fallo la carga de archivos normales de data" );
        }
    }

    @Test
    void testIngredienteRepetidoException( )
    {
        try
        {
            
            restaurante.cargarInformacionRestaurante( archIng, archMenu, archCombos );
            
            restaurante.cargarInformacionRestaurante( archIng, archMenu, archCombos );
            fail( "Debio lanzar IngredienteRepetidoException" );
        }
        catch( IngredienteRepetidoException e )
        {
           
        }
        catch( Exception e )
        {
            fail( "Lanzo una excepcion diferente" );
        }
    }

    @Test
    void testProductoFaltanteException( )
    {
        try
        {
          
            File archivoVacio = new File( "data/ingredientes.txt" ); 
            restaurante.cargarInformacionRestaurante( archivoVacio, archivoVacio, archCombos );
            fail( "Debio lanzar ProductoFaltanteException" );
        }
        catch( ProductoFaltanteException e )
        {
  
        }
        catch( Exception e )
        {
       
        }
    }

}