package edu.ufsc;

import java.util.Set;

import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.OWLAnnotation;
import org.semanticweb.owlapi.model.OWLAnnotationProperty;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLEntity;
import org.semanticweb.owlapi.model.OWLOntology;

public class CommentaryExtractor {

	OWLOntology ontology;

	public CommentaryExtractor(OWLOntology ontology) {
		this.ontology = ontology;
	}

	public String extractCommentary(OWLEntity entity) {
		OWLDataFactory factory = OWLManager.getOWLDataFactory();
		OWLAnnotationProperty commentProperty = factory.getRDFSComment();
		Set<OWLAnnotation> annotations = entity.getAnnotations(ontology, commentProperty);
		OWLAnnotation annotation = annotations.iterator().next();
		return annotation.getValue().toString().toLowerCase().replace("\"", "");
	}
}
