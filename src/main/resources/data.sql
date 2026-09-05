-- Tabela 11: Codigos de Tributacao
INSERT INTO tb_cod_trib (codigo, descricao) VALUES ('311011101', 'Operacoes de credito - Receita');
INSERT INTO tb_cod_trib (codigo, descricao) VALUES ('411011104', 'Perdas incorridas nas operacoes de credito - Despesa');

-- Balancete Map: Contas da empresa e seus saldos
INSERT INTO tb_balancete_map (conta, saldo, cod_trib_codigo) VALUES ('Conta Receita Juros 01', 500000.00, '311011101');
INSERT INTO tb_balancete_map (conta, saldo, cod_trib_codigo) VALUES ('Conta Receita Juros 02', 150000.00, '311011101');
INSERT INTO tb_balancete_map (conta, saldo, cod_trib_codigo) VALUES ('Conta Despesa PDD 01', 50000.00, '411011104');

-- Variavel Mapeamento: De/Para CodTrib para Variaveis SpEL
INSERT INTO tb_variavel_mapeamento (nome_variavel, operacao, cod_trib_codigo) VALUES ('vRecTrib', 'ADD', '311011101');
INSERT INTO tb_variavel_mapeamento (nome_variavel, operacao, cod_trib_codigo) VALUES ('vDespDedut', 'ADD', '411011104');

-- Fórmulas (Tabela 12) - CodBC 1005 (Simplificado)
INSERT INTO tb_formula (cod_bc, expressao_spel) VALUES ('1005', '(#vRecTrib - #vDespDedut) / (1 + ((#pIBSMun + #pIBSUF + #pCBS) / 100))');

-- Variáveis Globais (Alíquotas e Valores Fixos)
INSERT INTO tb_variavel_global (name, valor) VALUES ('pIBSMun', 2.00);
INSERT INTO tb_variavel_global (name, valor) VALUES ('pIBSUF', 1.00);
INSERT INTO tb_variavel_global (name, valor) VALUES ('pCBS', 9.00);