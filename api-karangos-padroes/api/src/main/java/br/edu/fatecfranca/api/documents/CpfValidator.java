package br.edu.fatecfranca.api.documents;

public class CpfValidator implements DocumentValidator {

    @Override
    public boolean isValid(String document) {
        return document != null && document.replaceAll("\\D", "").length() == 11;
    }

    @Override
    public String format(String document) {
        String d = document.replaceAll("\\D", "");
        return d.replaceFirst("(\\d{3})(\\d{3})(\\d{3})(\\d{2})", "$1.$2.$3-$4");
    }
}
