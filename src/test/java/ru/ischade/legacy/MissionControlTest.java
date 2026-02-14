package ru.ischade.legacy;

import org.jspecify.annotations.NonNull;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.DisabledOnOs;
import org.junit.jupiter.api.condition.EnabledIf;
import org.junit.jupiter.api.condition.OS;
import org.junit.jupiter.api.extension.ConditionEvaluationResult;
import org.junit.jupiter.api.extension.ExecutionCondition;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.ExtensionContext;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@ExtendWith(MissionTypeCondition.class)
@interface Mission {
}

public class MissionControlTest {

    @Test
    @EnabledIf("isTestEnvironment")
    void testOnlyInTestEnv() {
        System.out.println("Тест выполняется только в тестовой среде.");
    }

    @Test
    @DisabledOnOs(OS.WINDOWS)
    void notOnLinux() {
        System.out.println("Этот тест не выполняется на Windows.");
    }

    @Test
    @Mission
    void missionSpecificTest() {
        System.out.println("Тест выполняется только для конкретной миссии.");
    }

    private static boolean isTestEnvironment() {
        return "test".equals(System.getProperty("ENV"));
    }

    @Nested
    @DisplayName("Тесты о чем то")
    class WhenAccountIsApproved {

        @Test
        void shouldAllowTransactions() {
            System.out.println("Тест выполняется только для конкретной миссии.");
        }

        @Nested
        @DisplayName("name2")
        class nestedClass {
            @Test
            void shouldAllowTransactions() {
                System.out.println("Тест выполняется только для конкретной миссии.");
            }
        }
    }
}

class MissionTypeCondition implements ExecutionCondition {

    @Override
    public @NonNull ConditionEvaluationResult evaluateExecutionCondition(@NonNull ExtensionContext context) {
        boolean isMissionDay = java.time.LocalDate.now().getDayOfWeek().getValue() <= 5;
        return isMissionDay
                ? ConditionEvaluationResult.enabled("Тест включён для будних дней")
                : ConditionEvaluationResult.disabled("Тест отключён на выходные");
    }
}