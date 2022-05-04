package com.topicos.forum.DAO;

import java.util.List;

import com.topicos.forum.dominio.DomainEntity;

public interface IDAO {
    public void salvar(DomainEntity entidade);
    public void alterar(DomainEntity entidade);
    public void excluir(DomainEntity entidade);
    public List<DomainEntity> consultar(DomainEntity entidade);
}
