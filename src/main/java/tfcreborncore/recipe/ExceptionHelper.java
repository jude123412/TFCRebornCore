package tfcreborncore.recipe;

import net.minecraft.item.ItemStack;

public class ExceptionHelper {

    /**
     * Ensures that the given {@link ItemStack} is non‑null and not empty.
     * <p>
     * This helper is used to validate recipe inputs and outputs before
     * registration. If the stack is {@code null} or {@link ItemStack#isEmpty()},
     * an {@link IllegalArgumentException} is thrown with the provided message.
     *
     * @param item    The item stack to validate.
     * @param message The exception message to use if validation fails.
     */
    public static void requireNonEmpty(ItemStack item, String message) {
        if (item == null || item.isEmpty())
            throw new IllegalArgumentException(message);
    }

    /**
     * Ensures that the given integer value is not zero.
     * <p>
     * This helper is used for validating numeric parameters such as durations,
     * temperatures, or counts where zero is considered invalid. If the value is
     * zero, an {@link IllegalArgumentException} is thrown with the provided
     * message.
     *
     * @param number  The number to validate.
     * @param message The exception message to use if validation fails.
     */
    public static void requireNonZero(int number, String message) {
        if (number == 0)
            throw new IllegalArgumentException(message);
    }
}
