package controller;

import java.util.List;

import javax.swing.JOptionPane;

import model.bo.ProdutoBO;
import model.seletor.ProdutoSeletor;
import model.vo.Produto;

public class ControllerProduto {

	private ProdutoBO produtoBO = new ProdutoBO();

	public String salvar(Produto produto) {
		String mensagem = "";
		String validacao = validarProduto(produto);

		if (validacao == "") {
			if (produtoBO.existeCodBar(Integer.parseInt(produto.getCodBarra()))) {
				JOptionPane.showConfirmDialog(null,
						"Produto ja cadastrado, deseja fazer alteração com os dados inseridos?", "",
						JOptionPane.OK_CANCEL_OPTION);
				// UPDATE
				if (produtoBO.atualizar(produto) == "") {
					mensagem = "Produto atualizado com sucesso";
				} else {
					mensagem = "ERRO ao atualizar produto";
				}
			} else {
				// INSERT
				if (produtoBO.inserir(produto) == "") {
					mensagem = "Produto cadastrado com sucesso";
				} else {
					mensagem = "ERRO ao cadastrar produto";
				}
			}
		}
		return mensagem;
	}

	private String validarProduto(Produto produto) {
		String validacao = "";

		if (produto == null) {
			validacao = "Produto esta vazio";
		} else {
			// validações de campo vazio e nulo
			if (produto.getCodBarra().trim().equals("")) {
				validacao = "Produto deve possuir um codigo";
			}
			if (produto.getNome().trim().equals("") || produto.getNome() == null) {
				validacao = "Nome do produto é obrigatorio";
			}
			if (produto.getCategoria() == "") {
				validacao = "Categoria deve ser selecionado";
			}
			if (produto.getPreco() <= 0.0) {
				validacao = "Preço do produto deve ser maior que zero";
			}

		}
		return validacao;
	}

	public List<Produto> listarProdutos(ProdutoSeletor seletor) {
		return produtoBO.listarProdutos(seletor);
	}

	public boolean existeProdutoCodBar(int produtoSelecionado) {
		return produtoBO.existeCodBar(produtoSelecionado);
	}

	public String excluir(int produtoSelecionado) {
		return produtoBO.excluir(produtoSelecionado);
	}

}
