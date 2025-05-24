package it.zthink;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Base syntax
 * int value = Decode.on(y).map("a", 1).map("b", 2).or(3).get();
 * 
 * Variants:
 * int value = Decode.map("a", 1).map("b", 2).or(3).get(y);  // without value
 * 
 * Optional<Integer> value = Decode.on(y).map("a", 1).map("b", 2).get(); // without default
 * Optional<Integer> value = Decode.map("a", 1).map("b", 2).get(y); // without default
 *
 * Decoder Object for multiple calls:
 * var decoder = Decode.map("a", 1).map("b", 2).or(3);
 * decoder.get(y) // Integer
 * 
 * without default returns an Optional
 * var decoder = Decode.map("a", 1).map("b", 2);
 * decoder.get(y) // Optional<Integer>
 * 
 */
public class Decode {
	
	static <K,V> Optional<V> fn( K val, K k, V v )
	{
		return (Optional<V>) fn(val, List.of(k), List.of(v), Optional.empty() );
	}

	static <K,V> V fn( K val, K k, V v, V def )
	{
		return fn(val, List.of(k), List.of(v), Optional.of(def) ).get();
	}

	static <K,V> Optional<V> fn( K val, K k0, V v0, K k1, V v1 )
	{
		return (Optional<V>) fn(val, List.of(k0,k1), List.of(v0,v1), Optional.empty() );
	}

	static <K,V> V fn( K val, K k0, V v0, K k1, V v1, V failback )
	{
		return fn(val, List.of(k0,k1), List.of(v0,v1),  Optional.of(failback) ).get();
	}

	static <K,V> V fn( K val, K k0, V v0, K k1, V v1, K k2, V v2, V failback )
	{
		return fn(val, List.of(k0,k1), List.of(v0,v1), Optional.of(failback) ).get();
	}

	static <K,V> V fn( K val, K k0, V v0, K k1, V v1, K k2, V v2, K k3, V v3, V failback )
	{
		return fn(val, List.of(k0,k1,k2), List.of(v0,v1,v2),  Optional.of(failback) ).get();
	}
	
	static <K,V> Optional<V> fn( K val, List<K> ks, List<V> vs, Optional<V> failback )
	{
		assert( ks.size()==vs.size() );
		
		for ( int i=0; i<ks.size(); ++i )
		{
			if (val.equals(ks.get(i)))
				return Optional.of(vs.get(i));
		}
		
		return failback;
	}
	
	public static <K,V> DecoderWithValue<K,V> on( K k )
	{
		return new DecoderWithValue<K,V>(k);
	}
	
	public static <K,V> Decoder<K,V> map( K k, V v)
	{
		return new Decoder<K,V>().map(k, v);
	}
	
	public static <K,V> DecoderWithDefault<K,V> or(  V v)
	{
		return new DecoderWithDefault<K,V>( new HashMap<>(), v);
	}
	
	public static class DecoderWithValue <K,V>
	{
		private K value;
		private Map<K,V> mapping = new HashMap<>();
		
		DecoderWithValue( K value)
		{
			this.value = value;
		}
		
		public DecoderWithValue( K value, Map<K,V> mapping)
		{
			this.value = value;
			this.mapping=mapping;
		}
	
		public DecoderWithValue<K,V> map( K k, V v)
		{
			mapping.put( k,  v);
			return this;
		}
		
		public Optional<V> get()
		{
			return mapping.containsKey(value) ?
					Optional.of(mapping.get(value)) :  Optional.empty();
		}
		
		public DecoderWithValueAndDefault<K,V> or( V v )
		{
			return new DecoderWithValueAndDefault<K,V>(value,mapping,v);
		}
		
	}
	
	public static class DecoderWithValueAndDefault <K,V>
	{
		private K value;
		private Map<K,V> mapping = new HashMap<>();
		private V failback;
		
		DecoderWithValueAndDefault( K value, Map<K,V> mapping, V failback)
		{
			this.value = value;
			this.mapping=mapping;
			this.failback=failback;
		}
	
		public DecoderWithValueAndDefault<K,V> map( K k, V v)
		{
			mapping.put( k,  v);
			return this;
		}
		
		public V get(  )
		{
			return mapping.containsKey( value ) ?
					mapping.get(value) :  failback;
		}
		
	}
	
	public static class Decoder <K,V> {
	
		private Map<K,V> mapping = new HashMap<>();
		
		Decoder()
		{
		}
	
		public Decoder<K,V> map( K k, V v)
		{
			mapping.put( k,  v);
			return this;
		}
		
		public Optional<V> get( K k )
		{
			return mapping.containsKey( k ) ?
					Optional.of(mapping.get(k)) :  Optional.empty();
		}
		
		public DecoderWithDefault<K,V> or( V v )
		{
			return new DecoderWithDefault<K,V>(mapping,v);
		}
		
		public DecoderWithValue<K,V> on( K v )
		{
			return new DecoderWithValue<K,V>(v,mapping);
		}

	}
	
	public static class DecoderWithDefault <K,V> {
		
		private Map<K,V> mapping = new HashMap<>();
		
		private V failback;
		
		DecoderWithDefault( Map<K,V> mapping, V failback)
		{
			this.mapping=mapping;
			this.failback=failback;
		}
	
		public DecoderWithDefault<K,V> map( K k, V v)
		{
			mapping.put( k,  v);
			return this;
		}
		
		public V get( K k )
		{
			return mapping.containsKey( k ) ?
					mapping.get(k) :  failback;
		}
		
		public DecoderWithValueAndDefault<K,V> on( K k )
		{
			return new DecoderWithValueAndDefault<K,V>(k,mapping,failback);
		}
		

	}
	
}
	
	
