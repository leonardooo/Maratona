import java.io.*;
import java.util.StringTokenizer;

class Bomb {

    public int x;
    public int y;
    public int rad;


    public Bomb( int x, int y , int rad ) {
            this.x = x;
            this.y = y;
            this.rad = rad;
    }

    public double distance( Bomb otherBomb ) {

        return Math.sqrt( ( x - otherBomb.x ) * ( x - otherBomb.x ) + ( y - otherBomb.y ) * ( y - otherBomb.y) ) - rad - otherBomb.rad ;
    }

}


class Main {


    public static void main ( String args[] ) throws Exception {

        BufferedReader in = new BufferedReader( new InputStreamReader( System.in ) );

        int testCases = Integer.parseInt( in.readLine() );

        for( int i = 0 ; i < testCases; i++ ) {

            StringTokenizer st = new StringTokenizer( in.readLine() );
            int width = Integer.parseInt( st.nextToken() );
            int length = Integer.parseInt( st.nextToken() );
            int b = Integer.parseInt( st.nextToken() );

            Bomb bombs[] = new Bomb[ b + 2 ];

            bombs[ 0 ] = new Bomb( 0 , 0 , 0 );
            bombs[ b + 1 ] = new Bomb( width, 0 , 0 );

            for( int j = 1; j <= b; j++ ) {
                st = new StringTokenizer( in.readLine() );
                bombs[ j ] = new Bomb( Integer.parseInt( st.nextToken() ) , Integer.parseInt( st.nextToken() ), Integer.parseInt( st.nextToken() ) );
            }

            double  distances[][] = new double[ b + 2 ][ b + 2 ];

            distances[ 0 ][ b + 1 ] = width;
            distances[ b + 1 ][ 0 ] = width;
            for( int j = 1; j<= b; j++ ) {
                distances[ 0 ][ j ] = bombs[ j ].x - bombs[ j ].rad;
                distances[ j ][ 0 ] = distances[ 0 ][ j ];
                distances[ b + 1][ j ] = width - bombs[ j ].x - bombs[ j ].rad;
                distances[ j ][ b + 1 ] = distances[ b + 1 ][ j ];
            }

            for( int j = 1; j <= b ; j++ ) {
                for( int k = j + 1; k <= b ; k++ ) {
                    double d = bombs[ j ].distance( bombs[ k ] );
                    distances[ j ][ k ] = d;
                    distances[ k ][ j ] = d;
                }
            }

            for( int init = 0; init <= b +1; init ++ )
                for( int med = 0; med <= b + 1; med++ )
                    for( int end = 0; end <= b+1; end++) {
                        if( distances[ init ][ med ] <=0 && distances[ med ][ end ] <= 0 )
                            distances[ init ][ end ] = 0;
                    }

            if( distances[ 0 ][ b + 1] == 0 ) {
                    System.out.println( "Bridge already split" );
            } else {

                double minimum = width;
                for( int j = 0; j <= b + 1; j++ ) {
                        if( distances[ j ][ 0 ] <= 0 ) {
                                for( int k = 0; k <= b + 1; k++ ) {
                                    if( distances[ k ][ b + 1] == 0 ) {
                                            if( distances[ j ][ k ] < minimum ) {
                                                minimum = distances[ j ][ k ];
                                            }
                                    }
                                }
                        }
                }

                System.out.println( (int) Math.ceil( minimum / 2 ) );
            }
        }
    }

}
