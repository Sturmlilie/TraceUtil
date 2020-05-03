package ancurio.traceutil.anno;

// Some basic sanity is nice
public abstract class AbstractAnnotater implements Annotater {
	// Technically gated by GL_MAX_DEBUG_GROUP_STACK_DEPTH
	protected static final int MAX_DEPTH = 16;
	protected int currentDepth = 0;
	private String fullPrefix;
	
	protected AbstractAnnotater(String namespace) {
		fullPrefix = namespace + ":";
	}
	
	public void appendPrefix(String prefix) {
		fullPrefix += prefix + "/";
	}
	
	public void push(String scope) {
		currentDepth += 1;
		if (currentDepth > MAX_DEPTH) {
			throw new RuntimeException("Stack overflow");
		}
	}
	
	public void pop() {
		currentDepth -= 1;
		if (currentDepth < 0) {
			throw new RuntimeException("Stack underflow");
		}
	}
	
	public void swap(String scope) {
		pop();
		push(scope);
	}
	
	protected String prefix(String str) {
		return fullPrefix + str;
	}
}
