package it.zthink;

import java.util.Objects;

public class Tuple2 <T0, T1> {
	
	public static <T0,T1> Tuple2<T0,T1> of( T0 _0, T1 _1  )
	{
		return new Tuple2<T0,T1>(_0,_1);
	}

	public final T0 _0;
	public final T1 _1;
	
	public Tuple2(T0 _0, T1 _1 )
	{
		this._0=_0;
		this._1=_1;
	}

	public T0 get0() {
		return _0;
	}

	public T1 get1() {
		return _1;
	}

	@Override
	public int hashCode() {
		return Objects.hash(_0, _1);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Tuple2 other = (Tuple2) obj;
		return Objects.equals(_0, other._0) && Objects.equals(_1, other._1);
	}

	@Override
	public String toString() {
		return "Tuple2 [_0=" + _0 + ", _1=" + _1 + "]";
	}
	
}
