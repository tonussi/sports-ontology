package edu.ufsc.ontology.impl;

import edu.ufsc.ontology.*;


import java.net.URI;
import java.util.Collection;
import javax.xml.datatype.XMLGregorianCalendar;

import org.protege.owl.codegeneration.WrappedIndividual;
import org.protege.owl.codegeneration.impl.WrappedIndividualImpl;

import org.protege.owl.codegeneration.inference.CodeGenerationInference;

import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLOntology;


/**
 * Generated by Protege (http://protege.stanford.edu).<br>
 * Source Class: DefaultSport <br>
 * @version generated on Sun May 07 17:49:55 BRT 2017 by senhorinha
 */
public class DefaultSport extends WrappedIndividualImpl implements Sport {

    public DefaultSport(CodeGenerationInference inference, IRI iri) {
        super(inference, iri);
    }





    /* ***************************************************
     * Object Property http://www.co-ode.org/ontologies/pizza/pizza.owl#hasEquipament
     */
     
    public Collection<? extends Equipament> getHasEquipament() {
        return getDelegate().getPropertyValues(getOwlIndividual(),
                                               Vocabulary.OBJECT_PROPERTY_HASEQUIPAMENT,
                                               DefaultEquipament.class);
    }

    public boolean hasHasEquipament() {
	   return !getHasEquipament().isEmpty();
    }

    public void addHasEquipament(Equipament newHasEquipament) {
        getDelegate().addPropertyValue(getOwlIndividual(),
                                       Vocabulary.OBJECT_PROPERTY_HASEQUIPAMENT,
                                       newHasEquipament);
    }

    public void removeHasEquipament(Equipament oldHasEquipament) {
        getDelegate().removePropertyValue(getOwlIndividual(),
                                          Vocabulary.OBJECT_PROPERTY_HASEQUIPAMENT,
                                          oldHasEquipament);
    }


    /* ***************************************************
     * Object Property http://www.co-ode.org/ontologies/pizza/pizza.owl#hasFamous
     */
     
    public Collection<? extends WrappedIndividual> getHasFamous() {
        return getDelegate().getPropertyValues(getOwlIndividual(),
                                               Vocabulary.OBJECT_PROPERTY_HASFAMOUS,
                                               WrappedIndividualImpl.class);
    }

    public boolean hasHasFamous() {
	   return !getHasFamous().isEmpty();
    }

    public void addHasFamous(WrappedIndividual newHasFamous) {
        getDelegate().addPropertyValue(getOwlIndividual(),
                                       Vocabulary.OBJECT_PROPERTY_HASFAMOUS,
                                       newHasFamous);
    }

    public void removeHasFamous(WrappedIndividual oldHasFamous) {
        getDelegate().removePropertyValue(getOwlIndividual(),
                                          Vocabulary.OBJECT_PROPERTY_HASFAMOUS,
                                          oldHasFamous);
    }


    /* ***************************************************
     * Object Property http://www.co-ode.org/ontologies/pizza/pizza.owl#isPlayedBy
     */
     
    public Collection<? extends Player> getIsPlayedBy() {
        return getDelegate().getPropertyValues(getOwlIndividual(),
                                               Vocabulary.OBJECT_PROPERTY_ISPLAYEDBY,
                                               DefaultPlayer.class);
    }

    public boolean hasIsPlayedBy() {
	   return !getIsPlayedBy().isEmpty();
    }

    public void addIsPlayedBy(Player newIsPlayedBy) {
        getDelegate().addPropertyValue(getOwlIndividual(),
                                       Vocabulary.OBJECT_PROPERTY_ISPLAYEDBY,
                                       newIsPlayedBy);
    }

    public void removeIsPlayedBy(Player oldIsPlayedBy) {
        getDelegate().removePropertyValue(getOwlIndividual(),
                                          Vocabulary.OBJECT_PROPERTY_ISPLAYEDBY,
                                          oldIsPlayedBy);
    }


    /* ***************************************************
     * Object Property http://www.co-ode.org/ontologies/pizza/pizza.owl#isPlayerOf
     */
     
    public Collection<? extends Player> getIsPlayerOf() {
        return getDelegate().getPropertyValues(getOwlIndividual(),
                                               Vocabulary.OBJECT_PROPERTY_ISPLAYEROF,
                                               DefaultPlayer.class);
    }

    public boolean hasIsPlayerOf() {
	   return !getIsPlayerOf().isEmpty();
    }

    public void addIsPlayerOf(Player newIsPlayerOf) {
        getDelegate().addPropertyValue(getOwlIndividual(),
                                       Vocabulary.OBJECT_PROPERTY_ISPLAYEROF,
                                       newIsPlayerOf);
    }

    public void removeIsPlayerOf(Player oldIsPlayerOf) {
        getDelegate().removePropertyValue(getOwlIndividual(),
                                          Vocabulary.OBJECT_PROPERTY_ISPLAYEROF,
                                          oldIsPlayerOf);
    }


    /* ***************************************************
     * Object Property urn:absolute:sport-ontology#isNotPlayedIn
     */
     
    public Collection<? extends Ground> getIsNotPlayedIn() {
        return getDelegate().getPropertyValues(getOwlIndividual(),
                                               Vocabulary.OBJECT_PROPERTY_ISNOTPLAYEDIN,
                                               DefaultGround.class);
    }

    public boolean hasIsNotPlayedIn() {
	   return !getIsNotPlayedIn().isEmpty();
    }

    public void addIsNotPlayedIn(Ground newIsNotPlayedIn) {
        getDelegate().addPropertyValue(getOwlIndividual(),
                                       Vocabulary.OBJECT_PROPERTY_ISNOTPLAYEDIN,
                                       newIsNotPlayedIn);
    }

    public void removeIsNotPlayedIn(Ground oldIsNotPlayedIn) {
        getDelegate().removePropertyValue(getOwlIndividual(),
                                          Vocabulary.OBJECT_PROPERTY_ISNOTPLAYEDIN,
                                          oldIsNotPlayedIn);
    }


    /* ***************************************************
     * Object Property urn:absolute:sport-ontology#isPlayedIn
     */
     
    public Collection<? extends Ground> getIsPlayedIn() {
        return getDelegate().getPropertyValues(getOwlIndividual(),
                                               Vocabulary.OBJECT_PROPERTY_ISPLAYEDIN,
                                               DefaultGround.class);
    }

    public boolean hasIsPlayedIn() {
	   return !getIsPlayedIn().isEmpty();
    }

    public void addIsPlayedIn(Ground newIsPlayedIn) {
        getDelegate().addPropertyValue(getOwlIndividual(),
                                       Vocabulary.OBJECT_PROPERTY_ISPLAYEDIN,
                                       newIsPlayedIn);
    }

    public void removeIsPlayedIn(Ground oldIsPlayedIn) {
        getDelegate().removePropertyValue(getOwlIndividual(),
                                          Vocabulary.OBJECT_PROPERTY_ISPLAYEDIN,
                                          oldIsPlayedIn);
    }


    /* ***************************************************
     * Object Property urn:absolute:sport-ontology#notHasEquipament
     */
     
    public Collection<? extends Equipament> getNotHasEquipament() {
        return getDelegate().getPropertyValues(getOwlIndividual(),
                                               Vocabulary.OBJECT_PROPERTY_NOTHASEQUIPAMENT,
                                               DefaultEquipament.class);
    }

    public boolean hasNotHasEquipament() {
	   return !getNotHasEquipament().isEmpty();
    }

    public void addNotHasEquipament(Equipament newNotHasEquipament) {
        getDelegate().addPropertyValue(getOwlIndividual(),
                                       Vocabulary.OBJECT_PROPERTY_NOTHASEQUIPAMENT,
                                       newNotHasEquipament);
    }

    public void removeNotHasEquipament(Equipament oldNotHasEquipament) {
        getDelegate().removePropertyValue(getOwlIndividual(),
                                          Vocabulary.OBJECT_PROPERTY_NOTHASEQUIPAMENT,
                                          oldNotHasEquipament);
    }


}
