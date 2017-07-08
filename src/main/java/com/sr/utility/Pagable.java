package com.sr.utility;

public class Pagable<T> extends Paging{
	private T t;
	public T getT() {
		return t;
	}
	public void setT(T t) {
		this.t = t;
	}
}
