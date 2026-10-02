package br.edu.fatecfranca.api.audit;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Padrão Singleton.
 *
 * Garante uma única instância do registrador de auditoria em toda a
 * aplicação, com um ponto de acesso global (getInstance()). Qualquer
 * classe do sistema que precise registrar um evento usa a MESMA
 * instância, evitando históricos fragmentados.
 *
 * Observação: em Spring, um @Service já é gerenciado como singleton
 * pelo container. Esta classe foi implementada como Singleton clássico
 * (construtor privado + getInstance()) deliberadamente, para fins
 * didáticos deste exercício.
 */
public class AuditLogger {

    private static AuditLogger instance;

    private final List<String> events = new ArrayList<>();

    // Construtor privado: impede "new AuditLogger()" de fora da classe.
    private AuditLogger() {
    }

    public static synchronized AuditLogger getInstance() {
        if (instance == null) {
            instance = new AuditLogger();
        }
        return instance;
    }

    public void log(String event) {
        String entry = LocalDateTime.now() + " - " + event;
        events.add(entry);
        System.out.println("[AUDITORIA] " + entry);
    }

    public List<String> getEvents() {
        return Collections.unmodifiableList(events);
    }
}
