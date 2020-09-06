package it.lastminute.sales.business.utility;

import java.util.Collection;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;

/**
 * A helper class for evaluating certain constraints. Any violation will thrown
 * a {@link RuntimeException}.
 */
public final class Constraint {

    /**
     * Private constructor, avoid instantiation.
     */
    private Constraint() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * Checks that the given collection is empty. If the collection is not empty
     * an {@link RuntimeException} specified is thrown.
     * 
     * @param <T>
     *            type of items in the collection
     * @param collection
     *            collection to check
     * @param message
     *            message used in the log entry
     * @param logger
     *            the logger
     * @param exception
     *            the exception to throw
     * 
     * @return the checked input
     */
    public static <T> Collection<T> isEmpty(final Collection<T> collection, final String message, final Logger logger,
            final RuntimeException exception) {
        if (collection != null && !collection.isEmpty()) {
            logger.error(message);
            throw exception;
        }

        return collection;
    }

    /**
     * Checks that the given boolean is false. If not an
     * {@link RuntimeException} specified is thrown.
     * 
     * @param b
     *            boolean to check
     * @param message
     *            message used in the log entry
     * @param logger
     *            the logger
     * @param exception
     *            the exception to throw
     * 
     * @return the checked boolean
     */
    public static boolean isFalse(final boolean b, final String message, final Logger logger,
            final RuntimeException exception) {
        if (b) {
            logger.error(message);
            throw exception;
        }

        return b;
    }

    /**
     * Checks that the given number is greater than a given threshold. If the
     * number is not greater than the threshold an {@link RuntimeException}
     * specified is thrown.
     * 
     * @param threshold
     *            the threshold
     * @param number
     *            the number to be checked
     * @param message
     *            message used in the log entry
     * @param logger
     *            the logger
     * @param exception
     *            the exception to throw
     * 
     * @return the checked input
     */
    public static long isGreaterThan(final long threshold, final long number, final String message, final Logger logger,
            final RuntimeException exception) {
        if (number <= threshold) {
            logger.error(message);
            throw exception;
        }

        return number;
    }

    /**
     * Checks that the given number is greater than, or equal to, a given
     * threshold. If the number is not greater than, or equal to, the threshold
     * an {@link RuntimeException} specified is thrown.
     * 
     * @param threshold
     *            the threshold
     * @param number
     *            the number to be checked
     * @param message
     *            message used in the log entry
     * @param logger
     *            the logger
     * @param exception
     *            the exception to throw
     * 
     * @return the checked input
     */
    public static long isGreaterThanOrEqual(final long threshold, final long number, final String message,
            final Logger logger, final RuntimeException exception) {
        if (number < threshold) {
            logger.error(message);
            throw exception;
        }

        return number;
    }

    /**
     * Checks that the given number is less than a given threshold. If the
     * number is not less than the threshold an {@link RuntimeException}
     * specified is thrown.
     * 
     * @param threshold
     *            the threshold
     * @param number
     *            the number to be checked
     * @param message
     *            message used in the log entry
     * @param logger
     *            the logger
     * @param exception
     *            the exception to throw
     * 
     * @return the checked input
     */
    public static long isLessThan(final long threshold, final long number, final String message, final Logger logger,
            final RuntimeException exception) {
        if (number >= threshold) {
            logger.error(message);
            throw exception;
        }

        return number;
    }

    /**
     * Checks that the given number is less than, or equal to, a given
     * threshold. If the number is not less than, or equal to, the threshold an
     * {@link RuntimeException} specified is thrown.
     * 
     * @param threshold
     *            the threshold
     * @param number
     *            the number to be checked
     * @param message
     *            message used in the log entry
     * @param logger
     *            the logger
     * @param exception
     *            the exception to throw
     * 
     * @return the checked input
     */
    public static long isLessThanOrEqual(final long threshold, final long number, final String message,
            final Logger logger, final RuntimeException exception) {
        if (number > threshold) {
            logger.error(message);
            throw exception;
        }

        return number;
    }

    /**
     * Checks that the given collection is not empty. If the collection is empty
     * an {@link RuntimeException} specified is thrown.
     * 
     * @param <T>
     *            type of items in the collection
     * @param collection
     *            collection to check
     * @param message
     *            message used in the log entry
     * @param logger
     *            the logger
     * @param exception
     *            the exception to throw
     * 
     * @return the checked input
     */
    public static <T> Collection<T> isNotEmpty(final Collection<T> collection, final String message,
            final Logger logger, final RuntimeException exception) {
        if (collection == null || collection.isEmpty()) {
            logger.error(message);
            throw exception;
        }

        return collection;
    }

    /**
     * Checks that the given array is not empty. If the array is empty an
     * {@link RuntimeException} specified is thrown.
     * 
     * @param <T>
     *            type of items in the array
     * @param array
     *            array to check
     * @param message
     *            message used in the log entry
     * @param logger
     *            the logger
     * @param exception
     *            the exception to throw
     * 
     * @return the checked input
     */
    public static <T> T[] isNotEmpty(final T[] array, final String message, final Logger logger,
            final RuntimeException exception) {
        if (array == null || array.length == 0) {
            logger.error(message);
            throw exception;
        }

        return array;
    }

    /**
     * Checks that the given byte array is not empty. If the array is empty an
     * {@link RuntimeException} specified is thrown.
     * 
     * @param array
     *            array to check
     * @param message
     *            message used in the log entry
     * @param logger
     *            the logger
     * @param exception
     *            the exception to throw
     * 
     * @return the checked input
     */
    public static byte[] isNotEmpty(final byte[] array, final String message, final Logger logger,
            final RuntimeException exception) {
        if (array == null || array.length == 0) {
            logger.error(message);
            throw exception;
        }

        return array;
    }

    /**
     * Checks that the given string is not empty. If the string is empty a
     * {@link RuntimeException} specified is thrown.
     * 
     * @param string
     *            string to check
     * @param message
     *            message used in the log entry
     * @param logger
     *            the logger
     * @param exception
     *            the exception to throw
     * 
     * @return the checked input
     */
    public static String isNotNullOrEmpty(final String string, final String message, final Logger logger,
            final RuntimeException exception) {
        if (StringUtils.isBlank(string)) {
            logger.error(message);
            throw exception;
        }

        return string;
    }

    /**
     * Checks that only one of the given string is not empty. If more than one
     * string is empty a {@link RuntimeException} specified is thrown.
     * 
     * @param firstString
     *            the string to check
     * @param secondString
     *            the string to check
     * @param message
     *            message used in the log entry
     * @param logger
     *            the logger
     * @param exception
     *            the exception to throw
     * 
     */
    public static void isOnlyOneNotEmpty(final String firstString, final String secondString, final String message,
            final Logger logger, final RuntimeException exception) {
        if (!StringUtils.isBlank(firstString) && !StringUtils.isBlank(secondString)) {
            logger.error(message);
            throw exception;
        }
    }

    /**
     * Checks that the given object is not null. If the object is null an
     * {@link RuntimeException} specified is thrown.
     * 
     * @param <T>
     *            object type
     * @param obj
     *            object to check
     * @param message
     *            message used in the log entry
     * @param logger
     *            the logger
     * @param exception
     *            the exception to throw
     * 
     * @return the checked input
     */
    public static <T> T isNotNull(final T obj, final String message, final Logger logger,
            final RuntimeException exception) {
        if (obj == null) {
            logger.error(message);
            throw exception;
        }

        return obj;
    }

    /**
     * Checks that the given object is null. If the object is not null an
     * {@link RuntimeException} specified is thrown.
     * 
     * @param <T>
     *            object type
     * @param obj
     *            object to check
     * @param message
     *            message used in the log entry
     * @param logger
     *            the logger
     * @param exception
     *            the exception to throw
     * 
     * @return the checked input
     */
    public static <T> T isNull(final T obj, final String message, final Logger logger,
            final RuntimeException exception) {
        if (obj != null) {
            logger.error(message);
            throw exception;
        }

        return obj;

    }

    /**
     * Checks that the given boolean is true. If not an {@link RuntimeException}
     * specified is thrown.
     * 
     * @param b
     *            boolean to check
     * @param message
     *            message used in the log entry
     * @param logger
     *            the logger
     * @param exception
     *            the exception to throw
     * 
     * @return the checked boolean
     */
    public static boolean isTrue(final boolean b, final String message, final Logger logger,
            final RuntimeException exception) {
        if (!b) {
            logger.error(message);
            throw exception;
        }

        return b;
    }

    /**
     * Checks that the array does not contain any null elements.
     * 
     * @param <T>
     *            type of elements in the array
     * @param array
     *            array to check
     * @param message
     *            message used in the log entry
     * @param logger
     *            the logger
     * @param exception
     *            the exception to throw
     * 
     * @return the given array
     */
    public static <T> T[] noNullItems(final T[] array, final String message, final Logger logger,
            final RuntimeException exception) {
        if (array == null) {
            logger.error(message);
            throw exception;
        }

        for (final T element : array) {
            if (element == null) {
                logger.error(message);
                throw exception;
            }
        }

        return array;
    }

    /**
     * Checks that the collection does not contain any null elements.
     *
     * @param <T>
     *            type of collection to inspect.
     * @param collection
     *            to check.
     * @param message
     *            message used in the log entry
     * @param logger
     *            the logger
     * @param exception
     *            the exception to throw
     *
     * @return the given array
     */
    public static <T extends Collection<?>> T noNullItems(final T collection, final String message, final Logger logger,
            final RuntimeException exception) {
        if (collection == null) {
            logger.error(message);
            throw exception;
        }

        for (final Object element : collection) {
            if (element == null) {
                logger.error(message);
                throw exception;
            }
        }

        return collection;
    }

    /**
     * Checks that the given number is in the exclusive range. If the number is
     * not in the range an {@link RuntimeException} specified is thrown.
     * 
     * @param lowerTheshold
     *            lower bound of the range
     * @param upperThreshold
     *            upper bound of the range
     * @param number
     *            number to check
     * @param message
     *            message used in the log entry
     * @param logger
     *            the logger
     * @param exception
     *            the exception to throw
     * 
     * @return the checked input
     */
    public static long numberInRangeExclusive(final long lowerTheshold, final long upperThreshold, final long number,
            final String message, final Logger logger, final RuntimeException exception) {
        if (number <= lowerTheshold || number >= upperThreshold) {
            logger.error(message);
            throw exception;
        }

        return number;
    }

    /**
     * Checks that the given number is in the inclusive range. If the number is
     * not in the range an {@link RuntimeException} specified is thrown.
     * 
     * @param lowerTheshold
     *            lower bound of the range
     * @param upperThreshold
     *            upper bound of the range
     * @param number
     *            number to check
     * @param message
     *            message used in the log entry
     * @param logger
     *            the logger
     * @param exception
     *            the exception to throw
     * 
     * @return the checked input
     */
    public static long numberInRangeInclusive(final long lowerTheshold, final long upperThreshold, final long number,
            final String message, final Logger logger, final RuntimeException exception) {
        if (number < lowerTheshold || number > upperThreshold) {
            logger.error(message);
            throw exception;
        }

        return number;
    }

    /**
     * Checks that not all string passed are null or empty.
     * 
     * @param message
     *            the message
     * @param logger
     *            the logger
     * @param exception
     *            the exception to throw
     * @param values
     *            the values to check
     */
    public static void areNotAllNullOrEmpty(final String message, final Logger logger, final RuntimeException exception,
            final String... values) {
        // se sono tutti null o empty errore (almeno uno valorizzato)
        boolean areAllBlank = true;
        for (String value : values) {
            if (!StringUtils.isBlank(value)) {
                areAllBlank = false;
                break;
            }
        }
        if (areAllBlank) {
            logger.error(message);
            throw exception;
        }
    }
}
