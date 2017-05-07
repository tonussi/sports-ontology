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

public class Main {

	private static List<String> questions = new ArrayList<String>();
	private static List<String> possibleAnswers = new ArrayList<String>();

	public static void main(String[] args) throws Exception {

		buildQuestions();

		buildPossibleAnswers();

		Scanner scanner = new Scanner(System.in);

		try {
			System.out.println("\nBem vindo ao Animais Vertebrados!\n");

			OWLOntologyManager manager = OWLManager.createOWLOntologyManager();

			String path = "sports.owl";

			OWLOntology ontology = manager.loadOntologyFromOntologyDocument(new File(path));

			SimpleInference simpleInference = new SimpleInference(ontology);

			List<OWLClass> owlClasses = new ArrayList<OWLClass>(simpleInference.getOwlClasses());

			String[] classes = { "mamiferos", "peixes", "repteis", "anfibios", "aves" };

			String classeEscolhida = null;

			while (classeEscolhida == null) {
				for (String classe : classes) {
					String respostaUsuario = "";

					while (!possibleAnswers.contains(respostaUsuario)) {
						System.out.println("Seu animal pertence à classe dos " + classe + "? (s/n/naosei/talvez)");

						respostaUsuario = scanner.next();
					}

					if ("s".equals(respostaUsuario)) {
						classeEscolhida = classe;
						break;
					}
				}
			}

			for (OWLClass owlClass : owlClasses) {

				if (owlClass.toString().contains(classeEscolhida)) {

					Set<OWLClassExpression> eClasses = owlClass.getEquivalentClasses(ontology);

					List<OWLNamedIndividual> animaisCandidatos = new ArrayList<OWLNamedIndividual>(
							simpleInference.getIndividuals(owlClass));

					Map<OWLIndividual, String> individualsAsserts = new HashMap<OWLIndividual, String>();

					for (OWLIndividual individual : animaisCandidatos) {
						individualsAsserts.put(individual, ontology.getClassAssertionAxioms(individual).toString());
					}

					for (OWLClassExpression eClass : eClasses) {

						String respostaUsuario = "";

						while (!possibleAnswers.contains(respostaUsuario)) {
							Set<OWLEntity> entity = eClass.getSignature();
							String[] parts = entity.toString().split(",");
							String[] aux = parts[0].split("#");
							String part2 = aux[aux.length - 1];

							aux = parts[1].split("#");
							String part1 = aux[aux.length - 1];

							part1 = removeDiatrics(part1);
							part2 = removeDiatrics(part2);

							if (!questions.contains(part1)) {

								String auxString = part1;
								part1 = part2;
								part2 = auxString;

							}

							System.out.println("Seu animal " + part1.toLowerCase() + " " + part2.toLowerCase()
									+ "? (s/n/naosei/talvez)"); // reposta

							respostaUsuario = scanner.next();

							if ("s".equals(respostaUsuario)) {

								for (OWLIndividual individual : individualsAsserts.keySet()) {

									String asserts = individualsAsserts.get(individual);
									boolean containsProperty = asserts.contains(part1) && asserts.contains(part2);

									if (!containsProperty) {
										animaisCandidatos.remove(individual);
									}

								}
							}

							if (animaisCandidatos.size() == 1) {
								String animalEscolhido = animaisCandidatos.get(0).toString();
								animalEscolhido = animalEscolhido.split("#")[1];
								animalEscolhido = removeDiatrics(animalEscolhido);

								System.out.println("Fim de jogo! O animal que você escolheu é: " + animalEscolhido);
								break;
							}
						}

						if (animaisCandidatos.size() == 1) {
							break;
						}
					}

					if (animaisCandidatos.size() != 1) {
						System.out.println("Fim de jogo! Você venceu! Não descobri qual é o animal.");
					}
				}

			}

		} catch (OWLOntologyCreationException e) {
			e.printStackTrace();
			throw new Exception("Não foi possível carregar a ontologia");
		} finally {
			scanner.close();
		}

	}

	private static void buildQuestions() {
		questions.add("comportamento");
		questions.add("cobertoPor");
		questions.add("ehIRRACIONAL");
		questions.add("ehRacional");
		questions.add("maiorQue");
		questions.add("menorQue");
		questions.add("respiraçao");
		questions.add("seLocomove");
		questions.add("seReproduz");

	}

	private static void buildPossibleAnswers() {
		possibleAnswers.add("s");
		possibleAnswers.add("n");
		possibleAnswers.add("naosei");
		possibleAnswers.add("talvez");
	}

	private static String removeDiatrics(String value) {
		value = value.replace("#", "");
		value = value.replace("<", "");
		value = value.replace(">", "");
		value = value.replace("[", "");
		value = value.replace("]", "");

		return value;
	}
}
