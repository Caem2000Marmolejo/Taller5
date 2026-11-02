package uniandes.dpoo.hamburguesas.tests;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import uniandes.dpoo.hamburguesas.mundo.Pedido;
import uniandes.dpoo.hamburguesas.mundo.ProductoMenu;

public class PedidoTest {

    private Pedido pedido;
    private ProductoMenu hamburguesa;
    private ProductoMenu papas;

    @BeforeEach
    void setUp( ) throws Exception
    {
        pedido = new Pedido( "Carlos", "Calle 678" );
        hamburguesa = new ProductoMenu( "corral", 10000 );
        papas = new ProductoMenu( "papas", 5000 );
    }

    @AfterEach
    void tearDown( ) throws Exception
    {
    }

    @Test
    void testDatosBasicos( )
    {
        assertEquals( "Carlos", pedido.getNombreCliente( ), "El nombre del cliente no es el esperado." );
        assertTrue( "El id deberia ser mayor o igual a 0", pedido.getIdPedido( ) >= 0 );
    }

    @Test
    void testCalculoPrecios( )
    {
        assertEquals( 0, pedido.getPrecioTotalPedido( ), "El total debia ser 0 al inicio" );

        pedido.agregarProducto( hamburguesa );
        pedido.agregarProducto( papas );

  
        assertEquals( 15000, pedido.getPrecioNetoPedido( ), "El precio neto no es el esperado." );
        assertEquals( 2850, pedido.getPrecioIVAPedido( ), "El IVA no es el esperado." );
        assertEquals( 17850, pedido.getPrecioTotalPedido( ), "El precio total no es el esperado." );
    }

    @Test
    void testGenerarTextoFactura( )
    {
        pedido.agregarProducto( hamburguesa );
        pedido.agregarProducto( papas );

        String factura = pedido.generarTextoFactura( );
        assertTrue( "No contiene el nombre del cliente", factura.contains( "Carlos" ) );
        assertTrue( "No contiene la direccion", factura.contains( "Calle 678" ) );
        assertTrue( "No contiene el valor neto", factura.contains( "Precio Neto:  15000" ) );
        assertTrue( "No contiene el valor de IVA", factura.contains( "IVA:          2850" ) );
        assertTrue( "No contiene el precio total", factura.contains( "Precio Total: 17850" ) );
    }

    @Test
    void testGuardarFactura( )
    {
        pedido.agregarProducto( hamburguesa );
        File archivo = new File( "prueba_factura.txt" );

        try
        {
            pedido.guardarFactura( archivo );
        }
        catch( Exception e )
        {
            fail( "No se creo el archivo de factura" );
        }

        assertTrue( "No existe el archivo", archivo.exists( ) );
        archivo.delete( );
    }

    @Test
    void testGuardarFacturaExceptions( )
    {
        try
        {
            pedido.guardarFactura( null );
            fail( "Debio haber lanzado exception" );
        }
        catch( Exception e )
        {
      
        }
    }

}