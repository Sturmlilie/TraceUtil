package ancurio.traceutil.anno;

import ancurio.traceutil.anno.Annotater;

public class DummyAnnotater extends Annotater {
	public DummyAnnotater() {
		super("");
	}

	@Override
	public void push(String scope) {
	}

	@Override
	public void pop() {
	}

	@Override
	public void insert(String message) {
	}

	@Override
	public void swap(String scope) {
	}

	@Override
	public void labelObject(int id, Annotater.ObjectType type, String label) {
	}
}
