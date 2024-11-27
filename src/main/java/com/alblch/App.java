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
                    List<AsistenciaMedica> asistencias = new ArrayList<>();
                    Seguro seguro =
                            new Seguro(321, "12345678Z", "Juan", "Cháfer", "Bellver", 66, 3, LocalDate.now());
                    AsistenciaMedica asistenciaMedica5_1 =
                            new AsistenciaMedica(1, seguro, "Mislata", "Médico de cabecera");
                    AsistenciaMedica asistenciaMedica5_2 =
                            new AsistenciaMedica(2, seguro, "Sevilla", "Operación de bypass");

                    asistencias.add(asistenciaMedica5_1);
                    asistencias.add(asistenciaMedica5_2);
                    seguro.setAsistenciasMedicas(asistencias);

                    Session session5 = HibernateUtil.getSession();
                    session5.beginTransaction();
                    session5.persist(seguro);
                    session5.getTransaction().commit();
                    session5.close();
                    break;

                case 6:
                    List<AsistenciaMedica> asistencias1 = new ArrayList<>();
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

                    asistencias1.add(asistenciaMedica1);
                    asistencias1.add(asistenciaMedica2);
                    seguro6_1.setAsistenciasMedicas(asistencias1);
                    asistencias1.clear();
                    asistencias1.add(asistenciaMedica3);
                    asistencias1.add(asistenciaMedica4);
                    asistencias1.add(asistenciaMedica5);
                    seguro6_2.setAsistenciasMedicas(asistencias1);

                    Session session6 = HibernateUtil.getSession();
                    session6.beginTransaction();
                    session6.persist(seguro6_1);
                    session6.persist(seguro6_2);
                    System.out.println("Parte 1");
                    Query<Seguro> query1 = session6.createQuery("FROM Seguro", Seguro.class);
                    List<Seguro> listaClientes1 = query1.list();
                    for (Seguro listaCliente : listaClientes1) {
                        System.out.println(listaCliente.toString());
                    }
                    System.out.println("Parte 2");
                    Query<Seguro> query2 = session6.createQuery("FROM Seguro", Seguro.class);
                    List<Seguro> listaClientes2 = query2.list();
                    for (Seguro listaCliente : listaClientes2) {
                        System.out.println(listaCliente.toString());
                    }
                    session6.getTransaction().commit();
                    session6.close();


                    break;
                case 7:
                    salir=true;
                    break;
            }
        }
    }
}