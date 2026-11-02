package uniandes.dpoo.hamburguesas.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import uniandes.dpoo.hamburguesas.mundo.Combo;
import uniandes.dpoo.hamburguesas.mundo.ProductoMenu;

public class ComboTest
{
    private Combo combo;

    @BeforeEach
    void setUp( )
    {
        ProductoMenu prod1 = new ProductoMenu( "corral", 10000 );
        ProductoMenu prod2 = new ProductoMenu( "papas", 5000 );

        ArrayList<ProductoMenu> items = new ArrayList<>( );
        items.add( prod1 );
        items.add( prod2 );
        combo = new Combo( "combo corral", 0.10, items );
    }

    @Test
    void testGetNombre( )
    {
        assertEquals( "combo corral", combo.getNombre( ) );
    }

    @Test
    void testGetPrecio( )
    {
        assertEquals( 13500, combo.getPrecio( ) );
    }

    @Test
    void testGenerarTextoFactura( )
    {
        String texto = combo.generarTextoFactura( );
        assertTrue( texto.contains( "combo corral" ) );
        assertTrue( texto.contains( "0.1" ) );
        assertTrue( texto.contains( "13500" ) );
    }
}