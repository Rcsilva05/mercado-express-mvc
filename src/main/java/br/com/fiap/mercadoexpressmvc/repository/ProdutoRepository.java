package br.com.fiap.mercadoexpressmvc.repository;

import br.com.fiap.mercadoexpressmvc.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositorio JPA para acesso a tabela TDS_MVC_TB_MERCADO.
 * O Spring Data JPA gera automaticamente as operacoes de
 * Create, Read, Update e Delete (CRUD).
 */
@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}
