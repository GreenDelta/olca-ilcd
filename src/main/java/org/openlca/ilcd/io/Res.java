package org.openlca.ilcd.io;

/**
 * Wraps a result value. It is like `Optional` but with an additional error
 * state, this it can have a value, error, or nothing (is empty).
 */
public record Res<T>(T value, String error) {

	public static final Res<Void> VOID = Res.of(null);

	public static <T> Res<T> of(T value) {
		return new Res<>(value, null);
	}

	public static <T> Res<T> error(String message) {
		return new Res<>(null, message);
	}

	public static <T> Res<T> error(String message, Throwable e) {
		return e != null
			? error(message + ": " + e.getMessage())
			: error(message);
	}

	public boolean hasValue() {
		return value != null;
	}

	public boolean hasError() {
		return error != null;
	}

	@SuppressWarnings("unchecked")
	public <E> Res<E> castError() {
		if (error != null && value == null)
			return (Res<E>) this;
		return error != null
			? error(error)
			: error("no error message");
	}

	/**
	 * Packs the given error message on top of the current error and
	 * returns that combined error.
	 */
	public <E> Res<E> packError(String outerErr) {
		return error != null
			? error(outerErr + "\n\t" + error)
			: error(outerErr);
	}

	public T orElseThrow() {
		if (error != null)
			throw new IllegalStateException("result has an error: " + error);
		if (this == VOID)
			return null;
		if (value == null)
			throw new NullPointerException("result has a null value");
		return value;
	}

}
