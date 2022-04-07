package br.com.fatecmogidascruzes.avaliacao.DAO;

public class FabricaDAO {

    public static ProdutoDAO getProdutoDAO() {

        return new ProdutoDAOPostgreSQL();
    }

}
