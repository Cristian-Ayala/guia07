/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.uesocc.ingenieria.prn335_2018.flota.guia07.control;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;
import sv.edu.uesocc.ingenieria.prn335_2018.flota.datos.definicion.TipoUsuario;

/**
 *
 * @author cristian
 */
@Stateless
@LocalBean
public class Utilidades {

    //Crear el EM (Entity Manager)
    @PersistenceContext(unitName = "flota_unit", type = PersistenceContextType.EXTENDED)
    public EntityManager em;

    public void insert(TipoUsuario tipoUsuario) throws Exception {
        if (em != null) {
            em.persist(tipoUsuario);
        }
    }

    public void update(TipoUsuario tipoUsuario) throws Exception {
        if (em != null) {
            em.merge(tipoUsuario);
        }

    }

    public void delete(TipoUsuario tipoUsuario) throws Exception {
        if (em != null) {
            em.remove(tipoUsuario);
        }
    }

//Este método devolverá la lista de todos lo elementos de la entidad
    public List<TipoUsuario> selectAll() throws Exception {
        if (em != null) {
            Query query = em.createQuery("SELECT m FROM TipoUsuario m");
            return query.getResultList();
        }
        return null;
    }

    //Este método devolverá la cantidad de los elementos existentes de la entidad
    public int countAll() {
        if (em != null) {
            Query query = em.createQuery("SELECT COUNT(m) FROM TipoUsuario m");
            return ((Long) query.getSingleResult()).intValue();
        }
        return 0;

    }

    //Este método deberá devolver una lista de todos los campos de la entidad asignada, donde
    //donde se encuentre el “ID” de la entidad pasada como parámetro.
    public List<TipoUsuario> findById(int IdEntity) {
        if (em != null) {
            //Instanciamos una entidad única y la obtenemos mediante el método find de nuesto em el cual requiere un ID como parámetro y la clase de la cual la iremos a traer
            TipoUsuario tipoUsuario = em.find(TipoUsuario.class , IdEntity);
            //Iniciamos un arraylist adonde insertaremos la entidad encontrada
            ArrayList<TipoUsuario> List = new ArrayList<>();
            //Le pasamos la entidad a nuestro arraylist     
            List.add(tipoUsuario);
            return List;
        }
        return null;

    }

    //Método que devuelve una lista con los registros de la entidad asignada comprendidos en el
    //rango que se le indique.
    //int inicio ---> El primer registro que tomara en cuenta.
    //int totalRegistros ---> El máximo de registros a mostrar
    public List findRange(int inicio, int fin) {
        if (em != null) {
            ArrayList<TipoUsuario> ListConTodosLosValores = new ArrayList<>();
            ArrayList<TipoUsuario> ListCorrecta = new ArrayList<>();

            Query query = em.createQuery("SELECT m FROM TipoUsuario m  ORDER BY m.idTipoUsuario");
            ListConTodosLosValores = (ArrayList<TipoUsuario>) query.getResultList();
            //Verificamos si la lista retornada tiene el ID 
            for (int i = 0; i < ListConTodosLosValores.size(); i++) {
                //Si lo tiene entonces creará una lista con los valores que inicien desde el, hasta un máximo definido por "fin"
                if (ListConTodosLosValores.get(i).getIdTipoUsuario().equals(inicio)) {
                    //Comenzamos a recorrer el arrayList y le comenzamos a enviar los parametros
                    for (int j = i; j < ListConTodosLosValores.size(); j++) {
                        //Verificamos que la lista sea del tamaño deseado
                        if (ListCorrecta.size() < fin) {
                            ListCorrecta.add(ListConTodosLosValores.get(j));
                        }
                    }
                }
            }
            return ListCorrecta;
        }else{
        return null;
        }
    }

    ///Este método devuelve una lista de los campos que cumplen con los criterios de búsqueda.
    //Recibirá dos arrays de tipo String, uno con los campos a buscar y el otro con los criterios de
    //búsqueda. La búsqueda debe hacerse de manera que todos los campos cumplan con los
    //criterios de búsqueda. Ejemplo:
    //String[] campos {“idMarca”,”nombre”,”activo”,”descripcion”}; ---> los campos de la entidad
    //String[] busquedas {“3”,”Toyota”,”true”,”Es un fabricante”}; -----> los valores que busqueda
    public List findByMultiple(String[] campos, String[] busquedas) {
        if (em != null) {
            ArrayList<TipoUsuario> List = new ArrayList<>();
            String jpql = "SELECT m FROM TipoUsuario m WHERE ";
            for (int i = 0; i < campos.length; i++) {
                if (i < (campos.length - 1)) {
                    jpql = jpql + "m." + campos[i] + "=" + "'" + busquedas[i] + "'" + " AND ";
                } else {
                    jpql = jpql + "m." + campos[i] + "=" + "'" + busquedas[i] + "'";
                }
            }
           List =(ArrayList<TipoUsuario>) em.createQuery(jpql).getResultList();
            return List;
        }else{
        return null;
        }
    }
}
