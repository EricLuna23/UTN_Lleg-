package com.example.usuario.utn_llego;

/**
 * Created by usuario on 31/05/2017.
 */

public class string {

 String x[];

    public static void sortStringExchange( String  x [ ] )
    {
        int i, j;
        String temp;

        for ( i = 0;  i < x.length - 1;  i++ )
        {
            for ( j = i + 1;  j < x.length;  j++ )
            {
                if ( x [ i ].compareToIgnoreCase( x [ j ] ) > 0 )
                {                                             // ascending sort
                    temp = x [ i ];
                    x [ i ] = x [ j ];    // swapping
                    x [ j ] = temp;

                }
            }
        }
    }
}