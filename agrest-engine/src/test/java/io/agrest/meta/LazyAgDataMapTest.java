package io.agrest.meta;

import io.agrest.annotation.AgAttribute;
import io.agrest.annotation.AgId;
import io.agrest.annotation.AgRelationship;
import io.agrest.compiler.AgEntityCompiler;
import io.agrest.compiler.AnnotationsAgEntityCompiler;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class LazyAgDataMapTest {

	private static AgDataMap dataMap;

	@BeforeAll
	public static void before() {
		AgEntityCompiler compiler = new AnnotationsAgEntityCompiler(Collections.emptyMap());
		dataMap = new LazyAgDataMap(Collections.singletonList(compiler));
	}

	@Test
	public void testGetAgEntity_NoRelationships() {

		AgEntity<Tr> tr = dataMap.getEntity(Tr.class);
		assertNotNull(tr);
		assertEquals("Tr", tr.getName());
		assertSame(Tr.class, tr.getType());
		assertEquals("Tr", tr.getName());

		assertNotNull(tr.getAttribute("a"));
		assertNotNull(tr.getAttribute("b"));

		assertEquals(0, tr.getRelationships().size());
	}

	@Test
	public void testGetAgEntity_Relationships() {

		AgEntity<Ts> ts = dataMap.getEntity(Ts.class);
		assertNotNull(ts);
		assertEquals("Ts", ts.getName());
		assertSame(Ts.class, ts.getType());
		assertEquals("Ts", ts.getName());

		assertNotNull(ts.getAttribute("m"));
		assertNotNull(ts.getAttribute("n"));
		assertNotNull(ts.getRelationship("rtrs"));

		assertEquals(1, ts.getRelationships().size());
	}

	public static class Tr {

		@AgId
		public int getId() {
			throw new UnsupportedOperationException();
		}

		@AgAttribute
		public int getA() {
			throw new UnsupportedOperationException();
		}

		@AgAttribute
		public String getB() {
			throw new UnsupportedOperationException();
		}
	}

	public static class Ts {

		@AgId
		public int getId() {
			throw new UnsupportedOperationException();
		}

		@AgAttribute
		public String getN() {
			throw new UnsupportedOperationException();
		}

		@AgAttribute
		public String getM() {
			throw new UnsupportedOperationException();
		}

		@AgRelationship
		public List<Tr> getRtrs() {
			throw new UnsupportedOperationException();
		}
	}
}
