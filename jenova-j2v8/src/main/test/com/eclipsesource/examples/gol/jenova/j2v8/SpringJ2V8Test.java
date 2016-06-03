
package com.eclipsesource.examples.gol.jenova.j2v8;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@SuppressWarnings("resource")
public class SpringJ2V8Test {

	@Test
	public void shouldContainBeanNamedIHello() {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("/META-INF/spring/j2v8-snippets-applicationContext.xml", getClass());
		assertTrue(Arrays.asList(ctx.getBeanNamesForType(IHello.class)).contains("ihello"));
	}

    @Test
    public void implementingJavaInterfacesByScriptsGenerateSingleInteger() throws Exception {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("/META-INF/spring/j2v8-snippets-applicationContext.xml", getClass());

		IHello uut = ctx.getBean(IHello.class);

        int fourtyTwo = uut.generateSingleInteger();
        assertEquals(42, fourtyTwo);
    }
}
