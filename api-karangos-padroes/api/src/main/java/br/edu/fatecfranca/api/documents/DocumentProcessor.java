package br.edu.fatecfranca.api.documents;

public abstract class DocumentProcessor {

    // Factory Method: decide qual validador concreto será usado.
    protected abstract DocumentValidator createValidator();

    // Algoritmo reaproveitado por todas as subclasses -- escrito uma única vez.
    public String validateAndFormat(String rawDocument) {
        DocumentValidator validator = createValidator();

        if (!validator.isValid(rawDocument)) {
            throw new IllegalArgumentException("Documento inválido: " + rawDocument);
        }

        return validator.format(rawDocument);
    }
}
