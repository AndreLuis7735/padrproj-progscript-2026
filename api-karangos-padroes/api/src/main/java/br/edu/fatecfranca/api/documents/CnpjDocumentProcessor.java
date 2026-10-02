package br.edu.fatecfranca.api.documents;

public class CnpjDocumentProcessor extends DocumentProcessor {

    @Override
    protected DocumentValidator createValidator() {
        return new CnpjValidator();
    }
}
