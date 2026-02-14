package ru.ischade.legacy;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BookingServiceTest {

    private BookingService bookingService;
    private Booking booking;

    // Добавьте корректную аннотацию
    @BeforeEach
    void initializeService() {
        bookingService = new BookingService();
        System.out.println("BookingService инициализирован.");
    }

    // Добавьте корректную аннотацию
    @AfterEach
    void logOnServiceCleanUp() {
        System.out.println("BookingService завершил работу.");
    }

    // Добавьте корректную аннотацию
    @Nested
    class WhenBookingIsCreated {

        // Добавьте корректную аннотацию
        @BeforeEach
        void initializeBooking() {
            booking = bookingService.createBooking();
            System.out.println("Бронирование создано с начальным статусом.");
        }

        // Добавьте корректную аннотацию
        @AfterEach
        void logOnTheCleanup() {
            System.out.println("Завершена работа с созданным бронированием.");
        }

        // Добавьте корректную аннотацию
        @Test
        void shouldHaveCreatedStatus() {
            assertEquals("CREATED", booking.getStatus());
        }

        // Добавьте корректную аннотацию
        @Test
        void shouldRemainCreatedStatusAfterEdit() {
            bookingService.editBooking(booking);
            assertEquals("CREATED", booking.getStatus());
        }
    }

    // Добавьте корректную аннотацию
    @Nested
    class WhenBookingIsConfirmed {

        // Добавьте корректную аннотацию
        @BeforeEach
        void confirmBooking() {
            booking = bookingService.createBooking();
            bookingService.confirmBooking(booking);
            System.out.println("Бронирование подтверждено.");
        }

        // Добавьте корректную аннотацию
        @AfterEach
        void cleanupConfirmedBooking() {
            System.out.println("Завершена работа с подтвержденным бронированием.");
        }

        // Добавьте корректную аннотацию
        @Test
        void shouldHaveConfirmedStatus() {
            assertEquals("CONFIRMED", booking.getStatus());
        }

        // Добавьте корректную аннотацию
        @Test
        void shouldChangeStatusToCanceledAfterCancellation() {
            bookingService.cancelBooking(booking);
            assertEquals("CANCELED", booking.getStatus());
        }
    }
}

class BookingService {

    public Booking createBooking() {
        return new Booking("CREATED");
    }

    public void editBooking(Booking booking) {
        System.out.println("Обновление бронирования: " + booking);
    }

    public void confirmBooking(Booking booking) {
        booking.setStatus("CONFIRMED");
    }

    public void cancelBooking(Booking booking) {
        booking.setStatus("CANCELED");
    }
}

class Booking {
    private String status;

    public Booking(String status) {
        this.status = status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}