package edu.ufsc;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLClassExpression;
import org.semanticweb.owlapi.model.OWLEntity;
import org.semanticweb.owlapi.model.OWLIndividual;
import org.semanticweb.owlapi.model.OWLNamedIndividual;
import org.semanticweb.owlapi.model.OWLObjectProperty;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyManager;

import uk.ac.manchester.cs.owl.owlapi.OWLObjectExactCardinalityImpl;

public class Main {

	private static Scanner scanner;
	private static CommentaryExtractor commentaryExtractor;
	private static List<Question> maybeAnwsers;

	public static void main(String[] args) throws Exception {

		scanner = new Scanner(System.in);
		OWLOntologyManager manager = OWLManager.createOWLOntologyManager();
		String path = "sports.owl";
		OWLOntology ontology = manager.loadOntologyFromOntologyDocument(new File(path));
		commentaryExtractor = new CommentaryExtractor(ontology);

		List<OWLNamedIndividual> sports = new ArrayList<OWLNamedIndividual>(ontology.getIndividualsInSignature());
		HashMap<OWLIndividual, List<Question>> sportQuestions = buildQuestions(ontology, sports);
		List<Question> questions = separateQuestionsFromIndividuals(sportQuestions);
		Question currentQuestion = null;
		maybeAnwsers = new ArrayList<Question>();
		printWelcomeMessage();

		while (!questions.isEmpty() && questions.size() != maybeAnwsers.size() && sportQuestions.size() != 1) {
			currentQuestion = questions.get(0);
			Awnser anwser = ask(currentQuestion);
			sportQuestions = handleQuestion(currentQuestion, anwser, sportQuestions);
			questions = separateQuestionsFromIndividuals(sportQuestions);
			questions.removeAll(maybeAnwsers);
		}

		if (questions.isEmpty()) {
			System.out.println("Oooops, falhamos ao tentar ler sua mente.");
		} else if (sportQuestions.size() != 1) {
			Set<OWLIndividual> individuals = sportQuestions.keySet();
			List<String> individualsName = new ArrayList<>();
			for (OWLIndividual owlIndividual : individuals) {
				individualsName.add(commentaryExtractor.extractCommentary(owlIndividual.getSignature().iterator().next()));
			}
			printSportsDoubtGuessed(individualsName.toArray(new String[individualsName.size()]));
		} else if (sportQuestions.size() == 1) {
			OWLEntity sportIndividualEntity = sportQuestions.keySet().iterator().next().getSignature().iterator().next();
			printSportsGuessed(commentaryExtractor.extractCommentary(sportIndividualEntity));
		}
	}

	private static void printWelcomeMessage() {
		System.out
				.println("Bem-vindo ao adivinhador de esportes. Pense em algum dos esportes a seguir: tênis, natação, futebol ou ciclismo.");
		System.out.println("Pensou? Vamos começar!\n");

	}

	private static void printSportsDoubtGuessed(String[] sportNames) {
		System.out.println("\nFicamos em dúvida... Provávelmente você pensou em: ");
		System.err.print(String.join(", ", sportNames));
	}

	private static void printSportsGuessed(String sport) {
		System.out.println("\nAhãa, já sabemos seu esporte. Você pensou em ");
		System.err.print(sport);
	}

	private static HashMap<OWLIndividual, List<Question>> handleQuestion(Question question, Awnser anwser,
			HashMap<OWLIndividual, List<Question>> sportQuestions) {
		switch (anwser) {
		case YES:
			return removeQuestion(removeIndividuals(sportQuestions, question, false), question);
		case NO:
			return removeQuestion(removeIndividuals(sportQuestions, question, true), question);
		default:
			maybeAnwsers.add(question);
			return sportQuestions;
		}
	}

	private static HashMap<OWLIndividual, List<Question>> removeQuestion(HashMap<OWLIndividual, List<Question>> sportQuestions,
			Question question) {
		HashMap<OWLIndividual, List<Question>> newSportQuestions = new HashMap<>();
		for (OWLIndividual individual : sportQuestions.keySet()) {
			List<Question> questions = sportQuestions.get(individual);
			questions.remove(question);
			newSportQuestions.put(individual, questions);
		}

		return newSportQuestions;
	}

	private static HashMap<OWLIndividual, List<Question>> removeIndividuals(HashMap<OWLIndividual, List<Question>> sportQuestions,
			Question question, boolean withQuestion) {
		List<OWLIndividual> individualsToRemove = new ArrayList<OWLIndividual>();
		for (OWLIndividual individual : sportQuestions.keySet()) {
			if (sportQuestions.get(individual).contains(question) == withQuestion) {
				individualsToRemove.add(individual);
			}
		}

		sportQuestions.keySet().removeAll(individualsToRemove);
		return sportQuestions;
	}

	private static Awnser ask(Question question) {
		System.out.println(String.format("%s %s? %s", "Seu esporte", question.toString(), "s(im), n(ão), t(alvez)"));
		String anwser = scanner.nextLine();
		if (anwser.startsWith("s")) {
			return Awnser.YES;
		} else if (anwser.startsWith("n")) {
			return Awnser.NO;
		}
		return Awnser.MAYBE;
	}

	public static List<Question> separateQuestionsFromIndividuals(HashMap<OWLIndividual, List<Question>> sportQuestions) {
		List<Question> questions = new ArrayList<Question>();
		for (OWLIndividual owlIndividual : sportQuestions.keySet()) {
			questions.addAll(sportQuestions.get(owlIndividual));
		}

		Collections.shuffle(questions, new Random(System.nanoTime()));
		return questions;
	}

	public static HashMap<OWLIndividual, List<Question>> buildQuestions(OWLOntology ontology, List<OWLNamedIndividual> sports) {
		HashMap<OWLIndividual, List<Question>> sportQuestions = new HashMap<OWLIndividual, List<Question>>();
		for (OWLIndividual individual : sports) {
			Set<OWLClassExpression> expressions = individual.getTypes(ontology);
			List<Question> questions = new ArrayList<Question>();
			for (OWLClassExpression owlClassExpression : expressions) {
				if (owlClassExpression.getClassExpressionType().toString() == "Class") {
					continue;
				}
				Set<OWLEntity> signature = owlClassExpression.getSignature();
				OWLClass clazz = null;
				OWLObjectProperty property = null;
				Integer cardinality = null;
				for (OWLEntity owlEntity : signature) {
					if (owlEntity.getEntityType().toString() == "ObjectProperty") {
						property = owlEntity.getObjectPropertiesInSignature().iterator().next();
						if (property.toString().endsWith("isPlayedBy>")) {
							cardinality = ((OWLObjectExactCardinalityImpl) owlClassExpression).getCardinality();
						}
					} else {
						clazz = owlEntity.getClassesInSignature().iterator().next();
					}
				}

				questions.add(new Question(commentaryExtractor.extractCommentary(property), commentaryExtractor.extractCommentary(clazz),
						cardinality));
			}
			sportQuestions.put(individual, questions);
		}

		return sportQuestions;

	}

}
