package net.crisps.cloud;

import net.crisps.framework.tac.starter.client.notify.NotifyManager;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;

public class NotifyTest extends AbstractTest {

    @Autowired
    NotifyManager notifyManager;

    @Test
    public void notifyTest() {
        notifyManager.sendMsg("error", new HashMap<>());
    }
}
