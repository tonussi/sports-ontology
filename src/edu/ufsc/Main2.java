package edu.ufsc;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import org.protege.owl.codegeneration.inference.SimpleInference;
import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLClassExpression;
import org.semanticweb.owlapi.model.OWLEntity;
import org.semanticweb.owlapi.model.OWLIndividual;
import org.semanticweb.owlapi.model.OWLNamedIndividual;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyManager;

public class Main2 {

	private static List<String> questions = new ArrayList<String>();
	private static List<String> possibleAnswers = new ArrayList<String>();

	public static void main(String[] args) throws Exception {

		OWLOntologyManager manager = OWLManager.createOWLOntologyManager();

		String path = "sports.owl";

		OWLOntology ontology = manager.loadOntologyFromOntologyDocument(new File(path));

		SimpleInference simpleInference = new SimpleInference(ontology);

		List<OWLClass> owlClasses = new ArrayList<OWLClass>(simpleInference.getOwlClasses());
		

		for (OWLClass owlClass : owlClasses) {

			Set<OWLClassExpression> eClasses = owlClass.getEquivalentClasses(ontology);

			List<OWLNamedIndividual> animaisCandidatos = new ArrayList<OWLNamedIndividual>(
					simpleInference.getIndividuals(owlClass));

			Map<OWLIndividual, String> individualsAsserts = new HashMap<OWLIndividual, String>();

			for (OWLIndividual individual : animaisCandidatos) {
				String axiom = ontology.getClassAssertionAxioms(individual).toString();
				System.out.println(axiom);
				individualsAsserts.put(individual, axiom);
			}
			for (OWLClassExpression eClass : eClasses) {
				System.out.println(eClass.getSignature());
			}
		}
	}
}
