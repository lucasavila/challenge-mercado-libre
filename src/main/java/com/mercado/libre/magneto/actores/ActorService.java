package com.mercado.libre.magneto.actores;

import java.util.Map;
import groovyx.gpars.actor.Actor;
public abstract class ActorService<T> {

    Class <T> actorClass;
    Actor actor;

    public ActorService(Class<T> actorClass) {
        this.actorClass = actorClass;
    }

    protected Actor getCurrentActor() {

       if (actor == null || !actor.isActive()) {

           try {
               actor = (Actor) actorClass.newInstance();
           } catch (InstantiationException e) {

           } catch (IllegalAccessException e) {

           }
           actor.silentStart();
        }

        return actor;
    }

    public void sendMessage(Map message) {
        Actor actor = this.getCurrentActor();
        actor.send(message);
    }

}
