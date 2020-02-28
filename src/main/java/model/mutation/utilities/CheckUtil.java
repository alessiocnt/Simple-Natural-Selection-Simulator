package model.mutation.utilities;

import java.util.function.Supplier;

/**
 * Class for check predicate and eventually throw exception.
 */
public final class CheckUtil {

    private CheckUtil() {
    }

    /**
     * @param supplier
     * Supplier of the boolean value.
     * @param exception
     * Exception to throw if the supplier carry false.
     */
    public static void check(final Supplier<Boolean> supplier, final RuntimeException exception) {
        if (!supplier.get()) {
            throw exception;
        }
    }
}
