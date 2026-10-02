package br.edu.fatecfranca.api.documents;

public class CpfDocumentProcessor extends DocumentProcessor {

    @Override
    protected DocumentValidator createValidator() {
        return new CpfValidator();
    }
}
