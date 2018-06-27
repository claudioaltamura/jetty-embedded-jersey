package de.claudioaltamura.jetty.jersey.superheroes.resource.v2;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.validation.constraints.NotNull;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;
import org.hibernate.validator.constraints.NotBlank;

import de.claudioaltamura.jetty.jersey.superheroes.entity.Hero;
import de.claudioaltamura.jetty.jersey.superheroes.service.HeroService;

@Path("v2/heroes")
public class HeroResource {

	private static final Logger LOG = Logger.getLogger(HeroResource.class);

	protected HeroService heroService;

	@PostConstruct
	public void init() {
		LOG.debug(this.getClass().getSimpleName() + " initialized");
		heroService = new HeroService();
	}

    @GET
    @NotNull
	@Produces(MediaType.APPLICATION_JSON)
	public List<Hero> searchForCity(
            @NotBlank(message = "{search.string.empty}") @QueryParam("q") String searchValue) {
		LOG.info("searchForCity=" + searchValue);
		
		return heroService.findByCity(searchValue);
    }

    /*
     * Other possible examples
     * 
     * sorting v2/heroes/?sort=name&desc=name
     *
     * pagination v2/heroes?offset=30&limit=15 #returns heroes 30 to 45
     * @DefaultValue would be a really nice feature here.
     * 
     * first, last, count and so on ...
     */
}
