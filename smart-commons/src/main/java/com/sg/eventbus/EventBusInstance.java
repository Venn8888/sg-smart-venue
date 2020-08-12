package com.sg.eventbus;

import com.google.common.eventbus.EventBus;
import com.sg.eventbus.listen.EventListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author dwf
 * @date 2019/9/11 20:16
 */

@Component
public class EventBusInstance {

  private final static EventBus INSTANCE = new EventBus();

  public static EventBus getInstance() {
    return INSTANCE;
  }

  @Autowired
  public EventBusInstance(List<EventListener> listeners) {
    listeners.forEach(EventBusInstance.getInstance()::register);
  }

}
