package de.claudioaltamura.jetty.jersey.superheroes.resource.v1;

import static org.junit.jupiter.api.Assertions.fail;

import javax.ws.rs.core.Application;

import org.glassfish.jersey.test.JerseyTest;
import org.glassfish.jersey.test.TestProperties;
import org.junit.jupiter.api.Test;

import de.claudioaltamura.jetty.jersey.superheroes.SuperHeroesApp;

class HeroResourceTest extends JerseyTest {

    @Override
    protected Application configure() {
        enable(TestProperties.LOG_TRAFFIC);
        enable(TestProperties.DUMP_ENTITY);

        //return new SuperHeroesApp().property(ServerProperties.BV_SEND_ERROR_IN_RESPONSE, true);
        return new SuperHeroesApp();
    }
//    @Override
//  protected URI getBaseUri() {
//  final UriBuilder baseUriBuilder = UriBuilder.fromUri(super.getBaseUri()).path("bean-validation-webapp");
//  final boolean externalFactoryInUse = getTestContainerFactory() instanceof ExternalTestContainerFactory;
//  return externalFactoryInUse ? baseUriBuilder.path("api").build() : baseUriBuilder.build();
//}
//
//@Test
//public void testAddContact() throws Exception {
//  final WebTarget target = target()
//          .path("contact");
//  final Response response = target.request(MediaType.APPLICATION_JSON_TYPE)
//          .post(Entity.entity(CARD_1, MediaType.APPLICATION_JSON_TYPE));
//
//  final ContactCard contactCard = response.readEntity(ContactCard.class);
//
//  assertEquals(200, response.getStatus());
//  assertNotNull(contactCard.getId());
//
//  final Response invalidResponse = target.request(MediaType.APPLICATION_JSON_TYPE)
//          .post(Entity.entity(CARD_1, MediaType.APPLICATION_JSON_TYPE));
//  assertEquals(500, invalidResponse.getStatus());
//  assertTrue(getValidationMessageTemplates(invalidResponse).contains("{contact.already.exist}"));
//
//  assertEquals(200, target.path("" + contactCard.getId()).request(MediaType.APPLICATION_JSON_TYPE).delete().getStatus());
//}


//    @Override
//    protected void configureClient(final ClientConfig config) {
//        super.configureClient(config);
//
//        config.register(MoxyJsonFeature.class);
//        // Turn off BV otherwise the entities on client would be validated as well.
//        config.register(new MoxyJsonConfig()
//                .property(MarshallerProperties.BEAN_VALIDATION_MODE, BeanValidationMode.NONE)
//                .resolver());
//    }

//    @Override
//    protected URI getBaseUri() {
//        final UriBuilder baseUriBuilder = UriBuilder.fromUri(super.getBaseUri()).path("bean-validation-webapp");
//        final boolean externalFactoryInUse = getTestContainerFactory() instanceof ExternalTestContainerFactory;
//        return externalFactoryInUse ? baseUriBuilder.path("api").build() : baseUriBuilder.build();
//    }
//
//    @Test
//    public void testAddContact() throws Exception {
//        final WebTarget target = target()
//                .path("contact");
//        final Response response = target.request(MediaType.APPLICATION_JSON_TYPE)
//                .post(Entity.entity(CARD_1, MediaType.APPLICATION_JSON_TYPE));
//
//        final ContactCard contactCard = response.readEntity(ContactCard.class);
//
//        assertEquals(200, response.getStatus());
//        assertNotNull(contactCard.getId());
//
//        final Response invalidResponse = target.request(MediaType.APPLICATION_JSON_TYPE)
//                .post(Entity.entity(CARD_1, MediaType.APPLICATION_JSON_TYPE));
//        assertEquals(500, invalidResponse.getStatus());
//        assertTrue(getValidationMessageTemplates(invalidResponse).contains("{contact.already.exist}"));
//
//        assertEquals(200, target.path("" + contactCard.getId()).request(MediaType.APPLICATION_JSON_TYPE).delete().getStatus());
//    }
	
	
	@Test
	void testFindall() {
		fail("Not yet implemented");
	}

	@Test
	void testFind() {
		fail("Not yet implemented");
	}

	@Test
	void testCreate() {
		fail("Not yet implemented");
	}

	@Test
	void testPut() {
		fail("Not yet implemented");
	}

	@Test
	void testPatch() {
		fail("Not yet implemented");
	}

	@Test
	void testDelete() {
		fail("Not yet implemented");
	}

}
