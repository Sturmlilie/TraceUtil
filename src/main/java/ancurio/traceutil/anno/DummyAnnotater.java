package ancurio.traceutil.anno;

public class DummyAnnotater implements Annotater {
	public static boolean isAvailable() {
		return true;
	}
	
	public DummyAnnotater() {
	}
	
	public void appendPrefix(String prefix) {
	}
	
	public void push(String scope) {
	}
	
	public void pop() {
	}
	
	public void swap(String scope) {
	}
	
	public void insert(String message) {
	}
}
