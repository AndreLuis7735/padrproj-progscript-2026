package br.edu.fatecfranca.api.documents;

public class CnpjValidator implements DocumentValidator {

    @Override
    public boolean isValid(String document) {
        return document != null && document.replaceAll("\\D", "").length() == 14;
    }

    @Override
    public String format(String document) {
        String d = document.replaceAll("\\D", "");
        return d.replaceFirst("(\\d{2})(\\d{3})(\\d{3})(\\d{4})(\\d{2})", "$1.$2.$3/$4-$5");
    }
}
