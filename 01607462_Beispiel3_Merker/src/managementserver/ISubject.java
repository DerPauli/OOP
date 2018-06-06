package managementserver;

import cashregister.IObserver;

public interface ISubject<TYPE> {
	TYPE getChanges();
	boolean register(IObserver obs);
	boolean unregister(IObserver obs);
}
