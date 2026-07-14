package dev.worldgen.datapatched.impl.event;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class DatapatchedEvent<T> {
	protected Function<List<T>, T> invoker;
	protected List<T> listeners = new ArrayList<>();
	
	public DatapatchedEvent(Function<List<T>, T> invoker) {
		this.invoker = invoker;
	}
	
	public void register(T callback) {
		this.listeners.add(callback);
	}
	
	public T invoker() {
		return this.invoker.apply(this.listeners);
	}
}
