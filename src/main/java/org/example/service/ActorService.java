package org.example.service;

import org.example.entity.Actor;

public interface ActorService extends GenericsService<Actor> {

    Actor read();
}
