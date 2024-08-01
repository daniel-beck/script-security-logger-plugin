package io.jenkins.plugins.script_security_logger;

import edu.umd.cs.findbugs.annotations.NonNull;
import hudson.Extension;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jenkinsci.plugins.scriptsecurity.sandbox.Whitelist;

@Extension(ordinal = Integer.MAX_VALUE)
public class LoggingWhitelist extends Whitelist {
    private static final Logger LOGGER = Logger.getLogger(LoggingWhitelist.class.getName());

    @Override
    public boolean permitsMethod(@NonNull Method method, @NonNull Object receiver, @NonNull Object[] args) {
        LOGGER.log(Level.FINE, () -> "Method: " + method + " on object: " + receiver + " with args: " + Arrays.asList(args));
        return false;
    }

    @Override
    public boolean permitsConstructor(@NonNull Constructor<?> constructor, @NonNull Object[] args) {
        LOGGER.log(Level.FINE, () -> "Constructor: " + constructor + " with args: " + Arrays.asList(args));
        return false;
    }

    @Override
    public boolean permitsStaticMethod(@NonNull Method method, @NonNull Object[] args) {
        LOGGER.log(Level.FINE, () -> "Static method: " + method + " with args: " + Arrays.asList(args));
        return false;
    }

    @Override
    public boolean permitsFieldGet(@NonNull Field field, @NonNull Object receiver) {
        LOGGER.log(Level.FINE, () -> "Get field: " + field + " on object: " + receiver);
        return false;
    }

    @Override
    public boolean permitsFieldSet(@NonNull Field field, @NonNull Object receiver, Object value) {
        LOGGER.log(Level.FINE, () -> "Set field: " + field + " to value: " + value + " on object: " + receiver);
        return false;
    }

    @Override
    public boolean permitsStaticFieldGet(@NonNull Field field) {
        LOGGER.log(Level.FINE, () -> "Get static field: " + field);
        return false;
    }

    @Override
    public boolean permitsStaticFieldSet(@NonNull Field field, Object value) {
        LOGGER.log(Level.FINE, () -> "Set static field: " + field + " to value: " + value);
        return false;
    }
}
