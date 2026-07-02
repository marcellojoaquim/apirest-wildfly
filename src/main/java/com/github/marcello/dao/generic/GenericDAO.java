package com.github.marcello.dao.generic;

import com.github.marcello.domain.Persistente;
import com.github.marcello.exception.DAOException;

import javax.enterprise.context.Dependent;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

@Dependent
public class GenericDAO<T extends Persistente, E extends Serializable> {

    private final Class<T> persistenteClass;

    @PersistenceContext(unitName = "prod")
    private EntityManager entityManager;

    public GenericDAO(Class<T> persistenteClass) {
        this.persistenteClass = persistenteClass;
    }

    public T cadastrar(T entity) throws DAOException {
        entityManager.persist(entity);
        return entity;
    }

    public Collection<T> buscarTodos() {
        List<T> list =
                entityManager
                        .createQuery(getSelectSql(), this.persistenteClass).getResultList();
        return list;
    }

    public T consultar(E valor) throws DAOException {
        T entity = entityManager.find(this.persistenteClass, valor);
        return entity;
    }

    public T consultarPorCodigo(String codigo) throws DAOException {
        //String jpql = "SELECT obj FROM " +this.persistenteClass.getSimpleName()+ " obj WHERE obj.codigo =:codigo";
        String jpql = getByCodigoProduto(codigo);
        return entityManager
                .createQuery(jpql, this.persistenteClass)
                .setParameter("codigo", codigo)
                .getSingleResult();
    }

    public T atualizar(T entity) throws DAOException {
        entity = entityManager.merge(entity);
        return entity;
    }

    private String getSelectSql() {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT obj FROM ");
        sb.append(this.persistenteClass.getSimpleName());
        sb.append(" obj");
        return sb.toString();
    }

    private String getByCodigoProduto(String codigo) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT obj FROM ");
        sb.append(this.persistenteClass.getSimpleName());
        sb.append("obj WHERE obj.codigo = :codigo");
        return sb.toString();
    }
}
