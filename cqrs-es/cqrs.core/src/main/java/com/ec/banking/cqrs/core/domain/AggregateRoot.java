package com.ec.banking.cqrs.core.domain;

import com.ec.banking.cqrs.core.events.BaseEvent;
import lombok.Getter;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author edisoncsi on 14/9/23
 * @project banking-account
 */
public abstract class AggregateRoot {
    @Getter
    protected String id;
    @Getter
    private int version = -1;

    private final List<BaseEvent> changes = new ArrayList<>();
    private final Logger logger = Logger.getLogger(AggregateRoot.class.getName());

    public void setVersion(int version){
        this.version = version;
    }

    public List<BaseEvent> getUncommitedChanges(){
        return this.changes;
    }

    public void markChangesAsCommitted(){
        this.changes.clear();
    }

    protected void applyChange(BaseEvent event, Boolean isNewEvent){
        try{
            var method = getClass().getDeclaredMethod("apply", event.getClass());
            method.setAccessible(true);
            method.invoke(this, event);
        }catch(NoSuchMethodException e){
            logger.log(Level.WARNING, MessageFormat.format("El metodo apply no fue encontrado para {0}", event.getClass().getName()));
        }catch(Exception e){
            logger.log(Level.SEVERE, "Errores aplicando el evento al aggregate", e);
        }finally{
            if(isNewEvent){
                changes.add(event);
            }
        }
    }

    public void raiseEvent(BaseEvent event){
        applyChange(event, true);
    }

    public void replayEvents(Iterable<BaseEvent> events){
        events.forEach(event -> applyChange(event, false));
    }
}
