package it.zthink;

import java.util.*;
import java.util.function.*;

public class Fn {

	public static <T> List<T> filter( List<T> l, Function<T,Boolean> f ) {
		return l.stream().filter( x -> f.apply(x) ).toList();
	}
	
	public static <S,T> List<T> map( List<S> l, Function<S,T> f ) {
		return l.stream().map( x -> f.apply(x) ).toList();
	}
	
	public static <T> void forEach( List<T> l, Consumer<T> f ) {
		l.stream().forEach( x -> f.accept(x) );
	}

}
