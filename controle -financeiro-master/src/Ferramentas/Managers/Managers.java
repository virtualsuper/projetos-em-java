/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ferramentas.Managers;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

/**
 *
 * @author ANDRE
 */
public class Managers {

    public static EntityManager GetManager() {
        EntityManager e = Persistence.createEntityManagerFactory("GerarBoletosPU").createEntityManager();
        return e;
    }

}
