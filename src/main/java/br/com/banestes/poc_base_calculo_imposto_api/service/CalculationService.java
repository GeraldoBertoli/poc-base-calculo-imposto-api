package br.com.banestes.poc_base_calculo_imposto_api.service;

import br.com.banestes.poc_base_calculo_imposto_api.model.*;
import br.com.banestes.poc_base_calculo_imposto_api.repository.*;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.SimpleEvaluationContext;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CalculationService {

    private final FormulaRepository formulaRepository;
    private final VariableMappingRepository variableMappingRepository;
    private final AccountBalanceRepository accountBalanceRepository;
    private final CalculationAuditRepository auditRepository;

    public CalculationService(FormulaRepository formulaRepository, 
                              VariableMappingRepository variableMappingRepository, 
                              AccountBalanceRepository accountBalanceRepository, 
                              CalculationAuditRepository auditRepository) {
        this.formulaRepository = formulaRepository;
        this.variableMappingRepository = variableMappingRepository;
        this.accountBalanceRepository = accountBalanceRepository;
        this.auditRepository = auditRepository;
    }

    public CalculationAudit calcularBase(String codBc) {
        // 1. Buscar a Fórmula DML cadastrada
        Formula formula = formulaRepository.findByCodBc(codBc);
        if (formula == null) throw new RuntimeException("Fórmula não encontrada para o código: " + codBc);

        // 2. Agregar valores das variáveis baseadas no balancete
        Map<String, BigDecimal> variables = new HashMap<>();
        List<VariableMapping> mappings = variableMappingRepository.findAll();
        
        for (VariableMapping map : mappings) {
            List<AccountBalance> balances = accountBalanceRepository.findByCodTribCodigo(map.getCodTrib().getCodigo());
            BigDecimal sum = balances.stream()
                                     .map(AccountBalance::getSaldo)
                                     .reduce(BigDecimal.ZERO, BigDecimal::add);
            
            // Incrementar no mapa de variáveis dinamicamente
            variables.merge(map.getNomeVariavel(), sum, BigDecimal::add);
        }

        // 3. Adicionar as alíquotas (mockadas para a PoC)
        variables.put("pIBSMun", new BigDecimal("2.0"));
        variables.put("pIBSUF", new BigDecimal("1.0"));
        variables.put("pCBS", new BigDecimal("9.0"));

        // 4. Preparar o contexto de execução segura (SpEL) limitando RCE
        ExpressionParser parser = new SpelExpressionParser();
        EvaluationContext context = SimpleEvaluationContext.forReadWriteDataBinding().build();
        
        for (Map.Entry<String, BigDecimal> entry : variables.entrySet()) {
            context.setVariable(entry.getKey(), entry.getValue());
        }

        // 5. Avaliar a expressão matemática cadastrada no banco
        BigDecimal resultado = parser.parseExpression(formula.getExpressaoSpel())
                                     .getValue(context, BigDecimal.class);
                                     
        if(resultado != null) {
            resultado = resultado.setScale(2, RoundingMode.HALF_UP);
        }

        // 6. Criar rastro para Auditoria (Snapshot do Contexto)
        CalculationAudit audit = new CalculationAudit();
        audit.setCodBc(codBc);
        audit.setJsonVariaveisEntrada(variables.toString());
        audit.setValorResultado(resultado);
        audit.setDtProcessamento(LocalDateTime.now());
        
        return auditRepository.save(audit);
    }
    
    public List<CalculationAudit> getAuditorias() {
        return auditRepository.findAll();
    }
}