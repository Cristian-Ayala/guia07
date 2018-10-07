/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.uesocc.ingenieria.prn335_2018.flota.guia07.control;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;
import javax.persistence.EntityManager;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import org.mockito.runners.MockitoJUnitRunner;
import sv.edu.uesocc.ingenieria.prn335_2018.flota.datos.definicion.TipoUsuario;

/**
 *
 * @author cristian
 */
@RunWith(MockitoJUnitRunner.class)
public class UtilidadesTest {

    private List<TipoUsuario> allData;
    @Mock
    private EntityManager em;

    public UtilidadesTest() {
    }

    @Before
    public void iniciar() {

        allData = new ArrayList<>();
        allData.add(new TipoUsuario(1, "Cristian", "5677", true));
        allData.add(new TipoUsuario(2, "Espadero", "746784", true));
        allData.add(new TipoUsuario(3, "Navas", "73457", true));
        allData.add(new TipoUsuario(4, "Armando", "84558", true));
        allData.add(new TipoUsuario(5, "Jose", "84574", true));
        allData.add(new TipoUsuario(6, "Miranda", "234562", true));
        allData.add(new TipoUsuario(7, "Jaime", "23567", true));
        allData.add(new TipoUsuario(8, "Ayala", "09876", true));

        //Para el método de countAll()
        Query q = Mockito.mock(Query.class);
        Mockito.when(q.getSingleResult()).thenReturn(3L);
        Mockito.when(this.em.createQuery("SELECT COUNT(m) FROM TipoUsuario m")).thenReturn(q);
    }

    /**
     * Test of insert method, of class Utilidades.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testInsert() throws Exception {
        System.out.println("insert");
        TipoUsuario tipoUsuario = new TipoUsuario(1, "Mantenedor", "AC2354", true);
        Utilidades instance = new Utilidades();
        instance.em = this.em;
        instance.insert(tipoUsuario);
        Mockito.verify(em, Mockito.times(1)).persist(tipoUsuario);
    }

    /**
     * Test of update method, of class Utilidades.
     */
    @Test
    public void testUpdate() throws Exception {
        System.out.println("update");
        TipoUsuario tipoUsuario = new TipoUsuario(1, "Mantenedor", "AC2354", true);
        Utilidades instance = new Utilidades();
        instance.em = this.em;
        instance.update(tipoUsuario);
        Mockito.verify(em, Mockito.times(1)).merge(tipoUsuario);
    }

    /**
     * Test of delete method, of class Utilidades.
     */
    @Test
    public void testDelete() throws Exception {
        System.out.println("delete");
        TipoUsuario tipoUsuario = new TipoUsuario(1, "Mantenedor", "AC2354", true);
        Utilidades instance = new Utilidades();
        instance.em = this.em;
        instance.delete(tipoUsuario);
        Mockito.verify(em, Mockito.times(1)).remove(tipoUsuario);
    }

    /**
     * Test of selectAll method, of class Utilidades.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testSelectAll() throws Exception {
        System.out.println("selectAll");
        Utilidades instance = new Utilidades();
        instance.em = this.em;
        Query q = Mockito.mock(Query.class);
        Mockito.when(this.em.createQuery("SELECT m FROM TipoUsuario m")).thenReturn(q);
        Mockito.when(q.getResultList()).thenReturn(allData);
        List<TipoUsuario> expResult = allData;
        List<TipoUsuario> result = instance.selectAll();
        assertEquals(expResult, result);

    }

    /**
     * Test of countAll method, of class Utilidades.
     */
    @Test
    public void testCountAll() {
        System.out.println("countAll");
        Utilidades instance = new Utilidades();
        instance.em = this.em;
        int expResult = 3;
        int result = instance.countAll();
        assertEquals(expResult, result);
    }

    /**
     * Test of findById method, of class Utilidades.
     */
    @Test
    public void testFindById() {
        System.out.println("findById");
        int IdEntity = 1;
        Utilidades instance = new Utilidades();
        instance.em = this.em;
        //comenzar a setear valores a mi entidad
        TipoUsuario datosEsp = new TipoUsuario();
        datosEsp.setIdTipoUsuario(1);
        datosEsp.setNombre("Cristian Ayala");
        datosEsp.setAppId("12345");
        datosEsp.setActivo(true);
        datosEsp.setObservaciones("observaciones");
        //EntityManager em = mock(EntityManager.class);
        when(em.find(TipoUsuario.class, 1)).thenReturn(datosEsp);
        List<TipoUsuario> expResult = new ArrayList<>();
        expResult.add(new TipoUsuario(1, "Cristian Ayala", "12345", true));
        List<TipoUsuario> result = instance.findById(IdEntity);
        assertEquals(expResult, result);
    }

    /**
     * Test of findRange method, of class Utilidades.
     */
    @Test
    public void testFindRange() {
        System.out.println("findRange");

        List<TipoUsuario> expResult = new ArrayList<>();
        expResult.add(new TipoUsuario(2, "Espadero", "746784", true));
        expResult.add(new TipoUsuario(3, "Navas", "73457", true));
        expResult.add(new TipoUsuario(4, "Armando", "84558", true));
        expResult.add(new TipoUsuario(5, "Jose", "84574", true));

        Utilidades instance = new Utilidades();
        instance.em = this.em;
        Query q = Mockito.mock(Query.class);
        Mockito.when(this.em.createQuery("SELECT m FROM TipoUsuario m  ORDER BY m.idTipoUsuario")).thenReturn(q);
        Mockito.when(q.getResultList()).thenReturn(allData);
        int inicio = 2;
        int fin = 4;

        List result = instance.findRange(inicio, fin);
        assertEquals(expResult, result);
    }

    /**
     * Test of findByMultiple method, of class Utilidades.
     */
    @Test
    public void testFindByMultiple() {
        System.out.println("findByMultiple");
        String[] campos = {"idTipoUsuario", "appId", "activo", "nombre", "observaciones"};
        String[] busquedas = {"1", "AC16053", "true", "cristian ayala", "io no se xD"};
        Utilidades instance = new Utilidades();
        instance.em = this.em;
        Query q = Mockito.mock(Query.class);
        Mockito.when(this.em.createQuery("SELECT m FROM TipoUsuario m WHERE m.idTipoUsuario='1' AND m.appId='AC16053' AND m.activo='true' AND m.nombre='cristian ayala' AND m.observaciones='io no se xD'")).thenReturn(q);
        //Tiene que devolver sólo la entidad específica 
        List<TipoUsuario> expResult = new ArrayList<>();
        expResult.add(new TipoUsuario(1, "cristian ayala", "AC16053", true));
        expResult.get(0).setObservaciones("io no se xD");
        Mockito.when(q.getResultList()).thenReturn(expResult);
        List result = instance.findByMultiple(campos, busquedas);
        assertEquals(expResult, result);

    }

}
