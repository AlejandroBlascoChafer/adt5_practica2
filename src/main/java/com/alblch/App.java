package com.alblch;

import org.hibernate.Session;
import org.hibernate.query.Query;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
    public static void menu(){
        System.out.println("==========================================================");
        System.out.println("========================== MENÚ ==========================");
        System.out.println("1.- Ejercicio1 - Insertar en la entidad Seguro.");
        System.out.println("2.- Ejercicio1 - Actualizar en la entidad Seguro.");
        System.out.println("3.- Ejercicio1 - Borrar en la entidad Seguro.");
        System.out.println("4.- Ejercicio1 - Leer de la entidad Seguro.");
        System.out.println("5.- Ejercicio2 - Creación entidad AsistenciaMedica.");
        System.out.println("6.- Ejercicio3 - Consultas a la base de datos.");
        System.out.println("7.- Salir.");
        System.out.println("==========================================================");
        System.out.println("==========================================================");
        System.out.print("Opción? ");
    }



    public static void menu2(){
        System.out.println("==========================================================");
        System.out.println("========================== MENÚ ==========================");
        System.out.println("1. Lanza una consulta que nos retorne todos los seguros que hay en la base de datos.");
        System.out.println("2. Lanza una consulta que nos retorne sólo las columnas NIF y Nombre de todos los seguros que hay en la base de datos.");
        System.out.println("3. Lanza una consulta que nos retorne sólo el NIF para el seguro con nombre “Juan Chafer Bellver”. " +
                "Usa el método uniqueResult() y 3 parámetros con nombre para el nombre y los apellidos.");
        System.out.println("4. Lanza una consulta que retorna el idAsistenciaMedica de todas las asistencias cuyo importe esté entre 2.000 y 5.000 euros " +
                "(utilizando parámetros por posición para los valores).");
        System.out.println("6. Lanza una consulta que calcule el saldo medio de todas las asistencias médicas.");
        System.out.println("7. Lanza una consulta que calcule cuántos seguros hay.");
        System.out.println("8. Lanza una consulta que muestre para cada seguro cuántas asistencias médicas posee.");
        System.out.println("9. Lanza una consulta sobre la tabla seguro pero usando una SQL Nativa de MySQL.");
        System.out.println("10. Salir. ");
        System.out.println("==========================================================");
        System.out.println("==========================================================");
        System.out.print("Opción? ");
    }
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        boolean salir = false;
        int opcion;
        while (!salir){
            menu();
            opcion = entrada.nextInt();

            switch (opcion){
                case 1:
                    Seguro s1 = new Seguro(1, "111111111A", "Paco", "Paco", "Paco", 50, 2, LocalDate.now());
                    Seguro s2 = new Seguro(2, "222222222B", "Pedro", "Pedro", "Pedro", 30, 3, LocalDate.now());


                    /* INSERTAR UN OBJETO*/
                    Session session1 = HibernateUtil.getSession();
                    session1.beginTransaction();
                    session1.persist(s1);
                    session1.persist(s2);
                    session1.getTransaction().commit();
                    session1.close();
                    break;
                case 2:
                    /* MODIFICAR UN OBJETO*/
                    Seguro seguroModificar = new Seguro(2, "222222222B", "Pedr", "Pedr", "Pedr", 30, 3, LocalDate.now());
                    Session session2 = HibernateUtil.getSession();
                    session2.beginTransaction();
                    session2.merge(seguroModificar);
                    session2.getTransaction().commit();
                    session2.close();

                    break;

                case 3:
                    /* ELIMINAR UN OBJETO*/
                    Seguro seguroEliminar = new Seguro(2, "222222222B", "Pedr", "Pedr", "Pedr", 30, 3, LocalDate.now());
                    Session session3 = HibernateUtil.getSession();
                    session3.beginTransaction();
                    session3.remove(seguroEliminar);
                    session3.getTransaction().commit();
                    session3.close();
                    break;


                case 4:
                    /* LISTAR OBJETOS*/
                    Session session4 = HibernateUtil.getSession();
                    session4.beginTransaction();
                    Query<Seguro> query = session4.createQuery("FROM Seguro", Seguro.class);
                    List<Seguro> listaClientes = query.list();
                    for (Seguro listaCliente : listaClientes) {
                        System.out.println(listaCliente.toString());
                    }
                    session4.getTransaction().commit();
                    session4.close();
                    break;

                case 5:

                    Seguro seguro =
                            new Seguro(321, "12345678Z", "Juan", "Cháfer", "Bellver", 66, 3, LocalDate.now());
                    AsistenciaMedica asistenciaMedica5_1 =
                            new AsistenciaMedica(1, seguro, "Mislata", "Médico de cabecera");
                    AsistenciaMedica asistenciaMedica5_2 =
                            new AsistenciaMedica(2, seguro, "Sevilla", "Operación de bypass");


                    Session session5 = HibernateUtil.getSession();
                    session5.beginTransaction();
                    session5.persist(asistenciaMedica5_1);
                    session5.persist(asistenciaMedica5_2);
                    session5.getTransaction().commit();
                    session5.close();
                    break;

                case 6:
                    boolean salir2 = false;
                    Seguro seguro6_1=
                            new Seguro(321, "12345678Z", "Juan", "Chafer", "Bellver", 66, 3, LocalDate.now());
                    AsistenciaMedica asistenciaMedica1 =
                            new AsistenciaMedica(1, seguro6_1, "Médico de cabecera", "Mislata", 4500);
                    AsistenciaMedica asistenciaMedica2 =
                            new AsistenciaMedica(2, seguro6_1, "Operación de bypass" , "Sevilla", 4500);
                    Seguro seguro6_2 =
                            new Seguro(654, "48573562T", "Amparo", "Martí", "López", 26, 0, LocalDate.now());
                    AsistenciaMedica asistenciaMedica3 =
                            new AsistenciaMedica(3, seguro6_2, "Médico de cabecera", "Valencia", 700);
                    AsistenciaMedica asistenciaMedica4 =
                            new AsistenciaMedica(4, seguro6_2, "Operación de miopía", "Valencia", 4500);
                    AsistenciaMedica asistenciaMedica5 =
                            new AsistenciaMedica(5, seguro6_2 , "Operación estética", "Madrid", 14500);

                    Session session6 = HibernateUtil.getSession();
                    session6.beginTransaction();
                    session6.persist(asistenciaMedica1);
                    session6.persist(asistenciaMedica2);
                    session6.persist(asistenciaMedica3);
                    session6.persist(asistenciaMedica4);
                    session6.persist(asistenciaMedica5);
                    session6.getTransaction().commit();
                    session6.close();
                    while (!salir2){
                        menu2();
                        opcion = entrada.nextInt();

                        switch (opcion){
                            case 1:

                                session6 = HibernateUtil.getSession();
                                session6.beginTransaction();
                                Query<Seguro> query1 = session6.createQuery("FROM Seguro", Seguro.class);
                                List<Seguro> listaClientes1 = query1.list();
                                for (Seguro listaCliente : listaClientes1) {
                                    System.out.println(listaCliente.toString());
                                }
                                session6.getTransaction().commit();
                                session6.close();


                                break;

                            case 2:
                                session6 = HibernateUtil.getSession();
                                session6.beginTransaction();
                                Query<Object[]> query2 = session6.createQuery("SELECT s.nif, s.nombre FROM Seguro s", Object[].class);
                                List<Object[]> listaClientes2 = query2.list();
                                for (Object[] cliente : listaClientes2) {
                                    System.out.println(cliente[0] + " - " + cliente[1]);
                                }
                                session6.getTransaction().commit();
                                session6.close();
                                break;

                            case 3:
                                session6 = HibernateUtil.getSession();
                                session6.beginTransaction();
                                Query<Seguro> query3 = session6.createQuery("FROM Seguro s WHERE s.nombre = :nombre AND s.ape1 = :ape1 AND s.ape2 = :ape2", Seguro.class);
                                query3.setParameter("nombre", "Juan");
                                query3.setParameter("ape1", "Chafer");
                                query3.setParameter("ape2", "Bellver");
                                Seguro seguro3 = query3.uniqueResult();
                                System.out.println(seguro3.getNombre() + " " + seguro3.getApe1() + " " + seguro3.getApe2());
                                session6.getTransaction().commit();
                                session6.close();



                                break;

                            case 4:
                                /*
                                Lanza una consulta que retorna el idAsistenciaMedica de todas las asistencias cuyo importe
                                esté entre 2.000 y 5.000 euros (utilizando parámetros por posición para los valores).
                                 */
                                session6 = HibernateUtil.getSession();
                                session6.beginTransaction();
                                Query<Object[]> query4 = session6.createQuery("SELECT am.idAsistenciaMedica FROM AsistenciaMedica am WHERE am.coste>=2000 AND am.coste <=5000", Object[].class);
                                List<Object[]> listaClientes4 = query4.list();
                                for (Object[] id : listaClientes4) {
                                    System.out.println(id[0]);
                                }
                                session6.getTransaction().commit();
                                session6.close();
                                break;

                            case 5:
                                session6 = HibernateUtil.getSession();
                                session6.beginTransaction();
                                Query<Long> query5 = session6.createQuery("SELECT sum(am.coste) FROM AsistenciaMedica am", Long.class);
                                Long sumaCoste = query5.uniqueResult();
                                System.out.println("Suma de los costes: " + sumaCoste);
                                session6.getTransaction().commit();
                                session6.close();
                                break;

                            case 6:
                                session6 = HibernateUtil.getSession();
                                session6.beginTransaction();
                                Query<Double> query6 = session6.createQuery("SELECT avg(am.coste) FROM AsistenciaMedica am", Double.class);
                                Double mediaCoste = query6.uniqueResult();
                                System.out.println("Media de los costes: " + mediaCoste);
                                session6.getTransaction().commit();
                                session6.close();
                                break;

                            case 7:
                                session6 = HibernateUtil.getSession();
                                session6.beginTransaction();
                                Query<Double> query7 = session6.createQuery("SELECT avg(am.coste) FROM AsistenciaMedica am", Double.class);
                                Double numSeguros = query7.uniqueResult();
                                System.out.println("Numero de seguros: " + numSeguros);
                                session6.getTransaction().commit();
                                session6.close();
                                break;

                            case 10:
                                salir2=true;



                                break;
                        }
                    }
                    break;
                case 7:
                    salir=true;
                    break;
            }
        }
    }
}