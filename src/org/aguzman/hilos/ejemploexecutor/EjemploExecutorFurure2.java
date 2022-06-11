package org.aguzman.hilos.ejemploexecutor;

import java.util.concurrent.*;

public class EjemploExecutorFurure2 {
    public static void main(String[] args) throws InterruptedException, ExecutionException, TimeoutException {
        ThreadPoolExecutor executor= (ThreadPoolExecutor) Executors.newFixedThreadPool(3);

        System.out.println("Tamaño del pool: "+executor.getPoolSize());
        System.out.println("Cantidad de tareas en cola: "+executor.getQueue().size());


        Callable<String> tarea=()->{
            System.out.println("Inicio de tarea");
            try {
                System.out.println("Nombre del thread "+Thread.currentThread().getName());
                TimeUnit.SECONDS.sleep(3);
            }catch (InterruptedException e){
                Thread.currentThread().interrupt();
                e.printStackTrace();
            }
            System.out.println("Finaliza la tarea.....");
            return "Algun resultado importante de la tarea";
        };

        Callable<Integer>tarea2=()->{
            System.out.println("Iniciando tarea 2 .......");
            TimeUnit.SECONDS.sleep(3);
            return 10;
        };

        Future<String> resultados = executor.submit(tarea);
        Future<String>resultado2=executor.submit(tarea);
        Future<Integer>resultado3=executor.submit(tarea2);
        System.out.println("Tamaño del pool: "+executor.getPoolSize());
        System.out.println("Cantidad de tareas en cola: "+executor.getQueue().size());
        executor.shutdown();
        System.out.println("Continuando con la ejecucion del metodo main");

        //System.out.println(resultados.isDone());
        while (!(resultados.isDone() && resultado2.isDone() && resultado3.isDone())){
            System.out.println(String.format("resultado1: %s - resultado2: %s - resultado3: %s",
                    resultados.isDone()? "finalizo: ":"en proceso",
                    resultado2.isDone()? "finalizo: ":"en proceso",
                    resultado3.isDone()? "finalizo: ":"en proceso"));

            TimeUnit.MILLISECONDS.sleep(1500);
        }

        System.out.println("Obtenemos resultados de la tarea: "+resultados.get());
        System.out.println("Finaliza la tarea1: "+resultados.isDone());

        System.out.println("Obtenemos resultado 2 de la tarea: "+resultado2.get());
        System.out.println("Finaliza la tarea2: "+resultado2.isDone());

        System.out.println("Obtenemos resultado 3 de la tarea: "+resultado3.get());
        System.out.println("Finaliza la tarea3: "+resultado3.isDone());
    }
}
