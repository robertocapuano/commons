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

	static <K,V> Optional<V> decode( K val, K k, V v )
	{
		return (Optional<V>) decode(val, List.of(k), List.of(v), Optional.empty() );
	}

	static <K,V> V decode( K val, K k, V v, V def )
	{
		return decode(val, List.of(k), List.of(v), Optional.of(def) ).get();
	}

	static <K,V> Optional<V> decode( K val, K k0, V v0, K k1, V v1 )
	{
		return (Optional<V>) decode(val, List.of(k0,k1), List.of(v0,v1), Optional.empty() );
	}

	static <K,V> V decode( K val, K k0, V v0, K k1, V v1, V failback )
	{
		return decode(val, List.of(k0,k1), List.of(v0,v1),  Optional.of(failback) ).get();
	}

	static <K,V> V decode( K val, K k0, V v0, K k1, V v1, K k2, V v2, V failback )
	{
		return decode(val, List.of(k0,k1), List.of(v0,v1), Optional.of(failback) ).get();
	}

	static <K,V> V decode( K val, K k0, V v0, K k1, V v1, K k2, V v2, K k3, V v3, V failback )
	{
		return decode(val, List.of(k0,k1,k2), List.of(v0,v1,v2),  Optional.of(failback) ).get();
	}
	
	static <K,V> Optional<V> decode( K val, List<K> ks, List<V> vs, Optional<V> failback )
	{
		assert( ks.size()==vs.size() );
		
		for ( int i=0; i<ks.size(); ++i )
		{
			if (val.equals(ks.get(i)))
				return Optional.of(vs.get(i));
		}
		
		return failback;
	}
	
}
