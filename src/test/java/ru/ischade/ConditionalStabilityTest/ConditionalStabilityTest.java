package ru.ischade.ConditionalStabilityTest;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

class ConditionalStabilityTest {

    // Добавьте аннотацию, которая будет запускать тест 5 раз.     
    // При каждом запуске должно отображаться следующее название: Тест номерПовторения из максимальноеКоличествоПовторений
    @RepeatedTest(5)
    void testConditionalStability(RepetitionInfo repetitionInfo) {
        boolean result = runOperation();
        System.out.println("Запуск теста " + repetitionInfo.getCurrentRepetition() +
                " из " + repetitionInfo.getTotalRepetitions());

        if (5 == repetitionInfo.getCurrentRepetition()) {
            // Строгая проверка на последнем повторении            
            assertTrue(result, "Ожидается успешный результат на последнем повторении.");
        } else {
            // Мягкая проверка на всех остальных повторениях            
            assertFalse(result, "На этом повторении допустим произвольный результат.");
        }
    }

    @ValueSource(strings = {"123", "321"})
    boolean runOperation() {
        return Math.random() > 0.5; // Успешный результат в 50% случаев    
    }
}