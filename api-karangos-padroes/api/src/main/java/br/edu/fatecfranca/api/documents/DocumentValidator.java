package br.edu.fatecfranca.api.documents;

public interface DocumentValidator {
    boolean isValid(String document);
    String format(String document);
}
