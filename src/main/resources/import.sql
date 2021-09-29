INSERT INTO pauta(descricao) VALUES ('Spring Foda');

INSERT INTO sessao(inicio, fim, pauta_id) VALUES (DATE_ADD(CURRENT_TIMESTAMP, interval 30 SECOND), DATE_ADD(CURRENT_TIMESTAMP, interval 60 SECOND), 1); 

INSERT INTO voto(cpf, sessao_id, voto) VALUES('50285724029', 1, false);
INSERT INTO voto(cpf, sessao_id, voto) VALUES('54793936008', 1, false);
INSERT INTO voto(cpf, sessao_id, voto) VALUES('80784053081', 1, true);
INSERT INTO voto(cpf, sessao_id, voto) VALUES('43936095060', 1, true);
INSERT INTO voto(cpf, sessao_id, voto) VALUES('25556548013', 1, true);