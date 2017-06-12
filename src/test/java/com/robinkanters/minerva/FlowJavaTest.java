package com.robinkanters.minerva;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import org.junit.Test;

import static com.robinkanters.minerva.Flow.flow;
import static com.robinkanters.minerva.TestComponent.test;
import static com.robinkanters.minerva.component.MapComponent.map;
import static org.junit.Assert.assertEquals;

public class FlowJavaTest {
    @Test
    public void flowWithTestComponent() throws Exception {
        Flow<String> flow = flow("name", new Function1<Flow<String>, Unit>() {
            public Unit invoke(Flow<String> stringFlow) {
                test(stringFlow, "Foo");

                return null;
            }
        });

        assertEquals("Foo", flow.invoke("Bar"));
    }

    @Test
    public void flowWithMapComponent() throws Exception {
        Flow<String> flow = flow("name", new Function1<Flow<String>, Unit>() {
            public Unit invoke(Flow<String> stringFlow) {
                map(stringFlow, new Function1<String, String>() {
                    public String invoke(String s) {
                        return s.replace("B", "F").replace("ar", "oo");
                    }
                });

                return null;
            }
        });

        assertEquals("Foo", flow.invoke("Bar"));
    }
}
