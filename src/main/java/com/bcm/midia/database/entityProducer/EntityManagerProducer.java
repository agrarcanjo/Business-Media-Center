package com.bcm.midia.database.entityProducer;

import java.util.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * 
 * @author gotardo.soares
 * 
 *         Responsavel por criar o Producer do EntityFactory
 * 
 */
@ApplicationScoped
public class EntityManagerProducer {
	@Produces						
	@PersistenceContext(unitName = "bcmDS")
	EntityManager entityManager;

	@Produces
	public Logger produceLog(InjectionPoint injectionPoint) {
		return Logger.getLogger(injectionPoint.getMember().getDeclaringClass()
				.getName());
	}

}
