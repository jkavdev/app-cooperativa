INSERT INTO pauta(descricao) VALUES ('Spring Foda');

/*INSERT INTO sessao(inicio, fim, pauta_id) VALUES (DATEADD('MINUTE',1, CURRENT_TIMESTAMP), DATEADD('MINUTE',2, CURRENT_TIMESTAMP), 1);*/
INSERT INTO sessao(inicio, fim, pauta_id) VALUES (DATEADD('SECOND',1, CURRENT_TIMESTAMP), DATEADD('SECOND',2, CURRENT_TIMESTAMP), 1); 

INSERT INTO voto(cpf, sessao_id, voto) VALUES('50285724029', 1, false);
INSERT INTO voto(cpf, sessao_id, voto) VALUES('54793936008', 1, false);
INSERT INTO voto(cpf, sessao_id, voto) VALUES('80784053081', 1, true);
INSERT INTO voto(cpf, sessao_id, voto) VALUES('43936095060', 1, true);
INSERT INTO voto(cpf, sessao_id, voto) VALUES('25556548013', 1, true);