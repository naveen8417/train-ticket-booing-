package com.cloudbess.ticketbooking.Service;

import com.cloudbess.ticketbooking.model.Model;
import com.cloudbess.ticketbooking.model.Model.Ticket;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class BookingService {
    private Map<String, Model.Ticket> tickets = new HashMap<>();
    private Queue<String> sectionASeats = new LinkedList<>(Arrays.asList("A1", "A2", "A3"));
    private Queue<String> sectionBSeats = new LinkedList<>(Arrays.asList("B1", "B2", "B3"));

    public Ticket purchaseTicket(Model.User user) {
        String seat = allocateSeat();
        if (seat == null) {
            return null; // No seats available
        }
        Ticket ticket = new Ticket();
        ticket.setUser(user);
        ticket.setSeat(seat);
        tickets.put(user.getEmail(), ticket);
        return ticket;
    }

    public Ticket getTicket(String email) {
        return tickets.get(email);
    }

    public List<Ticket> getUsersBySection(String section) {
        List<Ticket> usersInSection = new ArrayList<>();
        for (Ticket ticket : tickets.values()) {
            if (ticket.getSeat().startsWith(section)) {
                usersInSection.add(ticket);
            }
        }
        return usersInSection;
    }

    public boolean removeUser(String email) {
        return tickets.remove(email) != null;
    }

    public boolean modifySeat(String email, String newSection) {
        Ticket ticket = tickets.get(email);
        if (ticket != null) {
            String newSeat = allocateSeat(newSection);
            if (newSeat != null) {
                ticket.setSeat(newSeat);
                return true;
            }
        }
        return false;
    }

    private String allocateSeat() {
        if (!sectionASeats.isEmpty()) {
            return sectionASeats.poll();
        } else if (!sectionBSeats.isEmpty()) {
            return sectionBSeats.poll();
        } else {
            return null;
        }
    }

    private String allocateSeat(String section) {
        if (section.equals("A") && !sectionASeats.isEmpty()) {
            return sectionASeats.poll();
        } else if (section.equals("B") && !sectionBSeats.isEmpty()) {
            return sectionBSeats.poll();
        }
        return null;
    }
}
