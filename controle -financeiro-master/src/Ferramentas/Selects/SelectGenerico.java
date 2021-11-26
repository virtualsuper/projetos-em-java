/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ferramentas.Selects;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 *
 * @author ANDRE
 * @param <T>
 */
public class SelectGenerico<T> {

    private final EntityManager entity;

    public SelectGenerico(EntityManager em) {
        this.entity = em;
    }
    /**
     * seleciona todos os registros da Tabela
     * @param Classe Classe de entidade
     * @param ordernar Campo de Ordenação 
     * @param asc Tipo de Ordenação
     * @return retorna Lista de Dados
     */
    public List<T> All(Class Classe, String ordernar, Boolean asc) {
        return SelectBase(new Object[0][0], new Object[0][0], Classe, ordernar, asc);
    }

    /**
     *
     * @param objRelacional
     * @param objSimp
     * @param Classe Classe de entidade
     * @return retorna Lista de Dados
     */
    public List<T> ComSemFk(Object[][] objRelacional, Object[][] objSimp, Class Classe) {
        return SelectBase(objRelacional, objSimp, Classe, null, null);
    }

    /**
     * Seleciona registros da Tabela com e sem Relacionamentos, sem Ordenação, 
     * Ex: new Object[][]{{"campoFk","campoOutraTB",valor},{"campoFk","campoOutraTB",valor}}
     *
     * @param objRelacional
     * @param objSimp new Object[][]{{"campo",valor},{"campo",valor}}
     * @param Classe Classe de entidade
     * @param ordernar Campo de Ordenação 
     * @param asc Tipo de Ordenação
     * @return retorna Lista de Dados
     */
    public List<T> ComSemFk(Object[][] objRelacional, Object[][] objSimp, Class Classe, String ordernar, Boolean asc) {
        return SelectBase(objRelacional, objSimp, Classe, ordernar, asc);
    }

    /**
     * Seleciona registros da Tabela somente com os Relacionamentos, sem Ordenação, Ex: new Object[][]{{"campoFk","campoOutraTB",valor},{"campoFk","campoOutraTB",valor}}
     * @param objRel
     * @param Classe Classe de entidade
     * @return retorna Lista de Dados
     */
    public List<T> ComFk(Object[][] objRel, Class Classe) {
        return SelectBase(objRel, new Object[0][0], Classe, null, null);
    }

    /**
     * Seleciona registros da Tabela somente com os Relacionamentos, com Ordenação,Ex: new Object[][]{{"campoFk","campoOutraTB",valor},{"campoFk","campoOutraTB",valor}}
     * @param objRel
     * @param Classe Classe de entidade
     * @param ordernar Campo de Ordenação 
     * @param asc Tipo de Ordenação
     * @return retorna Lista de Dados
     */
    public List<T> ComFk(Object[][] objRel, Class Classe, String ordernar, Boolean asc) {
        return SelectBase(objRel, new Object[0][0], Classe, ordernar, asc);
    }

    /**
     * Seleciona registros da Tabela Sem usar Relacionamento, sem Ordenação
     * @param objRel Lista de Objetos com os Campos e Valores. Ex: new Object[][]{{"campo",valor},{"campo",valor}}
     * @param Classe Classe de entidade
     * @return retorna Lista de Dados
     */
    public List<T> SemFk(Object[][] objRel, Class Classe) {
        return SelectBase(new Object[0][0], objRel, Classe, null, null);
    }

    /**
     * Seleciona registros da Tabela Sem usar Relacionamento, com Ordenação
     * @param objRel Lista de Objetos com os Campos e Valores. Ex: new Object[][]{{"campo",valor},{"campo",valor}}
     * @param Classe Classe de entidade
     * @param ordernar Campo de Ordenação 
     * @param asc Tipo de Ordenação
     * @return retorna Lista de Dados
     */
    public List<T> SemFk(Object[][] objRel, Class Classe, String ordernar, Boolean asc) {
        return SelectBase(new Object[0][0], objRel, Classe, ordernar, asc);
    }

    private List<T> SelectBase(Object[][] objRel, Object[][] obj, Class C, String o, Boolean asc) {
        try {
            CriteriaBuilder builder = entity.getCriteriaBuilder();
            CriteriaQuery query = builder.createQuery(C);
            Root from = query.from(C);
            Predicate predicate = builder.and();
            for (Object[] ob : objRel) {
                predicate = builder.and(predicate, builder.equal(from.get(ob[0].toString()).get(ob[1].toString()), ob[2]));
            }
            for (Object[] ob : obj) {
                predicate = builder.and(predicate, builder.equal(from.get(ob[0].toString()), ob[1]));
            }
            TypedQuery typedQuery;
            if (o != null) {
                if (asc) {
                    typedQuery = entity.createQuery(query.select(from).where(predicate).orderBy(builder.asc(from.get(o))));
                } else {
                    typedQuery = entity.createQuery(query.select(from).where(predicate).orderBy(builder.desc(from.get(o))));
                }
            } else {
                typedQuery = entity.createQuery(query.select(from).where(predicate));
            }
            List<T> results = typedQuery.getResultList();
            return results;
        } finally {

        }

    }

}
