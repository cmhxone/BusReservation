package com.example.busreservation.repository;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class ReservationRepository {
	
	private ArrayList<String> reservation = new ArrayList<>();
	
	public void addReservation(String routeno) {
		if (!reservation.contains(routeno)) {
			reservation.add(routeno);
		}
	}
	
	public void removeReservation(String routeno) {
		reservation.remove(routeno);
	}
	
	public ArrayList<String> getReservation() {
		return reservation;
	}
}
