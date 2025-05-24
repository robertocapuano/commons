package it.zthink;

import java.util.Optional;
import java.util.Set;
import java.util.*;

/**
 * Hello world!
 *
 */
public class App 
{
	
    public static void main( String[] args )
    {
//    	var v = Map.of(  
//	    			"a", true,
//	    			"c", false
//    			).getOrDefault( "c", false);
//    	
//    	System.out.println(v);
//    	System.out.println( decode( 12, 2, "a", 12, "b" ) );
    
    	var dec = Decode.map( "a", 1).map("b", 2).or(3);
    	System.out.println( dec.get("0") );

    	var y = "b";
    	var dec1 = Decode.on(y).map("a", 1).map("b", 2).or(3).get();
    	System.out.println( dec1 );

    	var dec2 = Decode.map( "a", 1).map("b", 2).or(3).get(y);
    	System.out.println( dec2 );
    	
    	var dec3 = Decode.map( "a", 1).map("b", 2).or(3).on(y).get();
    	System.out.println( dec3 );

    	var dec4 = Decode.fn( y,  "a", 1, "b", 2, 3 );
    	System.out.println( dec4 );
    	//    	var v2 = Decode
//    		.map( "a", 1 )
//    		.map( "b", 2 )
//    		.getOrDefault("c",3);
    	
    	var x = Tuple2.of(12,"b");
    	
    	System.out.println( Decode.map( Tuple2.of(12,"a"), 11 ).map(Tuple2.of(12,"b"),12).get(x) );
    	
    	System.out.println( Decode.fn( 
    			x, 
    			Tuple2.of(12,"a"), 11,
    			Tuple2.of(12,"b"), 12) );
    	
    	var s = "a";
    	
    	System.out.println( Decode.fn( 
    			s, 
    			"a", true,
    			"c", false,
    			false ) );
    	var s2 = Set.of("xx");
    	
        System.out.println( "Hello".replaceAll("[a-z]", "*") );
    }
}
