package dataFactory;

import pojo.ComponentePojo;
import pojo.ProdutoPojo;

import java.util.ArrayList;
import java.util.List;

public class ProdutoDataFactory {
    public static ProdutoPojo criarProdutoComumComOValorIgualA(double valor) {

        ProdutoPojo produto = new ProdutoPojo();
        produto.setProdutoNome("Play Station 5");
        produto.setProdutoValor(valor);
        List<String> cores = new ArrayList<>();
        cores.add("preto");
        cores.add("branco");

        produto.setProdutoCores(cores);
        produto.setProdutoUrlMock("");

        List<ComponentePojo> componentes = new ArrayList<>();
        ComponentePojo componente = new ComponentePojo();
        componente.setComponenteNome("controle");
        componente.setComponenteQuantidade(2);
        componentes.add(componente);

        ComponentePojo segundoComponentes = new ComponentePojo();
        segundoComponentes.setComponenteNome("Memory Card");
        segundoComponentes.setComponenteQuantidade(2);
        componentes.add(segundoComponentes);

        produto.setComponentes(componentes);

        return produto;


    }
}
