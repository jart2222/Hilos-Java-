package org.aguzman.hilos.ejemploexecutor;

import org.aguzman.hilos.ejemplosync.Panaderia;
import org.aguzman.hilos.ejemplosync.runnable.Consumidor;
import org.aguzman.hilos.ejemplosync.runnable.Panadero;

import java.util.concurrent.*;

public class EjemploExecutorPeoductorConsumidor {
    public static void main(String[] args) throws InterruptedException, ExecutionException, TimeoutException {
        ThreadPoolExecutor executor= (ThreadPoolExecutor) Executors.newFixedThreadPool(3);
        System.out.println("Tamaño del pool: "+executor.getPoolSize());
        System.out.println("Cantidad de tareas en cola: "+executor.getQueue().size());

        Panaderia p= new Panaderia();
        Runnable productor= new Panadero(p);
        Runnable consumidor=new Consumidor(p);

        Future<?> resultado1 = executor.submit(productor);
        Future<?> resultado2= executor.submit(consumidor);

        System.out.println("Tamaño del pool: "+executor.getPoolSize());
        System.out.println("Cantidad de tareas en cola: "+executor.getQueue().size());
        executor.shutdown();
        System.out.println("Continuando con la ejecucion del metodo main");
    }
}
