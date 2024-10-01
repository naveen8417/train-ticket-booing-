package com.cloudbess.ticketbooking.Controller;

import com.cloudbess.ticketbooking.Service.BookingService;
import com.cloudbess.ticketbooking.model.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/booking")
public class HomeController {

    @Autowired
    private BookingService bookingService;

    @PostMapping("/purchase")
    public ResponseEntity<Model.Ticket> purchaseTicket(@RequestBody Model.User user) {
        Model.Ticket ticket = bookingService.purchaseTicket(user);
        if (ticket == null) {
            return ResponseEntity.status(400).build();
        }
        return ResponseEntity.ok(ticket);
    }

    @GetMapping("/ticket/{email}")
    public ResponseEntity<Model.Ticket> getTicket(@PathVariable String email) {
        Model.Ticket ticket = bookingService.getTicket(email);
        if (ticket == null) {
            return ResponseEntity.status(404).build();
        }
        return ResponseEntity.ok(ticket);
    }

    @GetMapping("/users/{section}")
    public ResponseEntity<List<Model.Ticket>> getUsersBySection(@PathVariable String section) {
        return ResponseEntity.ok(bookingService.getUsersBySection(section));
    }

    @DeleteMapping("/remove/{email}")
    public ResponseEntity<Void> removeUser(@PathVariable String email) {
        if (bookingService.removeUser(email)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.status(404).build();
        }
    }

    @PutMapping("/modify/{email}")
    public ResponseEntity<Void> modifySeat(@PathVariable String email, @RequestParam String newSection) {
        if (bookingService.modifySeat(email, newSection)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(400).build();
        }
    }
}
