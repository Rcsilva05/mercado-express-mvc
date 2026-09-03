package br.com.fiap.mercadoexpressmvc.service;

import br.com.fiap.mercadoexpressmvc.model.Produto;
import br.com.fiap.mercadoexpressmvc.repository.ProdutoRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Camada de servico com as regras de negocio do CRUD de produtos.
 * O Controller nao fala diretamente com o Repository: passa sempre
 * por aqui, o que deixa a aplicacao mais facil de manter e testar.
 */
@Service
@RequiredArgsConstructor
public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    public List<Produto> listarTodos() {
        return produtoRepository.findAll();
    }

    public Produto buscarPorId(Long id) {
        return produtoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Produto nao encontrado com id: " + id));
    }

    public Produto salvar(Produto produto) {
        return produtoRepository.save(produto);
    }

    public Produto atualizar(Long id, Produto dadosAtualizados) {
        Produto produtoExistente = buscarPorId(id);
        produtoExistente.setNome(dadosAtualizados.getNome());
        produtoExistente.setTipo(dadosAtualizados.getTipo());
        produtoExistente.setSetor(dadosAtualizados.getSetor());
        produtoExistente.setTamanho(dadosAtualizados.getTamanho());
        produtoExistente.setPreco(dadosAtualizados.getPreco());
        return produtoRepository.save(produtoExistente);
    }

    public void excluir(Long id) {
        Produto produto = buscarPorId(id);
        produtoRepository.delete(produto);
    }
}
