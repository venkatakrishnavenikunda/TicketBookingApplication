package com.example.demo.bookings.entity;

import com.example.demo.show.entity.Show;
import com.example.demo.theatre.entity.Seat;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@Table(indexes = {@Index(name = "idx_booking_member", columnList = "memberId")})

public class Booking {


    public enum BookingStatus {
        INITIATED,
        PAYMENT_PENDING,
        CONFIRMED,
        CANCELLED,
        EXPIRED
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String bookingRef;

    private String memberId;

    @Version
    private Long version;   // OPTIMISTIC LOCK

    @ManyToOne(fetch=FetchType.LAZY, optional = false)
    @JoinColumn(name="show_id")
    private Show show;


    @ManyToMany
    @JoinTable(
            name = "booking_seats",
            joinColumns = @JoinColumn(name = "booking_id"),
            inverseJoinColumns = @JoinColumn(name = "seat_id")
    )
    private List<Seat> seats;

    @Column(nullable = false)
    private BigDecimal totalAmount;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private BookingStatus status;

    private String paymentId;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    private LocalDateTime confirmedAt;
    private LocalDateTime cancelledAt;

}
