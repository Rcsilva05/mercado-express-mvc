package br.com.fiap.mercadoexpressmvc.controller;

import br.com.fiap.mercadoexpressmvc.model.Produto;
import br.com.fiap.mercadoexpressmvc.service.ProdutoService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Controller MVC responsavel pela interface Web do Mercado Express.
 *
 * Rotas publicas (qualquer visitante): listar e visualizar detalhes.
 * Rotas privadas (exigem login, ver SecurityConfig): criar, editar e excluir.
 *
 *  GET  /produtos              -> lista todos os produtos           (publica)
 *  GET  /produtos/{id}         -> mostra detalhes de um produto      (publica)
 *  GET  /produtos/novo         -> formulario de cadastro             (privada)
 *  POST /produtos              -> salva um novo produto (CREATE)     (privada)
 *  GET  /produtos/{id}/editar  -> formulario de edicao               (privada)
 *  POST /produtos/{id}         -> atualiza o produto (UPDATE)        (privada)
 *  POST /produtos/{id}/excluir -> remove o produto (DELETE)          (privada)
 */
@Controller
@RequestMapping("/produtos")
@RequiredArgsConstructor
public class ProdutoController {

    private final ProdutoService produtoService;

    // READ - lista todos os produtos
    @GetMapping
    public String listar(Model model) {
        model.addAttribute("produtos", produtoService.listarTodos());
        return "produtos/lista";
    }

    // READ - detalhe de um produto
    @GetMapping("/{id}")
    public String detalhar(@PathVariable Long id, Model model) {
        model.addAttribute("produto", produtoService.buscarPorId(id));
        return "produtos/detalhe";
    }

    // CREATE - exibe formulario vazio
    @GetMapping("/novo")
    public String novoForm(Model model) {
        model.addAttribute("produto", new Produto());
        model.addAttribute("modoEdicao", false);
        return "produtos/form";
    }

    // CREATE - processa o cadastro
    @PostMapping
    public String criar(@Valid @ModelAttribute("produto") Produto produto,
                         BindingResult resultado,
                         Model model,
                         RedirectAttributes redirectAttributes) {
        if (resultado.hasErrors()) {
            model.addAttribute("modoEdicao", false);
            return "produtos/form";
        }
        produtoService.salvar(produto);
        redirectAttributes.addFlashAttribute("mensagemSucesso", "Produto \"" + produto.getNome() + "\" cadastrado com sucesso!");
        return "redirect:/produtos";
    }

    // UPDATE - exibe formulario preenchido
    @GetMapping("/{id}/editar")
    public String editarForm(@PathVariable Long id, Model model) {
        model.addAttribute("produto", produtoService.buscarPorId(id));
        model.addAttribute("modoEdicao", true);
        return "produtos/form";
    }

    // UPDATE - processa a atualizacao
    @PostMapping("/{id}")
    public String atualizar(@PathVariable Long id,
                             @Valid @ModelAttribute("produto") Produto produto,
                             BindingResult resultado,
                             Model model,
                             RedirectAttributes redirectAttributes) {
        if (resultado.hasErrors()) {
            model.addAttribute("modoEdicao", true);
            return "produtos/form";
        }
        produtoService.atualizar(id, produto);
        redirectAttributes.addFlashAttribute("mensagemSucesso", "Produto \"" + produto.getNome() + "\" atualizado com sucesso!");
        return "redirect:/produtos";
    }

    // DELETE
    @PostMapping("/{id}/excluir")
    public String excluir(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        Produto produto = produtoService.buscarPorId(id);
        produtoService.excluir(id);
        redirectAttributes.addFlashAttribute("mensagemSucesso", "Produto \"" + produto.getNome() + "\" excluido com sucesso!");
        return "redirect:/produtos";
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public String tratarNaoEncontrado(Model model) {
        return "produtos/nao-encontrado";
    }
}
