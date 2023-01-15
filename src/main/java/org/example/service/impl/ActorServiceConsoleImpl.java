package org.example.service.impl;

import org.example.entity.Actor;
import org.example.repository.ActorRepository;
import org.example.service.ActorService;

import java.sql.Date;
import java.util.Scanner;

public class ActorServiceConsoleImpl implements ActorService {

    private final ActorRepository actorRepository;

    public ActorServiceConsoleImpl(ActorRepository actorRepository) {
        this.actorRepository = actorRepository;
    }

    @Override
    public Actor read() {
        Scanner keyBoard = new Scanner(System.in);
        System.out.println("Insert the First Name of the actor:");
        String firstName = keyBoard.nextLine().trim();

        System.out.println("Insert the Last Name of the actor:");
        String lastName = keyBoard.nextLine().trim();

        System.out.println("Insert the Date of the actor in format yyyy-mm-dd:");
        Date birthDate = Date.valueOf(keyBoard.nextLine());

        return new Actor(firstName,lastName,birthDate);
    }

    @Override
    public Actor add() {
        return actorRepository.create(read());
    }

    @Override
    public void displayAll() {
        System.out.println(actorRepository.findAll());
    }
}
