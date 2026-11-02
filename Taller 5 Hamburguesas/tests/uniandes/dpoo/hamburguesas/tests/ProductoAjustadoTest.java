package uniandes.dpoo.hamburguesas.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import uniandes.dpoo.hamburguesas.mundo.Ingrediente;
import uniandes.dpoo.hamburguesas.mundo.ProductoAjustado;
import uniandes.dpoo.hamburguesas.mundo.ProductoMenu;

public class ProductoAjustadoTest
{
    private ProductoMenu base;
    private ProductoAjustado ajustado;

    @BeforeEach
    void setUp( )
    {
        base = new ProductoMenu( "corral", 14000 );
        ajustado = new ProductoAjustado( base );
    }

    @Test
    void testGetNombre( )
    {
        assertEquals( "corral", ajustado.getNombre( ) );
    }

    @Test
    void testGetPrecioSinAjustes( )
    {
        assertEquals( 14000, ajustado.getPrecio( ) );
    }

    @Test
    void testGetPrecioConIngredientes( )
    {
        Ingrediente queso = new Ingrediente( "queso", 2000 );
        Ingrediente tocineta = new Ingrediente( "tocineta", 3000 );
        Ingrediente cebolla = new Ingrediente( "cebolla", 1000 );

        ajustado.agregarIngrediente( queso );
        ajustado.agregarIngrediente( tocineta );
        ajustado.eliminarIngrediente( cebolla );
        assertEquals( 19000, ajustado.getPrecio( ) );
    }

    @Test
    void testGenerarTextoFactura( )
    {
        Ingrediente queso = new Ingrediente( "queso", 2000 );
        Ingrediente tomate = new Ingrediente( "tomate", 1000 );

        ajustado.agregarIngrediente( queso );
        ajustado.eliminarIngrediente( tomate );

        String texto = ajustado.generarTextoFactura( );
        assertTrue( texto.contains( "corral" ) );
        assertTrue( texto.contains( "+queso" ) );
        assertTrue( texto.contains( "-tomate" ) );
        assertTrue( texto.contains( "16000" ) );
    }
}